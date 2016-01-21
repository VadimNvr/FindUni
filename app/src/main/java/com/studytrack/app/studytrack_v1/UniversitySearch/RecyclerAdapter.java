package com.studytrack.app.studytrack_v1.UniversitySearch;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.studytrack.app.studytrack_v1.R;

import java.util.List;

/**
 * Created by vadim on 18.01.16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    private static final int TYPE_HEADER = 2;
    private static final int TYPE_ITEM = 1;
    private List<RecyclerItem> universities;
    Typeface typeface, typeface_light;

    public RecyclerAdapter(Activity _activity, List<RecyclerItem> _universities) {
        universities = _universities;
        typeface = Typeface.createFromAsset(_activity.getAssets(), "fonts/Roboto-Regular.ttf");
        typeface_light = Typeface.createFromAsset(_activity.getAssets(), "fonts/Roboto-Light.ttf");
        context = _activity;
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
            ((TextView) v.findViewById(R.id.location_title)).setTypeface(typeface_light);
            ((TextView) v.findViewById(R.id.cost_title)).setTypeface(typeface_light);
            ((TextView) v.findViewById(R.id.mark_title)).setTypeface(typeface_light);
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
            holder.average_mark.setText(Float.toString(uni.getMark()));
            Picasso.with(context).load(uni.getIconId()).into(holder.logo);
            holder.cost.setText(uni.getCost());


            holder.name.setTypeface(typeface, Typeface.BOLD);
            holder.location.setTypeface(typeface, Typeface.BOLD);
            holder.cost.setTypeface(typeface, Typeface.BOLD);
            holder.average_mark.setTypeface(typeface, Typeface.BOLD);
            //viewHolder.average_mark.setTypeface(typeface_light, Typeface.NORMAL);
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
