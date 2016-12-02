package com.phile.yrj.takethebullfighterwiththehorns.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.phile.yrj.takethebullfighterwiththehorns.GalleryTab_Fragment;
import com.phile.yrj.takethebullfighterwiththehorns.NewsTab_Fragment;
import com.phile.yrj.takethebullfighterwiththehorns.RankingTab_Fragment;

public class CustomPagerAdapter extends FragmentPagerAdapter {
    int mNumOfTabs;

    public CustomPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.mNumOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment tab = null;
        switch (position) {
            case 0:
                tab = new NewsTab_Fragment();
                break;
            case 1:
                tab = new RankingTab_Fragment();
                break;
            case 2:
                tab = new GalleryTab_Fragment();
                break;
        }
        return tab;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}