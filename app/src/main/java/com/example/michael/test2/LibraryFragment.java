package com.example.michael.test2;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LibraryFragment extends Fragment {

    private ImageView test;
    private String filename = "myfile";
    private String fileContents = "Hello world!";
    private Button saveSomeDataButton;
    private TextView testText;
    private String getText;
    private Button showText;
    private ColorItemListAdapter adapter;

    Button buttonAdd;
    ExpandableHeightGridView gridView;

    private List<ColorItem> colors = new ArrayList<>();

    public LibraryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        saveSomeDataButton = view.findViewById(R.id.save_button);
        testText = view.findViewById(R.id.saved_data_text);
        showText = view.findViewById(R.id.show_text);

        gridView = (ExpandableHeightGridView) view.findViewById(R.id.gridView);

        gridView.setExpanded(true);

        adapter = new ColorItemListAdapter(this);
        gridView.setAdapter(adapter);

        saveSomeDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDataTest();
            }
        });

        showText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BufferedReader in = null;
                try {
                    in = new BufferedReader(new InputStreamReader(getActivity().openFileInput(filename)));
                    getText = in.readLine();
                    testText.setText(getText);
                } catch (IOException e) {
                    testText.setText("Error");
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            // ignore
                        }
                    }
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false);
    }

    private void saveDataTest(){
        try {
            FileOutputStream outputStream = getActivity().openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            testText.setText("error");
        }
    }

    public void addColor(ColorItem color) {
        colors.add(color);
        adapter.notifyDataSetChanged();
    }

    private List<ColorItem> getColors() {
        return colors;
    }

    public static class ColorItemListAdapter extends BaseAdapter {

        private LibraryFragment context;

        public ColorItemListAdapter(LibraryFragment fragment) {
            this.context = fragment;
        }

        @Override
        public int getCount() {
            return context.colors.size();
        }

        @Override
        public Object getItem(int i) {
            return context.colors.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View listItem = context.getActivity().getLayoutInflater().inflate(R.layout.color_item, null);
            ImageView box = listItem.findViewById(R.id.imageView);
            ColorItem color = context.getColors().get(i);
            PaintDrawable paintDrawable = new PaintDrawable(Color.rgb(color.getR(), color.getG(), color.getB()));
            paintDrawable.setCornerRadius((float) 12.5);
            //paintDrawable.setShape(new RoundRectShape());
            box.setBackground(paintDrawable);
            ((TextView)listItem.findViewById(R.id.textView)).setText(color.getHexValue());

            return listItem;
        }
    }

//    public void saveColor(){
//        ColorItemListAdapter c = null;
//        Integer count = c.getCount();
//        String savedColorId = count.toString();
//        try {
//            FileOutputStream outputStream = getActivity().openFileOutput(savedColorId, Context.MODE_PRIVATE);
//            outputStream.write(fileContents.getBytes());
//            outputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            testText.setText("error");
//        }
//    }

}
