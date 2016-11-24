package com.example.budna.smoney.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.budna.smoney.R;
import com.example.budna.smoney.baza.DbHelper;

/**
 * Created by Jure on 16. jan 2016.
 */
public class PodjetjaAdapter extends CursorAdapter {

    Cursor podjetja;
    Context context;
    DbHelper dbHelper;

    public PodjetjaAdapter(Context context, Cursor podjetja){
        super(context, podjetja);

        this.podjetja = podjetja;
        this.context = context;
        dbHelper = new DbHelper(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        return mInflater.inflate(R.layout.listview_podjetja, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        dbHelper = new DbHelper(context);
        String username = cursor.getString(cursor.getColumnIndex(DbHelper.IME_PODJETJA));
        double urna_postavka = Double.parseDouble(cursor.getString(cursor.getColumnIndex(DbHelper.URNA_POSTAVKA)));
        int ure = dbHelper.vrniUrePodjetja(username);
        double zasluzek = urna_postavka * ure; //Double.parseDouble(String.format("%.1f", zasluzek));;
//        zasluzek = Double.parseDouble(String.format("%.1f", zasluzek));;
        //urna_postavka = Double.parseDouble(String.format("%.1f", Double.toString(urna_postavka)));

        // pokaži ime podjetja
        TextView textView_ime = (TextView) view.findViewById(R.id.listview_podjetje_item_ime);
        textView_ime.setText(cursor.getString(cursor.getColumnIndex(DbHelper.IME_PODJETJA)));

        // pokaži vrsto dela
        TextView textView_vrsta_dela = (TextView) view.findViewById(R.id.listview_podjetje_item_vrsta_dela);
        textView_vrsta_dela.setText(cursor.getString(cursor.getColumnIndex(DbHelper.VRSTA_DELA)));

        // pokaži urno postavko
        TextView textView_postavka = (TextView) view.findViewById(R.id.listview_podjetje_item_postavka);
        textView_postavka.setText(String.valueOf(urna_postavka));

        // pokaži število ur
        TextView textView_st_ur = (TextView) view.findViewById(R.id.listview_podjetje_item_ur);
        textView_st_ur.setText(String.valueOf(ure));

        // pokaži zaslužek
        TextView textView_average = (TextView) view.findViewById(R.id.listview_podjetje_item_zasluzek);
        textView_average.setText(String.valueOf(zasluzek));

    }
}

