package org.ecloga.dynamico;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class AppConfig {

    public static final String API_URL = "http://ecloga.org/dynamico";
    public static final String API_KEY = "RANDOM_API_KEY";

    public static String getApiUrl(String url) {
        return API_URL + "/" + url;
    }

    public static String getPath(String name, Context context) {
        return context.getFilesDir() + File.separator + name;
    }
}