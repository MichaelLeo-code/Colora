package com.example.michael.test2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class TestFragment extends Fragment {

    private float Rcolor;
    private float Gcolor;
    private float Bcolor;
    private int RcolorInt;
    private int GcolorInt;
    private int BcolorInt;
    private float Kcolor = 58;
    private float Ccolor = 11;
    private float Mcolor = 12;
    private float Ycolor= 64;
//    private float cColor;
//    private float mColor;
//    private float yColor;

    public TestFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        RGB to CMYK
//        Kcolor = 255-Math.max(Rcolor, Math.max(Gcolor, Bcolor));
//        Ccolor = (255 - Rcolor - Kcolor)/(255 - Kcolor)*255;
//        Mcolor = (255 - Gcolor - Kcolor)/(255 - Kcolor)*255;
//        Ycolor = (255 - Bcolor - Kcolor)/(255 - Kcolor)*255;

        Rcolor = ((255*(100-Ccolor))*(100-Kcolor))/10000;
        Gcolor = ((255*(100-Mcolor))*(100-Kcolor))/10000;
        Bcolor = ((255*(100-Ycolor))*(100-Kcolor))/10000;

        RcolorInt = (int) Math.round(Rcolor);
        GcolorInt = (int) Math.round(Gcolor);
        BcolorInt = (int) Math.round(Bcolor);

//        Log.i("CMYK", String.valueOf(Ccolor)+" "+String.valueOf(Mcolor)+" "+String.valueOf(Ycolor)+" "+String.valueOf(Kcolor));
        Log.i("RGB", String.valueOf(RcolorInt)+" "+String.valueOf(GcolorInt)+" "+String.valueOf(BcolorInt));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

}
