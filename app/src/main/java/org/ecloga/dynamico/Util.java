package org.ecloga.dynamico;

import android.graphics.Color;
import android.util.Log;
import android.content.Context;
import android.net.ConnectivityManager;

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

    public static boolean isValidURL(String url) {
        return url.contains("https")
                || url.contains("http")
                || url.contains("fpt")
                || url.contains("file");
    }

    public static boolean isValidColor(String url) {
        try {
            Color.parseColor(url);
            return true;
        }catch(IllegalArgumentException e) {
            return false;
        }
    }
}