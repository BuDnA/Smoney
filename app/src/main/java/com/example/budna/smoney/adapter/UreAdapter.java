package com.example.budna.smoney.adapter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.budna.smoney.R;
import com.example.budna.smoney.baza.DbHelper;
import com.example.budna.smoney.baza.DbPodjetje;

/**
 * Created by Jure on 17. jan 2016.
 */
public class UreAdapter extends CursorAdapter {
    Cursor ure;
    Context context;
    DbHelper dbHelper;

    public UreAdapter(Context context, Cursor ure) {
        super(context, ure);

        this.ure = ure;
        this.context = context;
        this.dbHelper = dbHelper;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        return mInflater.inflate(R.layout.listview_ure, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor ure) {
        Log.e("TOLE VRNE", ure.getString(ure.getColumnIndex(DbHelper.IME_UR)));
        dbHelper = new DbHelper(context);
        DbPodjetje podjetje = dbHelper.getPodjetjeByUsername(ure.getString(ure.getColumnIndex(DbHelper.IME_UR)));

        String vrsta_dela = podjetje.getVrstaDela();
        //String vrsta_dela = ure.getString(ure.getColumnIndex(DbHelper.IME_UR));
        String datum;
        int st_ur = ure.getInt(ure.getColumnIndex(DbHelper.STEVILO_UR));
        double urna_postavka = podjetje.getPostavka();
        double zasluzek = st_ur * urna_postavka; //Double.parseDouble(String.format("%.1f", zasluzek));;
        //zasluzek = Double.parseDouble(String.format("%.1f", zasluzek));
        //int zasluzek = 0;
        //urna_postavka = Double.parseDouble(String.format("%.1f", Double.toString(urna_postavka)));

        // skrit id
        TextView textView_id = (TextView) view.findViewById(R.id.listview_ure_item_id);
        textView_id.setText(ure.getString(ure.getColumnIndex(DbHelper.ID_UR)));

        // skrito ime
        TextView textView_ime = (TextView) view.findViewById(R.id.listview_ure_item_ime);
        textView_ime.setText(podjetje.getImeP());


        // pokaži vrsto dela
        TextView textView_vrsta_dela = (TextView) view.findViewById(R.id.listview_ure_item_vrsta_dela);
        //textView_vrsta_dela.setText(podjetje.getVrstaDela());
        textView_vrsta_dela.setText(vrsta_dela);

        // pokaži datum
        TextView textView_postavka = (TextView) view.findViewById(R.id.listview_ure_item_datum);
        textView_postavka.setText(ure.getString(ure.getColumnIndex(DbHelper.DATUM)));

        // pokaži število ur
        TextView textView_st_ur = (TextView) view.findViewById(R.id.listview_ure_item_ur);
        textView_st_ur.setText(String.valueOf(st_ur));

        // pokaži zaslužek
        TextView textView_average = (TextView) view.findViewById(R.id.listview_ure_item_zasluzek);
        textView_average.setText(String.valueOf(zasluzek));

    }
}
