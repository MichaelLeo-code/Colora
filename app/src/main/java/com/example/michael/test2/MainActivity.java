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
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private int progressR = 0;
    private int progressG = 0;
    private int progressB = 0;
    private EditText textR;
    private SeekBar sliderR;
    private EditText textG;
    private SeekBar sliderG;
    private EditText textB;
    private SeekBar sliderB;
    private TextView colorBox;
    private TextView codeHex;
    private ImageButton copyButton;
    private Button switchButton;
    private Drawable shrinkR;
    private Drawable growR;
    private Drawable shrinkG;
    private Drawable growG;
    private Drawable shrinkB;
    private Drawable growB;
//    private Toast hexCopy;

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

//        hexCopy = Toast.makeText(MainActivity.this, "Hex Code Copied", Toast.LENGTH_SHORT);

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
//                hexCopy.show();
//                h.postDelayed(new Runnable() {
//                    public void run() {
//                        hexCopy.cancel();
//                    }
//                }, 1000);
                animateCopyButton();
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

        textR.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {
                int selectionPlace = textR.getSelectionStart();
                try {
                    String text = textR.getText().toString();
                    int textProgressR = Integer.parseInt(text);
                    progressR = textProgressR;
                    sliderR.setProgress(textProgressR);
                    textR.setSelection(selectionPlace);
                } catch (NumberFormatException nfe){

                }
            }
            @Override
            public void afterTextChanged(final Editable s) {

            }
        });

        textG.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {
                int selectionPlace = textG.getSelectionStart();
                try {
                    String text = textG.getText().toString();
                    int textProgressG = Integer.parseInt(text);
                    progressG = textProgressG;
                    sliderG.setProgress(textProgressG);
                    textG.setSelection(selectionPlace);
                } catch (NumberFormatException nfe){

                }
            }
            @Override
            public void afterTextChanged(final Editable s) {

            }
        });

        textB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {
                int selectionPlace = textB.getSelectionStart();
                try {
                    String text = textB.getText().toString();
                    int textProgressB = Integer.parseInt(text);
                    progressB = textProgressB;
                    sliderB.setProgress(textProgressB);
                    textB.setSelection(selectionPlace);
                } catch (NumberFormatException nfe){

                }
            }
            @Override
            public void afterTextChanged(final Editable s) {

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

    private void animateCopyButton(){
        Drawable d = copyButton.getDrawable();
        if (d instanceof AnimatedVectorDrawableCompat){
            AnimatedVectorDrawableCompat avd = (AnimatedVectorDrawableCompat) d;
            avd.start();
        }else if (d instanceof AnimatedVectorDrawable) {
            AnimatedVectorDrawable avd = (AnimatedVectorDrawable) d;
            avd.start();
        }
    }
}
