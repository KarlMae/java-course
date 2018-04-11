package io.github.karlmae.minegoat.entities;

import android.databinding.BaseObservable;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import io.github.karlmae.minegoat.R;
import io.github.karlmae.minegoat.activities.GameActivity;

public class Goat extends BaseObservable {

    private GameActivity game;

    public enum Direction { LEFT, RIGHT, DOWN, UP }
    private int xCoordinate;
    private int yCoordinate;

    private int health;
    private int points;

    private int screenWidth;
    private int screenHeight;

    private ImageView goatImage;

    private TextView pointView;
    private TextView healthView;


    public Goat(GameActivity game) {
        this.game = game;

        this.screenWidth = game.getScreenWidth();
        this.screenHeight = game.getScreenHeight();

        goatImage = game.goatImage;

        xCoordinate = goatImage.getLeft();
        yCoordinate = goatImage.getTop();

        health = 3;

        pointView = game.findViewById(R.id.points);
        healthView = game.findViewById(R.id.health);
    }

    public int getX() {
        return xCoordinate;
    }

    public int getY() {
        return yCoordinate;
    }

    public void moveLeft() {
        if (xCoordinate <= -screenWidth / 2 + (goatImage.getWidth())) return;
        xCoordinate = xCoordinate - 10;
    }

    public void moveRight() {
        if (xCoordinate >= screenWidth / 2 - (goatImage.getWidth())) return;
        xCoordinate = xCoordinate + 10;
    }

    public void moveDown() {
        if (yCoordinate >= screenHeight / 2 - (goatImage.getHeight() / 1.55)) return;
        yCoordinate = yCoordinate + 10;
    }

    public void moveUp() {
        if (yCoordinate <= -screenHeight / 2 + (goatImage.getHeight() / 1.55)) return;
        yCoordinate = yCoordinate - 10;
    }

    public void addPoint(Mine mine) {
        points += mine.getWorth();
        pointView.setText(String.valueOf(points));
    }

    public void removeHealth() {
        health -= 1;

        if (health <= 0) {
            game.endGame();
        }

        healthView.setText(String.valueOf(health));
    }

    public void moveGoat(View button, final Goat.Direction direction) {
        button.setOnTouchListener(new View.OnTouchListener() {

            Handler mHandler;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 5);
                        break;

                    case MotionEvent.ACTION_UP:
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        mHandler = null;
                        break;
                }
                return false;
            }


            Runnable mAction = new Runnable() {
                @Override
                public void run() {
                    switch (direction) {
                        case UP:
                            moveUp();
                            goatImage.animate().translationY(getY()).setDuration(0);
                            goatImage.setRotation(0);
                            goatImage.invalidate();
                            break;
                        case DOWN:
                            moveDown();
                            goatImage.setRotation(180);
                            goatImage.animate().translationY(getY()).setDuration(0);
                            goatImage.invalidate();
                            break;
                        case LEFT:
                            moveLeft();
                            goatImage.animate().translationX(getX()).setDuration(0);
                            goatImage.setRotation(-90);
                            goatImage.invalidate();
                            break;
                        case RIGHT:
                            moveRight();
                            goatImage.animate().translationX(getX()).setDuration(0);
                            goatImage.setRotation(90);
                            goatImage.invalidate();
                            break;
                    }
                    mHandler.postDelayed(this, 5);
                }
            };
        });


    }
}
