package com.example.mycontacts.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceAPI  {

    @GET("contacts")
    Call<String> listar();
}