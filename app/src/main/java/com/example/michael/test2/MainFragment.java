package com.example.michael.test2;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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
import android.widget.TextView;

import static android.content.Context.CLIPBOARD_SERVICE;

public class MainFragment extends Fragment implements ColorChangeListener {

    private int progressR = 0;
    private int progressG = 0;
    private int progressB = 0;
    private int progressC = 0;
    private int progressM = 0;
    private int progressY = 0;
    private int progressK = 0;

    private TextView colorBox;
    private ImageButton copyButton;
    private ImageButton saveButton;
    private EditText textHex;
    private int colorFromHexR;
    private int colorFromHexG;
    private int colorFromHexB;
    private Button buttonSwitch;
    private int caseNumber = 1;

    private View rgbLayout;
    private View cmykLayout;

    private GradientSeekBar sliderR;
    private GradientSeekBar sliderG;
    private GradientSeekBar sliderB;
    private GradientSeekBar sliderC;
    private GradientSeekBar sliderM;
    private GradientSeekBar sliderY;
    private GradientSeekBar sliderK;

    private float Rcolor;
    private float Gcolor;
    private float Bcolor;

    ColorSaveListener colorSaveListener;

    private boolean navigationVisible = true;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final FragmentActivity activity = getActivity();

        if (activity instanceof ColorSaveListener) {
            colorSaveListener = (ColorSaveListener) activity;
        }
        Resources res = activity.getResources();

        sliderR = new GradientSeekBar(res, view, R.id.sliderR, R.id.textR, R.drawable.avd_anim_shrink, R.drawable.avd_anim_grow, 255, this);
        sliderG = new GradientSeekBar(res, view, R.id.sliderG, R.id.textG, R.drawable.avd_anim_shrink, R.drawable.avd_anim_grow, 255, this);
        sliderB = new GradientSeekBar(res, view, R.id.sliderB, R.id.textB, R.drawable.avd_anim_shrink, R.drawable.avd_anim_grow, 255, this);
        sliderC = new GradientSeekBar(res, view, R.id.sliderC, R.id.textC, R.drawable.avd_anim_shrink, R.drawable.avd_anim_grow, 100, this);
        sliderM = new GradientSeekBar(res, view, R.id.sliderM, R.id.textM, R.drawable.avd_anim_shrink, R.drawable.avd_anim_grow, 100, this);
        sliderY = new GradientSeekBar(res, view, R.id.sliderY, R.id.textY, R.drawable.avd_anim_shrink, R.drawable.avd_anim_grow, 100, this);
        sliderK = new GradientSeekBar(res, view, R.id.sliderK, R.id.textK, R.drawable.avd_anim_shrink, R.drawable.avd_anim_grow, 100, this);

        sliderR.listen();
        sliderG.listen();
        sliderB.listen();
        sliderC.listen();
        sliderM.listen();
        sliderY.listen();
        sliderK.listen();

        colorBox = view.findViewById(R.id.colorBox);
        copyButton = view.findViewById(R.id.CopyButton);
        saveButton = view.findViewById(R.id.saveButton);
        textHex = view.findViewById(R.id.textHex);
        buttonSwitch = view.findViewById(R.id.buttonSwitch);

        rgbLayout = view.findViewById(R.id.rgb_layout_fragment_inserted);
        cmykLayout = view.findViewById(R.id.cmyk_layout_fragment_inserted);

        cmykLayout.setVisibility(View.INVISIBLE);

        buttonSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (caseNumber == 3){
                    caseNumber = 1;
                } else {
                    caseNumber = caseNumber + 1;
                }
                switch (caseNumber) {
                    case 1:
                        buttonSwitch.setText("RGB");
                        rgbLayout.setVisibility(View.VISIBLE);
                        cmykLayout.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        buttonSwitch.setText("CMYK");
                        rgbLayout.setVisibility(View.INVISIBLE);
                        cmykLayout.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        buttonSwitch.setText("");
                        rgbLayout.setVisibility(View.INVISIBLE);
                        cmykLayout.setVisibility(View.INVISIBLE);
                        break;
                }
                updateColor();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveColor();
                animateSaveButton();
                saveButton.setClickable(false);
                Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    public void run() {
                        saveButton.setClickable(true);
                    }
                }, 500);
            }
        });

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = textHex.getText().toString();
                ClipboardManager clipboardManager = (ClipboardManager) activity.getSystemService(CLIPBOARD_SERVICE);
                clipboardManager.setPrimaryClip(ClipData.newPlainText("Colora hex", txt));
