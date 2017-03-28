package com.edu.tce.emr_sqlite.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by RaamKumr on 3/28/2017.
 */

public class viewpageradapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> tabTitle = new ArrayList<>();

    public void addFragmentsFragment (Fragment fragments,String tabTitles)
    {
        this.fragments.add(fragments);
        this.tabTitle.add(tabTitles);
    }
    public viewpageradapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle.get(position);
    }
}
