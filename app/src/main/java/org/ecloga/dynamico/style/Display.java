package org.ecloga.dynamico.style;

import android.content.Context;
import org.ecloga.dynamico.Util;

class Display {

    private static int dpToPx(int dp, Context context) {
        return (int) (context.getResources().getDisplayMetrics().density * dp);
    }

    private static int spToPx(int sp, Context context) {
        return (int) (context.getResources().getDisplayMetrics().scaledDensity * sp);
    }

    public static int unitToPx(String dimension, Context context) {
        String unit = dimension.substring(dimension.length() - 2);

        int value;

        try {
            value = Integer.parseInt(dimension.replace(unit, ""));
        }catch(NumberFormatException e) {
            Util.log("Unit error", "Caused by dimension " + dimension + "\nDetails: " + e.getMessage());
            return 0;
        }

        if(unit.equalsIgnoreCase("dp")) {
            return dpToPx(value, context);
        }else if(unit.equalsIgnoreCase("sp")) {
            return spToPx(value, context);
        }else {
            return value;
        }
    }
}