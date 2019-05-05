package org.ecloga.dynamico.style;

import android.content.Context;
import android.view.View;
import android.widget.GridLayout;
import org.json.JSONObject;

final class GridLayoutStyler extends ViewStyler {

    GridLayoutStyler(ViewFactory factory, Context context) {
        super(factory, context);
    }

    @Override
    public View style(View view, JSONObject attributes) throws Exception {
        super.style(view, attributes);

        GridLayout gridLayout = (GridLayout) view;

        if(attributes.has("alignmentMode")) {
            String mode = attributes.getString("alignmentMode");

            if(mode.equalsIgnoreCase("align_bounds")) {
                gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
            }else if(mode.equalsIgnoreCase("align_margins")) {
                gridLayout.setAlignmentMode(GridLayout.ALIGN_MARGINS);
            }
        }

        if(attributes.has("orientation")) {
            String orientation = attributes.getString("orientation");

            if(orientation.equalsIgnoreCase("vertical")) {
                gridLayout.setOrientation(GridLayout.VERTICAL);
            }else if(orientation.equalsIgnoreCase("horizontal")) {
                gridLayout.setOrientation(GridLayout.HORIZONTAL);
            }
        }

        if(attributes.has("columnCount")) {
            gridLayout.setColumnCount(attributes.getInt("columnCount"));
        }

        if(attributes.has("rowCount")) {
            gridLayout.setRowCount(attributes.getInt("rowCount"));
        }

        if(attributes.has("columnOrderPreserved")) {
            gridLayout.setColumnOrderPreserved(attributes.getBoolean("columnOrderPreserved"));
        }

        if(attributes.has("rowOrderPreserved")) {
            gridLayout.setRowOrderPreserved(attributes.getBoolean("rowOrderPreserved"));
        }

        if(attributes.has("useDefaultMargins")) {
            gridLayout.setUseDefaultMargins(attributes.getBoolean("useDefaultMargins"));
        }

        return gridLayout;
    }
}