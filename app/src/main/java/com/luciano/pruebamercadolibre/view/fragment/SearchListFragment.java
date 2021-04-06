package com.luciano.pruebamercadolibre.view.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.luciano.pruebamercadolibre.R;
import com.luciano.pruebamercadolibre.databinding.FragmentSearchListBinding;
import com.luciano.pruebamercadolibre.model.Item;
import com.luciano.pruebamercadolibre.utils.Keyboard;
import com.luciano.pruebamercadolibre.view.adapter.ItemAdapter;
import com.luciano.pruebamercadolibre.viewmodel.ViewModelItem;

import java.security.Key;
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

        setUpRecycler();
        Keyboard.showSoftKeyboard(getActivity(),binding.fragmentSearchListTextInputEditText);

        viewModelItem = new ViewModelProvider(requireActivity()).get(ViewModelItem.class);
        setUpObservers();

        binding.fragmentSearchListTextInputLayout.setStartIconOnClickListener(view -> {
            initializeSearch();
        });
        binding.fragmentSearchListTextInputEditText.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                initializeSearch();
                return true;
            }
            return false;
        });

        return binding.getRoot();
    }

    public void initializeSearch() {
        if(!binding.fragmentSearchListTextInputEditText.getText().toString().isEmpty()){
            Keyboard.hideKeyboardFrom(getContext(),binding.fragmentSearchListTextInputEditText);
            binding.fragmentSearchListTextInputEditText.clearFocus();
            viewModelItem.searchItems(binding.fragmentSearchListTextInputEditText.getText().toString(),getContext());
        }else{
            noSeInsertoTextoEnElEditText();
        }
    }

    public void noSeInsertoTextoEnElEditText() {
        Toast.makeText(getContext(), R.string.search_vacio, Toast.LENGTH_SHORT).show();
    }

    public void setUpRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.fragmentSearchListRecyclerView.setLayoutManager(linearLayoutManager);
        itemAdapter = new ItemAdapter(new ArrayList<>(),this);
        binding.fragmentSearchListRecyclerView.setAdapter(itemAdapter);
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
            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
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
