package dnr2i.master.unicaen.fr.androidproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class AdViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private TextView idView;
    private TextView titleView;
    private TextView cityView;
    private TextView dateView;
    private TextView priceView;
    private ImageView imageView;

    public AdViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        idView = itemView.findViewById(R.id.adsListId);
        titleView = itemView.findViewById(R.id.adsListTitle);
        priceView = itemView.findViewById(R.id.adsListPrice);
        cityView = itemView.findViewById(R.id.adsListCity);
        dateView = itemView.findViewById(R.id.adsListDate);
        imageView = itemView.findViewById(R.id.image);
    }

    public void bind(Ad ad) {
        idView.setText(ad.getId());
        titleView.setText(ad.getTitle());
        priceView.setText(ad.getPrice() + context.getString(R.string.currency));
        cityView.setText(ad.getCity() + " " + "(" + ad.getPostcode() + ")");
        dateView.setText(ad.getFormattedDate());
        if (ad.getImages().size() > 0) {
            Picasso.with(imageView.getContext()).load(ad.getImages().get(0)).centerCrop().fit().into(imageView);
        } else {
            imageView.setVisibility(View.GONE);
        }
    }
}
