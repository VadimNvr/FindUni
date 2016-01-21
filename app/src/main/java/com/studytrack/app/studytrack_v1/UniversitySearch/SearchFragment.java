package com.studytrack.app.studytrack_v1.UniversitySearch;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.gordonwong.materialsheetfab.MaterialSheetFabEventListener;
import com.studytrack.app.studytrack_v1.Fab;
import com.studytrack.app.studytrack_v1.R;
import com.studytrack.app.studytrack_v1.SerializeObject;
import com.studytrack.app.studytrack_v1.UniversitySearch.Filters.FilterFragment;
import com.studytrack.app.studytrack_v1.myFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by vadim on 03.01.16.
 */
public class SearchFragment extends myFragment {
    protected Activity activity;
    protected View fragment;
    protected FilterFragment filter;
    protected View toolbar;

    protected Fab fab;
    protected MaterialSheetFab materialSheetFab;
    protected View[] sheetItems;

    protected RecyclerView university_recycler;

    //protected View.OnClickListener sheetFabListener;
    //protected ListView.OnScrollListener onScrollListener;

    public SearchFragment() {

        /*
        onScrollListener = new ListView.OnScrollListener() {
            private int mLastFirstVisibleItem;

            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                //Scrolling down
                if (mLastFirstVisibleItem < firstVisibleItem) {
                    fab.hide_down();
                }
                //Scrolling up
                if (mLastFirstVisibleItem > firstVisibleItem) {
                    fab.show_up();
                }
                mLastFirstVisibleItem = firstVisibleItem;
            }
        };
        */
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new InitFrag().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup _container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, _container, false);
    }


    public void onPause() {
        super.onPause();
        toolbar.setTranslationY(0);
    }


    @Override
    public boolean onBackPressed() {
        if (filter.isAdded() && filter.onBackPressed()) {
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("ВУЗы");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.main_fragment, new FilterFragment())
                        .commit();
                return true;
        }

        return false;
    }

    private void Init() {
        /*
        sheetFabListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.fab_sheet_item_filter:
                        materialSheetFab.hideSheet();
                        filter = new FilterFragment();
                        getFragmentManager().beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.main_fragment, filter)
                                .commit();
                        break;
                    default:
                        Toast.makeText(getActivity(), "Coming soon", Toast.LENGTH_SHORT).show();
                }
            }
        };
        */

        //activity.findViewById(R.id.fab_sheet_item_filter).setOnClickListener(sheetFabListener);
        //activity.findViewById(R.id.fab_sheet_item_sort).setOnClickListener(sheetFabListener);
    }

    private class InitFrag extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progressDialog;
        private ArrayList<RecyclerItem> listItems;

        private void initRecycler() {
            university_recycler = (RecyclerView) fragment.findViewById(R.id.university_list);
            university_recycler.setLayoutManager(new LinearLayoutManager(activity));

            university_recycler.setOnScrollListener(new HidingScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    toolbar.setTranslationY(Math.min(
                            0.0f,
                            Math.max(toolbar.getTranslationY() - dy, -3*getResources().getDimensionPixelSize(R.dimen.toolbar_height))
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
                    filter = new FilterFragment();
                    getFragmentManager().beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.main_fragment, filter)
                            .commit();
                }
            });
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            activity = getActivity();
            fragment = activity.findViewById(R.id.main_fragment);
            toolbar = activity.findViewById(R.id.toolbar_actionbar);
            progressDialog = new ProgressDialog(activity);
            initSheetFab();
            initRecycler();
        }

        @Override
        protected Void doInBackground(Void... params) {
/*
            listItems = new ArrayList<>();

            for (int univ_id = 0; univ_id < 20; ++univ_id) {
                listItems.add(new RecyclerItem(
                                R.drawable.msu_logo,
                                "Московский государственный университет им. М.В. Ломоносова",
                                "Москва",
                                "250 000р.",
                                87.7f)
                );
            }
            return null;
            */

            //Loading from memory
            //listItems = (ArrayList<RecyclerItem>) SerializeObject.ReadSettings(activity, "ulist.dat");
            if (listItems == null) {
                listItems = new ArrayList<>();
            }
            else {
                //Wait for drawer
                return null;
            }

            //Else load from server
            try {
                URL url = new URL("http://finduniv.appspot.com/getUniversities?count=20");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoInput(true);
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null)
                    buffer.append(line);

                urlConnection.disconnect();

                int[] logos = new int[] {
                        R.drawable.msu_logo,
                        R.drawable.vse_logo,
                        R.drawable.mipt_logo,
                        R.drawable.mgimo_logo,
                        R.drawable.lingv_logo
                };

                try {
                    JSONArray ar = new JSONArray(buffer.toString());
                    for (int univ_id = 0; univ_id < ar.length(); ++univ_id) {
                        JSONObject univ = ar.getJSONObject(univ_id);
                        listItems.add(new RecyclerItem(
                                logos[univ_id % 5],
                                univ.getString("name"),
                                "Москва",
                                "250 000р.",
                                87.7f)
                        );
                    }

                    SerializeObject.WriteSettings(activity, listItems, "ulist.dat");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(activity, listItems);
            university_recycler.setAdapter(recyclerAdapter);
        }
    }
}
