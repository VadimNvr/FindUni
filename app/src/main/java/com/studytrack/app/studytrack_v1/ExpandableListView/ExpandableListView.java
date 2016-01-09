package com.studytrack.app.studytrack_v1.ExpandableListView;

import android.content.Context;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by vadim on 08.01.16.
 */
public class ExpandableListView extends android.widget.ExpandableListView {

    //View parentView;
    Context ctx;

    private ArrayList<String> headers;
    private HashMap<String, ArrayList<String>> children;

    public ExpandableListView(Context context) {
        super(context);
        ctx = context;

        headers = new ArrayList<>();
        children  = new HashMap<>();
    }

    public ExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctx = context;

        headers = new ArrayList<>();
        children  = new HashMap<>();
    }

    public ExpandableListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        ctx = context;

        headers = new ArrayList<>();
        children  = new HashMap<>();
    }

    public ExpandableListView withGroups(IExpandableGroup... groups)
    {
        for (IExpandableGroup group : groups) {
            headers.add(group.text);
            children.put(group.text, group.items);
        }
        return this;
    }

    public ExpandableListView withGroups(ArrayList<IExpandableGroup> groups)
    {
        for (IExpandableGroup group : groups) {
            headers.add(group.text);
            children.put(group.text, group.items);
        }
        return this;
    }

    public ExpandableListView build() {

        ExpandableListAdapter adapter = new ExpandableListAdapter(
                ctx,
                headers,
                children);

        this.setAdapter(adapter);
        return this;
    }
}
