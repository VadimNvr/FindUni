package com.studytrack.app.studytrack_v1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.gordonwong.materialsheetfab.MaterialSheetFabEventListener;

import java.util.ArrayList;

/**
 * Created by vadim on 03.01.16.
 */
public class SearchFragment extends myFragment {
    private UniListAdapter searchListAdapter;
    private ArrayList<UniListItem> listItems;
    private ListView listView;
    private MaterialSheetFab materialSheetFab;
    private Fab fab;
    private FilterFragment filterFragment;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initListView();
        filterFragment = new FilterFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        initFab(view);
        return view;
    }

    private void initFab(View container) {
        fab = (Fab) container.findViewById(R.id.fab);
        View sheetView = container.findViewById(R.id.fab_sheet);
        View overlay = container.findViewById(R.id.overlay);

        int sheetColor = getResources().getColor(R.color.backgroundColorPrimary);
        int fabColor = getResources().getColor(R.color.banana);

        materialSheetFab = new MaterialSheetFab<>(fab, sheetView, overlay,
                sheetColor, fabColor);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.fab_sheet_item_filter:
                        materialSheetFab.hideSheet();
                        getFragmentManager().beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.main_fragment, filterFragment)
                                .commit();
                        break;
                    default:
                        Toast.makeText(getActivity(), "Coming soon", Toast.LENGTH_SHORT).show();
                }
            }
        };

        container.findViewById(R.id.fab_sheet_item_filter).setOnClickListener(listener);
        container.findViewById(R.id.fab_sheet_item_sort).setOnClickListener(listener);
    }

    @Override
    public boolean onBackPressed() {
        if (filterFragment.isAdded() && filterFragment.onBackPressed()) {
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

    @Override
    public void putData(Bundle _data)
    {
        try
        {
            listItems = (ArrayList<UniListItem>) _data.getSerializable("uni_array");
        }
        catch (Exception e) {}

        if (listItems != null)
            Log.d("myFragments", "Data Size: " + listItems.size());
        else
            Log.d("myFragments", "Data Size: empty");

    }

    @Override
    public void Refresh()
    {
        searchListAdapter.updateData(listItems);
        searchListAdapter.notifyDataSetChanged();
    }

    private void initListView() {

        if (listItems == null)
            listItems = new ArrayList<>();

        searchListAdapter = new UniListAdapter(getActivity(), listItems);
        listView = (ListView) getActivity().findViewById(R.id.search_list_111);
        listView.setAdapter(searchListAdapter);

        listView.setOnScrollListener(new ListView.OnScrollListener() {
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
        });
    }
}
