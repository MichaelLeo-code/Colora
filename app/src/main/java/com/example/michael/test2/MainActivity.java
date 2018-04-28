package com.example.michael.test2;

import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int progressR = 0;
    private int progressG = 0;
    private int progressB = 0;
    private TextView textR;
    private SeekBar sliderR;
    private TextView textG;
    private SeekBar sliderG;
    private TextView textB;
    private SeekBar sliderB;
    private TextView colorBox;
    private TextView codeHex;
    private Button copyButton;
    private Button switchButton;
    private Drawable shrinkR;
    private Drawable growR;
    private Drawable shrinkG;
    private Drawable growG;
    private Drawable shrinkB;
    private Drawable growB;
    private Drawable vector2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shrinkR = getDrawable(R.drawable.avd_anim_shrink_r);
        growR = getDrawable(R.drawable.avd_anim_r);
        shrinkG = getDrawable(R.drawable.avd_anim_shrink_g);
        growG = getDrawable(R.drawable.avd_anim_g);
        shrinkB = getDrawable(R.drawable.avd_anim_shrink_b);
        growB = getDrawable(R.drawable.avd_anim_b);
        vector2 = getDrawable(R.drawable.vector2);

        textR = findViewById(R.id.textR);
        sliderR = findViewById(R.id.sliderR);
        textG = findViewById(R.id.textG);
        sliderG = findViewById(R.id.sliderG);
        textB = findViewById(R.id.textB);
        sliderB = findViewById(R.id.sliderB);
        colorBox = findViewById(R.id.colorBox);
        codeHex = findViewById(R.id.codeHEX);
        copyButton = findViewById(R.id.CopyButton);
        switchButton = findViewById(R.id.switch_button);
        final Handler h = new Handler();

        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Library.class));
            }
        });

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = codeHex.getText().toString();
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                clipboardManager.setText(txt);
            }
        });

        sliderR.setMax(255);
        sliderR.setProgress(progressR);
        sliderG.setMax(255);
        sliderG.setProgress(progressG);
        sliderB.setMax(255);
        sliderB.setProgress(progressG);

        textR.setText(String.valueOf(progressR));
        textG.setText(String.valueOf(progressG));
        textB.setText(String.valueOf(progressG));


        sliderR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressR = i;
                textR.setText(String.valueOf(progressR));
                updateColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                sliderR.setThumb(growR);
                animateR();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                sliderR.setThumb(shrinkR);
                animateR();
            }
        });

        sliderG.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressG = i;
                textG.setText(String.valueOf(progressG));
                updateColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                sliderG.setThumb(growG);
                animateG();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                sliderG.setThumb(shrinkG);
                animateG();
                h.postDelayed(new Runnable() {
                    public void run() {
                        sliderG.setThumb(vector2);
                    }
                }, 100);
            }
        });

        sliderB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressB = i;
                textB.setText(String.valueOf(progressB));
                updateColor();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                sliderB.setThumb(growB);
                animateB();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                sliderB.setThumb(shrinkB);
                animateB();
            }
        });

        updateColor();
    }

    private void updateColor() {
        int colorsR[] = new int[]{Color.rgb(0, progressG, progressB), Color.rgb(255, progressG, progressB)};
        int colorsG[] = new int[]{Color.rgb(progressR, 0, progressB), Color.rgb(progressR, 255, progressB)};
        int colorsB[] = new int[]{Color.rgb(progressR, progressG, 0), Color.rgb(progressR, progressG, 255)};
        codeHex.setText(getHexColor(progressR, progressG, progressB));
        GradientDrawable background = (GradientDrawable) colorBox.getBackground();
        background.setColor(getColorValue());

        GradientDrawable sliderRbackground = (GradientDrawable) sliderR.getProgressDrawable();
        sliderRbackground.setColors(colorsR);

        GradientDrawable sliderGbackground = (GradientDrawable) sliderG.getProgressDrawable();
        sliderGbackground.setColors(colorsG);

        GradientDrawable sliderBbackground = (GradientDrawable) sliderB.getProgressDrawable();
        sliderBbackground.setColors(colorsB);
    }

    private int getColorValue() {
        return Color.rgb(progressR, progressG, progressB);
    }

    private String getHexColor(int r, int g, int b) {
        return String.format("#%02x%02x%02x", r, g, b);
    }

    private void animateR() {
        Drawable dR = sliderR.getThumb();
        if (dR instanceof AnimatedVectorDrawableCompat){
            AnimatedVectorDrawableCompat avdR = (AnimatedVectorDrawableCompat) dR;
            avdR.start();
        }else if (dR instanceof AnimatedVectorDrawable){
            AnimatedVectorDrawable avdR = (AnimatedVectorDrawable) dR;
            avdR.start();
        }
    }

    private void animateG() {
        Drawable dG = sliderG.getThumb();
        if (dG instanceof AnimatedVectorDrawableCompat){
            AnimatedVectorDrawableCompat avdG = (AnimatedVectorDrawableCompat) dG;
            avdG.start();
        }else if (dG instanceof AnimatedVectorDrawable){
            AnimatedVectorDrawable avdG = (AnimatedVectorDrawable) dG;
            avdG.start();
        }
    }

    private void animateB() {
        Drawable dB = sliderB.getThumb();
        if (dB instanceof AnimatedVectorDrawableCompat){
            AnimatedVectorDrawableCompat avdB = (AnimatedVectorDrawableCompat) dB;
            avdB.start();
        }else if (dB instanceof AnimatedVectorDrawable){
            AnimatedVectorDrawable avdB = (AnimatedVectorDrawable) dB;
            avdB.start();
        }
    }
}
