package com.studytrack.app.studytrack_v1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by vadim on 03.01.16.
 */
public class SearchFragment extends myFragment {
    UniListAdapter searchListAdapter;
    ArrayList<UniListItem> listItems;
    ListView listView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Log.d("myFragments", "SearchFrag: onAttach");
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("myFragments", "SearchFrag: onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("myFragments", "SearchFrag: onCreateView");
        return inflater.inflate(R.layout.fragment_search, container, false);

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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d("myFragments", "SearchFrag: onActivityCreated");
        if (listItems == null)
            listItems = new ArrayList<>();

        searchListAdapter = new UniListAdapter(getActivity(), listItems);
        listView = (ListView) getActivity().findViewById(R.id.search_list_111);
        listView.setAdapter(searchListAdapter);
    }


    public void onStart() {
        super.onStart();
        Log.d("myFragments", "SearchFrag: onStart");
    }

    public void onResume() {
        super.onResume();
        Log.d("myFragments", "SearchFrag: onResume");
    }

    public void onPause() {
        super.onPause();
        Log.d("myFragments", "SearchFrag: onPause");
    }

    public void onStop() {
        super.onStop();
        Log.d("myFragments", "SearchFrag: onStop");
    }

    public void onDestroyView() {
        super.onDestroyView();
        Log.d("myFragments", "SearchFrag: onDestroyView");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d("myFragments", "SearchFrag: onDestroy");
    }

    public void onDetach() {
        super.onDetach();
        Log.d("myFragments", "SearchFrag: onDetach");
    }



}
