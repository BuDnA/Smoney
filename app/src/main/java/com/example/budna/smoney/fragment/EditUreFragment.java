package com.example.budna.smoney.fragment;


import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.example.budna.smoney.baza.DbPodjetje;
import com.example.budna.smoney.baza.DbUra;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditUreFragment extends android.app.Fragment {

    Spinner spinerIme;
    public Button buttonAdd;
    public Button buttonDelete;
    public EditText editTextUre;
    public TextView konc;
    public String datum;
    private DatePicker dobPicker;
    ArrayAdapter<String> adapter;
    EditText txtDate;

    private DbHelper dbHelper;
    private Context context;


    public EditUreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_edit_ure, container, false);

        context = getActivity();
        dbHelper = new DbHelper(context);

        spinerIme=(Spinner)rootView.findViewById(R.id.spinner);


        // Set up spinner to display fuel types and get fuelID when selected
        List<String> imena = dbHelper.getListPodjetja();
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

        Bundle bundle = getArguments();
        final String username = bundle.getString("username");
        final int id = bundle.getInt("id");

        Log.e("USERNAME", username);
        Log.e("ID", Integer.toString(id));

        context = getActivity();
        dbHelper = new DbHelper(context);

        buttonDelete = (Button)rootView.findViewById(R.id.Bdelete);
        buttonAdd = (Button)rootView.findViewById(R.id.Bshrani);
        editTextUre = (EditText)rootView.findViewById(R.id.ETureIme);
        txtDate =(EditText)rootView.findViewById(R.id.txtdate);
        datum = txtDate.getText().toString();
        spinerIme = (Spinner)rootView.findViewById(R.id.spinner);

        DbUra ure = dbHelper.getUreById(id);
        txtDate.setText(String.valueOf(ure.getDatum()), TextView.BufferType.EDITABLE);
        editTextUre.setText(String.valueOf(ure.getStevilo_ur()), TextView.BufferType.EDITABLE);


        if (!username.equals(null)) {
            int spinnerPosition = adapter.getPosition(username);
            spinerIme.setSelection(spinnerPosition);
        }

        buttonDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Izbris ur");
                builder.setMessage("Ali ste prepričani?");

                builder.setPositiveButton("Da", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.deleteUre(id);

                        Toast.makeText(getActivity(), "Ure uspešno izbrisane", Toast.LENGTH_SHORT).show();

                        android.app.Fragment newFragment = new Domov();
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
                String ure = editTextUre.getText().toString();
                String izbranoIme = spinerIme.getSelectedItem().toString();
                String datum = txtDate.getText().toString();
                if (ure.matches("")) {
                    Toast.makeText(getActivity(), "Niste vnesli števila ur", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    dbHelper.updateUre(new DbUra(izbranoIme, datum, Integer.parseInt(ure)), id);
                    Toast.makeText(getActivity(), "Ure uspešno posodobljene", Toast.LENGTH_SHORT).show();

                    android.app.Fragment newFragment = new Domov();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.main_content, newFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }

            }
        });

        txtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean hasfocus) {

                if (hasfocus) {
                    DateDialog dialog = new DateDialog(view);

                    FragmentTransaction ft = getFragmentManager().beginTransaction();

                    dialog.show(ft, "DatePicker");
                    datum = dialog.date;
                }


            }


        });

        return rootView;
    }

}
