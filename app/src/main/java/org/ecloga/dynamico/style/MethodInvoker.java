package org.ecloga.dynamico.style;

import android.content.Context;
import org.ecloga.dynamico.Util;
import org.json.JSONArray;
import org.json.JSONObject;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MethodInvoker {

    private String name;
    private JSONObject attributes;
    private ArrayList<Class> types;
    private ArrayList<Object> args;
    private Class listener;
    private Method method;

    private MethodInvoker(String name, JSONObject attributes, Context context) throws Exception {
        this.name = name;
        this.attributes = attributes.getJSONObject(name);

        types = new ArrayList<>();
        args = new ArrayList<>();

        // class:Class - name:String - object:Object

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

        listener = Class.forName(attributes.getString("class"));
    }

    private void initialize() throws Exception {
        method = listener.getMethod(attributes.getString("method"), types.toArray(new Class[types.size()]));
    }

    private void setAdditionalTypes(Class ... varargs) {
        List<Class> types = Arrays.asList(varargs);

        for(int i = types.size() - 1; i >= 0; i--) {
            this.types.add(0, types.get(i));
            this.args.add(0, null);
        }
    }

    private void setAdditionalArgs(Object ... varargs) {
        List<Object> args = Arrays.asList(varargs);

        for(int i = 0; i < args.size(); i++) {
            this.args.set(i, args.get(i));
        }
    }

    public void invoke(Object ... varargs) {
        setAdditionalArgs(varargs);

        try {
            method.invoke(listener, this.args.toArray());
        }catch(Exception e) {
            Util.log(name + " error", e.getMessage());
        }
    }

    public static class Builder {

        private MethodInvoker invoker;

        public Builder(String name, JSONObject attributes, Context context) throws Exception {
            invoker = new MethodInvoker(name, attributes, context);
        }

        public Builder setAdditionalTypes(Class ... types) throws Exception {
            invoker.setAdditionalTypes(types);

            return this;
        }

        public MethodInvoker build() throws Exception {
            invoker.initialize();

            return invoker;
        }
    }
}