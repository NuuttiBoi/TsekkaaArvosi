package tsekkaa.arvosi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MuistutusAdapter extends RecyclerView.Adapter<MuistutusAdapter.MuistutusHolder> {
    private List<Muistutus> muistutukset = new ArrayList<>();

    @NonNull
    @Override
    public MuistutusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.muistutus_item, parent, false);
        return new MuistutusHolder(itemView);
    }

    /**
     * Populates the recycler view, hides empty text views
     * @param holder An item in the recycler view
     * @param position A counter variable
     */
    @Override
    public void onBindViewHolder(@NonNull MuistutusHolder holder, int position) {
        Muistutus valittuMuistutus = muistutukset.get(position);
        holder.pvmTextView.setText(valittuMuistutus.getPvm());

        if (valittuMuistutus.getMitattavat().isEmpty()) {
            holder.mitattavatTextView.setVisibility(View.GONE);
        } else {
            holder.mitattavatTextView.setText(valittuMuistutus.getMitattavat());
        }

        if (valittuMuistutus.getLisatiedot().isEmpty()) {
            holder.lisatiedotTextView.setVisibility(View.GONE);
        } else {
            holder.lisatiedotTextView.setText(valittuMuistutus.getLisatiedot());
        }
    }

    @Override
    public int getItemCount() {
        return muistutukset.size();
    }

    public void naytaMuistutukset(List<Muistutus> muistutukset) {
        this.muistutukset = muistutukset;
        notifyDataSetChanged();
    }

    public Muistutus haePaikka(int paikka) {
        return muistutukset.get(paikka);
    }

    class MuistutusHolder extends RecyclerView.ViewHolder {
        private TextView pvmTextView;
        private TextView mitattavatTextView;
        private TextView lisatiedotTextView;

        public MuistutusHolder(@NonNull View itemView) {
            super(itemView);
            pvmTextView = itemView.findViewById(R.id.pvmTextView);
            mitattavatTextView = itemView.findViewById(R.id.mitattavatTextView);
            lisatiedotTextView = itemView.findViewById(R.id.lisatiedotTextView);

        }
    }
}