package io.github.karlmae.cookieclicker;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import io.github.karlmae.cookieclicker.databinding.ActivityGameBinding;

/**
 * Show everything on screen
 */
public class GameActivity extends AppCompatActivity {

    static Clickers clickers;
    static Player cookieplayer;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        cookieplayer = new Player();
        clickers = new Clickers();

        super.onCreate(savedInstanceState);

        ActivityGameBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_game);

        dataBinding.shop.setCookieplayer(cookieplayer);
        dataBinding.shop.setClicker(clickers);
        dataBinding.clicker.setCookieplayer(cookieplayer);

        vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        setButtons();
    }


    // Initialize all buttons on screen and set their on click activities.
    private void setButtons() {

        // About button on right bottom edge
        final ImageButton aboutButton = findViewById(R.id.helpButton);
        final RelativeLayout aboutMenu = findViewById(R.id.about_menu);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutMenu.setVisibility(View.VISIBLE);
            }
        });
        buttonEffect(aboutButton);


        // Close button on about screen
        final ImageButton closeAboutButton = findViewById(R.id.aboutBackButton);
        closeAboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutMenu.setVisibility(View.INVISIBLE);
            }
        });
        buttonEffect(closeAboutButton);


        // Exit from game button
        final ImageButton exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
        buttonEffect(exitButton);


        // Main button in the middle of screen
        final ImageButton eapButton = findViewById(R.id.eap_button);
        eapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cookieplayer.addPoints(cookieplayer.getFingers());
                cookieplayer.updateFields();
                vibrator.vibrate(30);
            }
        });
        buttonResize(eapButton);


        //Buy buttons
        ImageButton buyFinger = findViewById(R.id.buy_finger);
        buyFinger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cookieplayer.buyFingers();
            }
        });
        buttonEffect(buyFinger);

        ImageButton buyAutoclicker = findViewById(R.id.buy_autoclicker);
        buyAutoclicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cookieplayer.buyClicker(Player.clickerTypes.autoclickers);
            }
        });
        buttonEffect(buyAutoclicker);

        ImageButton buyParents = findViewById(R.id.buy_parents);
        buyParents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cookieplayer.buyClicker(Player.clickerTypes.parents);
            }
        });
        buttonEffect(buyParents);
    }


    //When a button is clicked, make it half its size for the duration of the click
    public static void buttonResize(View button) {
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        view.setScaleX(view.getScaleX() / 2);
                        view.setScaleY(view.getScaleY() / 2);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        view.setScaleX(view.getScaleX() * 2);
                        view.setScaleY(view.getScaleY() * 2);
                        view.invalidate();
                        break;
                    }
                }

                return false;
            }
        });
    }

    // Change the color of the button, while being pressed
    // https://stackoverflow.com/questions/7175873/click-effect-on-button-in-android
    public static void buttonEffect(View button) {
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }
}
