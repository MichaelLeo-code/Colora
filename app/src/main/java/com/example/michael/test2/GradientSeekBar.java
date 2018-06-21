package com.example.michael.test2;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

public class GradientSeekBar implements SeekBar.OnSeekBarChangeListener, TextWatcher {

    private Drawable shrinkAnimation;
    private Drawable growAnimation;
    private SeekBar slider;
    private EditText text;
    private int max;
    private int progress;
    private ColorChangeListener callback;
    private final Handler h;
    private boolean isListening = false;

    public GradientSeekBar(Resources r, View v, int sliderId, int textId, int shrinkResId, int growResId,  int max, ColorChangeListener cb) {
        shrinkAnimation = r.getDrawable(shrinkResId).mutate();
        growAnimation = r.getDrawable(growResId).mutate();

        slider = (SeekBar) v.findViewById(sliderId);
        slider.setProgress(progress);
        slider.getProgressDrawable().mutate();
        slider.setMax(max);

        text = (EditText) v.findViewById(textId);
        text.setText(String.valueOf(progress));

        progress = 0;
        this.max = max;
        this.callback = cb;
        h = new Handler();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
        progress = i;
        text.setText(String.valueOf(progress));
        if (fromUser) {
            callback.onColorChange();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        slider.setThumb(growAnimation);
        animate();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        slider.setThumb(shrinkAnimation);
        animate();
    }



    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getProgress() {
        return slider.getProgress();
    }

    public void setProgress(int progress) {
        if (progress > max) progress = max;
        else if (progress < 0) progress = 0;
        this.progress = progress;
        slider.setProgress(progress);
    }

    private void animate() {
        Drawable thumb = slider.getThumb();
        if (thumb instanceof AnimatedVectorDrawableCompat) {
            AnimatedVectorDrawableCompat avd = (AnimatedVectorDrawableCompat) thumb;
            avd.start();
        } else if (thumb instanceof AnimatedVectorDrawable) {
            AnimatedVectorDrawable avd = (AnimatedVectorDrawable) thumb;
            avd.start();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        int selectionPlace = text.getSelectionStart();
        try {
            int textProgress = Integer.parseInt(text.getText().toString());
            progress = textProgress;
            slider.setProgress(textProgress);
            text.setSelection(selectionPlace);
            callback.onColorChange();
        } catch (Exception e){

        }

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (text.getText().toString().matches("")){
            h.postDelayed(new Runnable() {
                public void run() {
                    if (text.getText().toString().matches("")){
                        text.setText("0");
                    }
                }
            }, 1000);
        }

    }
    public void setColors(int[] colors) {
        GradientDrawable sliderBackground = (GradientDrawable) slider.getProgressDrawable();
        sliderBackground.setColors(colors);
    }

    public void listen() {
        if (!isListening) {
            slider.setOnSeekBarChangeListener(this);
            text.addTextChangedListener(this);
            isListening = true;
        }
    }
}
