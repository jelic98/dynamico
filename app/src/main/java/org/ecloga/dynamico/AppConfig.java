package org.ecloga.dynamico;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class AppConfig {

    // todo Move API_URL to Dynamico.java and let user to configure it
    public static final String API_URL = "http://ecloga.org/dynamico";

    public static String getApiUrl(String url) {
        return API_URL + "/" + url;
    }

    public static String getPath(String name, Context context) {
        return context.getFilesDir() + File.separator + name;
    }
}