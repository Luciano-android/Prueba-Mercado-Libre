package com.luciano.pruebamercadolibre.dao;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class DaoHelper {
    public Retrofit retrofit;
    public static final String BASE_URL = "https://api.mercadolibre.com/";

    public DaoHelper(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
