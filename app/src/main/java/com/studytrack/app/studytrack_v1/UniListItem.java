package com.studytrack.app.studytrack_v1;

/**
 * Created by vadim on 04.01.16.
 */
public class UniListItem {
    String name;
    int iconId;
    String location;
    float average_mark;

    UniListItem(String _name, int _iconId, String _location, float _average_mark)
    {
        name = _name;
        iconId = _iconId;
        location = _location;
        average_mark = _average_mark;
    }
}
