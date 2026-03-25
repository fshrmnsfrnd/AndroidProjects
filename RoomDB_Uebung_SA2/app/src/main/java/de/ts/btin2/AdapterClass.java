package de.ts.btin2;

import de.ts.btin2.databinding.ItemLayoutBinding;
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
        ItemEntity item = itemList.get(position);
        holder.binding.itemID.setText(String.valueOf(item.getId()));
        holder.binding.itemName.setText(String.valueOf(item.getName()));
        holder.binding.itemValue.setText(String.valueOf(item.getValue()));
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