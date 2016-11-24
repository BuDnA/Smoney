package com.example.budna.smoney.fragment;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.budna.smoney.R;
import com.example.budna.smoney.baza.DbHelper;

public class Dohodnina extends android.app.Fragment {
    private RadioGroup radioGroup;
    private RadioButton DA, NE;
    public Button izracun;
    public TextView konc;
    public double zasluzek;
    public double koliko;
    DbHelper dbHelper;

    public Dohodnina() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dohodnina, container, false);
        radioGroup = (RadioGroup)rootView.findViewById(R.id.rg);

        DA=(RadioButton)rootView.findViewById(R.id.RBda);
        NE=(RadioButton)rootView.findViewById(R.id.RBdaa);

        konc=(TextView)rootView.findViewById(R.id.TVrezultat);

        dbHelper = new DbHelper(getActivity());
        //ZASLUZEK
        zasluzek = dbHelper.vrniVesZaslužek();


        DA.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(radioGroup != null){
                    if(NE.isChecked()) NE.setChecked(false);
                }

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
                if(radioGroup != null){
                    if(DA.isChecked()) DA.setChecked(false);
                }
                if (zasluzek < 10866) {
                    konc.setText("Morate plačati 0 € dohodnine" + "\n" + "Vas zaslužek v letošnjem letu je: " + zasluzek);

                } else {
                    double razlika = zasluzek - 10866;
                    double procent = (razlika / 100);
                    koliko = procent * 30;
                    koliko = Double.parseDouble(String.format("%.2f", koliko));
                    konc.setText("Morate plačati " + koliko + "€ dohodnine" + "\n" + "Vas zaslužek v letošnjem letu je: " + zasluzek);
                    ;
                }


            }
        });
        izracun=(Button)rootView.findViewById(R.id.Bizracunaj);
        izracun.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                android.app.Fragment newFragment = new InfoDohodnina();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_content, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return rootView;
    }

}
