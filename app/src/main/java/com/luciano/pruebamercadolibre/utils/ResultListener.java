package com.luciano.pruebamercadolibre.utils;

public interface ResultListener<T> {
    void finish(T result);
    void onError(String error);
}
