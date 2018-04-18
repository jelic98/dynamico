package org.ecloga.dynamico.style;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import org.ecloga.dynamico.Util;
import org.json.JSONObject;

final class ImageViewStyler extends DefaultStyler implements OnDrawableLoadedListener {

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

        return imageView;
    }

    @Override
    public void onDrawableLoaded(Drawable drawable, int requestCode) {
        if(requestCode == LOAD_SRC) {
            imageView.setImageDrawable(drawable);
        }
    }
}