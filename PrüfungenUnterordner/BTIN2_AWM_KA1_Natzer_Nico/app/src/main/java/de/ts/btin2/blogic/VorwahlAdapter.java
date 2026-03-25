package de.ts.btin2.blogic;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import de.ts.btin2.blogic.databinding.DetailListeOrtBinding;

public class VorwahlAdapter extends RecyclerView.Adapter<VorwahlAdapter.VorwahlViewHolder> {
    private List<Vorwahl> liste;



    public void setListe(List<Vorwahl> liste) {
        this.liste = liste;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return liste == null ? 0 : liste.size();
    }

    @Override
    public VorwahlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DetailListeOrtBinding binding;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = DetailListeOrtBinding.inflate(inflater, parent, false);
        return new VorwahlViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final VorwahlViewHolder item,
                                 final int listPosition) {
        Vorwahl vw = liste.get(listPosition);
        item.binding.tvVorwahl.setText(vw.getVorwahl());
        item.binding.tvOrt.setText(vw.getOrt());
    }

    //********** statische innere ViewHolder-Klasse *******************/
    static class VorwahlViewHolder extends RecyclerView.ViewHolder {
        DetailListeOrtBinding binding;

        //Konstruktor
        VorwahlViewHolder(DetailListeOrtBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
