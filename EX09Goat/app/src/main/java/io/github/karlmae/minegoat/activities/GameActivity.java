package io.github.karlmae.minegoat.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import io.github.karlmae.minegoat.entities.Goat;
import io.github.karlmae.minegoat.entities.Mine;
import io.github.karlmae.minegoat.helpers.PixelSizeConverter;
import io.github.karlmae.minegoat.R;

public class GameActivity extends Activity {

    private boolean isRunning;
    private ConstraintLayout gameLayout;
    private Vibrator v;

    public int screenWidth;
    public int screenHeight;

    public Goat goat;
    public ImageView goatImage;

    int minesMade;
    private List<Mine> mines = new ArrayList<>();
    private Handler mineHandler;
    private final int mineTime = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isRunning = true;

        screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        setContentView(R.layout.game_layout);

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        gameLayout = findViewById(R.id.game_view);

        setGoat();
        setButtons();

        createMines();
        checkOverlapping();
    }

    private void setGoat() {
        goatImage = findViewById(R.id.goat);

        goat = new Goat(this);
    }

    private void setButtons() {
        ImageButton upButton = findViewById(R.id.up_button);
        goat.moveGoat(upButton, Goat.Direction.UP);

        ImageButton downButton = findViewById(R.id.down_button);
        goat.moveGoat(downButton, Goat.Direction.DOWN);

        ImageButton leftButton = findViewById(R.id.left_button);
        goat.moveGoat(leftButton, Goat.Direction.LEFT);

        ImageButton rightButton = findViewById(R.id.right_button);
        goat.moveGoat(rightButton, Goat.Direction.RIGHT);
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public List<Mine> getMines() {
        return mines;
    }

    public ConstraintLayout getGameLayout() {
        return gameLayout;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void vibrate() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }

    public boolean isViewOverlapping(View firstView, View secondView) {
        double firstX = PixelSizeConverter.dpToPx((int) firstView.getX(), this);
        double firstY = PixelSizeConverter.dpToPx((int) firstView.getY(), this);
        double firstHeight = PixelSizeConverter.dpToPx(firstView.getHeight(), this);
        double firstWidth = PixelSizeConverter.dpToPx(firstView.getWidth(), this);

        double secondX = PixelSizeConverter.dpToPx((int) secondView.getX(), this);
        double secondY = PixelSizeConverter.dpToPx((int) secondView.getY(), this);
        double secondHeight = PixelSizeConverter.dpToPx(secondView.getHeight(), this);
        double secondWidth = PixelSizeConverter.dpToPx(secondView.getWidth(), this);


        double goatLeft = firstX;
        double goatTop = firstY;
        double goatRight = firstX + firstWidth;
        double goatBottom = firstY + firstHeight;

        double mineLeft = secondX;
        double mineTop = secondY;
        double mineRight = secondX + secondWidth;
        double mineBottom = secondY + secondHeight;

        if (goatBottom < mineTop) return false;
        if (goatTop > mineBottom) return false;
        if (goatLeft > mineRight) return false;
        if (goatRight < mineLeft) return false;

        return true;
    }

    private void createMines() {
        minesMade = 0;
        mineHandler = new Handler();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {

                if (isRunning) {
                    Mine mine = new Mine(GameActivity.this);

                    ImageView mineImage = new ImageView(getApplicationContext());
                    mineImage.setLayoutParams
                            (new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    mineImage.setY(mine.getyCoordinate());
                    mineImage.setX(mine.getxCoordinate());
                    mineImage.setImageResource(R.drawable.mine);
                    mineImage.getLayoutParams().height = PixelSizeConverter.dpToPx
                            (mine.getMineHeight(), GameActivity.this);
                    mineImage.getLayoutParams().width = PixelSizeConverter.dpToPx
                            (mine.getMineWidth(), GameActivity.this);

                    gameLayout.addView(mineImage);
                    mine.setMineImage(mineImage);

                    mines.add(mine);
                    minesMade++;

                    int nextMineTime = mineTime - (minesMade / 10) * 1000;
                    if (nextMineTime < 1500) nextMineTime = 1500;

                    mineHandler.postDelayed(this, nextMineTime);
                }
            }
        };
        mineHandler.postDelayed(runnable, mineTime);
    }

    private void checkOverlapping() {
        mineHandler = new Handler();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Mine mineToRemove = null;

                if (isRunning) {
                    for (Mine mine : mines) {
                        if (isViewOverlapping(goatImage, mine.getMineImage())) {
                            mineToRemove = mine;
                            mineToRemove.stopMine();
                            goat.addPoint(mineToRemove);
                        }
                    }

                    if (mineToRemove != null) {
                        mines.remove(mineToRemove);
                        gameLayout.removeView(mineToRemove.getMineImage());
                    }
                    mineHandler.postDelayed(this, 5);
                }
            }
        };
        mineHandler.postDelayed(runnable, 5);
    }

    public void endGame() {
        isRunning = false;
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
