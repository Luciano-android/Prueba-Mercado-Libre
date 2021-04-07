package com.luciano.pruebamercadolibre.model;

import com.google.gson.annotations.SerializedName;

public class Eshop {
    @SerializedName("nick_name")
    private String nombre;
    @SerializedName("eshop_logo_url")
    private String urlLogoDelNegocio;

    public Eshop() {
    }
    public String getNombre() {
        return nombre;
    }

    public String getUrlLogoDelNegocio() {
        return urlLogoDelNegocio;
    }
}
