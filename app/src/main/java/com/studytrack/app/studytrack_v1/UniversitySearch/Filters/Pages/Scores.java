package com.studytrack.app.studytrack_v1.UniversitySearch.Filters.Pages;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studytrack.app.studytrack_v1.R;
import com.studytrack.app.studytrack_v1.myFragment;

/**
 * Created by vadim on 14.01.16.
 */
public class Scores extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.filter_item_fragment, container, false);
    }
}