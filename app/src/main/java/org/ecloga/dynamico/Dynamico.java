package org.ecloga.dynamico;

import android.os.Looper;
import android.view.ViewGroup;
import java.util.Timer;
import java.util.TimerTask;

public final class Dynamico {

    private static final String TAG = "Dynamico";

    private long asyncPause = 30 * 1000;
    private DynamicoLayoutLoader loader;
    private DynamicoOptions options;

    /**
     * One and only constructor
     * @param url URL of directory where JSON layout file is located (for example, "http://ecloga.org/dynamico")
     * @param name JSON layout file name with or without extension (for example, "activity_main")
     * @param layout wrapper layout that will contain inflated layout from JSON file (for example, findViewById(R.id.mainLayout))
     * @throws DynamicoException if any of passed parameters is null
     */
    public Dynamico(String url, String name, ViewGroup layout) throws DynamicoException {
        if(url == null || name == null || layout == null) {
            throw new DynamicoException("Parameters cannot be null");
        }

        this.loader = new DynamicoLayoutLoader(url, name, layout);
        this.options = new DynamicoOptions();
    }

    /**
     * Attaches event listener to Dynamico object
     * @param listener listener for success and error events caused by network, storage, etc.
     * @return Dynamico object ready for initialization
     */
    public Dynamico setListener(DynamicoListener listener) {
        loader.setListener(listener);

        return this;
    }

    /**
     * Attaches options to Dynamico object
     * @param options options for Dynamico (for example, ONLY_CACHE)
     * @return Dynamico object ready for initialization
     */
    public Dynamico setOptions(DynamicoOptions.Option ... options) {
        this.options = new DynamicoOptions(options);

        return this;
    }

    /**
     * Overrides default asyncPause (30 seconds)
     * Note: NON_STOP option must be enabled
     * @param millis asynchronous call pause in milliseconds
     * @return Dynamico object ready for initialization
     */
    public Dynamico setAsyncPause(long millis) {
        this.asyncPause = millis;

        return this;
    }

    /**
     * Starts layout fetching from cache/server depending on provided options
     */
    public void initialize() {
        Util.log(TAG, "Device information: " + Device.getAllInfo());
        Util.log(TAG, "Dynamico options: " + options);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(Looper.myLooper() == null) {
                    Looper.prepare();
                }

                if(options.isEnabled(DynamicoOptions.Option.ONLY_CACHE)) {
                    loader.loadLayoutFromCache();
                }else {
                    loader.loadLayoutFromServer();
                }

                if(!options.isEnabled(DynamicoOptions.Option.NON_STOP)) {
                    this.cancel();
                }
            }
        }, 0, asyncPause);
    }
}