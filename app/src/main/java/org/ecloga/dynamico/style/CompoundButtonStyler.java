package org.ecloga.dynamico.style;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.CompoundButton;
import org.ecloga.dynamico.Util;
import org.json.JSONObject;

class CompoundButtonStyler extends TextViewStyler implements OnDrawableLoadedListener {

    private static final int LOAD_BUTTON_DRAWABLE = 1;

    private CompoundButton compoundButton;

    CompoundButtonStyler(ViewFactory factory, Context context) {
        super(factory, context);
    }

    @Override
    public View style(View view, JSONObject attributes) throws Exception {
        super.style(view, attributes);

        compoundButton = (CompoundButton) view;

        if(attributes.has("checked")) {
            compoundButton.setChecked(attributes.getBoolean("checked"));
        }

        if(attributes.has("buttonDrawable")) {
            new DrawableLoader(attributes, this, context)
                    .load(attributes.getString("buttonDrawable"), LOAD_BUTTON_DRAWABLE);
        }

        if(attributes.has("onCheck")) {
            final MethodInvoker invoker = new MethodInvoker
                    .Builder(attributes.getJSONObject("onCheck"), context)
                    .setRequiredTypes(Boolean.class)
                    .build();

            compoundButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    invoker.invoke(isChecked);
                }
            });
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if(attributes.has("buttonTintMode")) {
                try {
                    compoundButton.setButtonTintMode(PorterDuff.Mode.valueOf(attributes.getString("buttonTintMode")));
                }catch(IllegalArgumentException e) {
                    Util.log("Style error", e.getMessage());
                }
            }
        }

        return compoundButton;
    }

    @Override
    public void onDrawableLoaded(Drawable drawable, int requestCode) {
        if(requestCode == LOAD_BUTTON_DRAWABLE) {
            compoundButton.setButtonDrawable(drawable);
        }
    }
}