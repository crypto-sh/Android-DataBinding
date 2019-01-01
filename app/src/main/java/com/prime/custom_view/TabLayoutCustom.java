package com.prime.custom_view;


import android.content.Context;
import android.graphics.Typeface;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import com.prime.utils.PublicFunction;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.ViewGroup;

import java.io.FileNotFoundException;


/**
 * Created by alishatergholi on 11/30/16.
 */
public class TabLayoutCustom extends TabLayout {

    Context context;

    final String TAG = TabLayoutCustom.class.getSimpleName();

    public TabLayoutCustom(Context context) {
        super(context);
        this.context = context;
        setTabLayoutConfig();

    }

    public TabLayoutCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setTabLayoutConfig();
    }

    public TabLayoutCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setTabLayoutConfig();
    }

    private void setTabLayoutConfig(){
        //this.setLayoutDirection(LAYOUT_DIRECTION_LTR);
    }

    @Override
    public void setupWithViewPager(@Nullable ViewPager viewPager) {
        super.setupWithViewPager(viewPager);
        this.removeAllTabs();
        ViewGroup slidingTabStrip = (ViewGroup) getChildAt(0);
        PagerAdapter adapter = viewPager.getAdapter();
        for (int i = 0, count = adapter.getCount(); i < count; i++) {
            Tab tab = this.newTab();
            this.addTab(tab.setText(adapter.getPageTitle(i)));
            TextViewCustom view = (TextViewCustom) ((ViewGroup) slidingTabStrip.getChildAt(i)).getChildAt(1);
            try {
                view.setTypeface(PublicFunction.getTypeFace(context), Typeface.NORMAL);
            } catch (FileNotFoundException e) {
                PublicFunction.LogData(false,TAG,e.getMessage());
            }
        }
    }
}