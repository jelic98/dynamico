package org.ecloga.dynamico;

import android.os.Build;
import static org.ecloga.dynamico.Device.Key.*;

public class Device {

    public enum Key {
        BRAND,
        MODEL,
        SDK
    }

    public static String getInfo(Key key) {
        if(key == BRAND) {
            return Build.MANUFACTURER;
        }else if(key == MODEL) {
            return Build.MODEL;
        }else if(key == SDK) {
            return String.valueOf(Build.VERSION.SDK_INT);
        }

        return "/";
    }

    public static String getAllInfo() {
        StringBuilder builder = new StringBuilder();
        builder.append(getInfo(BRAND));
        builder.append(" ");
        builder.append(getInfo(MODEL));
        builder.append(" ");
        builder.append(getInfo(SDK));

        return builder.toString();
    }
}