package com.example.budna.smoney.baza;

/**
 * Created by Jure on 16. jan 2016.
 */
public class DbUra {
    private String ime_podjetja;
    private String datum;
    private int stevilo_ur;

    public DbUra(String ime_podjetja, String datum, int stevilo_ur){
        this.ime_podjetja = ime_podjetja;
        this.datum = datum;
        this.stevilo_ur = stevilo_ur;
    }


    public String getImeU(){return ime_podjetja;}
    public void setImeU(String ime_podjetja){this.ime_podjetja = ime_podjetja;}

    public String getDatum(){return datum;}
    public void setDatum(String datum){this.datum = datum;}

    public int getStevilo_ur(){return stevilo_ur;}
    public void setStevilo_ur(int stevilo_ur){this.stevilo_ur = stevilo_ur;}

}
