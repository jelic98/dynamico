package org.ecloga.dynamico;

import android.os.Build;

public class Device {

    public static String getInfo(String key) {
        if(key.equalsIgnoreCase("brand")) {
            return Build.MANUFACTURER;
        }else if(key.equalsIgnoreCase("model")) {
            return Build.MODEL;
        }else if(key.equalsIgnoreCase("version")) {
            return Build.VERSION.RELEASE;
        }

        return "";
    }
}