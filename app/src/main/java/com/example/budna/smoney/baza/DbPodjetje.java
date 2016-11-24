package com.example.budna.smoney.baza;

/**
 * Created by Jure on 16. jan 2016.
 */
public class DbPodjetje {
    private String ime_podjetja;
    private String lokacija_podjetja;
    private String vrsta_dela;
    private double urna_postavka;
    private String informacije;

    public DbPodjetje(String ime_podjetja, String lokacija_podjetja, String vrsta_dela, double urna_postavka, String informacije){
        this.ime_podjetja = ime_podjetja;
        this.lokacija_podjetja = lokacija_podjetja;
        this.vrsta_dela = vrsta_dela;
        this.urna_postavka = urna_postavka;
        this.informacije = informacije;
    }

    public String getImeP(){return ime_podjetja;}
    public void setImeP(String ime_podjetja){this.ime_podjetja = ime_podjetja;}

    public String getLokacijaP(){return lokacija_podjetja;}
    public void setLokacijaP(String lokacija_podjetja){this.lokacija_podjetja = lokacija_podjetja;}

    public String getVrstaDela(){return vrsta_dela;}
    public void setVrstaDela(String vrsta_dela){this.vrsta_dela = vrsta_dela;}

    public double getPostavka(){return urna_postavka;}
    public void setPostavka(double urna_postavka){this.urna_postavka = urna_postavka;}

    public String getInformacije(){return informacije;}
    public void setInformacije(String informacije){this.informacije = informacije;}
}
