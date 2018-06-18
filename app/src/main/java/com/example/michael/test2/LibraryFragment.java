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
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class LibraryFragment extends Fragment implements ColorProvider {

    private static final String filename = "myfile";
    private static final String COLORS_FILE = "colors";
    private static final String ERROR = "Error";

    private String getText;
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

        gridView = (ExpandableHeightGridView) view.findViewById(R.id.gridView);

        gridView.setExpanded(true);

        adapter = new ColorItemListAdapter(this);
        gridView.setAdapter(adapter);

//        showText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                BufferedReader in = null;
//                try {
//                    in = new BufferedReader(new InputStreamReader(getActivity().openFileInput(filename)));
//                    getText = in.readLine();
//                    testText.setText(getText);
//                } catch (IOException e) {
//                    testText.setText(ERROR);
//                } finally {
//                    if (in != null) {
//                        try {
//                            in.close();
//                        } catch (IOException e) {
//                            // ignore
//                        }
//                    }
//                }
//            }
//        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false);
    }

//    private void saveDataTest(){
//        try {
//            FileOutputStream outputStream = getActivity().openFileOutput(filename, Context.MODE_PRIVATE);
//            outputStream.write(fileContents.getBytes());
//            outputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            testText.setText(ERROR);
//        }
//    }

    public void addColor(ColorItem color) {
        colors.add(color);
        adapter.notifyDataSetChanged();
    }

    public List<ColorItem> getColors() {
        return colors;
    }

    @Override
    public ColorItem getColor(int index) {
        if (colors.isEmpty()) return null;

        int i = index < 0 ? colors.size() + index : index;
        if (i < 0 || i >= colors.size()) i = colors.size() - 1;
        return colors.get(i);
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
            String text = color.getHexValue();
            if (color.getName() != null && color.getName().length() > 0) {
                text = color.getName();
            }
            ((TextView)listItem.findViewById(R.id.textView)).setText(text);

            return listItem;
        }
    }

    @Override
    public void onStop() {
        save();
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        load();
        adapter.notifyDataSetChanged();
    }

    private void load() {
        colors.clear();
        try {
            BufferedReader reader = new BufferedReader( new InputStreamReader(getActivity().openFileInput(COLORS_FILE)));
            String line;
            while((line = reader.readLine()) != null) {
                loadColor(line);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    private void loadColor(String line) {
        ColorItem item = null;
        String fields[] = line.split(",");
        if (fields.length != 2) return;
        String hexValue = fields[1];
        if (hexValue.length() != 7 || !hexValue.startsWith("#")) return;
        try {
            int r = Integer.valueOf(hexValue.substring(1, 3), 16);
            int g = Integer.valueOf(hexValue.substring(3, 5), 16);
            int b = Integer.valueOf(hexValue.substring(5, 7), 16);
            item = new ColorItem(r, g, b);
            item.setName(fields[0]);
        } catch (NumberFormatException nfe) {
            return;
        }
        colors.add(item);
    }

    private void save(){
        try {
            FileOutputStream outputStream = getActivity().openFileOutput(COLORS_FILE, Context.MODE_PRIVATE);
            Writer buf = new BufferedWriter(new OutputStreamWriter(outputStream));
            for (ColorItem color : colors) {
                buf.write(color.getName() + "," + color.getHexValue() + "\n");
            }
            buf.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void applyColorName(String colorName){
        colors.get(colors.size() - 1).setName(colorName);
        adapter.notifyDataSetChanged();
    }



}
