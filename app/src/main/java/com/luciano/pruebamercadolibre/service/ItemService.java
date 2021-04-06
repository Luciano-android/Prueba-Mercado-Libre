package com.luciano.pruebamercadolibre.service;

import com.luciano.pruebamercadolibre.model.Item;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ItemService {

    @GET("sites/MLA/search")
    Call<ItemResponse> getItems(@Query("q")String query);
}
