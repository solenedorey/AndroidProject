package dnr2i.master.unicaen.fr.androidproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

        int postcode;
        try {
            postcode = Integer.parseInt(((EditText) findViewById(R.id.addPostcodeField)).getText().toString());
        } catch (NumberFormatException e) {
            postcode = -1;
        }

        System.out.println(title);
        System.out.println(price);
        System.out.println(description);
        System.out.println(email);
        System.out.println(pseudo);
        System.out.println(phone);
        System.out.println(city);
        System.out.println(postcode);
    }
}
