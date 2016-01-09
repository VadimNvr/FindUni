package com.studytrack.app.studytrack_v1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by vadim on 09.01.16.
 */
public class FilterItemFragment extends myFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.filter_item_fragment, container, false);
        return view;
    }
}
