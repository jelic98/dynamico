package org.ecloga.dynamico;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import org.ecloga.dynamico.network.ApiResponse;
import org.ecloga.dynamico.network.FileDownload;
import org.ecloga.dynamico.style.ViewFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

final class DynamicoLayoutLoader {

    private static final String TAG = "Dynamico.DynamicoLayoutLoader";

    private String url, name;
    private ViewGroup layout;
    private Context context;
    private DynamicoListener listener;

    DynamicoLayoutLoader(String url, String name, ViewGroup layout) {
        this.url = url;
        this.name = name;
        this.layout = layout;
        this.context = layout.getContext();

        if(!this.name.endsWith(".json")) {
            this.name += ".json";
        }
    }

    public void setListener(DynamicoListener listener) {
        this.listener = listener;
    }

    public void loadLayoutFromServer() {
        Util.log(TAG, "Loading from server");

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
        Util.log(TAG, "Loading from cache");

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
            Util.log("File error", "Loading layout from cache produced the following error: " + e.getMessage());

            loadLayoutFromServer();

            if(listener != null) {
                listener.onError(e.getMessage());
            }
        }
    }

    private void addViews(String content) {
        ViewFactory factory = new ViewFactory(context);

        ViewGroup wrapper = new LinearLayout(context);

        try {
            JSONObject obj = new JSONObject(content);

            if(obj.has("targets")) {
                JSONArray targets = obj.getJSONArray("targets");

                boolean foundLayout = false;

                for(int i = 0; i < targets.length(); i++) {
                    JSONObject target = targets.getJSONObject(i);

                    String targetKey = target.getString("key");
                    String targetValue = target.getString("value");

                    if(targetValue.equalsIgnoreCase(Device.getInfo(Device.Key.valueOf(targetKey)))) {
                        factory.addViews(wrapper, target);

                        foundLayout = true;

                        break;
                    }
                }

                if(!foundLayout && obj.has("default")) {
                    factory.addViews(wrapper, obj.getJSONObject("default"));
                }
            }else {
                factory.addViews(wrapper, obj);
            }

            seamlessSwap(wrapper);

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

    private void seamlessSwap(ViewGroup wrapper) {
        layout.removeAllViews();
        layout.addView(wrapper);
    }
    private String getDirectoryUrl(String name) {
        return this.url + "/" + name;
    }

    private String getStoragePath(String name, Context context) {
        return context.getFilesDir() + File.separator + name;
    }
}