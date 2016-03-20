package com.studytrack.app.studytrack_v1.UniversitySearch.University;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.studytrack.app.studytrack_v1.UniversitySearch.University.RecyclerHolder.ContactViewHolder;

import Entities.University;


/**
 * Created by vadim on 29.01.16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    University data;

    RecyclerAdapter(University data) { this.data = data; }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int page) {
        return RecyclerHolder.newInstance(page, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int page) {
        switch (page) {
            case RecyclerHolder.OPTIONS:
                break;

            case RecyclerHolder.SCORES:
                ((RecyclerHolder.ScoresViewHolder) viewHolder).setValues((float)data.getMeanPoints()
                        ,(float) data.getMeanPrice());
                ((RecyclerHolder.ScoresViewHolder) viewHolder).animate();
                break;

            case RecyclerHolder.CONTACTS:
                ContactViewHolder holder = (ContactViewHolder) viewHolder;
                holder.address.setText(data.getAddress());
                holder.phone.setText(data.getPhone());
                holder.site.setText(data.getSite());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return RecyclerHolder.PAGES_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}