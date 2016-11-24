package com.example.budna.smoney.fragment;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.budna.smoney.MainActivity;
import com.example.budna.smoney.R;
import com.example.budna.smoney.TestActivity;
import com.example.budna.smoney.adapter.PodjetjaAdapter;
import com.example.budna.smoney.baza.DbHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class VsaPodjetja extends android.app.Fragment {

    public Button dodaj;
    private ListView listView;
    private View emptyView;
    Bundle bundle;

    DbHelper dbHelper;

    public VsaPodjetja() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dbHelper = new DbHelper(getActivity());
        View rootView = inflater.inflate(R.layout.fragment_vsa_podjetja, container, false);
        // Inflate the layout for this fragment
        dodaj=(Button)rootView.findViewById(R.id.Badd);
        dodaj.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                android.app.Fragment newFragment = new Podjetje();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_content, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // This needs to be done in onResume to refresh listView after returning from adding car
        DbHelper dbHelper = new DbHelper(getActivity());
        Cursor cursor = dbHelper.getPodjetja();
        PodjetjaAdapter pAdapter = new PodjetjaAdapter(getActivity(), cursor);
        listView.setAdapter(pAdapter);
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
                String name =(String) ((TextView) view.findViewById(R.id.listview_podjetje_item_ime)).getText();

                Log.e("BLAAAAAAAAAAAA", name);


                bundle.putString("username", name);
                android.app.Fragment newFragment = new EditPodjetjeFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                newFragment.setArguments(bundle);
                transaction.replace(R.id.main_content, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}
