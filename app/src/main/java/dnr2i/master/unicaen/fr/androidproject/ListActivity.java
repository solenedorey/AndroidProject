package dnr2i.master.unicaen.fr.androidproject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.*;
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

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://ensweb.users.info.unicaen.fr/android-api/?apikey=dnr2&method=listAll";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response.toString());
                parseResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        queue.add(jsonObjectRequest);

        recyclerView = (RecyclerView) findViewById(R.id.ads);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    protected void parseResponse(JSONObject response) {

        try {
            JSONArray jsonArray = response.getJSONArray("response");


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject adData = (JSONObject) jsonArray.get(i);
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
                ads.add(ad);
            }
            recyclerView.setAdapter(new AdAdapter(this, ads));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
