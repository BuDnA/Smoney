package com.example.budna.smoney.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.budna.smoney.R;
import com.example.budna.smoney.baza.DbHelper;
import com.example.budna.smoney.baza.DbUra;

import java.util.Arrays;
import java.util.List;

public class IzbiraRegije extends Fragment {
    Spinner spinerIme;
    public Button buttonAdd;
    public EditText editTextUre;
    public TextView konc;
    public String datum;
    private DatePicker dobPicker;
    ArrayAdapter<String> adapter;

    private Context context;

    public IzbiraRegije() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.izbira_regije, container, false);

        context = getActivity();


        spinerIme=(Spinner)rootView.findViewById(R.id.spinner);


        // Set up spinner to display fuel types and get fuelID when selected
        List<String> imena = Arrays.asList("Osrednjeslovenska", "Podravska", "Koroška", "Pomurska", "Savinjska", "Zasavska", "Gorenjska", "Goriška", "Notranjsko kraška", "Obalna", "Spodnjeposavska", "Dolenjska", "Tujina");
        for (String i: imena) {
            Log.e("***Vrne vsa podjetja***", i);
        }

        adapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, imena);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerIme.setAdapter(adapter);


        spinerIme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        buttonAdd=(Button)rootView.findViewById(R.id.Bshrani);
        editTextUre=(EditText)rootView.findViewById(R.id.ETure);
        konc=(TextView)rootView.findViewById(R.id.TVrezultat);
        final EditText txtDate=(EditText)rootView.findViewById(R.id.txtdate);
        buttonAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String ure = editTextUre.getText().toString();
                String izbranoIme = spinerIme.getSelectedItem().toString();
                datum=txtDate.getText().toString();
                if (ure.matches("")) {
                    Toast.makeText(getActivity(), "Niste vnesli števila ur", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Log.e("*****Izbrano ime*****", izbranoIme);
                    Log.e("*****Datum*****", datum);
                    Log.e("*****Število ur*****", ure);
                    //dbHelper.addUre(new DbUra(izbranoIme, datum, Integer.parseInt(ure)));
                    Toast.makeText(getActivity(), "Ure za " + izbranoIme + " uspešno dodane", Toast.LENGTH_SHORT).show();

                    Fragment newFragment = new Domov();
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
