package com.studytrack.app.studytrack_v1.UniversitySearch;

import java.io.Serializable;

/**
 * Created by vadim on 04.01.16.
 */
public class RecyclerItem implements Serializable {
    private String name;
    // TODO: 14.03.2016 replace with string path of file 
    String iconId;
    private String location;
    private String cost;
    private float average_mark;

    // TODO: 14.03.2016 rewrite 
    public RecyclerItem(String _iconId, String _name, String _location, String _cost, float _average_mark)
    {
        name = _name;
        iconId = _iconId;
        location = _location;
        average_mark = _average_mark;
        cost = _cost;
    }

    public String getName() { return name; }

    public String getIconId() { return iconId; }

    public String getLocation() { return location; }

    public String getCost() { return cost; }

    public String getViewableMark() {
        if (this.average_mark == 0.0) {
            return "-";
        } else {
            return Float.toString(average_mark);
        }

    }

    public String getViewableCost() {
        if (cost.equals("0")) {
            return "-";
        } else {
            return cost;
        }
    }

    public float getMark() { return average_mark; }
}
