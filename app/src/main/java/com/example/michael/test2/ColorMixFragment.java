package com.example.michael.test2;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;


public class ColorMixFragment extends Fragment {

    private ImageView mixBox;
    private GradientDrawable mixBoxDrawable;
    private ColorProvider library;
    private Spinner spinner1;
    private Spinner spinner2;

    private ArrayList<ColorItemForSpinner> mColor4spinnerList;
    private CustomSpinnerAdapter mAdapter;

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

        ColorItem lastColor = library.getColor(-1);
        ColorItem beforeLast = library.getColor(-2);
        int last = lastColor == null ? 0 : lastColor.getColor();
        int secondLast = beforeLast == null ? Color.WHITE : beforeLast.getColor();
        int colorsMixed[] = new int[]{last, secondLast};
        mixBoxDrawable.setColors(colorsMixed);
        mixBoxDrawable.setStroke(10, Color.parseColor("#F5F5F5"));

        initList();

        mAdapter = new CustomSpinnerAdapter(getActivity(), mColor4spinnerList);
        spinner1.setAdapter(mAdapter);
        spinner2.setAdapter(mAdapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_color_mix, container, false);
    }

    private void initList(){
        mColor4spinnerList = new ArrayList<>();
        Drawable color = getResources().getDrawable(R.drawable.ic_check_black_24dp).mutate();
        ColorItemForSpinner one = new ColorItemForSpinner(R.drawable.ic_error_outline_black_24dp);
        mColor4spinnerList.add(one);
//        mColor4spinnerList.add(new ColorItemForSpinner(R.drawable.ic_error_outline_black_24dp));
//        mColor4spinnerList.add(new ColorItemForSpinner(R.drawable.ic_collections_bookmark_black_24dp));
        }
}
