package org.ecloga.dynamico.network;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class ImageDownload extends Download {

    private byte[] bytes;

    public ImageDownload(String url, Context context) {
        super(url, context);

        setType("image/*");
    }

    @Override
    public void onResponse() {
        bytes = responseBytes.clone();
    }

    public byte[] getBytes() {
        return bytes;
    }

    public Drawable getDrawable() {
        return new BitmapDrawable(context.getResources(), BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
    }
}