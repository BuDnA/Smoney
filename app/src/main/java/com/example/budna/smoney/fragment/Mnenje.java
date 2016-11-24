package com.example.budna.smoney.fragment;

import android.content.Intent;
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

import com.example.budna.smoney.R;

public class Mnenje extends android.app.Fragment {

    Button buttonSend;
    public String textSubject;
    EditText textMessage;
    Spinner spinnerr;
    ArrayAdapter<CharSequence> adapterr;

    public Mnenje() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mnenje, container, false);
        spinnerr=(Spinner)rootView.findViewById(R.id.spinner);
        adapterr= ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.Zadeve, android.R.layout.simple_spinner_item);
        adapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerr.setAdapter(adapterr);
        spinnerr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        buttonSend = (Button) rootView.findViewById(R.id.Bshrani);
        textSubject = spinnerr.getSelectedItem().toString();
        textMessage = (EditText) rootView.findViewById(R.id.ETbesedilo);


        buttonSend.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String subject = textSubject.toString();
                String message = textMessage.getText().toString();

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ "xborutx@gmail.com" });
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);

                //need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));

            }
        });

        return rootView;
    }

}
