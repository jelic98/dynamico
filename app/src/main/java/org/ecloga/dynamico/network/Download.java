package org.ecloga.dynamico.network;

import android.content.Context;
import org.ecloga.dynamico.Util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Download extends ApiRequest {

    private File file;

    public Download(String url, String path, Context context) {
        this.url = url;
        this.context = context;
        this.file = new File(path);
    }

    @Override
    protected String doInBackground(Void... params) {
        super.doInBackground();

        executeRequest();

        if(output != null) {
            try {
                populateFile(output);
            }catch(Exception e) {
                error = e.getMessage();
                Util.log("JSON error", url + " : " + file.getAbsolutePath() + " : " + error);
            }
        }

        return null;
    }

    private void populateFile(String output) throws IOException {
        if(file.exists()) {
            file.delete();
        }

        file.createNewFile();

        FileWriter writer = new FileWriter(file);
        writer.write(output);
        writer.close();
    }
}