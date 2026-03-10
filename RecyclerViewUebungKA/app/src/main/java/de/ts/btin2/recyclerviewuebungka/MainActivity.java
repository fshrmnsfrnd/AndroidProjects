package de.ts.btin2.recyclerviewuebungka;

import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import de.ts.btin2.recyclerviewuebungka.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mViewModel;
    private AdapterClass adapter;
    private ActivityMainBinding binding;
    private ArrayList<ItemEntity> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RecyclerViewListener listener = new RecyclerViewListener() {
            @Override
            public void onItemSelected(int position) {
                if (position >= 0 && position < itemList.size()) {
                    ItemEntity item = itemList.get(position);
                    binding.itemIdSelected.setText(String.valueOf(item.getId()));
                    binding.itemValueSelected.setText(item.getValue());
                }
            }
        };

        binding.RecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new AdapterClass(itemList, listener);
        binding.RecyclerView.setAdapter(adapter);

        mViewModel = new MainViewModel(getApplication());
        mViewModel.getAllItems().observe(this, items -> {
            itemList.clear();
            if (items != null) {
                itemList.addAll(items);
            }
            adapter.setItemList(itemList);
        });
        mViewModel.getSearchResults().observe(this,items -> {
            if (!items.isEmpty())  {
                binding.itemIdSelected.setText(String.valueOf(items.get(0).getId()));
                binding.itemValueSelected.setText(items.get(0).getValue());
            } else {
                binding.itemIdSelected.setText("kein Treffer");
            }
        });
    }

    public void addItem(View v){
        String id = binding.itemIdSelected.getText().toString();
        String value = binding.itemValueSelected.getText().toString();

        if (!value.isEmpty()) {
            ItemEntity item = new ItemEntity(value);
            mViewModel.insertItem(item);
            //clearFields();
        } else {
            binding.itemIdSelected.setText("Unvollständige Information");
        }
    }

    public void findItem(View view) {
        mViewModel.findItem(binding.itemValueSelected.getText().toString());
    }

    public void deleteItem(View view) {
        mViewModel.deleteItem(binding.itemIdSelected.getText().toString());
        //clearFields();
    }

}