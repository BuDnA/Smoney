package com.example.budna.smoney.fragment;

/**
 * Created by budna on 10. 01. 2016.
 */
public class Delo {

    public String naziv = "", kraj = "", status = "", čas = "", plača = "", pogoji = "";

    public Delo() {

    }

    public String toString(){
        return naziv + " " + kraj + " " + status + " " + čas + " " + plača + " " + pogoji;
    }
}
