package com.example.mycontacts.retrofit;

import com.example.mycontacts.entity.CardList;
import com.example.mycontacts.entity.ContactList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceAPI  {

    @GET("contacts")
    Call<ContactList> listContacts();

    @GET("cards")
    Call<CardList> listCards();
}