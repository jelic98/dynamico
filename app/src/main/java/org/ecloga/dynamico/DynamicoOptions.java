package org.ecloga.dynamico;

import java.util.Arrays;
import java.util.EnumSet;

public class DynamicoOptions {

    public enum Option {
        ONLY_CACHE
    }

    private EnumSet<Option> options;

    public DynamicoOptions(Option ... options) {
        this.options = EnumSet.noneOf(Option.class);
        this.options.addAll(Arrays.asList(options));
    }

    public boolean isEnabled(Option option) {
        return options.contains(option);
    }
}