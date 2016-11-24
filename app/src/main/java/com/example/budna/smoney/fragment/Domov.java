package com.example.budna.smoney.fragment;

import android.app.FragmentTransaction;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.budna.smoney.R;
import com.example.budna.smoney.adapter.PodjetjaAdapter;
import com.example.budna.smoney.adapter.UreAdapter;
import com.example.budna.smoney.baza.DbHelper;

/**
 * Created by budna on 5. 01. 2016.
 */
public class Domov extends android.app.Fragment {

    public Button dodaj;
    private ListView listView;
    private View emptyView;
    Bundle bundle;

    DbHelper dbHelper;


    public Domov() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dbHelper = new DbHelper(getActivity());
        View rootView = inflater.inflate(R.layout.krneki, container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // This needs to be done in onResume to refresh listView after returning from adding car
        DbHelper dbHelper = new DbHelper(getActivity());
        Cursor cursor = dbHelper.getUre();
        UreAdapter uAdapter = new UreAdapter(getActivity(), cursor);
        listView.setAdapter(uAdapter);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emptyView = view.findViewById(android.R.id.empty);
        listView = (ListView) view.findViewById(android.R.id.list);
        listView.setEmptyView(emptyView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {

                bundle = new Bundle();
                String name = (String) ((TextView) view.findViewById(R.id.listview_ure_item_ime)).getText();
                int id = Integer.parseInt(((TextView) view.findViewById(R.id.listview_ure_item_id)).getText().toString());

                Log.e("USERNAME", name);
                Log.e("ID", Integer.toString(id));


                bundle.putString("username", name);
                bundle.putInt("id", id);
                android.app.Fragment newFragment = new EditUreFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                newFragment.setArguments(bundle);
                transaction.replace(R.id.main_content, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

 }


