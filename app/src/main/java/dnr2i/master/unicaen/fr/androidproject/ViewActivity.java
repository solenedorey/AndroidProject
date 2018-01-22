package dnr2i.master.unicaen.fr.androidproject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ViewActivity extends Activity {
    private SliderLayout sliderShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext()); // ou this
        String url = "https://ensweb.users.info.unicaen.fr/android-api/?apikey=dnr2&method=randomAd";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        System.out.println(response.toString());

                        JSONObject json = null;
                        try {
                            json = new JSONObject(response.toString());
                            JSONObject adData = (JSONObject) json.get("response");
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
                                    adData.getInt("date")
                            );
                            if (adData.has("images")) {
                                ad.setImages(adData.getJSONArray("images"));
                            }
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
                            System.out.println(ad);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        queue.add(stringRequest);
    }

    private void makeSlider(ArrayList<String> images) {
        this.sliderShow = (SliderLayout) findViewById(R.id.slider);
        for (String imageUrl : images) {
            DefaultSliderView defaultSliderView = new DefaultSliderView(this);
            defaultSliderView
                    .image(imageUrl)
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            this.sliderShow.addSlider(defaultSliderView);
        }

    }

    @Override
    protected void onStop() {
        this.sliderShow.stopAutoCycle();
        super.onStop();
    }
}
