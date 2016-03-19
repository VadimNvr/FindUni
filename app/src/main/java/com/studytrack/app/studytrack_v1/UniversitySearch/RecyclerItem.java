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
    private double cost;
    private double average_mark;

    // TODO: 14.03.2016 rewrite 
    public RecyclerItem(String _iconId, String _name, String _location, double _cost, double _average_mark)
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

    public Double getCost() { return cost; }

    public String getViewableMark() {
        if (this.average_mark == 0.0) {
            return "-";
        } else {
            return Double.toString(average_mark);
        }

    }

    public String getViewableCost() {
        if (Double.toString(cost).equals("0.0")) {
            return "-";
        } else {
            return Double.toString(cost);
        }
    }

    public Double getMark() { return average_mark; }
}
