package com.example.budna.smoney.baza;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.budna.smoney.fragment.Podjetje;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jure on 16. jan 2016.
 */
public class DbHelper extends SQLiteOpenHelper {
    //Ime baze
    private static final String dbName = "Baza.db";

    //Verzija
    private static final int dbVersion = 2;

    //Stolpci za tabelo Podjetja
    public static final String tabelaPodjetje = "Podjetje";
    public static final String ID_PODJETJA = "_id";
    public static final String IME_PODJETJA = "ime_podjetja";
    public static final String LOKACIJA_PODJETJA = "lokacija_podjetja";
    public static final String VRSTA_DELA = "vrsta_dela";
    public static final String URNA_POSTAVKA = "urna_postavka";
    public static final String INFORMACIJE = "informacije";

    //Stolpci za tabelo Ure
    public static final String tabelaUre = "Ure";
    public static final String ID_UR = "_id";
    public static final String IME_UR = "ime_ur";
    public static final String DATUM = "datum";
    public static final String STEVILO_UR = "stevilo_ur";

    public DbHelper(Context context){
        super(context, dbName, null, dbVersion);
        Log.e("OPERACIJE BAZE", "Baza ustvarjena / odprta...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + tabelaPodjetje + " (" +
                ID_PODJETJA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                IME_PODJETJA + " TEXT, " +
                LOKACIJA_PODJETJA + " TEXT, " +
                VRSTA_DELA + " TEXT, " +
                URNA_POSTAVKA + " DECIMAL, " +
                INFORMACIJE + " TEXT " +
                ");");
        Log.e("OPERACIJE BAZE", "Tabela 'Podjetje' ustvarjena...");

        db.execSQL("CREATE TABLE " + tabelaUre + " (" +
                ID_UR + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                IME_UR + " TEXT, " +
                DATUM + " TEXT, " +
                STEVILO_UR + " INTEGER" +
                ");");
        Log.e("OPERACIJE BAZE", "Tabela 'Ure' ustvarjena...");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + tabelaPodjetje);
        Log.e("OPERACIJE BAZE", "Tabela Podjetje izbrisana");

        db.execSQL("DROP TABLE IF EXISTS " + tabelaUre);
        Log.e("OPERACIJE BAZE", "Tabela Podjetje izbrisana");


        onCreate(db);
    }

    //Podjetje
    public void addPodjetje(DbPodjetje podjetje){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(IME_PODJETJA, podjetje.getImeP());
        cv.put(LOKACIJA_PODJETJA, podjetje.getLokacijaP());
        cv.put(VRSTA_DELA, podjetje.getVrstaDela());
        cv.put(URNA_POSTAVKA, podjetje.getPostavka());
        cv.put(INFORMACIJE, podjetje.getInformacije());

        db.insert(tabelaPodjetje, null, cv);

        db.close();
    }

