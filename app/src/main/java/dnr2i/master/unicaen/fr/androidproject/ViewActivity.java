package dnr2i.master.unicaen.fr.androidproject;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewActivity extends Activity {

    private SliderLayout sliderShow;
    private Ad ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            ad = extras.getParcelable("ad");
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
                            JSONObject jsonObject = (JSONObject) response.get("response");
                            ad = new Ad(jsonObject);
                            if (jsonObject.has("images")) {
                                ad.setImages(jsonObject.getJSONArray("images"));
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
        ((TextView) findViewById(R.id.viewTitle)).setText(ad.getTitle() + " - " + String.valueOf(ad.getPrice()) + this.getString(R.string.currency));
        ((TextView) findViewById(R.id.viewDescription)).setText(ad.getDescription());
        ((TextView) findViewById(R.id.viewEmail)).setText(ad.getEmail());
        ((TextView) findViewById(R.id.viewPhone)).setText(ad.getPhone());
        ((TextView) findViewById(R.id.viewCityPostcode)).setText("City : " + ad.getCity() + " (" + ad.getPostcode() + ")");
        ((TextView) findViewById(R.id.viewDatePseudo)).setText("Added the " + ad.getFormattedDate() + " by " + ad.getPseudo() + ".");
        makeSlider(ad.getImages());
    }

    private void makeSlider(ArrayList<String> images) {
        this.sliderShow = findViewById(R.id.slider);
        if (images != null) {
            for (String imageUrl : images) {
                DefaultSliderView defaultSliderView = new DefaultSliderView(this);
                defaultSliderView
                        .image(imageUrl)
                        .setScaleType(BaseSliderView.ScaleType.CenterInside);
                this.sliderShow.addSlider(defaultSliderView);
            }
        } else {
            this.sliderShow.setVisibility(View.GONE);
        }
    }

    public void call(View view) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + ad.getPhone()));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Toast toast = Toast.makeText(getApplicationContext(), "Operation not supported.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        startActivity(callIntent);
    }

    public void email(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { ad.getEmail() });
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, ad.getTitle());
        try {
            startActivity(emailIntent);
        } catch (ActivityNotFoundException e) {
            Toast toast = Toast.makeText(getApplicationContext(), "Operation not supported.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    protected void onStop() {
        this.sliderShow.stopAutoCycle();
        super.onStop();
    }
}
