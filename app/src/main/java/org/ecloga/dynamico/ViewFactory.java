package org.ecloga.dynamico;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.lang.reflect.Constructor;
import org.ecloga.dynamico.style.*;

public class ViewFactory {

    private static final String TAG = "Dynamico.ViewFactory";

    private Context context;

    public ViewFactory(Context context) {
        this.context = context;
    }

    public View addViews(ViewGroup layout, JSONObject object) throws Exception {
        Util.log(TAG, "Adding children for view " + layout.getClass().getSimpleName());

        JSONArray views = object.getJSONArray("views");

        for(int i = 0; i < views.length(); i++) {
            try {
                layout.addView(getView(views.getJSONObject(i)));
            }catch(Exception e) {
                Util.log("View error", "Caused by JSON object at index " + i + "\nDetails: " + e.getMessage());
            }
        }

        return layout;
    }

    private View getView(JSONObject object) throws Exception {
        View view = createView(object.getString("class"));

        if(object.has("views")) {
            view = addViews((ViewGroup) view, object);
        }

        if(object.has("attributes")) {
            view = styleView(view, object.getJSONObject("attributes"));
        }

        return view;
    }

    private View createView(String className) throws Exception {
        Util.log(TAG, "Creating view " + className);

        Class elementClass = Class.forName(className);

        Constructor constructor = elementClass.getConstructor(Context.class);

        return (View) constructor.newInstance(context);
    }

    public View styleView(View view, JSONObject attributes) throws Exception {
        Util.log(TAG, "Styling view " + view.getClass().getSimpleName());

        if(view instanceof Switch) {
            view = new SwitchStyler(this, context).style(view, attributes);
        }else if(view instanceof ToggleButton) {
            view = new ToggleButtonStyler(this, context).style(view, attributes);
        }else if(view instanceof CompoundButton) {
            view = new CompoundButtonStyler(this, context).style(view, attributes);
        }else if(view instanceof EditText) {
            view = new EditTextStyler(this, context).style(view, attributes);
        }else if(view instanceof TextView) {
            view = new TextViewStyler(this, context).style(view, attributes);
        }else if(view instanceof ImageView) {
            view = new ImageViewStyler(this, context).style(view, attributes);
        }else if(view instanceof LinearLayout) {
            view = new LinearLayoutStyler(this, context).style(view, attributes);
        }

        return view;
    }
}