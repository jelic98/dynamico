package org.ecloga.dynamico.style;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;
import org.apache.commons.io.FileUtils;
import org.ecloga.dynamico.Util;
import org.ecloga.dynamico.ViewFactory;
import org.ecloga.dynamico.network.ApiResponse;
import org.ecloga.dynamico.network.ImageDownload;
import org.json.JSONObject;
import java.io.File;
import java.nio.charset.StandardCharsets;

public class ImageViewStyler extends DefaultStyler {

    private static final String TAG = "Dynamico.ImageViewStyler";

    private boolean cache;

    public ImageViewStyler(ViewFactory factory, Context context) {
        super(factory, context);
    }

    @Override
    public View style(View view, JSONObject attributes) throws Exception {
        super.style(view, attributes);

        ImageView imageView = (ImageView) view;

        if(attributes.has("cache")) {
            cache = attributes.getBoolean("cache");
        }

        if(attributes.has("src")) {
            String src = attributes.getString("src");

            imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

            if(cache) {
                loadImageFromCache(imageView, src);
            }else {
                loadImageFromServer(imageView, src);
            }
        }

        if(attributes.has("scaleType")) {
            try {
                imageView.setScaleType(ImageView.ScaleType.valueOf(attributes.getString("scaleType").trim()));
            }catch(IllegalArgumentException e) {
                Util.log("Style error", e.getMessage());
            }
        }

        return imageView;
    }

    private void loadImageFromServer(final ImageView imageView, final String src) {
        final ImageDownload request = new ImageDownload(src, context);
        request.addHandler(new ApiResponse() {
            @Override
            public void onSuccess(String response) {
                if(cache) {
                    try {
                        FileUtils.writeByteArrayToFile(createFile(src), request.getBytes());
                    }catch(Exception e) {
                        Util.log("Image error", "Loading image from server and caching it produced the following error: " + e.getMessage());
                    }
                }

                imageView.setImageDrawable(request.getDrawable());
            }

            @Override
            public void onError(String message) {
                Util.log("Image error", "Loading image from server produced the following error: " + message);

                loadImageFromCache(imageView, src);
            }
        });
        request.start();
    }

    private void loadImageFromCache(final ImageView imageView, String src) {
        byte[] bytes;

        try {
            bytes = FileUtils.readFileToByteArray(createFile(src));
        }catch(Exception e) {
            Util.log("Image error", "Loading image from cache produced the following error: " + e.getMessage());

            loadImageFromServer(imageView, src);

            return;
        }

        if(bytes != null && bytes.length > 0) {
            imageView.setImageDrawable(new BitmapDrawable(context.getResources(),
                    BitmapFactory.decodeByteArray(bytes, 0, bytes.length)));
        }else {
            loadImageFromServer(imageView, src);
        }
    }

    private File createFile(String src) throws Exception {
        String path = context.getFilesDir() + File.separator + Util.hash(src.getBytes(StandardCharsets.UTF_8));

        Util.log(TAG, "Creating file at path: " + path);

        File file = new File(path);
        file.createNewFile();

        return file;
    }
}