package com.example.michael.test2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class CustomSpinnerAdapter extends ArrayAdapter<ColorItemForSpinner> {

    private ImageView imageViewColor;

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
        imageViewColor = convertView.findViewById(R.id.imageIcon4Spinner);
        ColorItemForSpinner currentItem = getItem(position);
        if (currentItem != null) {
            imageViewColor.setImageDrawable(currentItem.getIconImage());
        }
        return convertView;
    }
}
