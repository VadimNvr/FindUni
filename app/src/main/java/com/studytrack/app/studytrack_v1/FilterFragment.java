package com.studytrack.app.studytrack_v1;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;

import com.astuetz.PagerSlidingTabStrip;
import com.search.material.library.MaterialSearchView;

/**
 * Created by vadim on 09.01.16.
 */
public class FilterFragment extends myFragment {
    final int PAGES_COUNT = 3;
    private String[] page_titles;
    private FilterItemFragment[] pages;
    private MaterialSearchView searchView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

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

        searchView = (MaterialSearchView) getActivity().findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
                anim.setDuration(150);
                anim.setInterpolator(AnimationUtils.loadInterpolator(getContext(), R.interpolator.msf_interpolator));
                getActivity().findViewById(R.id.filter_screen).startAnimation(anim);
                getActivity().findViewById(R.id.filter_screen).setVisibility(View.INVISIBLE);
            }

            @Override
            public void onSearchViewClosed() {
                AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
                anim.setDuration(150);
                anim.setInterpolator(AnimationUtils.loadInterpolator(getContext(), R.interpolator.msf_interpolator));
                getActivity().findViewById(R.id.filter_screen).startAnimation(anim);
                getActivity().findViewById(R.id.filter_screen).setVisibility(View.VISIBLE);
            }
        });

        searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        SearchAdapter adapter = new SearchAdapter(getActivity());
        searchView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);

        // Initialize the ViewPager and set an adapter
        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(new FilterPagerAdapter(getActivity().getSupportFragmentManager(),
                PAGES_COUNT, pages, page_titles));

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        //Typeface style = Typeface.

        tabs.setTypeface(font, Typeface.BOLD);
        tabs.setViewPager(pager);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        super.onCreateOptionsMenu(menu, inflater);
        // Inflate the menu; this adds items to the action bar if it is present.

        MenuItem search = menu.findItem(R.id.action_search);
        searchView.setMenuItem(search);

        search.setVisible(true);
        menu.findItem(R.id.action_filter).setVisible(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Фильтры");
    }

    @Override
    public boolean onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
            return true;
        }
        else
            return false;
    }
}
