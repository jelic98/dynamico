package org.ecloga.dynamico.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageDownload extends Download {

    private byte[] imageBytes;

    public ImageDownload(String url, Context context) {
        super(url, context);

        setType("image/png");
    }

    @Override
    public void onResponse() {
        imageBytes = responseBytes.clone();
    }

    public byte[] getBytes() {
        return imageBytes;
    }

    public Bitmap getBitmap() {
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }
}