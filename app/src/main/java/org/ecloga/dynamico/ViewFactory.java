package org.ecloga.dynamico;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.json.JSONObject;
import java.lang.reflect.Constructor;

public class ViewFactory {

    private Context context;

    public ViewFactory(Context context) {
        this.context = context;
    }

    public View getView(JSONObject object) throws Exception {
        return styleView(createView(object.getString("class")), object.getJSONObject("attributes"));
    }

    private View createView(String className) throws Exception {
        Util.log("Dynamico", "Creating view " + className);

        Class elementClass = Class.forName(className);

        Constructor constructor = elementClass.getConstructor(Context.class);

        return (View) constructor.newInstance(context);
    }

    private View styleView(View view, JSONObject attributes) throws Exception {
        Util.log("Dynamico", "Styling view " + view.getClass().toString());

        view = styleDefault(view, attributes);

        if(view instanceof TextView) {
            view = styleTextView(view, attributes);
        }

        return view;
    }

    private View styleDefault(View view, JSONObject attributes) throws Exception {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        if(attributes.has("layout_width")) {
            String width = attributes.getString("layout_width");

            if(width.equalsIgnoreCase("match_parent")) {
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            }else if(width.equalsIgnoreCase("wrap_content")) {
                params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            }else {
                params.height = Util.dpStringToInt(width, context);
            }
        }

        if(attributes.has("layout_height")) {
            String width = attributes.getString("layout_height");

            if(width.equalsIgnoreCase("match_parent")) {
                params.height = ViewGroup.LayoutParams.MATCH_PARENT;
            }else if(width.equalsIgnoreCase("wrap_content")) {
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            }else {
                params.height = Util.dpStringToInt(width, context);
            }
        }

        if(attributes.has("layout_margin")) {
            int margin = Util.dpStringToInt(attributes.getString("layout_margin"), context);

            params.setMargins(margin, margin, margin, margin);
        }else {
            int start = 0;
            int top = 0;
            int end = 0;
            int bottom = 0;

            if(attributes.has("layout_marginStart")) {
                start = Util.dpStringToInt(attributes.getString("layout_marginStart"), context);
            }

            if(attributes.has("layout_marginTop")) {
                top = Util.dpStringToInt(attributes.getString("layout_marginTop"), context);
            }

            if(attributes.has("layout_marginEnd")) {
                end = Util.dpStringToInt(attributes.getString("layout_marginEnd"), context);
            }

            if(attributes.has("layout_marginBottom")) {
                bottom = Util.dpStringToInt(attributes.getString("layout_marginBottom"), context);
            }

            params.setMargins(start, top, end, bottom);
        }

        if(attributes.has("layout_gravity")) {
            String gravity = attributes.getString("layout_gravity");

            if(gravity.equalsIgnoreCase("start")) {
                params.gravity = Gravity.START;
            }else if(gravity.equalsIgnoreCase("top")) {
                params.gravity = Gravity.TOP;
            }else if(gravity.equalsIgnoreCase("end")) {
                params.gravity = Gravity.END;
            }else if(gravity.equalsIgnoreCase("bottom")) {
                params.gravity = Gravity.BOTTOM;
            }else if(gravity.equalsIgnoreCase("center")) {
                params.gravity = Gravity.CENTER;
            }else if(gravity.equalsIgnoreCase("center_horizontal")) {
                params.gravity = Gravity.CENTER_HORIZONTAL;
            }else if(gravity.equalsIgnoreCase("center_vertical")) {
                params.gravity = Gravity.CENTER_VERTICAL;
            }
        }

        view.setLayoutParams(params);

        if(attributes.has("padding")) {
            int padding = Util.dpStringToInt(attributes.getString("padding"), context);

            view.setPadding(padding, padding, padding, padding);
        }else {
            int start = 0;
            int top = 0;
            int end = 0;
            int bottom = 0;

            if(attributes.has("paddingStart")) {
                start = Util.dpStringToInt(attributes.getString("paddingStart"), context);
            }

            if(attributes.has("paddingTop")) {
                top = Util.dpStringToInt(attributes.getString("paddingTop"), context);
            }

            if(attributes.has("paddingEnd")) {
                end = Util.dpStringToInt(attributes.getString("paddingEnd"), context);
            }

            if(attributes.has("paddingBottom")) {
                bottom = Util.dpStringToInt(attributes.getString("paddingBottom"), context);
            }

            view.setPadding(start, top, end, bottom);
        }

        view.setBackgroundColor(Color.parseColor(attributes.getString("backgroundColor")));

        return view;
    }

    private View styleTextView(View view, JSONObject attributes) throws Exception {
        TextView textView = (TextView) view;

        if(attributes.has("text")) {
            textView.setText(attributes.getString("text"));
        }

        if(attributes.has("textColor")) {
            textView.setTextColor(Color.parseColor(attributes.getString("textColor")));
        }

        return textView;
    }
}