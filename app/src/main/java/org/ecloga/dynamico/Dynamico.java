package org.ecloga.dynamico;

import android.content.Context;
import android.view.ViewGroup;
import org.ecloga.dynamico.network.ApiResponse;
import org.ecloga.dynamico.network.FileDownload;
import org.json.JSONObject;
import java.io.*;

public class Dynamico {

    private String url, name;
    private ViewGroup layout;
    private Context context;
    private LayoutStateListener listener;
    private boolean onlyCache, cacheImages;

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

    public Dynamico onlyCache(boolean onlyCache) {
        this.onlyCache = onlyCache;

        return this;
    }

    public Dynamico cacheImages(boolean cacheImages) {
        this.cacheImages = cacheImages;

        return this;
    }

    public void initialize() {
        if(onlyCache) {
            loadLayoutFromCache();
        }else {
            loadLayoutFromServer();
        }
    }

    private void loadLayoutFromServer() {
        Util.log("Dynamico", "Loading from server");

        new FileDownload(getApiUrl(name), context, getPath(name, context))
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
            new ViewFactory(context).addViews(layout, new JSONObject(content));

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

    private String getApiUrl(String name) {
        return this.url + "/" + name;
    }

    private String getPath(String name, Context context) {
        return context.getFilesDir() + File.separator + name;
    }
}