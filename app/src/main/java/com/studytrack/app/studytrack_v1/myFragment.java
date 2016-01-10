package com.studytrack.app.studytrack_v1;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by vadim on 06.01.16.
 */
public class myFragment extends Fragment {
    Bundle data = null;

    public void putData(Bundle _data)
    {
        data = _data;
    }

    public void Refresh()
    {
    }

    public boolean onBackPressed() {
        return false;
    }
}
