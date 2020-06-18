package com.example.mycontacts.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mycontacts.entity.Card;
import com.example.mycontacts.entity.Contact;

public class DBController {

    private SQLiteDatabase db;
    private DBFactory factory;

    public DBController(Context context){
        factory = new DBFactory(context);
    }

    public String insertContact(Contact contact){
        ContentValues valores;
        long resultado;

        db = factory.getWritableDatabase();
        valores = new ContentValues();
        valores.put(DBFactory.CONTACT_NAME, contact.getName());
        valores.put(DBFactory.CONTACT_COMPANY, contact.getCompany());
        valores.put(DBFactory.CONTACT_PHOTO, contact.getPhoto());
        valores.put(DBFactory.CONTACT_NEW, contact.isNewContact() ? 1 : 0);

        resultado = db.insert("contacts", null, valores);
        db.close();

        if (resultado == -1) {
            return "Erro ao inserir registro";
        }else {
            return "Contato adicionado com sucesso";
        }
    }

    public String insertCard(Card card){
        ContentValues valores;
        long resultado;

        db = factory.getWritableDatabase();
        valores = new ContentValues();
        valores.put(DBFactory.CARD_NUMBER, card.getNumber());

        resultado = db.insert("cards", null, valores);
        db.close();

        if (resultado == -1) {
            return "Erro ao inserir registro";
        }else {
            return "Cartão adicionado com sucesso";
        }
    }

    public Cursor listContacts(String filter){
        Cursor cursor;
        db = factory.getReadableDatabase();
        String sql = "SELECT * FROM contacts";
        if(filter != null && !filter.isEmpty())
            sql += " WHERE " + filter;
        cursor = db.rawQuery(sql, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor listCards(String filter){
        Cursor cursor;
        db = factory.getReadableDatabase();
        String sql = "SELECT * FROM cards";
        if(filter != null && !filter.isEmpty())
            sql += " WHERE " + filter;
        cursor = db.rawQuery(sql, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}