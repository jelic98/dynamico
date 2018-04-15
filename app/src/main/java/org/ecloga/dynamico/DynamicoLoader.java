package org.ecloga.dynamico;

import android.content.Context;
import android.view.ViewGroup;
import org.ecloga.dynamico.network.ApiResponse;
import org.ecloga.dynamico.network.FileDownload;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DynamicoLoader {

    private String url, name;
    private ViewGroup layout;
    private Context context;
    private DynamicoListener listener;

    public DynamicoLoader(String url, String name, ViewGroup layout) {
        this.url = url;
        this.name = name + ".json";
        this.layout = layout;
        this.context = layout.getContext();
    }

    public void setListener(DynamicoListener listener) {
        this.listener = listener;
    }

    public void loadLayoutFromServer() {
        Util.log("DynamicoLoader", "Loading from server");

        new FileDownload(getDirectoryUrl(name), context, getStoragePath(name, context))
                .addHandler(new ApiResponse() {
                    @Override
                    public void onSuccess(String response) {
                        addViews(response);
                    }

                    @Override
                    public void onError(String message) {
                        Util.log("Server error", message);

                        loadLayoutFromCache();

                        if(listener != null) {
                            listener.onError(message);
                        }
                    }
                })
                .start();
    }

    public void loadLayoutFromCache() {
        Util.log("DynamicoLoader", "Loading from cache");

        File file = new File(getStoragePath(name, context));

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            StringBuilder content = new StringBuilder();
            String line;

            while((line = reader.readLine()) != null) {
                content.append(line);
            }

            addViews(content.toString());

            reader.close();
        }catch(IOException e) {
            Util.log("File error", e.getMessage());

            loadLayoutFromServer();

            if(listener != null) {
                listener.onError(e.getMessage());
            }
        }
    }

    private void addViews(String content) {
        try {
            JSONObject obj = new JSONObject(content);

            if(obj.has("targetKey")) {
                String targetKey = obj.getString("targetKey");

                JSONArray targets = obj.getJSONArray("targets");

                boolean foundLayout = false;

                for(int i = 0; i < targets.length(); i++) {
                    JSONObject target = targets.getJSONObject(i);

                    if(target.has("targetValue")) {
                        String targetValue = target.getString("targetValue");

                        if(targetValue.equalsIgnoreCase(Device.getInfo(targetKey))) {
                            new ViewFactory(context).addViews(layout, target);

                            foundLayout = true;

                            break;
                        }
                    }
                }

                if(!foundLayout) {
                    new ViewFactory(context).addViews(layout, obj.getJSONObject("targetDefault"));
                }
            }else {
                new ViewFactory(context).addViews(layout, obj);
            }

            if(listener != null) {
                listener.onSuccess(content);
            }
        }catch(Exception e) {
            Util.log("Layout error", e.getMessage());

            if(listener != null) {
                listener.onError(content);
            }
        }
    }

    private String getDirectoryUrl(String name) {
        return this.url + "/" + name;
    }

    private String getStoragePath(String name, Context context) {
        return context.getFilesDir() + File.separator + name;
    }
}