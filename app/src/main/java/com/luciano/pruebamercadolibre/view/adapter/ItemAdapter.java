package com.luciano.pruebamercadolibre.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.luciano.pruebamercadolibre.R;
import com.luciano.pruebamercadolibre.databinding.CeldaItemBinding;
import com.luciano.pruebamercadolibre.model.Item;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemAdapterHolder> {


    private List<Item> listaDeItems;
    private ItemAdapterListener itemAdapterListener;

    public ItemAdapter(List<Item> listaDeItems, ItemAdapterListener itemAdapterListener) {
        this.listaDeItems = listaDeItems;
        this.itemAdapterListener = itemAdapterListener;
    }

    @NonNull
    @Override
    public ItemAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CeldaItemBinding binding = CeldaItemBinding.inflate(layoutInflater, parent, false);
        return new ItemAdapterHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapterHolder holder, int position) {
        Item item = listaDeItems.get(position);
        holder.setData(item);
    }

    @Override
    public int getItemCount() {
        return listaDeItems.size();
    }

    protected class ItemAdapterHolder extends RecyclerView.ViewHolder{
        private final CeldaItemBinding binding;

        public ItemAdapterHolder(CeldaItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(view -> {
                Item item = listaDeItems.get(getAdapterPosition());
                itemAdapterListener.itemAdapterOnClickItem(item);
            });
        }
        public void setData(Item item){
            DecimalFormat decimalFormat = new DecimalFormat("#,###.##", DecimalFormatSymbols.getInstance(Locale.GERMANY));
            binding.nombreItemTextViewCeldaItem.setText(item.getTitle());
            binding.precioItemTextViewCeltaItem.setText(decimalFormat.format(item.getPrice())+ " " + item.getCurrency_id());
            Glide.with(binding.getRoot().getContext())
                    .load(item.getThumbnail())
                    .into(binding.imageItemImageViewCeldaItem);
        }
    }

    public interface ItemAdapterListener{
        void itemAdapterOnClickItem(Item item);
    }
    public void setListaDeItemsActualizada(List<Item> listaDeItemsActualizada){
        listaDeItems = listaDeItemsActualizada;
        notifyDataSetChanged();
    }
}
