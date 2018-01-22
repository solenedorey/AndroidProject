package dnr2i.master.unicaen.fr.androidproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import org.json.*;

public class ListActivity extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

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

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response.toString());
                    JSONArray ads = new JSONArray(jsonObject.get("response"));
                    for (int i = 0; i < ads.length(); i++) {
                        JSONObject adData = (JSONObject) ads.get(i);
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
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

    }
}
