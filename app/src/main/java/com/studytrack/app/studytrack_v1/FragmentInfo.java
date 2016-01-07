package com.studytrack.app.studytrack_v1;

import android.support.v4.app.Fragment;

/**
 * Created by vadim on 03.01.16.
 */
public class FragmentInfo {
    Fragment fragment;
    int titleId;

    FragmentInfo(Fragment _fragment, int _titleId)
    {
        fragment = _fragment;
        titleId = _titleId;
    }
}
