package org.ecloga.dynamico.style;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import org.apache.commons.io.FileUtils;
import org.ecloga.dynamico.Util;
import org.ecloga.dynamico.ViewFactory;
import org.ecloga.dynamico.network.ApiResponse;
import org.ecloga.dynamico.network.ImageDownload;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class ImageViewStyler extends DefaultStyler {

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
                imageView.setImageBitmap(request.getBitmap());

                if(cache) {
                    try {
                        FileUtils.writeByteArrayToFile(createFile(src), request.getBytes());
                    }catch(Exception e) {
                        Util.log("Image error", e.getMessage());
                    }
                }
            }

            @Override
            public void onError(String message) {
                Util.log("Image error", message);

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
            Util.log("Image error", e.getMessage());

            loadImageFromServer(imageView, src);

            return;
        }

        if(bytes != null && bytes.length > 0) {
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
        }else {
            loadImageFromServer(imageView, src);
        }
    }

    private File createFile(String src) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(src.getBytes(StandardCharsets.UTF_8));
        String path = new String(hash);

        File file = new File(context.getFilesDir() + File.separator + path);
        file.createNewFile();

        return file;
    }
}