package com.studytrack.app.studytrack_v1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

/**
 * Created by vadim on 09.01.16.
 */
public class FilterFragment extends myFragment {
    final int PAGES_COUNT = 3;
    myFragment[] pages;
    String[] page_titles;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pages = new FilterItemFragment[] {
                new FilterItemFragment(),
                new FilterItemFragment(),
                new FilterItemFragment()
        };

        page_titles = new String[] {
                "Города",
                "Специальности",
                "Баллы ЕГЭ"
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);

        // Initialize the ViewPager and set an adapter
        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(new TestAdapter(getActivity().getSupportFragmentManager()));

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        tabs.setViewPager(pager);
        return view;
    }

    private class TestAdapter extends FragmentPagerAdapter {

        public TestAdapter(FragmentManager fm) {
            super(fm);
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
}
