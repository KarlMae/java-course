package io.github.karlmae.minegoat.entities;

import android.os.Handler;
import android.widget.ImageView;

import io.github.karlmae.minegoat.helpers.PixelSizeConverter;
import io.github.karlmae.minegoat.R;
import io.github.karlmae.minegoat.activities.GameActivity;


class Explosion {

    private ImageView explosionImage;
    private GameActivity game;
    private long startTime;
    private Mine mine;

    Explosion(Mine mine, GameActivity game) {
        this.game = game;
        this.mine = mine;

        startTime = System.nanoTime();

        explosionImage = mine.getMineImage();
        explosionImage.setImageResource(R.drawable.plosion);
        explosionImage.getLayoutParams().height = PixelSizeConverter.dpToPx(mine.getMineHeight() * 2, game);
        explosionImage.getLayoutParams().width = PixelSizeConverter.dpToPx(mine.getMineWidth() * 2, game);
        explosionImage.setY(explosionImage.getY() - explosionImage.getHeight() / 2);
        explosionImage.setX(explosionImage.getX() - explosionImage.getWidth() / 2);

        game.vibrate();

        explodeTimer();
    }


    private void explodeTimer() {
        final Handler explosionHandler = new Handler();

        final Runnable runnable = new Runnable() {
            volatile boolean done = false;

            @Override
            public void run() {
                if (!done && game.isRunning()) {
                    if (game.isViewOverlapping(explosionImage, game.goatImage)) {
                        game.goat.removeHealth();
                        game.getGameLayout().removeView(mine.getMineImage());
                        done = true;
                    }

                    if (System.nanoTime() - startTime > 10000) {
                        game.getGameLayout().removeView(mine.getMineImage());
                        game.getMines().remove(mine);
                        done = true;
                    }
                    explosionHandler.postDelayed(this, 5);
                }
            }
        };
        explosionHandler.postDelayed(runnable, 5);
    }
}
