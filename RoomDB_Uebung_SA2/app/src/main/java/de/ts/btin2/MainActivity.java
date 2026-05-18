package de.ts.btin2;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;
import de.ts.btin2.databinding.ActivityMainBinding;

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
                    binding.txtID.setText(String.valueOf(item.getId()));
                    binding.txtName.setText(String.valueOf(item.getName()));
                    binding.txtValue.setText(String.valueOf(item.getValue()));
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
                binding.txtID.setText(String.valueOf(items.get(0).getId()));
                binding.txtName.setText(String.valueOf(items.get(0).getName()));
                binding.txtValue.setText(String.valueOf(items.get(0).getValue()));
            } else {
                binding.txtID.setText("kein Treffer");
            }
        });
    }

    public void addItem(View v){
        String name = binding.txtName.getText().toString();
        float value;
        try {
            value = Float.parseFloat(binding.txtValue.getText().toString());
            ItemEntity item = new ItemEntity(name, value);
            mViewModel.insertItem(item);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void findItem(View v) {
        mViewModel.searchItem(binding.txtName.getText().toString(),Float.parseFloat(binding.txtValue.getText().toString()));
    }

    public void deleteItem(View v) {
        mViewModel.deleteItem(new ItemEntity(binding.txtName.getText().toString(),Float.parseFloat(binding.txtValue.getText().toString())));
        //clearFields();
    }
}