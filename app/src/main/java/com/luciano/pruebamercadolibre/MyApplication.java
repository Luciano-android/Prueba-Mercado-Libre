package com.luciano.pruebamercadolibre;

import android.app.Application;

import cat.ereza.customactivityoncrash.config.CaocConfig;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CaocConfig.Builder.create()
                .errorDrawable(R.drawable.ic_baseline_error_24)
                .apply();
    }
}
