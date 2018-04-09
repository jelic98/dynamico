package org.ecloga.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import org.ecloga.dynamico.Dynamico;
import org.ecloga.dynamico.DynamicoException;
import org.ecloga.dynamico.LayoutStateListener;
import org.ecloga.dynamico.R;

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            new Dynamico("http://ecloga.org/dynamico", "activity_main", (ViewGroup) findViewById(R.id.mainLayout))
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