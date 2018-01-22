package dnr2i.master.unicaen.fr.androidproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ViewActivity extends Activity {
    private SliderLayout sliderShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            Ad ad = extras.getParcelable("ad");
            if (ad != null) {
                displayAd(ad);
            }
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://ensweb.users.info.unicaen.fr/android-api/?apikey=dnr2&method=randomAd";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject adData = (JSONObject) response.get("response");
                            Ad ad = new Ad(
                                    adData.getString("id"),
                                    adData.getString("titre"),
                                    adData.getString("description"),
                                    adData.getDouble("prix"),
                                    adData.getString("pseudo"),
                                    adData.getString("emailContact"),
                                    adData.getString("telContact"),
                                    adData.getString("ville"),
                                    adData.getString("cp"),
                                    adData.getLong("date")
                            );
                            if (adData.has("images")) {
                                ad.setImages(adData.getJSONArray("images"));
                            }
                            displayAd(ad);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        queue.add(jsonObjectRequest);
    }

    public void displayAd(Ad ad) {
        ((TextView) findViewById(R.id.viewTitle)).setText(ad.getTitle());
        ((TextView) findViewById(R.id.viewDescription)).setText(ad.getDescription());
        ((TextView) findViewById(R.id.viewPrice)).setText(String.valueOf(ad.getPrice()));
        ((TextView) findViewById(R.id.viewPseudo)).setText(ad.getPseudo());
        ((TextView) findViewById(R.id.viewEmail)).setText(ad.getEmail());
        ((TextView) findViewById(R.id.viewPhone)).setText(ad.getPhone());
        ((TextView) findViewById(R.id.viewCity)).setText(ad.getCity());
        ((TextView) findViewById(R.id.viewPostcode)).setText(ad.getPostcode());
        Date date = new Date(ad.getDate() * 1000L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ((TextView) findViewById(R.id.viewDate)).setText(dateFormat.format(date));
        makeSlider(ad.getImages());
    }

    private void makeSlider(ArrayList<String> images) {
        this.sliderShow = (SliderLayout) findViewById(R.id.slider);
        if (images != null) {
            for (String imageUrl : images) {
                DefaultSliderView defaultSliderView = new DefaultSliderView(this);
                defaultSliderView
                        .image(imageUrl)
                        .setScaleType(BaseSliderView.ScaleType.Fit);
                this.sliderShow.addSlider(defaultSliderView);
            }
        } else {
            this.sliderShow.setVisibility(View.GONE);
        }
    }

    private void testxml() {
            /*    <com.daimajia.slider.library.SliderLayout
        android:id="@+id/slider"
        android:layout_width="200dp"
        android:layout_height="200dp"
                />*/
        SliderLayout sliderLayout = new SliderLayout(this);

    }

    @Override
    protected void onStop() {
        this.sliderShow.stopAutoCycle();
        super.onStop();
    }
}
