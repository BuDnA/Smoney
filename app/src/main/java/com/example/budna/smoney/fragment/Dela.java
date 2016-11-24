package com.example.budna.smoney.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.budna.smoney.DelaListAdapter;
import com.example.budna.smoney.PopustListAdapter;
import com.example.budna.smoney.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dela extends android.app.Fragment {

    private ListView lvDela;
    public ProgressBar juhej;


    private static final String URL  = "https://www.studentski-servis.com/Studenti/Delo/Prosta-dela";

    public Dela() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dela, container, false);
        juhej = (ProgressBar)rootView.findViewById(R.id.progressBar1);
        lvDela = (ListView)rootView.findViewById(R.id.listview_dela);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle     savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        new Works().execute();
    }

    private class Works extends AsyncTask<Void, Void, Void> {
        private List<Delo> mDelaList = new ArrayList<>();

        //Context context = getActivity().getApplicationContext();

        private DelaListAdapter adapter2 = new DelaListAdapter(getActivity().getApplicationContext(), mDelaList);




        protected Void doInBackground(Void... voids) {
                try {
                    Document doc = Jsoup.connect("https://www.studentski-servis.com/index.php?t=prostaDela&page=1&perPage=100&sort=1&workType=1&keyword=").get();
                    Element body = doc.body();
                    Document juhej = Jsoup.parse(body.toString());
                    ArrayList<Delo> delo = new ArrayList<Delo>();
                    Elements naziv = doc.getElementsByClass("place");
                    System.out.println(naziv.size());
                    Elements naziv2 = doc.getElementsByClass("jobTitle");
                    System.out.println(naziv2.text());
                    for(int i = 0; i < naziv.size()/4; i++){
                        delo.add(new Delo());
                    }
                    for(int i = 0; i < naziv.size()/4; i++){
                        delo.get(i).naziv = naziv2.get(i).text().split("šifra")[0];
                        //String[] r = naziv.get(i).text().split("Kraj")[0];
                        for(int j = 0; j < 4; j++){
                            System.out.println(naziv.get(i*4+j).text());
                            delo.get(i).kraj = naziv.get(i*4+j++).text().split("Kraj")[1].split("Regija")[0].split(": ")[1];
                            //System.out.println(naziv.get(i*4+j).text());//
                            delo.get(i).status = naziv.get(i*4+j++).text().split(" ")[2];
                            delo.get(i).čas = naziv.get(i*4+j++).text().split("Trajanje")[1].split("Delovnik")[0].split(": ")[1];
                            delo.get(i).plača = naziv.get(i*4+j++).text();
                        }
                        //.split("(?=[0-9])");



                        //delo.get(i).naziv = r[0];
                        /*for(String s: r){
                            System.out.println(s);
                        }*/
                    }
                    /*Elements kraj = doc.getElementsByClass("tdPlace");
                    for(int i = 0; i < kraj.size(); i++){
                        delo.get(i).kraj = kraj.get(i).text();
                    }
                    Elements status = doc.getElementsByClass("tdStatus");
                    for(int i = 0; i < status.size(); i++){
                        delo.get(i).status = status.get(i).text();
                    }
                    Elements čas = doc.getElementsByClass("tdTime");
                    for(int i = 0; i < čas.size(); i++){
                        delo.get(i).čas = čas.get(i).text();
                    }
                    Elements plača = doc.getElementsByClass("tdPayment");
                    for(int i = 0; i < plača.size(); i++){
                        delo.get(i).plača = plača.get(i).text();
                    }
                    Elements pogoji = doc.getElementsByClass("tdContent");
                    for(int i = 0; i < pogoji.size(); i++){
                        delo.get(i).pogoji = pogoji.get(i).text();
                    }*/
                    for(Delo a : delo){
                        mDelaList.add(a);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            return null;
        }

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
            juhej.setVisibility(View.VISIBLE);
            //Context context = null;
            //progressDialog = ProgressDialog.show(context, "Loading", "Kuj", true, true);
            //progressDialog.setCancelable(true);


        }

        protected void onPostExecute(Void aVoid) {
            juhej.setVisibility(View.GONE);
            //progressDialog.dismiss();
           // super.onPostExecute(aVoid);
            lvDela.setAdapter(adapter2);
        }
    }
}