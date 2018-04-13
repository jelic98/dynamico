package org.ecloga.dynamico.style;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import org.ecloga.dynamico.Util;
import org.json.JSONObject;

public class DefaultStyler implements Styler {

    protected Context context;

    public DefaultStyler(Context context) {
        this.context = context;
    }

    @Override
    public View style(View view, JSONObject attributes) throws Exception {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        if(attributes.has("layout_width")) {
            String width = attributes.getString("layout_width");

            if(width.equalsIgnoreCase("match_parent")) {
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            }else if(width.equalsIgnoreCase("wrap_content")) {
                params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            }else {
                params.height = Util.dpToInt(width, context);
            }
        }

        if(attributes.has("layout_height")) {
            String width = attributes.getString("layout_height");

            if(width.equalsIgnoreCase("match_parent")) {
                params.height = ViewGroup.LayoutParams.MATCH_PARENT;
            }else if(width.equalsIgnoreCase("wrap_content")) {
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            }else {
                params.height = Util.dpToInt(width, context);
            }
        }

        if(attributes.has("layout_margin")) {
            int margin = Util.dpToInt(attributes.getString("layout_margin"), context);

            params.setMargins(margin, margin, margin, margin);
        }else {
            int start = 0;
            int top = 0;
            int end = 0;
            int bottom = 0;

            if(attributes.has("layout_marginStart")) {
                start = Util.dpToInt(attributes.getString("layout_marginStart"), context);
            }

            if(attributes.has("layout_marginTop")) {
                top = Util.dpToInt(attributes.getString("layout_marginTop"), context);
            }

            if(attributes.has("layout_marginEnd")) {
                end = Util.dpToInt(attributes.getString("layout_marginEnd"), context);
            }

            if(attributes.has("layout_marginBottom")) {
                bottom = Util.dpToInt(attributes.getString("layout_marginBottom"), context);
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
            int padding = Util.dpToInt(attributes.getString("padding"), context);

            view.setPadding(padding, padding, padding, padding);
        }else {
            int start = 0;
            int top = 0;
            int end = 0;
            int bottom = 0;

            if(attributes.has("paddingStart")) {
                start = Util.dpToInt(attributes.getString("paddingStart"), context);
            }

            if(attributes.has("paddingTop")) {
                top = Util.dpToInt(attributes.getString("paddingTop"), context);
            }

            if(attributes.has("paddingEnd")) {
                end = Util.dpToInt(attributes.getString("paddingEnd"), context);
            }

            if(attributes.has("paddingBottom")) {
                bottom = Util.dpToInt(attributes.getString("paddingBottom"), context);
            }

            view.setPadding(start, top, end, bottom);
        }

        if(attributes.has("visibility")) {
            String visibility = attributes.getString("visibility");

            if(visibility.equalsIgnoreCase("visible")) {
                view.setVisibility(View.VISIBLE);
            }else if(visibility.equalsIgnoreCase("invisible")) {
                view.setVisibility(View.INVISIBLE);
            }else if(visibility.equalsIgnoreCase("gone")) {
                view.setVisibility(View.GONE);
            }
        }

        if(attributes.has("alpha")) {
            view.setAlpha((float) attributes.getDouble("alpha"));
        }

        if(attributes.has("rotation")) {
            view.setRotation((float) attributes.getDouble("rotation"));
        }

        if(attributes.has("rotationX")) {
            view.setRotationX((float) attributes.getDouble("rotationX"));
        }

        if(attributes.has("rotationY")) {
            view.setRotationY((float) attributes.getDouble("rotationY"));
        }

        if(attributes.has("translationX")) {
            view.setTranslationX((float) attributes.getDouble("translationX"));
        }

        if(attributes.has("translationY")) {
            view.setTranslationY((float) attributes.getDouble("translationY"));
        }

        if(attributes.has("scaleX")) {
            view.setScaleX((float) attributes.getDouble("scaleX"));
        }

        if(attributes.has("scaleY")) {
            view.setScaleY((float) attributes.getDouble("scaleY"));
        }

        if(attributes.has("pivotX")) {
            view.setPivotX((float) attributes.getDouble("pivotX"));
        }

        if(attributes.has("pivotY")) {
            view.setPivotY((float) attributes.getDouble("pivotY"));
        }

        if(attributes.has("x")) {
            view.setX((float) attributes.getDouble("x"));
        }

        if(attributes.has("y")) {
            view.setY((float) attributes.getDouble("y"));
        }

        if(attributes.has("clickable")) {
            view.setClickable(attributes.getBoolean("clickable"));
        }

        if(attributes.has("backgroundColor")) {
            view.setBackgroundColor(Color.parseColor(attributes.getString("backgroundColor")));
        }

        return view;
    }
}
