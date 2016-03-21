package com.studytrack.app.studytrack_v1.UniversitySearch;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.rey.material.widget.ProgressView;
import com.studytrack.app.studytrack_v1.Fab;
import com.studytrack.app.studytrack_v1.R;
import com.studytrack.app.studytrack_v1.UniversitySearch.Filters.FilterFragment;
import com.studytrack.app.studytrack_v1.UniversitySearch.University.UniversityFragment;
import com.studytrack.app.studytrack_v1.Utils.Animator;
import com.studytrack.app.studytrack_v1.myFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import Entities.Region;
import Entities.Town;
import Entities.University;
import Requests.GetRegionsRequest;
import Requests.GetTownsRequest;
import Requests.GetUniversitiesRequest;

/**
 * Created by vadim on 03.01.16.
 */
public class SearchFragment extends myFragment {
    protected AppCompatActivity activity;
    protected View fragment;
    protected myFragment cur_frag;
    protected Toolbar toolbar;
    protected ActionBar actionbar;
    protected ProgressView progress;

    protected Fab fab;
    protected MaterialSheetFab materialSheetFab;
    protected View[] sheetItems;

    protected RecyclerView university_recycler;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup _container,
                             Bundle savedInstanceState) {
        return fragment = inflater.inflate(R.layout.fragment_search, _container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new FragmentLoader(true).execute();
    }

    @Override
    public boolean onBackPressed() {
        if ((cur_frag != null) && cur_frag.isAdded() && cur_frag.onBackPressed()) {
            return true;
        }
        else if (materialSheetFab.isSheetVisible()) {
            materialSheetFab.hideSheet();
            return true;
        }
        else return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        super.onCreateOptionsMenu(menu, inflater);
        // Inflate the menu; this adds items to the action bar if it is present.

        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_filter).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                cur_frag = new FilterFragment();
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.main_fragment, cur_frag)
                        .commit();
                return true;
        }

        return false;
    }

    private class FragmentLoader extends AsyncTask<Void, Void, Void> {
        private ArrayList<University> listItems = new ArrayList<>();
        private boolean loadFavorite;
        GetUniversitiesRequest request;

        public FragmentLoader(boolean loadFavorite) {
            this.loadFavorite = loadFavorite;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            initActivity();
            initProgress();
            progress.start();
            initToolbar();
            initSheetFab();
            initRecycler();
            initProgress();

            GetRegionsRequest regionsRequest = new GetRegionsRequest(activity);
            regionsRequest.execute();
            List<Region> regions = null;
            try {
                regions = regionsRequest.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            GetTownsRequest townsRequest = new GetTownsRequest(activity, regions.get(0));
            townsRequest.execute();
            List<Town> towns = null;
            try {
                towns = townsRequest.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            this.request = new GetUniversitiesRequest(activity, towns.get(0),0, 10);
            this.request.execute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            List<University> universities = null;
            try {
                universities = this.request.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            listItems.addAll(universities);
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            progress.stop();

            View.OnClickListener ocl = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cur_frag = new UniversityFragment();

                    int itemPosition = university_recycler.getChildAdapterPosition(view);
                    University university = listItems.get(itemPosition - 1);

                    putData(cur_frag, university);
                    activity.getSupportFragmentManager()
                            .beginTransaction()
                            //.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                            .addToBackStack(null)
                            .replace(R.id.main_fragment, cur_frag)
                            .commit();
                }
            };

            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(activity, listItems, ocl);
            university_recycler.setAdapter(recyclerAdapter);
        }

        //Initialising methods
        private void initActivity() {
            activity = (AppCompatActivity) getActivity();
        }

        private void initToolbar() {
            toolbar   = (Toolbar) activity.findViewById(R.id.main_toolbar);
            actionbar = activity.getSupportActionBar();

            actionbar.setTitle("Университеты");

            toolbar.setNavigationIcon(R.drawable.menu);
            toolbar.setTranslationY(0);
            Animator.animAlpha(toolbar, 150, View.VISIBLE);

            //Animator.animY(toolbar, 150, 0, 0 - (int) toolbar.getTranslationY(), true);
        }

        private void initSheetFab() {
            View sheetView = fragment.findViewById(R.id.fab_sheet);
            View overlay   = fragment.findViewById(R.id.overlay);
            int сolor = getResources().getColor(R.color.backgroundColorPrimary);

            fab = (Fab) fragment.findViewById(R.id.fab);
            materialSheetFab = new MaterialSheetFab<>(fab, sheetView, overlay, сolor, сolor);

            sheetItems = new View[2];
            sheetItems[0] = fragment.findViewById(R.id.fab_sheet_item_filter);
            sheetItems[1] = fragment.findViewById(R.id.fab_sheet_item_sort);

            sheetItems[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    materialSheetFab.hideSheet();
                    cur_frag = new FilterFragment();
                    getFragmentManager().beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.main_fragment, cur_frag)
                            .commit();
                }
            });
        }

        private void initRecycler() {
            university_recycler = (RecyclerView) fragment.findViewById(R.id.university_list);
            university_recycler.setLayoutManager(new LinearLayoutManager(activity));

            university_recycler.setOnScrollListener(new HidingScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    toolbar.setTranslationY(Math.min(
                            0.0f,
                            Math.max(toolbar.getTranslationY() - dy, -3 * getResources().getDimensionPixelSize(R.dimen.toolbar_height))
                    ));
                }

                @Override
                public void onHide() {
                    fab.hide_down();
                }

                @Override
                public void onShow() {
                    fab.show_up();
                }
            });
        }

        private void initProgress() {
            progress = (ProgressView) activity.findViewById(R.id.progress);
        }

        private void putData(myFragment frag, University data) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("data", data);
            frag.setArguments(bundle);
        }
    }
}