//                hexCopy.show();
//                h.postDelayed(new Runnable() {
//                    public void run() {
//                        hexCopy.cancel();
//                    }
//                }, 1000);
                animateCopyButton();
            }
        });


        updateColor();

        final Handler h = new Handler();


        textHex.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {
                int selectionPlace = textHex.getSelectionStart();
                textHex.setSelection(selectionPlace);
                String value = textHex.getText().toString();
                if (!value.startsWith("#") || value.length() < 7) {
                    /*
                    h.postDelayed(new Runnable() {
                        public void run() {
                            textHex.setText(getHexColor(progressR, progressG, progressB));
                        }
                    }, 5000);
                    */
                    return;
                }
                try {
                    colorFromHexR = Integer.valueOf(value.substring( 1, 3 ), 16 );
                    colorFromHexG = Integer.valueOf(value.substring( 3, 5 ), 16 );
                    colorFromHexB = Integer.valueOf(value.substring( 5, 7 ), 16 );

                    progressR = colorFromHexR;
                    progressG = colorFromHexG;
                    progressB = colorFromHexB;

                    sliderR.setProgress(progressR);
                    sliderG.setProgress(progressG);
                    sliderB.setProgress(progressB);

                } catch (NumberFormatException nfe){
                    /*
                    h.postDelayed(new Runnable() {
                        public void run() {
                            textHex.setText(getHexColor(progressR, progressG, progressB));
                        }
                    }, 5000);
                    */
                } catch (Exception e){

                }
            }
            @Override
            public void afterTextChanged(final Editable s) {

            }
        });

        View decorView = getActivity().getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener
                (new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility) {
                        if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) != 0) {
                            // The navigation bar is hidden
                            navigationVisible = false;
                        } else {
                            // The navigation bar is visible
                            navigationVisible = true;
                            //hideNavigationBar();
                        }
                    }
                });

        view.findViewById(R.id.main_fragment).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.findViewById(R.id.main_fragment).requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);

                hideNavigationBar();
                return false;
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    private void saveColor() {
        if (colorSaveListener != null) {
            colorSaveListener.onColorSave(new ColorItem(progressR, progressG, progressB));
        }
    }

    private void updateColor() {
        if (rgbLayout.getVisibility() == View.VISIBLE) {
            progressR = sliderR.getProgress();
            progressG = sliderG.getProgress();
            progressB = sliderB.getProgress();

            int colorsR[] = new int[]{Color.rgb(0, progressG, progressB), Color.rgb(255, progressG, progressB)};
            int colorsG[] = new int[]{Color.rgb(progressR, 0, progressB), Color.rgb(progressR, 255, progressB)};
            int colorsB[] = new int[]{Color.rgb(progressR, progressG, 0), Color.rgb(progressR, progressG, 255)};

            sliderR.setColors(colorsR);
            sliderG.setColors(colorsG);
            sliderB.setColors(colorsB);

        } else if (cmykLayout.getVisibility() == View.VISIBLE) {

            progressC = sliderC.getProgress();
            progressM = sliderM.getProgress();
            progressY = sliderY.getProgress();
            progressK = sliderK.getProgress();

            progressR = calculateRGB(progressC, progressK);
            progressG = calculateRGB(progressM, progressK);
            progressB = calculateRGB(progressY, progressK);

            int start = 255 - Math.round(progressK * 255 /100);

            int colorsC[] = new int[]{Color.rgb(start, progressG, progressB), Color.rgb(0, progressG, progressB)};
            int colorsM[] = new int[]{Color.rgb(progressR, start, progressB), Color.rgb(progressR, 0, progressB)};
            int colorsY[] = new int[]{Color.rgb(progressR, progressG, start), Color.rgb(progressR, progressG, 0)};
            int colorsK[] = new int[]{Color.rgb(progressR, progressG, progressB), Color.rgb(0, 0, 0)};

            //Log.i("Progresses", String.valueOf(progressR) + " " + String.valueOf(progressG) + " " + String.valueOf(progressB));
            //Log.i("Colors", "Y=" + hex(colorsY[0] & 0xffffff) + "-" + hex(colorsY[1] & 0xffffff));

            sliderC.setColors(colorsC);
            sliderM.setColors(colorsM);
            sliderY.setColors(colorsY);
            sliderK.setColors(colorsK);

            //Log.i("Backgrounds", "Y=" + sliderY.getBackground().toString());
        }else {
            //Nothing
        }
        textHex.setText(getHexColor(progressR, progressG, progressB));
        GradientDrawable background = (GradientDrawable) colorBox.getBackground();
        background.setColor(getColorValue());
    }

    private int calculateRGB(int color, int black) {
        return Math.round(((255 * (100 - color)) * (100 - black)) / 10000);
    }

    private int getColorValue() {
        return Color.rgb(progressR, progressG, progressB);
    }

    private String hex(int num) {
        return String.format("%06x", num);
    }

    private String getHexColor(int r, int g, int b) {
        return String.format("#%02x%02x%02x", r, g, b);
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

    private void animateSaveButton(){
        Drawable d = saveButton.getDrawable();
        if (d instanceof AnimatedVectorDrawableCompat){
            AnimatedVectorDrawableCompat avd = (AnimatedVectorDrawableCompat) d;
            avd.start();
        }else if (d instanceof AnimatedVectorDrawable) {
            AnimatedVectorDrawable avd = (AnimatedVectorDrawable) d;
            avd.start();
        }
    }

    private void hideNavigationBar() {
        if (!navigationVisible) return;
        View decorView = getActivity().getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
        decorView.setSystemUiVisibility(uiOptions);
        navigationVisible = false;
    }

    @Override
    public void onColorChange() {
        updateColor();
    }
}
