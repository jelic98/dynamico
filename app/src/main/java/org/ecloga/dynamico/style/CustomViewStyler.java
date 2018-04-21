package org.ecloga.dynamico.style;

import android.content.Context;
import android.view.View;
import org.json.JSONArray;
import org.json.JSONObject;

final class CustomViewStyler extends ViewStyler {

    CustomViewStyler(ViewFactory factory, Context context) {
        super(factory, context);
    }

    @Override
    public View style(View view, JSONObject attributes) throws Exception {
        super.style(view, attributes);

        if(attributes.has("custom")) {
            JSONArray custom = attributes.getJSONArray("custom");

            for(int i = 0; i < custom.length(); i++) {
                JSONObject method = custom.getJSONObject(i);

                new MethodInvoker.Builder(view, method, context)
                        .build()
                        .invoke();
            }
        }

        return view;
    }
}