package org.ecloga.dynamico.style;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.Switch;
import org.ecloga.dynamico.Display;
import org.ecloga.dynamico.DrawableLoader;
import org.ecloga.dynamico.ViewFactory;
import org.json.JSONObject;

public class SwitchStyler extends CompoundButtonStyler {

    private static final int LOAD_THUMB_DRAWABLE = 2;
    private static final int LOAD_TRACK_DRAWABLE = 3;

    private Switch switchButton;

    public SwitchStyler(ViewFactory factory, Context context) {
        super(factory, context);
    }

    @Override
    public View style(View view, JSONObject attributes) throws Exception {
        super.style(view, attributes);

        switchButton = (Switch) view;

        if(attributes.has("textOn")) {
            switchButton.setTextOn(attributes.getString("textOn"));
        }

        if(attributes.has("textOff")) {
            switchButton.setTextOn(attributes.getString("textOff"));
        }

        if(attributes.has("switchMinWidth")) {
            switchButton.setSwitchMinWidth(Display.unitToPx(attributes.getString("switchMinWidth"), context));
        }

        if(attributes.has("switchPadding")) {
            switchButton.setSwitchPadding(Display.unitToPx(attributes.getString("switchPadding"), context));
        }

        if(attributes.has("thumbTextPadding")) {
            switchButton.setThumbTextPadding(Display.unitToPx(attributes.getString("thumbTextPadding"), context));
        }

        if(attributes.has("thumbDrawable")) {
            new DrawableLoader(attributes, this, context)
                    .load(attributes.getString("thumbDrawable"), LOAD_THUMB_DRAWABLE);
        }

        if(attributes.has("trackDrawable")) {
            new DrawableLoader(attributes, this, context)
                    .load(attributes.getString("trackDrawable"), LOAD_TRACK_DRAWABLE);
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if(attributes.has("showText")) {
                switchButton.setShowText(attributes.getBoolean("showText"));
            }

            if(attributes.has("splitTrack")) {
                switchButton.setSplitTrack(attributes.getBoolean("splitTrack"));
            }
        }

        return switchButton;
    }

    @Override
    public void onDrawableLoaded(Drawable drawable, int requestCode) {
        super.onDrawableLoaded(drawable, requestCode);

        switch(requestCode) {
            case LOAD_THUMB_DRAWABLE:
                switchButton.setThumbDrawable(drawable);
                break;
            case LOAD_TRACK_DRAWABLE:
                switchButton.setTrackDrawable(drawable);
                break;
        }
    }
}