package org.ecloga.dynamico.network;

import android.content.Context;
import org.ecloga.dynamico.Util;

abstract class Download extends ApiRequest {

    Download(String url, Context context) {
        this.url = url;
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... params) {
        super.doInBackground();

        executeRequest();

        if(output != null) {
            try {
                onResponse();
            }catch(Exception e) {
                error = e.getMessage();
                Util.log("API error", url + " : " + error);
            }
        }

        return null;
    }

    public abstract void onResponse() throws Exception;
}