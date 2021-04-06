package com.luciano.pruebamercadolibre.view.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.luciano.pruebamercadolibre.R;
import com.luciano.pruebamercadolibre.databinding.FragmentSearchListBinding;
import com.luciano.pruebamercadolibre.model.Item;
import com.luciano.pruebamercadolibre.view.adapter.ItemAdapter;
import com.luciano.pruebamercadolibre.viewmodel.ViewModelItem;

import java.util.ArrayList;

public class SearchListFragment extends Fragment implements ItemAdapter.ItemAdapterListener {

    private FragmentSearchListBinding binding;
    private ViewModelItem viewModelItem;
    private SearchListFragmentListener searchListFragmentListener;
    private ItemAdapter itemAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSearchListBinding.inflate(inflater, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.fragmentSearchListRecyclerView.setLayoutManager(linearLayoutManager);
        itemAdapter = new ItemAdapter(new ArrayList<>(),this);
        binding.fragmentSearchListRecyclerView.setAdapter(itemAdapter);

        viewModelItem = new ViewModelProvider(requireActivity()).get(ViewModelItem.class);
        setUpObservers();

        binding.fragmentSearchListTextInputLayout.setStartIconOnClickListener(view -> {
            if(!binding.fragmentSearchListTextInputEditText.getText().toString().isEmpty()){
                viewModelItem.searchItems(binding.fragmentSearchListTextInputEditText.getText().toString(),getContext());
            }else{
                Toast.makeText(getContext(), R.string.search_vacio, Toast.LENGTH_SHORT).show();
            }
        });


        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.searchListFragmentListener = (SearchListFragmentListener) context;
    }

    private void setUpObservers(){
        viewModelItem.getItems().observe(getViewLifecycleOwner(), items -> {
            itemAdapter.setListaDeItemsActualizada(items);
        });
        viewModelItem.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if(isLoading){
                binding.fragmentSearchListProgressBar.setVisibility(View.VISIBLE);
            }else {
                binding.fragmentSearchListProgressBar.setVisibility(View.GONE);
            }
        });
        viewModelItem.getOnError().observe(getViewLifecycleOwner(), error -> {
            Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void itemAdapterOnClickItem(Item item) {
        searchListFragmentListener.searchListFragmentOnClickItem(item);
    }
    public interface SearchListFragmentListener{
        void searchListFragmentOnClickItem(Item item);
    }
}
