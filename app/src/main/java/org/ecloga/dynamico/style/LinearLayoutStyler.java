package org.ecloga.dynamico.style;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import org.json.JSONException;
import org.json.JSONObject;

public class LinearLayoutStyler extends DefaultStyler {

    public LinearLayoutStyler(Context context) {
        super(context);
    }

    @Override
    public View style(View view, JSONObject attributes) throws Exception {
        super.style(view, attributes);

        LinearLayout linearLayout = (LinearLayout) view;

        if(attributes.has("orientation")) {
            String orientation = attributes.getString("orientation");

            if(orientation.equalsIgnoreCase("vertical")) {
                linearLayout.setOrientation(LinearLayout.VERTICAL);
            }else if(orientation.equalsIgnoreCase("horizontal")) {
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            }
        }

        return linearLayout;
    }
}