package io.github.karlmae.minegoat.entities;

import android.os.Handler;
import android.widget.ImageView;

import java.util.Random;

import io.github.karlmae.minegoat.helpers.PixelSizeConverter;
import io.github.karlmae.minegoat.activities.GameActivity;

public class Mine {

    private int xCoordinate;
    private int yCoordinate;
    private int mineWidth = 50;
    private int mineHeight = 50;

    private final int updateTime = 1000;

    private int worth;

    private ImageView mineImage;
    private GameActivity game;

    private boolean mineExists;

    public Mine(GameActivity game) {
        Random coordinateGiver = new Random();

        int dpHeight = (PixelSizeConverter.dpToPx(mineHeight, game));
        int dpWidth = (PixelSizeConverter.dpToPx(mineWidth, game));

        int screenHeight = game.getScreenHeight();
        int screenWidth = game.getScreenWidth();

        xCoordinate = coordinateGiver.nextInt((screenWidth - ((dpWidth) * 2)) + (dpWidth));
        yCoordinate = coordinateGiver.nextInt((screenHeight - ((dpHeight) * 3)) + (dpHeight));
        this.game = game;
        worth = 5;

        mineExists = true;

        explodeTimer();
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public int getMineWidth() {
        return mineWidth;
    }

    public int getMineHeight() {
        return mineHeight;
    }

    public ImageView getMineImage() {
        return mineImage;
    }

    public void setMineImage(ImageView mineImage) {
        this.mineImage = mineImage;
    }

    public int getWorth() {
        return worth;
    }

    public void stopMine() {
        mineExists = false;
    }

    private void explode(Mine mine) {
        new Explosion(this, game);
    }

    private void explodeTimer() {
        final Handler mineHandler = new Handler();

        final Runnable runnable = new Runnable() {
            volatile boolean exploded = false;

            @Override
            public void run() {
                if (!exploded && mineExists && game.isRunning()) {
                    if (worth <= 3) {
                        worth = 0;
                        explode(Mine.this);
                        exploded = true;
                    } else {
                        worth--;
                    }

                    mineHandler.postDelayed(this, updateTime);
                }
            }
        };

        mineHandler.postDelayed(runnable, updateTime);
    }
}
