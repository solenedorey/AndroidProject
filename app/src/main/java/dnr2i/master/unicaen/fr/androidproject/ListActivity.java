package dnr2i.master.unicaen.fr.androidproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.view.View;
import android.widget.TextView;

import com.android.volley.*;
import com.android.volley.toolbox.*;

import org.json.*;

import java.util.ArrayList;

public class ListActivity extends Activity {

    private RecyclerView recyclerView;

    private ArrayList<Ad> ads = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // get the data passed by homeActivity
        final String searchInput = getIntent().getStringExtra("SEARCH_INPUT");

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://ensweb.users.info.unicaen.fr/android-api/?apikey=dnr2&method=listAll";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                parseResponse(response, searchInput);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        queue.add(jsonObjectRequest);

        recyclerView = findViewById(R.id.ads);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void displayAd(View view) {
        Intent intent = new Intent(this, ViewActivity.class);
        for (Ad ad : ads) {
            if (ad.getId().equals(((TextView) view.findViewById(R.id.adsListId)).getText().toString())) {
                intent.putExtra("ad", ad);
                break;
            }
        }
        startActivity(intent);
    }

    protected void parseResponse(JSONObject response, String searchInput) {
        try {
            JSONArray jsonArray = response.getJSONArray("response");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Ad ad = new Ad(jsonObject);

                if (searchInput == null) {
                    ads.add(ad);
                } else if (ad.getTitle().equals(searchInput)) {
                    ads.add(ad);
                }
            }
            if (ads.isEmpty()) {
                TextView errorMessage = new TextView(this);
                errorMessage.setText("No ads found.");


            } else {
                recyclerView.setAdapter(new AdAdapter(this, ads));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
