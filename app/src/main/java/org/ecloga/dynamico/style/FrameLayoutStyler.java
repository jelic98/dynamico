package org.ecloga.dynamico.style;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import org.json.JSONObject;

class FrameLayoutStyler extends ViewStyler {

    FrameLayoutStyler(ViewFactory factory, Context context) {
        super(factory, context);
    }

    @Override
    public View style(View view, JSONObject attributes) throws Exception {
        super.style(view, attributes);

        FrameLayout frameLayout = (FrameLayout) view;

        if(attributes.has("measureAllChildren")) {
            frameLayout.setMeasureAllChildren(attributes.getBoolean("measureAllChildren"));
        }

        if(attributes.has("foregroundGravity")) {
            String gravity = attributes.getString("foregroundGravity");

            if(gravity.equalsIgnoreCase("start")) {
                frameLayout.setForegroundGravity(Gravity.START);
            }else if(gravity.equalsIgnoreCase("top")) {
                frameLayout.setForegroundGravity(Gravity.TOP);
            }else if(gravity.equalsIgnoreCase("end")) {
                frameLayout.setForegroundGravity(Gravity.END);
            }else if(gravity.equalsIgnoreCase("bottom")) {
                frameLayout.setForegroundGravity(Gravity.BOTTOM);
            }else if(gravity.equalsIgnoreCase("center")) {
                frameLayout.setForegroundGravity(Gravity.CENTER);
            }else if(gravity.equalsIgnoreCase("center_horizontal")) {
                frameLayout.setForegroundGravity(Gravity.CENTER_HORIZONTAL);
            }else if(gravity.equalsIgnoreCase("center_vertical")) {
                frameLayout.setForegroundGravity(Gravity.CENTER_VERTICAL);
            }
        }

        return frameLayout;
    }
}