package com.luciano.pruebamercadolibre.model;

import com.google.gson.annotations.SerializedName;

public class Seller {
    @SerializedName("eshop")
    private Eshop eshop;
    @SerializedName("seller_reputation")
    private SellerReputation sellerReputation;

    public Seller() {
    }

    public Eshop getEshop() {
        return eshop;
    }

    public SellerReputation getSellerReputation() {
        return sellerReputation;
    }
}
