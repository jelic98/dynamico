package org.ecloga.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import org.ecloga.dynamico.Dynamico;
import org.ecloga.dynamico.DynamicoException;
import org.ecloga.dynamico.DynamicoListener;
import org.ecloga.dynamico.Util;

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            new Dynamico("http://ecloga.org/dynamico", "activity_main", (ViewGroup) findViewById(R.id.mainLayout))
                    .setListener(new DynamicoListener() {
                        @Override
                        public void onSuccess(String message) {
                            // everything is okay
                        }

                        @Override
                        public void onError(String message) {
                            // notify user
                        }
                    })
                    .initialize();
        }catch(DynamicoException e) {
            finish();
        }
    }

    public static void onImageClick(String name, Integer age) {
        Util.log("ActivityMain", "Name: " + name + " Age: " + age);
    }
}