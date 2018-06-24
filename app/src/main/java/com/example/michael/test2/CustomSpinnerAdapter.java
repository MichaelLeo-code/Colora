package com.example.michael.test2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class CustomSpinnerAdapter extends ArrayAdapter<ColorItemForSpinner> {

    public CustomSpinnerAdapter(Context context, ArrayList<ColorItemForSpinner> colorItemForSpinnersList) {
        super(context, 0, colorItemForSpinnersList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.color_spinner_row, parent, false
            );
        }
        ImageView imageViewColor = convertView.findViewById(R.id.imageIcon4Spinner);
        ColorItemForSpinner currentItem = getItem(position);
        if (currentItem != null) {
            imageViewColor.setImageResource(currentItem.getIconImage());
        }
        return convertView;
    }
}
