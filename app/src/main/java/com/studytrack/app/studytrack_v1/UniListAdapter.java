package com.studytrack.app.studytrack_v1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vadim on 04.01.16.
 */
public class UniListAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<UniListItem> objects;

    UniListAdapter(Context context, ArrayList<UniListItem> universities) {
        ctx = context;
        objects = universities;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void updateData(ArrayList<UniListItem> universities)
    {
        objects = universities;
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.univ_list_item, parent, false);
        }

        UniListItem p = (UniListItem) getItem(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка

        //((TextView) view.findViewById(R.id.name_view)).setText(p.name);
        //((ImageView) view.findViewById(R.id.icon_view)).setImageResource(p.iconId);
        //((TextView) view.findViewById(R.id.location_view)).setText(p.location);
        //((TextView) view.findViewById(R.id.mark_view)).setText(Float.toString(p.average_mark));

        return view;
    }

}
