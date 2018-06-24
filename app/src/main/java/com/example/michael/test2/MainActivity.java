package com.example.michael.test2;

import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ColorSaveListener, NameDialog.NameDialogListener {

    private boolean navigationVisible = true;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PageAdapter pageAdapter;
    private TabItem tabMain;
    private TabItem tabLibrary;
    private TabItem tabTest;
    private int iconColor;
    private int iconColorSelected;

    private Spinner spinner1;
    private Spinner spinner2;

    private ArrayList<ColorItemForSpinner> mColor4spinnerList;
    private CustomSpinnerAdapter mAdapter;

//  private Toast hexCopy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        tabMain = findViewById(R.id.tabMain);
        tabLibrary = findViewById(R.id.tabLibrary);
        tabTest = findViewById(R.id.tabTest);
        viewPager = findViewById(R.id.viewPager);

        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        iconColor = Color.rgb(137,137,137);
        iconColorSelected = Color.rgb(245,245,245);

//        hexCopy = Toast.makeText(MainActivity.this, "Hex Code Copied", Toast.LENGTH_SHORT);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 1){
                    //Library fragment
                }else if (tab.getPosition() == 2){
                    //Test fragment
                }else {
                    //Main fragment
                }
                tab.getIcon().setTint(iconColorSelected);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setTint(iconColor);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener((tabLayout)));

    }

    private void hideNavigationBar() {
        if (!navigationVisible) return;
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
        decorView.setSystemUiVisibility(uiOptions);
        navigationVisible = false;

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    public void onColorSave(ColorItem item) {
        LibraryFragment libraryFragment = pageAdapter.getLibrary();
        libraryFragment.addColor(item);
    }


    @Override
    public void applyName(String colorName) {
        LibraryFragment libraryFragment = pageAdapter.getLibrary();
        ColorItem colorItem = pageAdapter.getColorPicker().getColorItem();
        colorItem.setName(colorName);
        libraryFragment.addColor(colorItem);
        pageAdapter.getColorPicker().animateSaveButton();
    }
}
