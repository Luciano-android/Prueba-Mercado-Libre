package com.luciano.pruebamercadolibre.service;

import com.google.gson.annotations.SerializedName;
import com.luciano.pruebamercadolibre.model.Item;

import java.util.List;

public class ItemResponse {

    @SerializedName("results")
    private List<Item> itemList;

    public ItemResponse() {
    }

    public List<Item> getItems() {
        return itemList;
    }
}
