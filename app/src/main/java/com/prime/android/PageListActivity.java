package com.prime.android;


import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.gson.Gson;
import com.prime.baseClass.BaseActivity;
import com.prime.fragment.ListFragment;

import com.prime.R;
import com.prime.databinding.ActivityPageListBinding;

import com.prime.model.PageDataParcelable;


public class PageListActivity extends BaseActivity {

    ActivityPageListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = setContentView(PageListActivity.this, R.layout.activity_page_list);

        getPageData();
    }

    private void getPageData() {
        PageDataParcelable data = new Gson().fromJson(loadLocalData("home.json"), PageDataParcelable.class);
        setData(data);
    }

    private void setData(PageDataParcelable homeData) {
        binding.setAdapterHandler(new pagerAdapter(homeData, getSupportFragmentManager()));
        binding.setTabHandler(binding.pager);

    }

    public class pagerAdapter extends FragmentStatePagerAdapter {

        PageDataParcelable data;

        pagerAdapter(PageDataParcelable pageData, FragmentManager fragmentManager) {
            super(fragmentManager);
            this.data = pageData;
        }

        @Override
        public Fragment getItem(int position) {
            return ListFragment.newInstance(data);
        }

        @Override
        public int getCount() {
            if (data.pageData == null) {
                return 0;
            }
            return data.tabRow.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (data.tabRow == null || data.tabRow.size() < position) {
                return "";
            }
            return data.tabRow.get(position).getTitle();
        }
    }


}
