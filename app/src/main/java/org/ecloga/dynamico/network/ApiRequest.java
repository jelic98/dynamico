package org.ecloga.dynamico.network;

import android.content.Context;
import android.os.AsyncTask;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.IOUtils;
import org.ecloga.dynamico.Util;
import org.json.JSONObject;
import java.util.concurrent.TimeUnit;

public abstract class ApiRequest extends AsyncTask<Void, Void, String> {

    private static final String ERROR_DISCONNECTED = "No internet connection";

    byte[] responseBytes;
    String url, output, error;
    private String type;
    protected Context context;

    private OkHttpClient client;
    private ApiResponse handler;

    ApiRequest() {
        client = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES)
                .build();

        type = "application/json";
    }

    public ApiRequest addHandler(ApiResponse responseHandler) {
        this.handler = responseHandler;

        return this;
    }

    public void start() {
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    void executeRequest() {
        Request request;

        request = requestBuilder().get().build();

        try {
            Response response = client.newCall(request).execute();

            responseBytes = IOUtils.toByteArray(response.body().byteStream());

            output = new String(responseBytes);

            int responseCode = response.code();

            Util.log("API", url + " => " + responseCode + ": " + output);

            if (responseCode < 200 || responseCode > 299) {
                JSONObject jsonObject = new JSONObject(output);

                if (jsonObject.has("error")) {
                    JSONObject errorObject = jsonObject.getJSONObject("error");
                    error = errorObject.getString("message");
                }

                cancel(true);
            }
        }catch(Exception e) {
            error = e.getMessage();
            Util.log("API error", url + " : " + error);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if(!Util.hasNetworkAccess(context)) {
            error = ERROR_DISCONNECTED;
            cancel(true);
        }
    }

    @Override
    protected String doInBackground(Void... arg0) {
        return null;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();

        handle();
    }

    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);

        handle();
    }

    private void handle() {
        if(handler != null) {
            if(error != null) {
                handler.onError(error);
            }else {
                handler.onSuccess(output);
            }
        }
    }

    private Request.Builder requestBuilder() {
        return new Request.Builder()
                .url(url)
                .addHeader("Accept", getType());
    }

    String getType() {
        return type;
    }

    void setType(String type) {
        this.type = type;
    }
}