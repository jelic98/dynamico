package org.ecloga.dynamico.style;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import org.json.JSONObject;

final class RelativeLayoutStyler extends ViewStyler {

    RelativeLayoutStyler(ViewFactory factory, Context context) {
        super(factory, context);
    }

    @Override
    public View style(View view, JSONObject attributes) throws Exception {
        super.style(view, attributes);

        RelativeLayout relativeLayout = (RelativeLayout) view;

        if(attributes.has("gravity")) {
            String gravity = attributes.getString("gravity");

            if(gravity.equalsIgnoreCase("start")) {
                relativeLayout.setGravity(Gravity.START);
            }else if(gravity.equalsIgnoreCase("top")) {
                relativeLayout.setGravity(Gravity.TOP);
            }else if(gravity.equalsIgnoreCase("end")) {
                relativeLayout.setGravity(Gravity.END);
            }else if(gravity.equalsIgnoreCase("bottom")) {
                relativeLayout.setGravity(Gravity.BOTTOM);
            }else if(gravity.equalsIgnoreCase("center")) {
                relativeLayout.setGravity(Gravity.CENTER);
            }else if(gravity.equalsIgnoreCase("center_horizontal")) {
                relativeLayout.setGravity(Gravity.CENTER_HORIZONTAL);
            }else if(gravity.equalsIgnoreCase("center_vertical")) {
                relativeLayout.setGravity(Gravity.CENTER_VERTICAL);
            }
        }

        if(attributes.has("horizontalGravity")) {
            String gravity = attributes.getString("horizontalGravity");

            if(gravity.equalsIgnoreCase("start")) {
                relativeLayout.setHorizontalGravity(Gravity.START);
            }else if(gravity.equalsIgnoreCase("top")) {
                relativeLayout.setHorizontalGravity(Gravity.TOP);
            }else if(gravity.equalsIgnoreCase("end")) {
                relativeLayout.setHorizontalGravity(Gravity.END);
            }else if(gravity.equalsIgnoreCase("bottom")) {
                relativeLayout.setHorizontalGravity(Gravity.BOTTOM);
            }else if(gravity.equalsIgnoreCase("center")) {
                relativeLayout.setHorizontalGravity(Gravity.CENTER);
            }else if(gravity.equalsIgnoreCase("center_horizontal")) {
                relativeLayout.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
            }else if(gravity.equalsIgnoreCase("center_vertical")) {
                relativeLayout.setHorizontalGravity(Gravity.CENTER_VERTICAL);
            }
        }

        if(attributes.has("verticalGravity")) {
            String gravity = attributes.getString("verticalGravity");

            if(gravity.equalsIgnoreCase("start")) {
                relativeLayout.setVerticalGravity(Gravity.START);
            }else if(gravity.equalsIgnoreCase("top")) {
                relativeLayout.setVerticalGravity(Gravity.TOP);
            }else if(gravity.equalsIgnoreCase("end")) {
                relativeLayout.setVerticalGravity(Gravity.END);
            }else if(gravity.equalsIgnoreCase("bottom")) {
                relativeLayout.setVerticalGravity(Gravity.BOTTOM);
            }else if(gravity.equalsIgnoreCase("center")) {
                relativeLayout.setVerticalGravity(Gravity.CENTER);
            }else if(gravity.equalsIgnoreCase("center_horizontal")) {
                relativeLayout.setVerticalGravity(Gravity.CENTER_HORIZONTAL);
            }else if(gravity.equalsIgnoreCase("center_vertical")) {
                relativeLayout.setVerticalGravity(Gravity.CENTER_VERTICAL);
            }
        }

        return relativeLayout;
    }
}