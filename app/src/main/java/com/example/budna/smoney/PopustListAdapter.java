package com.example.budna.smoney;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.budna.smoney.fragment.Popust;

import java.util.List;

/**
 * Created by budna on 10. 01. 2016.
 */
public class PopustListAdapter extends BaseAdapter{

    private Context mContext;
    private List<Popust> mPopustList;

    public PopustListAdapter(Context mContext, List<Popust> mPopustList) {
        this.mContext = mContext;
        this.mPopustList = mPopustList;
    }

    @Override
    public int getCount() {
        return mPopustList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPopustList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item_popust_list, null);
        TextView tvName = (TextView)v.findViewById(R.id.tv_name);
        TextView tvPrice = (TextView)v.findViewById(R.id.tv_price);
        TextView tvDescription = (TextView)v.findViewById(R.id.tv_description);

        tvName.setText(mPopustList.get(position).popust + " na " + mPopustList.get(position).kategorija);
        tvPrice.setText("Znižano iz " + mPopustList.get(position).prej + " na " + mPopustList.get(position).potem);
        tvDescription.setText(mPopustList.get(position).company);


        return v;

    }
}
