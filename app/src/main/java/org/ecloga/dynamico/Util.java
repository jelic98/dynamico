package org.ecloga.dynamico;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.content.Context;
import android.net.ConnectivityManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import java.net.URI;
import java.net.URISyntaxException;

public class Util {

    public static void log(String tag, String msg) {
        Log.d((tag != null && !tag.isEmpty()) ? tag : "EMPTY TAG",
                (msg != null && !msg.isEmpty()) ? msg : "EMPTY MESSAGE");
    }

    public static boolean hasNetworkAccess(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    public static void blockTouches(Context context) {
        ((Activity) context).getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public static void allowTouches(Context context) {
        ((Activity) context).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private static int dpToPx(int dp, Context context) {
        return (int) (context.getResources().getDisplayMetrics().density * dp);
    }

    private static int spToPx(int sp, Context context) {
        return (int) (context.getResources().getDisplayMetrics().scaledDensity * sp);
    }

    public static int unitToPx(String dimension, Context context) {
        String unit = dimension.substring(0, dimension.length() - 2);

        int value = Integer.parseInt(unit);

        if(unit.equalsIgnoreCase("dp")) {
            return Util.dpToPx(value, context);
        }else if(unit.equalsIgnoreCase("sp")) {
            return Util.spToPx(value, context);
        }else {
            return value;
        }
    }

    public static boolean isValidURL(String url) {
        try {
            new URI(url).parseServerAuthority();
            return true;
        }catch(URISyntaxException e) {
            return false;
        }
    }
}