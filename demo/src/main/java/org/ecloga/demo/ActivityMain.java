package org.ecloga.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import org.ecloga.dynamico.Dynamico;
import org.ecloga.dynamico.DynamicoException;
import org.ecloga.dynamico.DynamicoListener;

public class ActivityMain extends AppCompatActivity {

    private static final String TAG = "Dynamico Demo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Option 1 - Create layout based on remote file
         String res = "https://ecloga.org/projects/dynamico";

        // Option 2 - Create layout based on local String
//        String res = "{\n" +
//                "  \"views\":[\n" +
//                "    {  \n" +
//                "      \"class\":\"android.widget.TextView\",\n" +
//                "      \"attributes\":{  \n" +
//                "        \"text\":\"Sample text\",\n" +
//                "        \"textColor\":\"#FF69B4\"\n" +
//                "      }\n" +
//                "    },\n" +
//                "    {  \n" +
//                "      \"class\":\"android.widget.ImageView\",\n" +
//                "      \"attributes\":{\n" +
//                "        \"src\": \"https://cdn69.picsart.com/186273671000202.jpg?r1024x1024\"\n" +
//                "      }\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}";

        try {
            new Dynamico(res, "activity_main", (ViewGroup) findViewById(R.id.mainLayout))
                    .setListener(new DynamicoListener() {
                        @Override
                        public void onSuccess(String message) {
                            // everything is okay
                            Log.v(TAG, message);
                        }

                        @Override
                        public void onError(String message) {
                            // notify user
                            Log.v(TAG, message);
                        }
                    })
                    .setAsyncPause(500000)
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
    public static void onButtonCheck(boolean isChecked, String message, Context context) {
        Toast.makeText(context, "Checked: " + isChecked + "\nMessage: " + message, Toast.LENGTH_SHORT).show();
    }
}
