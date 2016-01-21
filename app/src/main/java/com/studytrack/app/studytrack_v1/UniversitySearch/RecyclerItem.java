package com.studytrack.app.studytrack_v1.UniversitySearch;

import java.io.Serializable;

/**
 * Created by vadim on 04.01.16.
 */
public class RecyclerItem implements Serializable {
    private String name;
    private int iconId;
    private String location;
    private String cost;
    private float average_mark;

    public RecyclerItem(int _iconId, String _name, String _location, String _cost, float _average_mark)
    {
        name = _name;
        iconId = _iconId;
        location = _location;
        average_mark = _average_mark;
        cost = _cost;
    }

    public String getName() { return name; }

    public int getIconId() { return iconId; }

    public String getLocation() { return location; }

    public String getCost() { return cost; }

    public float getMark() { return average_mark; }
}
