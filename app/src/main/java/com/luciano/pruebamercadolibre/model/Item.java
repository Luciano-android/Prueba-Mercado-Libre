package com.luciano.pruebamercadolibre.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Item implements Serializable {

    private String id;
    private String title;
    @SerializedName("price")   // aca va lo de json
    private Double price;      // nombre que yo quiero perro.
    private String currency_id;
    private Boolean accepts_mercadopago;
    private Integer available_quantity;
    private String thumbnail;

    public Item() {
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public Boolean getAccepts_mercadopago() {
        return accepts_mercadopago;
    }

    public Integer getAvailable_quantity() {
        return available_quantity;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
