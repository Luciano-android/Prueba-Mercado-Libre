package com.luciano.pruebamercadolibre.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.luciano.pruebamercadolibre.R;
import com.luciano.pruebamercadolibre.databinding.FragmentItemDetailBinding;
import com.luciano.pruebamercadolibre.model.Item;

public class ItemDetailFragment extends Fragment {

    public static final String KEY_ITEM_RECIBIDO = "Item Recibido";
    private FragmentItemDetailBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentItemDetailBinding.inflate(inflater,container,false);
        Bundle bundleRecibido = getArguments();
        final Item itemRecibido = (Item) bundleRecibido.getSerializable(KEY_ITEM_RECIBIDO);

        if(itemRecibido.getAccepts_mercadopago()){
            binding.fragmentItemDetailTextViewAceptMercadoPago.setText(R.string.acepts_mercadopago);
        } else {
            binding.fragmentItemDetailTextViewAceptMercadoPago.setText(R.string.declines_mercadopago);
        }
        binding.fragmentItemDetailTextViewNombreItem.setText(itemRecibido.getTitle());
        binding.fragmentItemDetailTextViewPrecioItem.setText(itemRecibido.getPrice().toString() + " " + itemRecibido.getCurrency_id());
        Glide.with(binding.getRoot().getContext())
                .load(itemRecibido.getThumbnail())
                .into(binding.fragmentItemDetailImageViewImagenItem);
        binding.fragmentItemDetailTextViewItemDisponible.setText(itemRecibido.getAvailable_quantity().toString() + getString(R.string.unidades_disponibles));

        return binding.getRoot();
    }
}