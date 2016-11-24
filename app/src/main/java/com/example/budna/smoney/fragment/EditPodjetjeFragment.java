package com.example.budna.smoney.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class EditPodjetjeFragment extends android.app.Fragment {

    private DbHelper dbHelper;
    private Button buttonAdd;
    private Button buttonDelete;
    private EditText editTextIme;
    private EditText editTextLokacija;
    private EditText editTextPostavka;
    private EditText editTextInformacije;
    private Spinner spinnerDelo;
    private Context context;
    ArrayAdapter<CharSequence> adapter;

    public EditPodjetjeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_edit_podjetje, container, false);

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

        Bundle bundle = getArguments();
        final String username = bundle.getString("username");

        context = getActivity();
        dbHelper = new DbHelper(context);

        buttonDelete = (Button)rootView.findViewById(R.id.Bdelete);
        buttonAdd = (Button)rootView.findViewById(R.id.Bshrani);
        editTextIme = (EditText)rootView.findViewById(R.id.ETime);
        editTextLokacija = (EditText)rootView.findViewById(R.id.ETnaslov);
        editTextPostavka = (EditText)rootView.findViewById(R.id.ETura);
        editTextInformacije = (EditText)rootView.findViewById(R.id.ETinformacije);
        spinnerDelo = (Spinner)rootView.findViewById(R.id.spinner);

        DbPodjetje podj = dbHelper.getPodjetjeByUsername(username);
        editTextIme.setText(String.valueOf(podj.getImeP()), TextView.BufferType.EDITABLE);
        editTextLokacija.setText(String.valueOf(podj.getLokacijaP()), TextView.BufferType.EDITABLE);
        editTextPostavka.setText(String.valueOf(podj.getPostavka()), TextView.BufferType.EDITABLE);
        editTextInformacije.setText(String.valueOf(podj.getInformacije()), TextView.BufferType.EDITABLE);

        buttonDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Izbris podjetja '" + editTextIme.getText().toString() + "'");
                builder.setMessage("Ali ste prepričani?\n" +
                        "Izbrisale se bodo tudi vse ure tega podjetja.");

                builder.setPositiveButton("Da", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.deletePodjetje(username);

                        Toast.makeText(getActivity(), "Podjetje (" + username + ") uspešno izbrisano", Toast.LENGTH_SHORT).show();

                        android.app.Fragment newFragment = new VsaPodjetja();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.main_content, newFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();

                        dialog.dismiss();
                    }

                });

                builder.setNegativeButton("Ne", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });


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
                if (!ime.matches("") && !lokacija.matches("") && !postavka.matches("")) {
                    dbHelper.updatePodjetje(new DbPodjetje(ime, lokacija, vrsta_dela, Double.parseDouble(postavka), informacije), username);
                    Toast.makeText(getActivity(), "Podjetje (" + ime + ") uspešno posodobljeno", Toast.LENGTH_SHORT).show();

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
