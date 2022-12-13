package tsekkaa.arvosi;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * The adapter generates notifications with a specified layout and populates the Recycler View
 *
 *@author   Matleena Kankaanpää
 * @version 1.0
 * @since   2022-12-14
 */

public class MuistutusAdapter extends RecyclerView.Adapter<MuistutusAdapter.MuistutusHolder> {
    /* An empty array list must be created, otherwise if the array is referenced and it returns
    null, it will cause an error */
    private List<Muistutus> muistutukset = new ArrayList<>();


    /**
     * Defines the layout for each notification item
     * @param parent The ViewGroup the notification item belongs to
     * @param viewType The type of the view
     * @return A ViewHolder for the notification item to be added with the specified layout
     */
    @NonNull
    @Override
    public MuistutusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.muistutus_item, parent, false);
        return new MuistutusHolder(itemView);
    }

    /**
     *
     * If either text view is empty, it's hidden to not take up space.
     * @param holder An item in the recycler view
     * @param position A counter variable
     */

    @Override
    public void onBindViewHolder(@NonNull MuistutusHolder holder, int position) {
        Muistutus valittuMuistutus = muistutukset.get(position);
        holder.pvmTextView.setText(valittuMuistutus.getPvm());

        if (valittuMuistutus.getMitattavat().length() == 0) {
            holder.mitattavatTextView.setVisibility(View.GONE);
        } else {
            holder.mitattavatTextView.setText(valittuMuistutus.getMitattavat());
        }

        if (valittuMuistutus.getLisatiedot().length() == 0) {
            holder.lisatiedotTextView.setVisibility(View.GONE);
        } else {
            holder.lisatiedotTextView.setText(valittuMuistutus.getLisatiedot());
        }
    }

    /**
     *
     * @return The size of the notifications list
     */
    @Override
    public int getItemCount() {
        return muistutukset.size();
    }

    /**
     * @param muistutukset
     */
    public void naytaMuistutukset(List<Muistutus> muistutukset) {
        this.muistutukset = muistutukset;
        notifyDataSetChanged();
    }

    /**
     *
     * @param paikka A counter variable received from the Recycler View
     * @return The notification item at the given position
     */
    public Muistutus haePaikka(int paikka) {
        return muistutukset.get(paikka);
    }

    class MuistutusHolder extends RecyclerView.ViewHolder {
        private TextView pvmTextView;
        private TextView mitattavatTextView;
        private TextView lisatiedotTextView;

        /**
         * Finds the views from the layout
         */
        public MuistutusHolder(@NonNull View itemView) {
            super(itemView);
            pvmTextView = itemView.findViewById(R.id.pvmTextView);
            mitattavatTextView = itemView.findViewById(R.id.mitattavatTextView);
            lisatiedotTextView = itemView.findViewById(R.id.lisatiedotTextView);

        }
    }
}