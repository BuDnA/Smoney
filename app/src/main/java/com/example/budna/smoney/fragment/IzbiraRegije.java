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

import java.util.Arrays;
import java.util.List;

public class IzbiraRegije extends Fragment {
    Spinner spinerIme;
    public Button buttonSend;
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


        spinerIme=(Spinner)rootView.findViewById(R.id.regije);


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
                System.out.println(parent.getItemAtPosition(position) + " selected" + Toast.LENGTH_LONG);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        buttonSend =(Button)rootView.findViewById(R.id.Bshrani);
        editTextUre=(EditText)rootView.findViewById(R.id.ETure);
        konc=(TextView)rootView.findViewById(R.id.TVrezultat);
        final EditText txtDate=(EditText)rootView.findViewById(R.id.txtdate);
        buttonSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String izbranoIme = spinerIme.getSelectedItem().toString();
                    Fragment newFragment;
                    if(izbranoIme.equals("Osrednjeslovenska")) newFragment = new Dela("https://www.studentski-servis.com/index.php?t=prostaDela&page=1&perPage=1000&sort=3&workType=1&regija[]=101&regija[]=002&regija[]=003&regija[]=020&regija[]=021&keyword=");
                    else if(izbranoIme.equals("Podravska")) newFragment = new Dela("https://www.studentski-servis.com/index.php?t=prostaDela&page=1&perPage=1000&sort=3&workType=1&regija[]=009&regija[]=031&regija[]=030&keyword=");
                    else if(izbranoIme.equals("Koroška")) newFragment = new Dela("https://www.studentski-servis.com/index.php?t=prostaDela&page=1&perPage=1000&sort=3&workType=1&regija[]=012&keyword=");
                    else if(izbranoIme.equals("Pomurska")) newFragment = new Dela("https://www.studentski-servis.com/index.php?t=prostaDela&page=1&perPage=1000&sort=3&workType=1&regija[]=011&keyword=");
                    else if(izbranoIme.equals("Savinjska")) newFragment = new Dela("https://www.studentski-servis.com/index.php?t=prostaDela&page=1&perPage=1000&sort=3&workType=1&regija[]=015&keyword=");
                    else if(izbranoIme.equals("Zasavska")) newFragment = new Dela("https://www.studentski-servis.com/index.php?t=prostaDela&page=1&perPage=1000&sort=3&workType=1&regija[]=005&keyword=");
                    else if(izbranoIme.equals("Gorenjska")) newFragment = new Dela("https://www.studentski-servis.com/index.php?t=prostaDela&page=1&perPage=1000&sort=3&workType=1&regija[]=108&regija[]=023&regija[]=024&regija[]=025&regija[]=026&keyword=");
                    else if(izbranoIme.equals("Goriška")) newFragment = new Dela("https://www.studentski-servis.com/index.php?t=prostaDela&page=1&perPage=1000&sort=3&workType=1&regija[]=017&keyword=");
                    else if(izbranoIme.equals("Notranjsko kraška")) newFragment = new Dela("https://www.studentski-servis.com/index.php?t=prostaDela&page=1&perPage=10000&sort=3&workType=1&regija[]=018&keyword=");
                    else if(izbranoIme.equals("Obalna")) newFragment = new Dela("https://www.studentski-servis.com/index.php?t=prostaDela&page=1&perPage=1000&sort=3&workType=1&regija[]=007&regija[]=027&regija[]=028&regija[]=029&keyword=");
                    else if(izbranoIme.equals("Spodnjesavska")) newFragment = new Dela("https://www.studentski-servis.com/index.php?t=prostaDela&page=1&perPage=1000&sort=3&workType=1&regija[]=006&keyword=");
                    else if(izbranoIme.equals("Dolenjska")) newFragment = new Dela("https://www.studentski-servis.com/index.php?t=prostaDela&page=1&perPage=1000&sort=3&workType=1&regija[]=010&keyword=");

                    else newFragment = new Dela("https://www.studentski-servis.com/index.php?t=prostaDela&page=1&perPage=10&sort=3&workType=1&regija[]=014&keyword=");

                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.main_content, newFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }


        });




        return rootView;
    }







}
