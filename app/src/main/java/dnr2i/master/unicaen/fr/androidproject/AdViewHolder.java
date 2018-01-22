package dnr2i.master.unicaen.fr.androidproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AdViewHolder extends RecyclerView.ViewHolder{

    private Context context;
    private TextView titleView;
    private TextView cityView;
    private TextView postcodeView;
    private TextView dateView;
    private TextView priceView;
    /*private ImageView imageView;*/

    //itemView est la vue correspondante à 1 cellule
    public AdViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        titleView = (TextView) itemView.findViewById(R.id.adsListTitle);
        priceView = (TextView) itemView.findViewById(R.id.adsListPrice);
        cityView = (TextView) itemView.findViewById(R.id.adsListCity);
        dateView = (TextView) itemView.findViewById(R.id.adsListDate);
        /*imageView = (ImageView) itemView.findViewById(R.id.image);*/
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    public void bind(Ad ad){
        titleView.setText(ad.getTitle());
        priceView.setText(ad.getPrice() + context.getString(R.string.currency));
        cityView.setText(ad.getCity() + " " + "(" + ad.getPostcode() + ")");
        dateView.setText(String.valueOf(ad.getDate()));
        /*Picasso.with(imageView.getContext()).load(myObject.getImageUrl()).centerCrop().fit().into(imageView);*/
    }
}
