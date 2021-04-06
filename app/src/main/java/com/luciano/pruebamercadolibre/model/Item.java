package com.luciano.pruebamercadolibre.model;

import java.io.Serializable;
import java.util.List;

public class Item implements Serializable {

    private String id;
    private String title;
    private Double price;
    private String currency_id;
    private Boolean accepts_mercadopago;
    private Integer available_quantity;
    private String thumbnail;

    public Item(String id, String title, Double price, String currency_id, Boolean accepts_mercadopago, Integer available_quantity, String thumbnail) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.currency_id = currency_id;
        this.accepts_mercadopago = accepts_mercadopago;
        this.available_quantity = available_quantity;
        this.thumbnail = thumbnail;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public void setAccepts_mercadopago(Boolean accepts_mercadopago) {
        this.accepts_mercadopago = accepts_mercadopago;
    }

    public void setAvailable_quantity(Integer available_quantity) {
        this.available_quantity = available_quantity;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
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
