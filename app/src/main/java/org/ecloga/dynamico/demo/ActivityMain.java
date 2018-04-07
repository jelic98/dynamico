package org.ecloga.dynamico.demo;

import android.os.Bundle;
import android.view.ViewGroup;
import org.ecloga.dynamico.DynamicoException;
import org.ecloga.dynamico.R;
import org.ecloga.dynamico.Dynamico;
import org.ecloga.dynamico.LayoutStateListener;
import android.support.v7.app.AppCompatActivity;

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            new Dynamico("activity_main", (ViewGroup) findViewById(R.id.mainLayout))
                    .setLayoutStateListener(new LayoutStateListener() {
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
}