package com.example.michael.test2;


import android.content.ClipboardManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import static android.content.Context.CLIPBOARD_SERVICE;

public class MainFragment extends Fragment {

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

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final FragmentActivity activity = getActivity();
        Resources res = activity.getResources();
        shrinkR =  res.getDrawable(R.drawable.avd_anim_shrink_r);
        growR = res.getDrawable(R.drawable.avd_anim_r);
        shrinkG = res.getDrawable(R.drawable.avd_anim_shrink_g);
        growG = res.getDrawable(R.drawable.avd_anim_g);
        shrinkB = res.getDrawable(R.drawable.avd_anim_shrink_b);
        growB = res.getDrawable(R.drawable.avd_anim_b);

        textR = view.findViewById(R.id.textR);
        sliderR = view.findViewById(R.id.sliderR);
        textG = view.findViewById(R.id.textG);
        sliderG = view.findViewById(R.id.sliderG);
        textB = view.findViewById(R.id.textB);
        sliderB = view.findViewById(R.id.sliderB);
        colorBox = view.findViewById(R.id.colorBox);
        codeHex = view.findViewById(R.id.codeHEX);
        copyButton = view.findViewById(R.id.CopyButton);

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = codeHex.getText().toString();
                ClipboardManager clipboardManager = (ClipboardManager) activity.getSystemService(CLIPBOARD_SERVICE);
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

        final Handler h = new Handler();

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

                } catch (Exception e){

                }
            }
            @Override
            public void afterTextChanged(final Editable s) {
                if (textR.getText().toString().matches("")){
                    h.postDelayed(new Runnable() {
                        public void run() {
                            if (textR.getText().toString().matches("")){
                                textR.setText("0");
                            }
                        }
                    }, 1000);
                }
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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
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
