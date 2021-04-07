package com.luciano.pruebamercadolibre.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Utils {
    public static Boolean checkInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return cm.getActiveNetworkInfo() != null && netInfo.isConnected();
    }

    public static String getFormatedNumber(Double number){
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##", DecimalFormatSymbols.getInstance(Locale.GERMANY));
        return decimalFormat.format(number);
    }
}
