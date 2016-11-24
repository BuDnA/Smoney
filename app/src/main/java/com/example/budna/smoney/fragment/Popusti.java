package com.example.budna.smoney.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.budna.smoney.PopustListAdapter;
import com.example.budna.smoney.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Popusti extends Fragment {

    private ListView lvPopust;


    private static final String URL  = "https://www.studentski-servis.com/studenti/popusti-ugodnosti";

    public Popusti() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.popusti, container, false);
        lvPopust = (ListView)rootView.findViewById(R.id.listview_product);


        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle     savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new Discont().execute();
    }

    private class Discont extends AsyncTask<Void, Void, Void> {
        private List<Popust> mPopustList = new ArrayList<>();
        private PopustListAdapter adapter = new PopustListAdapter(getActivity().getApplicationContext(), mPopustList);

        protected Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect(URL).get();
                Element body = doc.body();
                ArrayList<Popust> popusti = new ArrayList<Popust>();
                Elements cena = doc.getElementsByClass("tdValue");
                for(int i = cena.size()/2; i < cena.size(); i++){
                    popusti.add(new Popust());
                }

                Elements procent = doc.getElementsByClass("tdDiscount");
                for (int i = 0; i < procent.size()/2 ; i++) {
                    popusti.get(i).popust = procent.get(i).text();
                }

                Elements kategorija = doc.getElementsByClass("tdCategory");
                for(int i = 0; i < kategorija.size(); i++){
                    String[] r = kategorija.get(i).text().split("(?=[a-z])");
                    popusti.get(i).kategorija = r[0];

                }

                Elements company = doc.getElementsByClass("tdCompany");
                for(int i = 0; i < company.size()/2; i++){
                    popusti.get(i).company = company.get(i).text();
                }

                int z = 0;
                for (int i = cena.size()/2; i < cena.size() ; i++) {
                    String[] cene = cena.get(i).text().split("€");
                    popusti.get(z).potem = cene[0];
                    popusti.get(z).prej = cene[1];
                    z++;
                }
                for(Popust a: popusti){
                    mPopustList.add(a);
                    System.out.println(a.toString());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lvPopust.setAdapter(adapter);
        }
    }
}
