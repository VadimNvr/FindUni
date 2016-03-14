package com.studytrack.app.studytrack_v1.UniversitySearch;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.studytrack.app.studytrack_v1.R;

import java.io.File;
import java.util.List;

/**
 * Created by vadim on 18.01.16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    private static final int TYPE_HEADER = 2;
    private static final int TYPE_ITEM = 1;
    private List<RecyclerItem> universities;
    private View.OnClickListener item_listener;

    public RecyclerAdapter(Activity _activity, List<RecyclerItem> _universities, View.OnClickListener ocl) {
        universities = _universities;
        context = _activity;
        item_listener = ocl;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(context).inflate(R.layout.recycler_header, parent, false);
            return new RecyclerHeaderViewHolder(v);
        }
        else {
            View v = LayoutInflater.from(context).inflate(R.layout.unisearch_list_item, parent, false);
            v.setOnClickListener(item_listener);
            return new RecyclerItemViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int pos) {
        if (!isPositionHeader(pos)) {
            RecyclerItem uni = universities.get(pos-1);
            RecyclerItemViewHolder holder = (RecyclerItemViewHolder) viewHolder;
            holder.name.setText(uni.getName());
            holder.location.setText(uni.getLocation());
            holder.average_mark.setText(uni.getViewableMark());
            File image = new File(uni.getIconId());
            Log.i("PathEx",uni.getIconId());
            Log.i("Ex", Boolean.toString(image.exists()));
            // TODO: 15.03.2016 переделать на норм лого если нет лого
            if(image.exists()) {
                Picasso.with(context).load(image).resize(300, 300).into(holder.logo);
            }
            else {
                Picasso.with(context).load(R.drawable.mgimo_logo).resize(300, 300).into(holder.logo);
            }
            holder.cost.setText(uni.getViewableCost());
        }
    }

    @Override
    public int getItemCount() {
        return universities.size() + 1; //count + header
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }
}
