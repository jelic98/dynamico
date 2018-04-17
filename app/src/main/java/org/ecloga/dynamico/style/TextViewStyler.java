package org.ecloga.dynamico.style;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import org.ecloga.dynamico.Util;
import org.json.JSONObject;

class TextViewStyler extends DefaultStyler {

    TextViewStyler(ViewFactory factory, Context context) {
        super(factory, context);
    }

    @Override
    public View style(View view, JSONObject attributes) throws Exception {
        super.style(view, attributes);

        TextView textView = (TextView) view;

        if(attributes.has("text")) {
            textView.setText(attributes.getString("text"));
        }

        if(attributes.has("textSize")) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, Display.unitToPx(attributes.getString("textSize"), context));
        }

        if(attributes.has("textColor")) {
            try {
                textView.setTextColor(Color.parseColor(attributes.getString("textColor")));
            }catch(IllegalArgumentException e) {
                Util.log("Style error", e.getMessage());
            }
        }

        if(attributes.has("gravity")) {
            String gravity = attributes.getString("gravity");

            if(gravity.equalsIgnoreCase("start")) {
                textView.setGravity(Gravity.START);
            }else if(gravity.equalsIgnoreCase("top")) {
                textView.setGravity(Gravity.TOP);
            }else if(gravity.equalsIgnoreCase("end")) {
                textView.setGravity(Gravity.END);
            }else if(gravity.equalsIgnoreCase("bottom")) {
                textView.setGravity(Gravity.BOTTOM);
            }else if(gravity.equalsIgnoreCase("center")) {
                textView.setGravity(Gravity.CENTER);
            }else if(gravity.equalsIgnoreCase("center_horizontal")) {
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
            }else if(gravity.equalsIgnoreCase("center_vertical")) {
                textView.setGravity(Gravity.CENTER_VERTICAL);
            }
        }

        return textView;
    }
}