package com.example.michael.test2;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

public class ColorItemForSpinner {
    private GradientDrawable mIconImage;
    private int mColor;

    public ColorItemForSpinner(GradientDrawable iconImage, int color){
        mIconImage = iconImage;
        mColor = color;
    }
    public GradientDrawable getIconImage(){
        return mIconImage;
    }

    public int getColor() {
        return mColor;
    }
}
