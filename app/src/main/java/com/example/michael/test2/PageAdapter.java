package com.example.michael.test2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter{

    private int numOfTabs;
    private LibraryFragment library;

    PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
        library = new LibraryFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MainFragment();
            case 1:
                return library;
            case 2:
                return new TestFragment();
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
}
