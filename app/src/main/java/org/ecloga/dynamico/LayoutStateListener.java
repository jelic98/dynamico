package org.ecloga.dynamico;

public abstract class LayoutStateListener {

    public abstract void onSuccess(String message);

    public abstract void onError(String message);
}