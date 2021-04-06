package com.luciano.pruebamercadolibre.viewmodel;

import android.content.Context;

import com.luciano.pruebamercadolibre.R;
import com.luciano.pruebamercadolibre.dao.DaoItemInternet;
import com.luciano.pruebamercadolibre.model.Item;
import com.luciano.pruebamercadolibre.utils.ResultListener;
import com.luciano.pruebamercadolibre.utils.Utils;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelItem extends ViewModel {

    private MutableLiveData<List<Item>> items;
    private MutableLiveData<Boolean> isLoading;
    private MutableLiveData<String> onError;

    public LiveData<List<Item>> getItems() {
        if (items == null) {
            items = new MutableLiveData<>();
        }
        return items;
    }

    public LiveData<Boolean> getIsLoading() {
        if (isLoading == null) {
            isLoading = new MutableLiveData<>();
        }
        return isLoading;
    }

    public LiveData<String> getOnError() {
        if (onError == null) {
            onError = new MutableLiveData<>();
        }
        return onError;
    }

    public void searchItems (String query, Context context){
        if(Utils.checkInternet(context)){
            isLoading.setValue(true);
            DaoItemInternet daoItemInternet = new DaoItemInternet();
            daoItemInternet.getItemList(query, new ResultListener<List<Item>>() {
                @Override
                public void finish(List<Item> result) {
                    isLoading.setValue(false);
                    items.setValue(result);
                }

                @Override
                public void onError(String error) {
                    isLoading.setValue(false);
                    onError.setValue(error);
                }
            });
        }else {
            onError.setValue(context.getString(R.string.no_internet_access));
        }
    }
}