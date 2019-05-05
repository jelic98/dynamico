package org.ecloga.dynamico.style;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import org.json.JSONObject;

final class LinearLayoutStyler extends ViewStyler implements OnDrawableLoadedListener {

    private static final int LOAD_DIVIDER_DRAWABLE = 1;

    private LinearLayout linearLayout;

    LinearLayoutStyler(ViewFactory factory, Context context) {
        super(factory, context);
    }

    @Override
    public View style(View view, JSONObject attributes) throws Exception {
        super.style(view, attributes);

        linearLayout = (LinearLayout) view;

        if(attributes.has("orientation")) {
            String orientation = attributes.getString("orientation");

            if(orientation.equalsIgnoreCase("vertical")) {
                linearLayout.setOrientation(LinearLayout.VERTICAL);
            }else if(orientation.equalsIgnoreCase("horizontal")) {
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            }
        }

        if(attributes.has("weightSum")) {
            linearLayout.setWeightSum((float) attributes.getDouble("weightSum"));
        }

        if(attributes.has("baselineAligned")) {
            linearLayout.setBaselineAligned(attributes.getBoolean("baselineAligned"));
        }

        if(attributes.has("measureWithLargestChildEnabled")) {
            linearLayout.setMeasureWithLargestChildEnabled(attributes.getBoolean("measureWithLargestChildEnabled"));
        }

        if(attributes.has("baselineAlignedChildIndex")) {
            linearLayout.setBaselineAlignedChildIndex(attributes.getInt("baselineAlignedChildIndex"));
        }

        if(attributes.has("dividerPadding")) {
            linearLayout.setDividerPadding(Display.unitToPx(attributes.getString("dividerPadding"), context));
        }

        if(attributes.has("gravity")) {
            String gravity = attributes.getString("gravity");

            if(gravity.equalsIgnoreCase("start")) {
                linearLayout.setGravity(Gravity.START);
            }else if(gravity.equalsIgnoreCase("top")) {
                linearLayout.setGravity(Gravity.TOP);
            }else if(gravity.equalsIgnoreCase("end")) {
                linearLayout.setGravity(Gravity.END);
            }else if(gravity.equalsIgnoreCase("bottom")) {
                linearLayout.setGravity(Gravity.BOTTOM);
            }else if(gravity.equalsIgnoreCase("center")) {
                linearLayout.setGravity(Gravity.CENTER);
            }else if(gravity.equalsIgnoreCase("center_horizontal")) {
                linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
            }else if(gravity.equalsIgnoreCase("center_vertical")) {
                linearLayout.setGravity(Gravity.CENTER_VERTICAL);
            }
        }

        if(attributes.has("horizontalGravity")) {
            String gravity = attributes.getString("horizontalGravity");

            if(gravity.equalsIgnoreCase("start")) {
                linearLayout.setHorizontalGravity(Gravity.START);
            }else if(gravity.equalsIgnoreCase("top")) {
                linearLayout.setHorizontalGravity(Gravity.TOP);
            }else if(gravity.equalsIgnoreCase("end")) {
                linearLayout.setHorizontalGravity(Gravity.END);
            }else if(gravity.equalsIgnoreCase("bottom")) {
                linearLayout.setHorizontalGravity(Gravity.BOTTOM);
            }else if(gravity.equalsIgnoreCase("center")) {
                linearLayout.setHorizontalGravity(Gravity.CENTER);
            }else if(gravity.equalsIgnoreCase("center_horizontal")) {
                linearLayout.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
            }else if(gravity.equalsIgnoreCase("center_vertical")) {
                linearLayout.setHorizontalGravity(Gravity.CENTER_VERTICAL);
            }
        }

        if(attributes.has("verticalGravity")) {
            String gravity = attributes.getString("verticalGravity");

            if(gravity.equalsIgnoreCase("start")) {
                linearLayout.setVerticalGravity(Gravity.START);
            }else if(gravity.equalsIgnoreCase("top")) {
                linearLayout.setVerticalGravity(Gravity.TOP);
            }else if(gravity.equalsIgnoreCase("end")) {
                linearLayout.setVerticalGravity(Gravity.END);
            }else if(gravity.equalsIgnoreCase("bottom")) {
                linearLayout.setVerticalGravity(Gravity.BOTTOM);
            }else if(gravity.equalsIgnoreCase("center")) {
                linearLayout.setVerticalGravity(Gravity.CENTER);
            }else if(gravity.equalsIgnoreCase("center_horizontal")) {
                linearLayout.setVerticalGravity(Gravity.CENTER_HORIZONTAL);
            }else if(gravity.equalsIgnoreCase("center_vertical")) {
                linearLayout.setVerticalGravity(Gravity.CENTER_VERTICAL);
            }
        }

        if(attributes.has("dividerDrawable")) {
            new DrawableLoader(attributes, this, context)
                    .load(attributes.getString("dividerDrawable"), LOAD_DIVIDER_DRAWABLE);
        }

        if(attributes.has("showDividers")) {
            String dividers = attributes.getString("showDividers");

            if(dividers.equalsIgnoreCase("beginning")) {
                linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_BEGINNING);
            }else if(dividers.equalsIgnoreCase("middle")) {
                linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            }else if(dividers.equalsIgnoreCase("end")) {
                linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_END);
            }else if(dividers.equalsIgnoreCase("none")) {
                linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
            }
        }

        return linearLayout;
    }

    @Override
    public void onDrawableLoaded(Drawable drawable, int requestCode) {
        if(requestCode == LOAD_DIVIDER_DRAWABLE) {
            linearLayout.setDividerDrawable(drawable);
        }
    }
}