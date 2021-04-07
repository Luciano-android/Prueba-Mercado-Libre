package com.luciano.pruebamercadolibre.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ItemService {

    @GET("sites/MLA/search")
    Call<ItemResponse> getItems(@Query("q") String query);
}
