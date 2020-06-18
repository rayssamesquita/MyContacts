package com.example.mycontacts.retrofit;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInicializer {

    private final Retrofit retrofit;

    public RetrofitInicializer() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://demo9508811.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ServiceAPI getHeroes(){
        return retrofit.create(ServiceAPI.class);
    }

}