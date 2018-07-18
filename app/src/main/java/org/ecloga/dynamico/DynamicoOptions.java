package org.ecloga.dynamico;

import java.util.Arrays;
import java.util.EnumSet;

public class DynamicoOptions {

    /**
     * Options that can be attached to Dynamico object
     */
    public enum Option {
        /**
         * Force checking cache before fetching layout from server
         */
        ONLY_CACHE,

        /**
         * Fetch layout as a String from a DB for example.
         */
        ONLY_STRING,

        /**
         * Fetch layout constantly
         * Note: Pause between server requests is 30 seconds or use setAsyncPause(long millis)
         */
        NON_STOP
    }

    private EnumSet<Option> options;

    DynamicoOptions(Option ... options) {
        this.options = EnumSet.noneOf(Option.class);
        this.options.addAll(Arrays.asList(options));
    }

    public boolean isEnabled(Option option) {
        return options.contains(option);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}