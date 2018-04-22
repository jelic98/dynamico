package org.ecloga.dynamico.style;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import org.ecloga.dynamico.Util;
import org.json.JSONObject;

final class ImageViewStyler extends ViewStyler implements OnDrawableLoadedListener {

    private static final int LOAD_SRC = 1;

    private ImageView imageView;

    ImageViewStyler(ViewFactory factory, Context context) {
        super(factory, context);
    }

    @Override
    public View style(View view, JSONObject attributes) throws Exception {
        super.style(view, attributes);

        imageView = (ImageView) view;

        if(attributes.has("src")) {
            new DrawableLoader(attributes, this, context)
                    .load(attributes.getString("src"), LOAD_SRC);
        }

        if(attributes.has("scaleType")) {
            try {
                imageView.setScaleType(ImageView.ScaleType.valueOf(attributes.getString("scaleType")));
            }catch(IllegalArgumentException e) {
                Util.log("Style error", e.getMessage());
            }
        }

        if(attributes.has("adjustViewBounds")) {
            imageView.setAdjustViewBounds(attributes.getBoolean("adjustViewBounds"));
        }

        if(attributes.has("maxWidth")) {
            imageView.setMaxWidth(Display.unitToPx(attributes.getString("maxWidth"), context));
        }

        if(attributes.has("maxHeight")) {
            imageView.setMaxHeight(Display.unitToPx(attributes.getString("maxHeight"), context));
        }

        if(attributes.has("baseline")) {
            imageView.setBaseline(Display.unitToPx(attributes.getString("baseline"), context));
        }

        if(attributes.has("baselineAlignBottom")) {
            imageView.setBaselineAlignBottom(attributes.getBoolean("baselineAlignBottom"));
        }

        if(attributes.has("cropToPadding")) {
            imageView.setCropToPadding(attributes.getBoolean("cropToPadding"));
        }

        if(attributes.has("selected")) {
            imageView.setSelected(attributes.getBoolean("selected"));
        }

        if(attributes.has("imageAlpha")) {
            imageView.setImageAlpha(attributes.getInt("imageAlpha"));
        }

        if(attributes.has("imageLevel")) {
            imageView.setImageLevel(attributes.getInt("imageLevel"));
        }

        if(attributes.has("colorFilter")) {
            try {
                imageView.setColorFilter(Color.parseColor(attributes.getString("colorFilter")));
            }catch(IllegalArgumentException e) {
                Util.log("Style error", e.getMessage());
            }
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if(attributes.has("imageTintMode")) {
                try {
                    imageView.setImageTintMode(PorterDuff.Mode.valueOf(attributes.getString("imageTintMode")));
                }catch(IllegalArgumentException e) {
                    Util.log("Style error", e.getMessage());
                }
            }
        }

        return imageView;
    }

    @Override
    public void onDrawableLoaded(Drawable drawable, int requestCode) {
        if(requestCode == LOAD_SRC) {
            imageView.setImageDrawable(drawable);
        }
    }
}