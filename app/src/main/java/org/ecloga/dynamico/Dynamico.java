package org.ecloga.dynamico;

import android.view.ViewGroup;

public final class Dynamico {

    private static final String TAG = "Dynamico";

    private DynamicoLayoutLoader loader;
    private DynamicoOptions options;

    public Dynamico(String url, String name, ViewGroup layout) throws DynamicoException {
        if(url == null || name == null || layout == null) {
            throw new DynamicoException("Parameters cannot be null");
        }

        this.loader = new DynamicoLayoutLoader(url, name, layout);
        this.options = new DynamicoOptions();
    }

    public Dynamico setListener(DynamicoListener listener) {
        loader.setListener(listener);

        return this;
    }

    public Dynamico setOptions(DynamicoOptions.Option ... options) {
        this.options = new DynamicoOptions(options);

        return this;
    }

    public void initialize() {
        Util.log(TAG, "Device information: " + Device.getAllInfo());

        if(options.isEnabled(DynamicoOptions.Option.ONLY_CACHE)) {
            loader.loadLayoutFromCache();
        }else {
            loader.loadLayoutFromServer();
        }
    }
}