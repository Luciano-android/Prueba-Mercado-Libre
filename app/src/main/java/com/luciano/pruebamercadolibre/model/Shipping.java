package com.luciano.pruebamercadolibre.model;

import com.google.gson.annotations.SerializedName;

public class Shipping {
    @SerializedName("free_shipping")
    private Boolean envioGratis;

    public Shipping() {
    }

    public Boolean getEnvioGratis() {
        return envioGratis;
    }
}
