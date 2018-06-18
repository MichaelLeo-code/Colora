package com.example.michael.test2;


import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class ColorMixFragment extends Fragment {

    private ImageView mixBox;
    private GradientDrawable mixBoxDrawable;
    private ColorProvider library;

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
        mixBoxDrawable = (GradientDrawable) mixBox.getDrawable().mutate();

        mixBoxDrawable.setCornerRadius(20);

        ColorItem lastColor = library.getColor(-1);
        ColorItem beforeLast = library.getColor(-2);
        int last = lastColor == null ? 0 : lastColor.getColor();
        int secondLast = beforeLast == null ? Color.WHITE : beforeLast.getColor();
        int colorsMixed[] = new int[]{last, secondLast};
        mixBoxDrawable.setColors(colorsMixed);
        mixBoxDrawable.setStroke(10, Color.parseColor("#F5F5F5"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_color_mix, container, false);
    }

}
