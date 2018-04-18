package org.ecloga.dynamico.style;

import android.content.Context;
import org.ecloga.dynamico.Util;
import org.json.JSONArray;
import org.json.JSONObject;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class MethodInvoker {

    private static final String TAG = "Dynamico.MethodInvoker";

    private JSONObject attributes;
    private ArrayList<Class> types;
    private ArrayList<Object> args;
    private Method method;
    private Class listenerClass;
    private Object listenerObject;

    private MethodInvoker(JSONObject attributes, Context context) throws Exception {
        this.attributes = attributes;

        types = new ArrayList<>();
        args = new ArrayList<>();

        if(attributes.has("parameters")) {
            JSONArray parameters = attributes.getJSONArray("parameters");

            for(int i = 0; i < parameters.length(); i++) {
                JSONObject p = parameters.getJSONObject(i);

                Class type = Class.forName(p.getString("type"));

                types.add(type);

                if(type == Context.class) {
                    args.add(context);
                    continue;
                }

                args.add(p.get("value"));
            }
        }

        if(attributes.has("class")) {
            listenerClass = Class.forName(attributes.getString("class"));
            listenerObject = listenerClass;
        }
    }

    private void initialize() throws Exception {
        method = listenerClass.getMethod(attributes.getString("method"), types.toArray(new Class[types.size()]));
    }

    private void setRequiredTypes(Class ... varargs) {
        List<Class> types = Arrays.asList(varargs);

        for(int i = types.size() - 1; i >= 0; i--) {
            this.types.add(0, types.get(i));
            this.args.add(0, null);
        }
    }

    private void setRequiredArgs(Object ... varargs) {
        List<Object> args = Arrays.asList(varargs);

        for(int i = 0; i < args.size(); i++) {
            this.args.set(i, args.get(i));
        }
    }

    void invoke(Object ... varargs) {
        setRequiredArgs(varargs);

        try {
            method.invoke(listenerObject, args.toArray());
        }catch(Exception e) {
            Util.log(TAG, e.getMessage());
        }
    }

    static class Builder {

        private MethodInvoker invoker;

        /**
         * Constructor used for invoking static methods
         * @param attributes holds class name, method name and array of parameters
         * @param context some methods invoked with reflection might be context dependent
         * @throws Exception if MethodInvoker's construction produces Exception
         */
        Builder(JSONObject attributes, Context context) throws Exception {
            invoker = new MethodInvoker(attributes, context);
        }

        /**
         * Constructor used for invoking instance methods
         * @param custom instance of a class which methods are going to be invoked
         * @param attributes holds method name and array of parameters
         * @param context some methods invoked with reflection might be context dependent
         * @throws Exception if MethodInvoker's construction produces Exception
         */
        Builder(Object custom, JSONObject attributes, Context context) throws Exception {
            invoker = new MethodInvoker(attributes, context);

            invoker.listenerObject = custom;
            invoker.listenerClass = custom.getClass();
        }

        /**
         * Adds required parameter types that are known at compile time
         * @param types parameter types
         * @return Builder object ready for initializing MethodInvoker
         */
        Builder setRequiredTypes(Class ... types) {
            invoker.setRequiredTypes(types);

            return this;
        }

        /**
         * Initializes MethodInvoker and returns it
         * @return MethodInvoker object ready for invoking
         */
        MethodInvoker build() throws Exception {
            invoker.initialize();

            return invoker;
        }
    }
}