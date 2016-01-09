package com.studytrack.app.studytrack_v1.ExpandableListView;

import com.mikepenz.fontawesome_typeface_library.FontAwesome.Icon;

import java.util.ArrayList;

/**
 * Created by vadim on 08.01.16.
 */
public class IExpandableGroup {
    String text;
    ArrayList<String> items;
    Icon icon;

    public IExpandableGroup() {
        text = "";
        items = new ArrayList<>();
    }

    public IExpandableGroup withTitle(String _text) {
        text = _text;
        return this;
    }

    public IExpandableGroup withItems(ArrayList<String> _items) {
        items = _items;
        return this;
    }

    public IExpandableGroup withIcon(Icon _icon) {
        icon = _icon;
        return this;
    }
}
