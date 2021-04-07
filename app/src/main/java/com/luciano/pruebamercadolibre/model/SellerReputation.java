package com.luciano.pruebamercadolibre.model;

import com.google.gson.annotations.SerializedName;

public class SellerReputation {
    @SerializedName("transactions")
    private Transactions transactions;

    public SellerReputation() {
    }

    public Transactions getTransactions() {
        return transactions;
    }
}
