package org.ecloga.dynamico;

import android.content.Context;
import android.content.SharedPreferences;
import static android.content.Context.MODE_PRIVATE;

public class Prefs {

    private Context context;

    public Prefs(Context context) {
        this.context = context;
    }

    public String get(String key) {
        return createPref().getString(key, "");
    }

    public void set(String key, String value) {
        SharedPreferences.Editor editor = createPref().edit();

        editor.putString(key, value);
        editor.apply();
    }

    private SharedPreferences createPref() {
        return context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
    }
}