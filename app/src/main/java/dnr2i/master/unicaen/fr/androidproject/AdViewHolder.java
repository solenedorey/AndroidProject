package dnr2i.master.unicaen.fr.androidproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AdViewHolder extends RecyclerView.ViewHolder{

    private TextView textViewView;
    /*private ImageView imageView;*/

    //itemView est la vue correspondante à 1 cellule
    public AdViewHolder(View itemView) {
        super(itemView);

        //c'est ici que l'on fait nos findView

        textViewView = (TextView) itemView.findViewById(R.id.text);
        /*imageView = (ImageView) itemView.findViewById(R.id.image);*/
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    public void bind(Ad ad){
        textViewView.setText(ad.getText());
        /*Picasso.with(imageView.getContext()).load(myObject.getImageUrl()).centerCrop().fit().into(imageView);*/
    }
}
