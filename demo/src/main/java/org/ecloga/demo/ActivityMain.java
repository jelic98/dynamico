package org.ecloga.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Toast;
import com.rengwuxian.materialedittext.MaterialEditText;
import org.ecloga.dynamico.Dynamico;
import org.ecloga.dynamico.DynamicoException;
import org.ecloga.dynamico.DynamicoListener;
import org.ecloga.dynamico.DynamicoOptions;

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
                    .setOptions(DynamicoOptions.Option.NON_STOP)
                    .setAsyncPause(5000)
                    .initialize();
        }catch(DynamicoException e) {
            finish();
        }
    }

    // parameters cannot be primitive types
    public static void onImageClick(String name, Integer age, Context context) {
        Toast.makeText(context, "Name: " + name + "\nAge: " + age, Toast.LENGTH_SHORT).show();
    }

    // first argument must be boolean and it will be used as 'checked' flag
    public static void onButtonCheck(Boolean isChecked, String message, Context context) {
        Toast.makeText(context, "Checked: " + isChecked + "\nMessage: " + message, Toast.LENGTH_SHORT).show();
    }
}