package com.example.budna.smoney;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.budna.smoney.fragment.Delo;
import com.example.budna.smoney.fragment.Popust;

import java.util.List;

/**
 * Created by budna on 10. 01. 2016.
 */
public class DelaListAdapter extends BaseAdapter {
    private Context mContext;
    private List<Delo> mDelaList;

    public DelaListAdapter(Context mContext, List<Delo> mDelaList) {
        this.mContext = mContext;
        this.mDelaList = mDelaList;
    }

    @Override
    public int getCount() {
        return mDelaList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDelaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item_dela_list, null);
        TextView tvNaziv = (TextView)v.findViewById(R.id.tv_naziv);
        TextView tvKraj = (TextView)v.findViewById(R.id.tv_kraj);
        TextView tvPlača = (TextView)v.findViewById(R.id.tv_plača);

        tvNaziv.setText(mDelaList.get(position).naziv);
        tvKraj.setText(mDelaList.get(position).kraj);
        tvPlača.setText(mDelaList.get(position).plača);


        return v;

    }
}
