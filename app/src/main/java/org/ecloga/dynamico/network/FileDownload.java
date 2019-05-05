package org.ecloga.dynamico.network;

import android.content.Context;
import java.io.File;
import java.io.FileWriter;

public final class FileDownload extends Download {

    private File file;

    public FileDownload(String url, Context context, String path) {
        super(url, context);

        this.file = new File(path);
    }

    @Override
    public void onResponse() throws Exception {
        if(file.exists()) {
            file.delete();
        }

        file.createNewFile();

        FileWriter writer = new FileWriter(file);
        writer.write(output);
        writer.close();
    }
}