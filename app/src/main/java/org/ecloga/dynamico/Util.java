package org.ecloga.dynamico;

import android.graphics.Color;
import android.util.Log;
import android.content.Context;
import android.net.ConnectivityManager;

import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Util {

    public static void log(String tag, String msg) {
        Log.d((tag != null && !tag.isEmpty()) ? tag : "EMPTY TAG",
                (msg != null && !msg.isEmpty()) ? msg : "EMPTY MESSAGE");
    }

    public static String hash(byte[] chars) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(chars);

        return new BigInteger(1, md.digest()).toString(16);
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