    public Cursor getPodjetja() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + tabelaPodjetje, null);
        c.moveToFirst();
        return c;
    }

    public List<String> getListPodjetja(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + tabelaPodjetje, null);
        c.moveToFirst();
        List<String> imena = new ArrayList<>();
        while (!c.isAfterLast()) {
            String fuelType = c.getString(c.getColumnIndex(IME_PODJETJA));

            imena.add(fuelType);

            c.moveToNext();
        }
        return imena;
    }

    public DbPodjetje getPodjetjeByID(int podjetjeID){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] params = new String[]{String.valueOf(podjetjeID)};
        Cursor c = db.rawQuery("SELECT * FROM " + tabelaPodjetje + " WHERE " + ID_PODJETJA + " = ?", params);
        c.moveToFirst();

        return new DbPodjetje(c.getString(c.getColumnIndex(IME_PODJETJA)),
                c.getString(c.getColumnIndex(LOKACIJA_PODJETJA)),
                c.getString(c.getColumnIndex(VRSTA_DELA)),
                c.getDouble(c.getColumnIndex(URNA_POSTAVKA)),
                c.getString(c.getColumnIndex(INFORMACIJE)));
    }

    public DbPodjetje getPodjetjeByUsername(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] params = new String[]{String.valueOf(username)};
        Cursor c = db.rawQuery("SELECT * FROM " + tabelaPodjetje + " WHERE " + IME_PODJETJA + " = ?", params);
        c.moveToFirst();

        return new DbPodjetje(c.getString(c.getColumnIndex(IME_PODJETJA)),
                c.getString(c.getColumnIndex(LOKACIJA_PODJETJA)),
                c.getString(c.getColumnIndex(VRSTA_DELA)),
                c.getDouble(c.getColumnIndex(URNA_POSTAVKA)),
                c.getString(c.getColumnIndex(INFORMACIJE)));
    }

    public void updatePodjetje(DbPodjetje podjetje, String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(IME_PODJETJA, podjetje.getImeP());
        cv.put(LOKACIJA_PODJETJA, podjetje.getLokacijaP());
        cv.put(VRSTA_DELA, podjetje.getVrstaDela());
        cv.put(URNA_POSTAVKA, podjetje.getPostavka());
        cv.put(INFORMACIJE, podjetje.getInformacije());

        db.update(tabelaPodjetje, cv, IME_PODJETJA + "=?", new String[]{toString().valueOf(username)});

        db.close();

    }

    public void deletePodjetje(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tabelaUre, IME_UR + " = '" + username + "'", null);
        db.delete(tabelaPodjetje, IME_PODJETJA + " = '" + username + "'", null);
        db.close();
    }

    //Ure
    public void addUre(DbUra ura){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(IME_UR, ura.getImeU());
        cv.put(DATUM, ura.getDatum());
        cv.put(STEVILO_UR, ura.getStevilo_ur());

        long i = db.insert(tabelaUre, null, cv);
        Log.e("****Row added*****", "Id: " + i);

        db.close();
    }

    public Cursor getUre() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + tabelaUre, null);
        c.moveToFirst();
        return c;
    }

    public void updateUre(DbUra ura, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(IME_UR, ura.getImeU());
        cv.put(DATUM, ura.getDatum());
        cv.put(STEVILO_UR, ura.getStevilo_ur());

        db.update(tabelaUre, cv, ID_UR + "=?", new String[]{toString().valueOf(id)});

        db.close();

    }

    public void deleteUre(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tabelaUre, ID_UR + " = '" + id + "'", null);
        db.close();
    }

    public DbUra getUreById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] params = new String[]{String.valueOf(id)};
        Cursor c = db.rawQuery("SELECT * FROM " + tabelaUre + " WHERE " + ID_UR + "=?", params);
        c.moveToFirst();
        db.close();

        return new DbUra(c.getString(c.getColumnIndex(IME_UR)),
                c.getString(c.getColumnIndex(DATUM)),
                c.getInt(c.getColumnIndex(STEVILO_UR)));
    }

    public DbUra getUreByUsername(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] params = new String[]{String.valueOf(username)};
        Cursor c = db.rawQuery("SELECT * FROM " + tabelaUre + " WHERE " + IME_UR + " = ?", params);
        c.moveToFirst();

        db.close();

        return new DbUra(c.getString(c.getColumnIndex(IME_UR)),
                c.getString(c.getColumnIndex(DATUM)),
                c.getInt(c.getColumnIndex(STEVILO_UR)));

    }

    public int vrniUrePodjetja(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] params = new String[]{String.valueOf(username)};
        Cursor c = db.rawQuery("SELECT SUM("+ STEVILO_UR + ") FROM " + tabelaPodjetje + " p, " + tabelaUre + " u" +
                " WHERE p." + IME_PODJETJA + " = u." + IME_UR + " AND p." + IME_PODJETJA + " = ?", params);
        if(c.moveToFirst()){
            return c.getInt(0);
        }

        return 0;
    }

    public double vrniZasluzekPodjetja(int st_ur, String username){
        SQLiteDatabase db = this.getReadableDatabase();
        double urna_postavka = 0;
        String[] params = new String[]{String.valueOf(username)};
        Cursor c = db.rawQuery("SELECT " + URNA_POSTAVKA + " FROM " + tabelaPodjetje + " WHERE " + IME_PODJETJA + " = ?", params);
        if(c.moveToFirst()){
            urna_postavka = c.getDouble(0);
        }

        double zasluzek = st_ur * urna_postavka;
        //zasluzek = Double.parseDouble(String.format("%.1f", zasluzek));

        return zasluzek;
    }

    public double vrniVesZaslužek(){
        double zasluzek = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor  c = db.rawQuery("SELECT * FROM " + tabelaPodjetje, null);
        if (c .moveToFirst()) {

            while (!c.isAfterLast()) {
                String username = c.getString(c.getColumnIndex(DbHelper.IME_PODJETJA));

                zasluzek += vrniZasluzekPodjetja(vrniUrePodjetja(username), username);
                c.moveToNext();
            }
        }

        return zasluzek;
    }
}
