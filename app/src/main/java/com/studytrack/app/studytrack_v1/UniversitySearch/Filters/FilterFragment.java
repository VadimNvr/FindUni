package com.studytrack.app.studytrack_v1.UniversitySearch.Filters;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.studytrack.app.studytrack_v1.UniversitySearch.Filters.Adapters.MainPagerAdapter;
import com.studytrack.app.studytrack_v1.R;
import com.studytrack.app.studytrack_v1.myFragment;

/**
 * Created by vadim on 09.01.16.
 */
public class FilterFragment extends myFragment {
    protected View toolbar;
    protected ViewPager pager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
/*
        searchView = (MaterialSearchView) getActivity().findViewById(R.id.search_view);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TranslateAnimation anim_tr = new TranslateAnimation(0, 0, 0,
                        -getResources().getDimensionPixelSize(R.dimen.toolbar_height));

                anim_tr.setDuration(150);
                anim_tr.setInterpolator(AnimationUtils.loadInterpolator(getContext(), R.interpolator.msf_interpolator));
                getActivity().findViewById(R.id.filter_screen).startAnimation(anim_tr);
                getActivity().findViewById(R.id.filter_screen).setVisibility(View.INVISIBLE);
                searchView.showSearch(true);
            }
        });
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
            }

            @Override
            public void onSearchViewClosed() {
                TranslateAnimation anim_tr = new TranslateAnimation(0, 0,
                        -getResources().getDimensionPixelSize(R.dimen.toolbar_height), 0);

                anim_tr.setDuration(150);
                anim_tr.setInterpolator(AnimationUtils.loadInterpolator(getContext(), R.interpolator.msf_interpolator));
                getActivity().findViewById(R.id.filter_screen).startAnimation(anim_tr);
                getActivity().findViewById(R.id.filter_screen).setVisibility(View.VISIBLE);
            }
        });

        searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        */

        //SearchAdapter adapter = new SearchAdapter(getActivity());
        //searchView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);
        toolbar = getActivity().findViewById(R.id.main_toolbar);

        // Initialize the ViewPager and set an adapter
        pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(new MainPagerAdapter(getActivity().getSupportFragmentManager()));

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        tabs.setTranslationY(toolbar.getTranslationY() + getResources().getDimensionPixelSize(R.dimen.toolbar_height));

        tabs.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf"),
                Typeface.BOLD);

        tabs.setViewPager(pager);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        super.onCreateOptionsMenu(menu, inflater);
        // Inflate the menu; this adds items to the action bar if it is present.

        MenuItem search = menu.findItem(R.id.action_search);
        //searchView.setMenuItem(search);

        //search.setVisible(true);
        menu.findItem(R.id.action_filter).setVisible(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Фильтры");

        menu.findItem(R.id.action_accept).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                ((MainPagerAdapter) pager.getAdapter()).initAccept();
                getActivity().getSupportFragmentManager().popBackStack();
                return true;
            }
        });
    }

    @Override
    public boolean onBackPressed() {
        //if (searchView.isSearchOpen()) {
        //    searchView.closeSearch();
        //    return true;
        //}
        //else
            return false;
    }
}
