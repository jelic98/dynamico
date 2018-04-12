package org.ecloga.dynamico.style;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import org.ecloga.dynamico.Util;
import org.ecloga.dynamico.network.ApiResponse;
import org.ecloga.dynamico.network.ImageDownload;
import org.json.JSONObject;

public class ImageViewStyler extends DefaultStyler {

    public ImageViewStyler(Context context) {
        super(context);
    }

    @Override
    public View style(View view, JSONObject attributes) throws Exception {
        super.style(view, attributes);

        final ImageView imageView = (ImageView) view;

        if(attributes.has("src")) {
            String src = attributes.getString("src");

            final ImageDownload request = new ImageDownload(src, context);
            request.addHandler(new ApiResponse() {
                @Override
                public void onSuccess(String response) {
                    imageView.setImageBitmap(request.getBitmap());
                }

                @Override
                public void onError(String message) {
                    Util.log("Image error", message);
                }
            });
            request.start();
        }

        return imageView;
    }
}