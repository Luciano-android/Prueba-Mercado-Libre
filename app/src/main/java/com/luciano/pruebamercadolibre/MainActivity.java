package com.luciano.pruebamercadolibre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.luciano.pruebamercadolibre.model.Item;
import com.luciano.pruebamercadolibre.utils.Keyboard;
import com.luciano.pruebamercadolibre.view.fragment.ItemDetailFragment;
import com.luciano.pruebamercadolibre.view.fragment.SearchListFragment;

public class MainActivity extends AppCompatActivity implements SearchListFragment.SearchListFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstFragment(new SearchListFragment());


    }

    @Override
    public void searchListFragmentOnClickItem(Item item) {
        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
        Bundle bundleAMandar = new Bundle();
        bundleAMandar.putSerializable(ItemDetailFragment.KEY_ITEM_RECIBIDO,item);
        itemDetailFragment.setArguments(bundleAMandar);
        replaceFragment(itemDetailFragment);
    }
    private void firstFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainerMainActivity, fragment);
        fragmentTransaction.commit();
        Keyboard.showSoftKeyboard(this,findViewById(R.id.fragmentContainerMainActivity));
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerMainActivity,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}