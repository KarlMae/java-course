package io.github.karlmae.cookieclicker;


import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Handler;

/*
* Hold player info and handle buying more stuff
* */

public class Player extends BaseObservable {

    // 1000 = 1 second
    private int defaultInterval = 5000;
    private int minimumInterval = 1000;
    private int reduceInterval = 100;

    private int fingers, autoclickers, parents, points;
    private Handler handler;
    public enum clickerTypes {parents, autoclickers}

    // Used for displaying "MAX" as price in XML, when max amount is bought
    private boolean autoclickerLimitReached = false;
    private boolean parentLimitReached = false;

    private double cookiesPerSec;

    Player() {
        fingers = 1;
        autoclickers = 0;
        parents = 0;
        points = 0;
    }

    void buyFingers() {
        if (GameActivity.clickers.getFingerPrice() > points) return;

        fingers++;
        points -= GameActivity.clickers.getFingerPrice();

        GameActivity.clickers.setFingerPrice(GameActivity.clickers.getFingerPrice() + 10);

        calculateCookiesPerSec();

        updateFields();
        GameActivity.clickers.updateFields();
    }

    void buyClicker(clickerTypes type) {
        switch (type) {
            case parents:
                if (GameActivity.clickers.getParentsPrice() > points || clickerLimitReached(parents)) return;

                if (parents == 1) createTimerThread(clickerTypes.parents);

                parents++;
                points -= GameActivity.clickers.getParentsPrice();

                if (clickerLimitReached(parents)) {
                    parentLimitReached = true;
                    notifyPropertyChanged(BR.parentLimitReached);
                } else {
                    GameActivity.clickers.setParentsPrice(GameActivity.clickers.getParentsPrice() + 200);
                }
                break;

            case autoclickers:
                if (GameActivity.clickers.getAutoclickerPrice() > points || clickerLimitReached(autoclickers)) return;

                if (autoclickers == 1) createTimerThread(clickerTypes.autoclickers);

                autoclickers++;
                points -= GameActivity.clickers.getAutoclickerPrice();

                if (clickerLimitReached(autoclickers)) {
                    autoclickerLimitReached = true;
                    notifyPropertyChanged(BR.autoclickerLimitReached);
                } else {
                    GameActivity.clickers.setAutoclickerPrice(GameActivity.clickers.getAutoclickerPrice() + 50);
                }
                break;
        }

        calculateCookiesPerSec();

        updateFields();
        GameActivity.clickers.updateFields();
    }

    @Bindable
    public int getFingers() {
        return fingers;
    }

    @Bindable
    public int getAutoclickers() {
        return autoclickers;
    }

    @Bindable
    public int getParents() {
        return parents;
    }

    @Bindable
    public int getPoints() {
        return points;
    }

    @Bindable
    public double getCookiesPerSec() {
        return cookiesPerSec;
    }

    @Bindable
    public boolean isAutoclickerLimitReached() {
        return autoclickerLimitReached;
    }

    @Bindable
    public boolean isParentLimitReached() {
        return parentLimitReached;
    }

    void addPoints(int points) {
        this.points += points;
    }

    // Update all the fields
    void updateFields() {
        notifyPropertyChanged(BR.fingers);
        notifyPropertyChanged(BR.autoclickers);
        notifyPropertyChanged(BR.parents);
        notifyPropertyChanged(BR.points);
        notifyPropertyChanged(BR.cookiesPerSec);
    }


    // Creates a thread, that repeats adding points to the player
    private void createTimerThread(final clickerTypes clicker) {
        handler = new Handler();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (clicker == clickerTypes.autoclickers){
                    points += autoclickers * fingers;
                    handler.postDelayed(this, defaultInterval - (autoclickers - 1) * reduceInterval);
                } else if (clicker == clickerTypes.parents) {
                    points += parents * 10;
                    handler.postDelayed(this, defaultInterval);
                }
                notifyPropertyChanged(BR.points);
            }
        };
        handler.postDelayed(runnable, defaultInterval);
    }


    // Return boolean if the player can buy any more clickers
    private boolean clickerLimitReached(int clickerAmount){
        return defaultInterval - clickerAmount * reduceInterval <= minimumInterval;
    }

    private void calculateCookiesPerSec(){
        double autoclickersPerSec = (1000 / (double)(defaultInterval - (autoclickers - 1) * reduceInterval)) * autoclickers * fingers;
        double parentsPerSec = (1000 / (double) defaultInterval) * parents * 10;

        this.cookiesPerSec = Math.round((autoclickersPerSec + parentsPerSec) * 100.0) / 100.0;
    }
}
