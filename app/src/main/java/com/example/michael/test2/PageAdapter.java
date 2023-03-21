package com.example.michael.test2;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter{

    private int numOfTabs;
    private LibraryFragment library;
    private MainFragment picker;
    private ColorMixFragment colorMix;
    private PhotoPickerFragment photoPicker;

    PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
        library = new LibraryFragment();
        picker = new MainFragment();
        colorMix = new ColorMixFragment();
        photoPicker = new PhotoPickerFragment();
        colorMix.setLibrary(library);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return picker;
            case 1:
                return library;
            case 2:
                return colorMix;
            case 3:
                return photoPicker;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return  numOfTabs;
    }

    public LibraryFragment getLibrary() {
        return library;
    }

    public MainFragment getColorPicker() {
        return picker;
    }
}
