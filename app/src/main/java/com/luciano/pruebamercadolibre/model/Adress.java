package com.luciano.pruebamercadolibre.model;

import com.google.gson.annotations.SerializedName;

public class Adress {
    @SerializedName("state_name")
    private String nombreLocalizacion;

    public Adress() {
    }

    public String getNombreLocalizacion() {
        return nombreLocalizacion;
    }
}
