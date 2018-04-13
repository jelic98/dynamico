package org.ecloga.dynamico;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.lang.reflect.Constructor;
import org.ecloga.dynamico.style.*;

public class ViewFactory {

    private Context context;

    public ViewFactory(Context context) {
        this.context = context;
    }

    public View addViews(ViewGroup layout, JSONObject object) throws Exception {
        Util.log("Dynamico", "Adding children for view " + layout.getClass().getSimpleName());

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
        Util.log("Dynamico", "Creating view " + className);

        Class elementClass = Class.forName(className);

        Constructor constructor = elementClass.getConstructor(Context.class);

        return (View) constructor.newInstance(context);
    }

    private View styleView(View view, JSONObject attributes) throws Exception {
        Util.log("Dynamico", "Styling view " + view.getClass().getSimpleName());

        if(view instanceof TextView) {
            view = new TextViewStyler(context).style(view, attributes);
        }else if(view instanceof ImageView) {
            view = new ImageViewStyler(context).style(view, attributes);
        }else if(view instanceof LinearLayout) {
            view = new LinearLayoutStyler(context).style(view, attributes);
        }

        return view;
    }
}