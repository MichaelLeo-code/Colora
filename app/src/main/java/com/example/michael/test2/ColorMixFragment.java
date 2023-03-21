package com.example.michael.test2;


import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;


public class ColorMixFragment extends Fragment {

    private static int DEFAULT_COLOR = Color.rgb(255,255,255);
    private ImageView mixBox;
    private GradientDrawable mixBoxDrawable;
    private ColorProvider library;
    private Spinner spinner1;
    private Spinner spinner2;

    private ArrayList<ColorItemForSpinner> mColor4spinnerList;
    private CustomSpinnerAdapter mAdapter;

    private int gradientStart = DEFAULT_COLOR;
    private int gradientStop = DEFAULT_COLOR;

    public ColorMixFragment() {
        // Required empty public constructor
    }

    public void setLibrary(ColorProvider library) {
        this.library = library;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mixBox = view.findViewById(R.id.mixBox);
        spinner1 = view.findViewById(R.id.color1spinner);
        spinner2 = view.findViewById(R.id.color2spinner);

        mixBoxDrawable = (GradientDrawable) mixBox.getDrawable().mutate();
        mixBoxDrawable.setCornerRadius(20);

//        ColorItem lastColor = library.getColor(-1);
//        ColorItem beforeLast = library.getColor(-2);
//        int last = lastColor == null ? 0 : lastColor.getColor();
//        int secondLast = beforeLast == null ? Color.WHITE : beforeLast.getColor();
//        int colorsMixed[] = new int[]{last, secondLast};

        mixBoxDrawable.setStroke(10, Color.parseColor("#F5F5F5"));

        initList();

        mAdapter = new CustomSpinnerAdapter(getActivity(), mColor4spinnerList);
        spinner1.setAdapter(mAdapter);
        spinner2.setAdapter(mAdapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                gradientStart = mColor4spinnerList.get(position).getColor();
                mixBoxDrawable.setColors(new int[]{gradientStart, gradientStop});
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                gradientStop = mColor4spinnerList.get(position).getColor();
                mixBoxDrawable.setColors(new int[]{gradientStart, gradientStop});
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

        int colorsMixed[] = new int[]{gradientStart, gradientStop};
        mixBoxDrawable.setColors(colorsMixed);
    }

    private int getSelectedColor(Spinner spinner) {
        View selected = spinner.getSelectedView();
        if (selected != null && selected instanceof ImageView) {
            return selected.getSolidColor();
        }
        return Color.rgb(255,255,255);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_color_mix, container, false);
    }

    public void initList(){
        mColor4spinnerList = new ArrayList<>();

        boolean getNext = true;
        int index = 0;
        ColorItem colorItem;
        while (getNext) {
            colorItem = library.getColor(index);
            if (colorItem != null) {
                GradientDrawable icon = (GradientDrawable) getResources().getDrawable(R.drawable.colorbox);
                icon.setColor(colorItem.getColor());
                ColorItemForSpinner item = new ColorItemForSpinner(icon, colorItem.getColor());
                mColor4spinnerList.add(item);
                index++;
            } else {
                getNext = false;
            }
        }
        
//        if (library.getColor(-1) != null){
//            GradientDrawable color = (GradientDrawable) getResources().getDrawable(R.drawable.colorbox);
//            color.setColor(library.getColor(-1).getColor());
//            ColorItemForSpinner four = new ColorItemForSpinner(color);
//            mColor4spinnerList.add(four);
//        }
//        if (library.getColor(-2) != null){
//            GradientDrawable color4 = (GradientDrawable) getResources().getDrawable(R.drawable.colorbox);
//            color4.setColor(library.getColor(-2).getColor());
//            ColorItemForSpinner four = new ColorItemForSpinner(color4);
//            mColor4spinnerList.add(four);
//        }
//        GradientDrawable color1 = (GradientDrawable) getResources().getDrawable(R.drawable.colorbox).mutate();
//        color1.setColor(Color.rgb(255,0,0));
//        ColorItemForSpinner one = new ColorItemForSpinner(color1);
//        mColor4spinnerList.add(one);
//        mColor4spinnerList.add(new ColorItemForSpinner(R.drawable.ic_error_outline_black_24dp));
//        mColor4spinnerList.add(new ColorItemForSpinner(R.drawable.ic_collections_bookmark_black_24dp));
        }
}
