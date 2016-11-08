package com.phile.yrj.takethebullfighterwiththehorns.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.phile.yrj.takethebullfighterwiththehorns.GalleryTab_Fragment;
import com.phile.yrj.takethebullfighterwiththehorns.NewsTab_Fragment;
import com.phile.yrj.takethebullfighterwiththehorns.RankingTab_Fragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                NewsTab_Fragment tab1 = new NewsTab_Fragment();
                return tab1;
            case 1:
                RankingTab_Fragment tab2 = new RankingTab_Fragment();
                return tab2;
            case 2:
                GalleryTab_Fragment tab3 = new GalleryTab_Fragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}