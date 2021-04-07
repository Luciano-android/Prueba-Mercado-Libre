package com.luciano.pruebamercadolibre.view.fragment;

import android.icu.util.ULocale;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.luciano.pruebamercadolibre.R;
import com.luciano.pruebamercadolibre.databinding.FragmentItemDetailBinding;
import com.luciano.pruebamercadolibre.model.Item;

import java.text.DecimalFormat;
import java.util.Formatter;
import java.util.Locale;

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

        settearValoresALaPantalla(itemRecibido);

        return binding.getRoot();
    }

    public void settearValoresALaPantalla(Item itemRecibido) {
        if(itemRecibido.getAccepts_mercadopago()){
            binding.fragmentItemDetailTextViewAceptMercadoPago.setText(R.string.acepts_mercadopago);
        } else {
            binding.fragmentItemDetailTextViewAceptMercadoPago.setText(R.string.declines_mercadopago);
        }
        binding.fragmentItemDetailTextViewNombreItem.setText(itemRecibido.getTitle());

        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        binding.fragmentItemDetailTextViewPrecioItem.setText(decimalFormat.format(itemRecibido.getPrice()) + " " + itemRecibido.getCurrency_id());

        Glide.with(binding.getRoot().getContext())
                .load(itemRecibido.getThumbnail())
                .into(binding.fragmentItemDetailImageViewImagenItem);

        if(itemRecibido.getAvailable_quantity() != 0){
            binding.fragmentItemDetailTextViewItemDisponible.setText(itemRecibido.getAvailable_quantity().toString() + getString(R.string.unidades_disponibles));
        } else {
            binding.fragmentItemDetailTextViewItemDisponible.setText(R.string.no_hay_unidades_disponibles);
        }
        if(!itemRecibido.getEnvio().getEnvioGratis()){
            binding.fragmentItemDetailTextViewEnvioGratis.setVisibility(View.GONE);
        }else {
            binding.fragmentItemDetailTextViewEnvioGratis.setText(R.string.envio_gratuito);
        }

        if (itemRecibido.getSeller().getEshop() != null){
            binding.fragmentItemDetailTextViewInformacionDelVendedor.setVisibility(View.VISIBLE);
            binding.fragmentItemDetailTextViewNombreDelNegocio.setText(itemRecibido.getSeller().getEshop().getNombre());
            binding.fragmentItemDetailTextViewLocalizacion.setText(itemRecibido.getAdress().getNombreLocalizacion());
            binding.fragmentItemDetailTextViewTotalVendidos.setText(itemRecibido.getSeller().getSellerReputation().getTransactions().getTotal().toString() + getString(R.string.cantidad_unidades_vendidas));
            Glide.with(binding.getRoot().getContext())
                    .load(itemRecibido.getSeller().getEshop().getUrlLogoDelNegocio())
                    .into(binding.fragmentItemDetailImageViewImagenLogoVendedor);
        }
    }
}