package dnr2i.master.unicaen.fr.androidproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void submit(View view) {
        String title = ((EditText) findViewById(R.id.addTtitleField)).getText().toString();

        double price;
        try {
            price = Double.parseDouble(((EditText) findViewById(R.id.addPriceField)).getText().toString());
        } catch (NumberFormatException e) {
            price = -1;
        }

        String description = ((EditText) findViewById(R.id.addDescriptionField)).getText().toString();
        String email = ((EditText) findViewById(R.id.addEmailField)).getText().toString();
        String pseudo = ((EditText) findViewById(R.id.addPseudoField)).getText().toString();
        String phone = ((EditText) findViewById(R.id.addPhoneField)).getText().toString();
        String city = ((EditText) findViewById(R.id.addCityField)).getText().toString();

        String postcode = ((EditText) findViewById(R.id.addPostcodeField)).getText().toString();

        System.out.println(title);
        System.out.println(price);
        System.out.println(description);
        System.out.println(email);
        System.out.println(pseudo);
        System.out.println(phone);
        System.out.println(city);
        System.out.println(postcode);

        final Ad ad = new Ad(null, title, description, price, pseudo, email, phone, city, postcode, new Date().getTime());

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://ensweb.users.info.unicaen.fr/android-api/";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        try {
                            JSONObject json = new JSONObject(response.toString());
                            JSONObject data = (JSONObject) json.get("response");
                            Ad ad = new Ad(
                                    data.get("id").toString(),
                                    data.get("titre").toString(),
                                    data.get("description").toString(),
                                    Double.parseDouble(data.get("prix").toString()),
                                    data.get("pseudo").toString(),
                                    data.get("emailContact").toString(),
                                    data.get("telContact").toString(),
                                    data.get("ville").toString(),
                                    data.get("cp").toString(),
                                    Long.parseLong(data.get("date").toString())
                            );
                            Intent intent = new Intent(AddActivity.this, ViewActivity.class);
                            intent.putExtra("ad", ad);
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("apikey", "dnr2");
                params.put("method", "save");
                params.put("titre", ad.getTitle());
                params.put("description", ad.getDescription());
                params.put("prix", String.valueOf(ad.getPrice()));
                params.put("pseudo", ad.getPseudo());
                params.put("emailContact", ad.getEmail());
                params.put("telContact", ad.getPhone());
                params.put("ville", ad.getCity());
                params.put("cp", ad.getPostcode());
                return params;
            }
        };

        queue.add(stringRequest);
    }
}
