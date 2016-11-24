package com.example.budna.smoney.fragment;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.budna.smoney.R;

/**
 * Created by Nedim on 6. 01. 2016.
 */
public class InfoDohodnina extends android.app.Fragment {
    private RadioGroup radioGroup;
    private RadioButton DA, NE;
    public EditText denar;
    public TextView konc;
    public Double koliko;
    public Button zakoni;


    public InfoDohodnina() {
        // Required empty public constructor
    }





    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.infodohodnina, container, false);
        radioGroup = (RadioGroup)rootView.findViewById(R.id.rg2);
        DA=(RadioButton)rootView.findViewById(R.id.RBdaa);
        NE=(RadioButton)rootView.findViewById(R.id.RBnee);
        denar=(EditText)rootView.findViewById(R.id.ETdenarr);
        konc=(TextView)rootView.findViewById(R.id.TVrezultat2);
        zakoni=(Button)rootView.findViewById(R.id.Bnazaj);

        DA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aa=denar.getText().toString();
                if(radioGroup != null){
                    if(NE.isChecked()) NE.setChecked(false);
                }
                if (aa.matches("")) {
                    Toast.makeText(getActivity(), "Niste vnesli zneska", Toast.LENGTH_SHORT).show();
                    return;
                }
                double zasluzek=Double.parseDouble(aa);

                if (zasluzek < 3302)
                {
                    konc.setText("Morate plačati 0 € dohodnine" + "\n" + "Vas zaslužek v letošnjem letu je: " + zasluzek);

                }
                else
                {
                    double razlika = zasluzek - 3302;
                    double procent = (razlika / 100);
                    koliko = procent * 22;
                    koliko = Double.parseDouble(String.format("%.2f", koliko));
                    konc.setText("Morate plačati " + koliko + "€ dohodnine" + "\n" + "Vas zaslužek v letošnjem letu je: " + zasluzek); ;
                }


            }
        });
        NE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aa=denar.getText().toString();
                if(radioGroup != null){
                    if(DA.isChecked()) DA.setChecked(false);
                }
                if (aa.matches("")) {
                    Toast.makeText(getActivity(), "Niste vnesli zneska", Toast.LENGTH_SHORT).show();
                    return;
                }
                double zasluzek=Double.parseDouble(aa);
                    if (zasluzek < 10866) {
                        konc.setText("Morate plačati 0 € dohodnine" + "\n" + "Vas zaslužek v letošnjem letu je: " + zasluzek);

                    } else {
                        double razlika = zasluzek - 10866;
                        double procent = (razlika / 100);
                        koliko = procent * 22;
                        koliko = Double.parseDouble(String.format("%.2f", koliko));
                        konc.setText("Morate plačati " + koliko + "€ dohodnine" + "\n" + "Vas zaslužek v letošnjem letu je: " + zasluzek);
                        ;
                    }


                }

        });
        zakoni.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                android.app.Fragment newFragment = new Olajsave();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_content, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });





        return rootView;
    }
}


