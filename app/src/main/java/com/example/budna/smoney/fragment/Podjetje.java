package com.example.budna.smoney.fragment;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.budna.smoney.R;
import com.example.budna.smoney.baza.DbHelper;
import com.example.budna.smoney.baza.DbPodjetje;

public class Podjetje extends android.app.Fragment {

    private DbHelper dbHelper;
    private Button buttonAdd;
    private EditText editTextIme;
    private EditText editTextLokacija;
    private EditText editTextPostavka;
    private EditText editTextInformacije;
    private Spinner spinnerDelo;
    private Context context;
    ArrayAdapter<CharSequence> adapter;

    public Podjetje() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.podjetje, container, false);
        spinnerDelo=(Spinner)rootView.findViewById(R.id.spinner);
        adapter= ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.Vrsta_dela, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDelo.setAdapter(adapter);
        spinnerDelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        context = getActivity();
        dbHelper = new DbHelper(context);

        buttonAdd = (Button)rootView.findViewById(R.id.Bshrani);
        editTextIme = (EditText)rootView.findViewById(R.id.ETime);
        editTextLokacija = (EditText)rootView.findViewById(R.id.ETnaslov);
        editTextPostavka = (EditText)rootView.findViewById(R.id.ETura);
        editTextInformacije = (EditText)rootView.findViewById(R.id.ETinformacije);
        spinnerDelo = (Spinner)rootView.findViewById(R.id.spinner);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ime = editTextIme.getText().toString();
                String lokacija = editTextLokacija.getText().toString();
                String postavka = editTextPostavka.getText().toString();
                String vrsta_dela = spinnerDelo.getSelectedItem().toString();
                String informacije = editTextInformacije.getText().toString();
                if (ime.matches("")) {
                    Toast.makeText(getActivity(), "Niste vnesli imena podjetja", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (lokacija.matches("")) {
                    Toast.makeText(getActivity(), "Niste vnesli naslova podjetja", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (postavka.matches("")) {
                    Toast.makeText(getActivity(), "Niste vnesli postavke na uro", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!ime.matches("") && !lokacija.matches("") && !postavka.matches("")){
                    dbHelper.addPodjetje(new DbPodjetje(ime, lokacija, vrsta_dela, Double.parseDouble(postavka), informacije));
                    Toast.makeText(getActivity(), "Podjetje (" + ime + ") uspešno dodano", Toast.LENGTH_SHORT).show();

                    android.app.Fragment newFragment = new VsaPodjetja();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.main_content, newFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }

            }
        });


        return rootView;
    }



}