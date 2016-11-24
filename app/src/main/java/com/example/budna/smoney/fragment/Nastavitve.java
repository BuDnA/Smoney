package com.example.budna.smoney.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.budna.smoney.R;
public class Nastavitve extends android.app.Fragment {
    Spinner spinerra;
    ArrayAdapter<CharSequence> adapte;


    public Nastavitve() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.nastavitve, container, false);
        spinerra=(Spinner)rootView.findViewById(R.id.spinne);
        adapte= ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.Jezik, android.R.layout.simple_spinner_item);
        adapte.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerra.setAdapter(adapte);
        spinerra.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return rootView;
    }


}
