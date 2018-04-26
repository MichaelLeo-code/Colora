package com.example.michael.test2;

import android.content.ClipboardManager;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.TreeSet;

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
    private ImageView test;
    private Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textR = findViewById(R.id.textR);
        sliderR = findViewById(R.id.sliderR);
        textG = findViewById(R.id.textG);
        sliderG = findViewById(R.id.sliderG);
        textB = findViewById(R.id.textB);
        sliderB = findViewById(R.id.sliderB);
        colorBox = findViewById(R.id.colorBox);
        codeHex = findViewById(R.id.codeHEX);
        copyButton = findViewById(R.id.CopyButton);
        goBack = findViewById(R.id.go_back_button);
        //test = findViewById(R.id.imageView);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, library.class));
            }
        });

//        test.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Drawable d = test.getDrawable();
//
//                if (d instanceof Animatable){
//                    ((Animatable) d).start();
//                }
//            }
//        });

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

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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

    private  int TestColor1 (){
        return  Color.rgb(255, 0,0);
    }

    private int getColorValue() {
        return Color.rgb(progressR, progressG, progressB);
    }

    private String getHexColor(int r, int g, int b) {
        return String.format("#%02x%02x%02x", r, g, b);
    }

//    public void animate(View view) {
//        test = findViewById(R.id.imageView);
//        Drawable d = test.getDrawable();
//        if (d instanceof AnimatedVectorDrawableCompat){
//            AnimatedVectorDrawableCompat avd = (AnimatedVectorDrawableCompat) d;
//            avd.start();
//        }else if (d instanceof AnimatedVectorDrawable){
//            AnimatedVectorDrawable avd = (AnimatedVectorDrawable) d;
//            avd.start();
//        }
//    }
}
