package com.prime.adapter;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.prime.fragment.ListFragment;
import com.prime.model.SeasonModel;

public class PagerSerialAdapter extends FragmentStatePagerAdapter {

    private List<SeasonModel> seasonModels;

    public PagerSerialAdapter(List<SeasonModel> seasons, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.seasonModels = seasons;
    }

    @Override
    public Fragment getItem(int position) {
        SeasonModel season = seasonModels.get(position);
        return ListFragment.newInstance(season.listParcelable);
    }

    @Override
    public int getCount() {
        if (seasonModels == null){
            return 0;
        }
        return seasonModels.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (seasonModels == null || seasonModels.size() < position){
            return "";
        }
        return seasonModels.get(position).getTitle();
    }


}
