package com.studytrack.app.studytrack_v1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by vadim on 10.01.16.
 */
public class FilterPagerAdapter extends FragmentPagerAdapter {
    private int PAGES_COUNT;
    private FilterItemFragment[] pages;
    private String[] page_titles;

    public FilterPagerAdapter(FragmentManager fm, int count, FilterItemFragment[] _pages, String[] titles) {
        super(fm);
        PAGES_COUNT = count;
        pages = _pages;
        page_titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return pages[position];
    }

    @Override
    public int getCount() {
        return PAGES_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return page_titles[position];
    }
}