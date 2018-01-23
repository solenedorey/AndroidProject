package dnr2i.master.unicaen.fr.androidproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("Registered")
public class PreferencesActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pref);
        EditText emailEditText = findViewById(R.id.addEmailPrefField);
        EditText telEditText = findViewById(R.id.addTelPrefField);
        EditText pseudoEditText = findViewById(R.id.addUsernameField);
        @SuppressLint("WrongConstant") SharedPreferences sharedPref = this.getSharedPreferences(String.valueOf(R.string.my_pref_file_key), Context.MODE_PRIVATE);
        String usernamePref = sharedPref.getString("username", null);
        String emailPref = sharedPref.getString("email", null);
        String telPref = sharedPref.getString("tel", null);
        if(usernamePref != null){
            pseudoEditText.setText(usernamePref);
        }
        if(emailPref != null){
            emailEditText.setText(emailPref);
        }
        if(telPref != null){
            telEditText.setText(telPref);
        }
    }

    public void submitPreferences(View view) {
        EditText usernameEditText = (EditText) findViewById(R.id.addUsernameField);
        EditText emailEditText = (EditText) findViewById(R.id.addEmailPrefField);
        EditText telEditText = (EditText) findViewById(R.id.addTelPrefField);

        @SuppressLint("WrongConstant") SharedPreferences sharedPref = this.getSharedPreferences(String.valueOf(R.string.my_pref_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username", usernameEditText.getText().toString());
        editor.putString("email", emailEditText.getText().toString());
        editor.putString("tel", telEditText.getText().toString());
        editor.commit();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
