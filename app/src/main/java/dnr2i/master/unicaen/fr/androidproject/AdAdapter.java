package dnr2i.master.unicaen.fr.androidproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class AdAdapter extends RecyclerView.Adapter<AdViewHolder> {

    ArrayList<Ad> ads;
    private Context context;

    //ajouter un constructeur prenant en entrée une liste
    public AdAdapter(Context context, ArrayList<Ad> adsList) {
        this.context = context;
        this.ads = adsList;
    }

    @Override
    public AdViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ad_cards,parent,false);
        return new AdViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(AdViewHolder holder, int position) {
        Ad ad = ads.get(position);
        holder.bind(ad);
    }

    @Override
    public int getItemCount() {
        return ads.size();
    }
}