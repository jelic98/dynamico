package org.ecloga.dynamico;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.ecloga.dynamico.network.ApiResponse;
import org.ecloga.dynamico.network.Download;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;

public class Dynamico {

    private String url, name;
    private ViewGroup layout;
    private Context context;
    private LayoutStateListener listener;
    private boolean loadCache;

    public Dynamico(String url, String name, ViewGroup layout) throws DynamicoException {
        if(url == null || name == null || layout == null) {
            throw new DynamicoException("Parameters cannot be null");
        }

        this.url = url;
        this.name = name + ".json";
        this.layout = layout;
        this.context = layout.getContext();
    }

    public Dynamico setLayoutStateListener(LayoutStateListener listener) {
        this.listener = listener;

        return this;
    }

    public Dynamico onlyCache() {
        this.loadCache = true;

        return this;
    }

    public void initialize() {
        if(loadCache) {
            loadLayoutFromCache();
        }else {
            loadLayoutFromServer();
        }
    }

    private void loadLayoutFromServer() {
        Util.log("Dynamico", "Loading from server");

        new Download(getApiUrl(name), getPath(name, context), context)
                .addHandler(new ApiResponse() {
                    @Override
                    public void onSuccess(String response) {
                        addLayout(response);
                    }

                    @Override
                    public void onError(String message) {
                        loadLayoutFromCache();

                        if(listener != null) {
                            listener.onError(message);
                        }
                    }
                })
                .send();
    }

    private void loadLayoutFromCache() {
        Util.log("Dynamico", "Loading from cache");

        File file = new File(getPath(name, context));

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            StringBuilder content = new StringBuilder();
            String line;

            while((line = reader.readLine()) != null) {
                content.append(line);
            }

            addLayout(content.toString());

            reader.close();
        }catch(IOException e) {
            Util.log("File error", e.getMessage());
            loadLayoutFromServer();
        }
    }

    private void addLayout(String content) {
        ViewFactory factory = new ViewFactory(context);

        try {
            JSONObject obj = new JSONObject(content);

            layout.removeAllViews();

            if(obj.has("views")) {
                JSONArray views = obj.getJSONArray("views");

                for(int i = 0; i < views.length(); i++) {
                    View view;

                    try {
                        view = factory.getView(views.getJSONObject(i));
                    }catch(Exception e) {
                        e.printStackTrace();
                        Util.log("View error", "Caused by JSON object at index " + i + "\nDetails: " + e.getMessage());
                        continue;
                    }

                    layout.addView(view);
                }
            }
        }catch(JSONException e) {
            Util.log("JSON error", e.getMessage());
        }

        if(listener != null) {
            listener.onSuccess(content);
        }
    }

    private String getApiUrl(String name) {
        return this.url + "/" + name;
    }

    private String getPath(String name, Context context) {
        return context.getFilesDir() + File.separator + name;
    }
}