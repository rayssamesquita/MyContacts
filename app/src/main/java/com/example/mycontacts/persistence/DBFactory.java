package com.example.mycontacts.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBFactory extends SQLiteOpenHelper {

    private static final String DATABASE = "promobit.db";
    private static final int VERSAO = 1;
    public static final String CONTACT_NAME = "name";
    public static final String CONTACT_COMPANY = "company";
    public static final String CONTACT_PHOTO = "photo";
    public static final String CONTACT_NEW = "new_contact";
    public static final String CARD_NUMBER = "number";

    public DBFactory(Context context){
        super(context, DATABASE,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String contactsSql = "CREATE TABLE contacts ("
                + "id integer primary key autoincrement,"
                + CONTACT_NAME + " text,"
                + CONTACT_COMPANY + " text,"
                + CONTACT_PHOTO + " text,"
                + CONTACT_NEW + " integer"
                +")";

        String cardsSql = "CREATE TABLE cards ("
                + "id integer primary key autoincrement,"
                + CARD_NUMBER + " text"
                +")";

        db.execSQL(contactsSql);
        db.execSQL(cardsSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        db.execSQL("DROP TABLE IF EXISTS cards");
        onCreate(db);
    }
}