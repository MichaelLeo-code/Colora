package com.example.michael.test2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter{

    private int numOfTabs;
    private LibraryFragment library;
    private MainFragment picker;
    private ColorMixFragment colorMix;

    PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
        library = new LibraryFragment();
        picker = new MainFragment();
        colorMix = new ColorMixFragment();
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
