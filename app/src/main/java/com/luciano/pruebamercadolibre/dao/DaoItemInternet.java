package com.luciano.pruebamercadolibre.dao;

import android.util.Log;

import com.luciano.pruebamercadolibre.model.Item;
import com.luciano.pruebamercadolibre.service.ItemResponse;
import com.luciano.pruebamercadolibre.service.ItemService;
import com.luciano.pruebamercadolibre.utils.ResultListener;

import java.util.List;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaoItemInternet extends DaoHelper {

    private ItemService itemService;

    public DaoItemInternet() {
        super();
        itemService = retrofit.create(ItemService.class);
    }

    public void getItemList(String query, final ResultListener<List<Item>> controllerListener) {
        itemService.getItems(query).enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(@NonNull Call<ItemResponse> call, @NonNull Response<ItemResponse> response) {
                if (response.isSuccessful())
                    controllerListener.finish(response.body().getItems());
                else controllerListener.onError();
            }

            @Override
            public void onFailure(@NonNull Call<ItemResponse> call, @NonNull Throwable t) {
                controllerListener.onError();
                Log.d("Fail_To_Call_Query", t.toString());
            }
        });
    }
}
