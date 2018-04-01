package io.github.karlmae.cookieclicker;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public final class Clickers extends BaseObservable {

    private int fingerPrice;
    private int autoclickerPrice;
    private int parentsPrice;

    Clickers() {
        fingerPrice = 10;
        autoclickerPrice = 50;
        parentsPrice = 200;
    }

    @Bindable
    public int getFingerPrice() {
        return fingerPrice;
    }

    @Bindable
    public int getAutoclickerPrice() {
        return autoclickerPrice;
    }

    @Bindable
    public int getParentsPrice() {
        return parentsPrice;
    }

    void setFingerPrice(int fingerPrice) {
        this.fingerPrice = fingerPrice;
    }

    void setAutoclickerPrice(int autoclickerPrice) {
        this.autoclickerPrice = autoclickerPrice;
    }

    void setParentsPrice(int parentsPrice) {
        this.parentsPrice = parentsPrice;
    }

    void updateFields() {
        notifyPropertyChanged(BR.fingerPrice);
        notifyPropertyChanged(BR.autoclickerPrice);
        notifyPropertyChanged(BR.parentsPrice);
    }

}
