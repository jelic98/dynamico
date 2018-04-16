package org.ecloga.dynamico.style;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.CompoundButton;
import org.ecloga.dynamico.DrawableLoader;
import org.ecloga.dynamico.Util;
import org.ecloga.dynamico.ViewFactory;
import org.ecloga.dynamico.OnDrawableLoadedListener;
import org.json.JSONArray;
import org.json.JSONObject;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class CompoundButtonStyler extends TextViewStyler implements OnDrawableLoadedListener {

    private static final int LOAD_BUTTON_DRAWABLE = 1;

    private CompoundButton compoundButton;

    public CompoundButtonStyler(ViewFactory factory, Context context) {
        super(factory, context);
    }

    @Override
    public View style(View view, JSONObject attributes) throws Exception {
        super.style(view, attributes);

        compoundButton = (CompoundButton) view;

        if(attributes.has("checked")) {
            compoundButton.setChecked(attributes.getBoolean("checked"));
        }

        if(attributes.has("buttonDrawable")) {
            String drawable = attributes.getString("buttonDrawable");

            boolean cache = false;

            if(attributes.has("cache")) {
                cache = attributes.getBoolean("cache");
            }

            new DrawableLoader(cache, this, context).load(drawable, LOAD_BUTTON_DRAWABLE);
        }

        if(attributes.has("onCheck")) {
            JSONObject check = attributes.getJSONObject("onCheck");

            final ArrayList<Class> types = new ArrayList<>();
            final ArrayList<Object> args = new ArrayList<>();

            JSONArray parameters = check.getJSONArray("parameters");

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

            types.add(0, Boolean.class);
            args.add(0, false);

            final Class listener = Class.forName(check.getString("class"));
            final Method method = listener.getMethod(check.getString("method"), types.toArray(new Class[types.size()]));

            compoundButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    args.set(0, isChecked);

                    try {
                        method.invoke(listener, args.toArray());
                    }catch(Exception e) {
                        Util.log("onCheck error", e.getMessage());
                    }
                }
            });
        }

        return compoundButton;
    }

    @Override
    public void onDrawableLoaded(Drawable drawable, int requestCode) {
        if(requestCode == LOAD_BUTTON_DRAWABLE) {
            compoundButton.setButtonDrawable(drawable);
        }
    }
}