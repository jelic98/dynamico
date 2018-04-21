package org.ecloga.dynamico.style;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.GridView;
import org.json.JSONObject;
import static android.widget.GridView.*;

final class GridViewStyler extends ViewStyler {

    GridViewStyler(ViewFactory factory, Context context) {
        super(factory, context);
    }

    @Override
    public View style(View view, JSONObject attributes) throws Exception {
        super.style(view, attributes);

        GridView gridView = (GridView) view;

        if(attributes.has("numColumns")) {
            gridView.setNumColumns(attributes.getInt("numColumns"));
        }

        if(attributes.has("selection")) {
            gridView.setSelection(attributes.getInt("selection"));
        }

        if(attributes.has("horizontalSpacing")) {
            gridView.setHorizontalSpacing(Display.unitToPx(attributes.getString("horizontalSpacing"), context));
        }

        if(attributes.has("verticalSpacing")) {
            gridView.setVerticalSpacing(Display.unitToPx(attributes.getString("verticalSpacing"), context));
        }

        if(attributes.has("columnWidth")) {
            gridView.setColumnWidth(Display.unitToPx(attributes.getString("columnWidth"), context));
        }

        if(attributes.has("stretchMode")) {
            String mode = attributes.getString("stretchMode");

            if(mode.equalsIgnoreCase("no_stretch")) {
                gridView.setStretchMode(NO_STRETCH);
            }else if(mode.equalsIgnoreCase("stretch_spacing")) {
                gridView.setStretchMode(STRETCH_SPACING);
            }else if(mode.equalsIgnoreCase("stretch_column_width")) {
                gridView.setStretchMode(STRETCH_COLUMN_WIDTH);
            }else if(mode.equalsIgnoreCase("stretch_spacing_uniform")) {
                gridView.setStretchMode(STRETCH_SPACING_UNIFORM);
            }
        }

        if(attributes.has("gravity")) {
            String gravity = attributes.getString("gravity");

            if(gravity.equalsIgnoreCase("start")) {
                gridView.setGravity(Gravity.START);
            }else if(gravity.equalsIgnoreCase("top")) {
                gridView.setGravity(Gravity.TOP);
            }else if(gravity.equalsIgnoreCase("end")) {
                gridView.setGravity(Gravity.END);
            }else if(gravity.equalsIgnoreCase("bottom")) {
                gridView.setGravity(Gravity.BOTTOM);
            }else if(gravity.equalsIgnoreCase("center")) {
                gridView.setGravity(Gravity.CENTER);
            }else if(gravity.equalsIgnoreCase("center_horizontal")) {
                gridView.setGravity(Gravity.CENTER_HORIZONTAL);
            }else if(gravity.equalsIgnoreCase("center_vertical")) {
                gridView.setGravity(Gravity.CENTER_VERTICAL);
            }
        }

        return gridView;
    }
}