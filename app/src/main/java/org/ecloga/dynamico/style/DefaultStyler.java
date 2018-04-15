package org.ecloga.dynamico.style;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import org.ecloga.dynamico.Display;
import org.ecloga.dynamico.Util;
import org.ecloga.dynamico.ViewFactory;
import org.ecloga.dynamico.network.ApiResponse;
import org.ecloga.dynamico.network.ImageDownload;
import org.json.JSONArray;
import org.json.JSONObject;
import java.lang.reflect.Field;

public class DefaultStyler implements Styler {

    private ViewFactory factory;
    protected Context context;

    public DefaultStyler(ViewFactory factory, Context context) {
        this.factory = factory;
        this.context = context;
    }

    @Override
    public View style(final View view, JSONObject attributes) throws Exception {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();

        if(params == null) {
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        if(attributes.has("layout_width")) {
            String width = attributes.getString("layout_width");

            if(width.equalsIgnoreCase("match_parent")) {
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            }else if(width.equalsIgnoreCase("wrap_content")) {
                params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            }else {
                params.height = Display.unitToPx(width, context);
            }
        }

        if(attributes.has("layout_height")) {
            String width = attributes.getString("layout_height");

            if(width.equalsIgnoreCase("match_parent")) {
                params.height = ViewGroup.LayoutParams.MATCH_PARENT;
            }else if(width.equalsIgnoreCase("wrap_content")) {
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            }else {
                params.height = Display.unitToPx(width, context);
            }
        }

        if(attributes.has("layout_margin")) {
            int margin = Display.unitToPx(attributes.getString("layout_margin"), context);

            params.setMargins(margin, margin, margin, margin);
        }else {
            boolean hasMargin = false;

            int start = 0;
            int top = 0;
            int end = 0;
            int bottom = 0;

            if(attributes.has("layout_marginStart")) {
                start = Display.unitToPx(attributes.getString("layout_marginStart"), context);
                hasMargin = true;
            }

            if(attributes.has("layout_marginTop")) {
                top = Display.unitToPx(attributes.getString("layout_marginTop"), context);
                hasMargin = true;
            }

            if(attributes.has("layout_marginEnd")) {
                end = Display.unitToPx(attributes.getString("layout_marginEnd"), context);
                hasMargin = true;
            }

            if(attributes.has("layout_marginBottom")) {
                bottom = Display.unitToPx(attributes.getString("layout_marginBottom"), context);
                hasMargin = true;
            }

            if(hasMargin) {
                params.setMargins(start, top, end, bottom);
            }
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
            int padding = Display.unitToPx(attributes.getString("padding"), context);

            view.setPadding(padding, padding, padding, padding);
        }else {
            boolean hasPadding = false;

            int start = 0;
            int top = 0;
            int end = 0;
            int bottom = 0;

            if(attributes.has("paddingStart")) {
                start = Display.unitToPx(attributes.getString("paddingStart"), context);
                hasPadding = true;
            }

            if(attributes.has("paddingTop")) {
                top = Display.unitToPx(attributes.getString("paddingTop"), context);
                hasPadding = true;
            }

            if(attributes.has("paddingEnd")) {
                end = Display.unitToPx(attributes.getString("paddingEnd"), context);
                hasPadding = true;
            }

            if(attributes.has("paddingBottom")) {
                bottom = Display.unitToPx(attributes.getString("paddingBottom"), context);
                hasPadding = true;
            }

            if(hasPadding) {
                view.setPadding(start, top, end, bottom);
            }
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

        if(attributes.has("background")) {
            String background = attributes.getString("background");

            if(Util.isValidURL(background)) {
                final ImageDownload request = new ImageDownload(background, context);
                request.addHandler(new ApiResponse() {
                    @Override
                    public void onSuccess(String response) {
                        view.setBackground(request.getDrawable());
                    }

                    @Override
                    public void onError(String message) {
                        Util.log("Image error", "Setting image as a background produced the following error: " + message);
                    }
                });
                request.start();
            }else if(Util.isValidColor(background)) {
                try {
                    view.setBackgroundColor(Color.parseColor(background));
                }catch(IllegalArgumentException e) {
                    Util.log("Style error", e.getMessage());
                }
            }
        }

        if(attributes.has("conditions")) {
            View conditionView = view;

            JSONArray conditions = attributes.getJSONArray("conditions");

            for(int i = 0; i < conditions.length(); i++) {
                JSONObject condition = conditions.getJSONObject(i);

                Class config = Class.forName(condition.getString("class"));
                String value = config.getDeclaredField(condition.getString("field")).get(config).toString();

                if(value.equalsIgnoreCase(condition.getString("value"))) {
                    conditionView = factory.styleView(view, condition.getJSONObject("attributes"));
                }
            }

            return conditionView;
        }

        return view;
    }
}