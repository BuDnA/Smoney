package com.example.budna.smoney.fragment;

/**
 * Created by budna on 9. 01. 2016.
 */
/**
 * Created by budna on 9. 01. 2016.
 */
public class Popust {

    public String popust;
    public String kategorija;
    public String company;
    public String prej;
    public String potem;

    public Popust() {
    }

    public String toString(){
        return popust + ", " + kategorija + ", " + company + ", " + prej + "€, " + potem + "€\n";
    }
}
