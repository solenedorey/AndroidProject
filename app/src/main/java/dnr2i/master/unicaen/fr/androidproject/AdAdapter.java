package dnr2i.master.unicaen.fr.androidproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class AdAdapter extends RecyclerView.Adapter<MyViewHolder> {

    ArrayList<Ad> adsList;

    //ajouter un constructeur prenant en entrée une liste
    public AdAdapter(ArrayList<Ad> adsList) {
        this.adsList = adsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ad_cards,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Ad ad = adsList.get(position);
        holder.bind(ad);
    }

    @Override
    public int getItemCount() {
        return adsList.size();
    }
}