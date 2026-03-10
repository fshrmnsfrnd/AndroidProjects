package de.ts.btin2.recyclerviewuebungka;

import de.ts.btin2.recyclerviewuebungka.databinding.ItemLayoutBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolder>{
    private List<ItemEntity> itemList;
    private RecyclerViewListener listener;

    public AdapterClass(List<ItemEntity> itemList, RecyclerViewListener listener) {
        this.listener = listener;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemLayoutBinding binding;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemLayoutBinding.inflate(inflater,parent,false);
        return new ViewHolder(binding,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Daten-Objekt aus Liste ermitteln
        ItemEntity item = itemList.get(position);
        //Werte des Datenobjektes in ViewHolder-Objekt schreiben
        // setText(int) erwartet eine String-Ressource; ID daher explizit in String wandeln
        holder.binding.itemText.setText(String.valueOf(item.getId()));
        holder.binding.itemValue.setText(item.getValue());
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    public void setItemList(List<ItemEntity> items){
        this.itemList = items;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemLayoutBinding binding;
        //Konstruktor
        ViewHolder(ItemLayoutBinding binding, RecyclerViewListener listener) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemSelected(getAdapterPosition());
                }
            });
        }
    }
}
