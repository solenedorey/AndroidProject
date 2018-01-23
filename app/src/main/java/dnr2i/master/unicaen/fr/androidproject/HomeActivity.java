package dnr2i.master.unicaen.fr.androidproject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        ActionBar actionBar = getActionBar();
        assert actionBar != null;
        actionBar.hide();
    }

    public void add(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    public void random(View view) {
        Intent intent = new Intent(this, ViewActivity.class);
        startActivity(intent);
    }

    public void listAds(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    public void search(View view) {
        EditText searchInput = findViewById(R.id.searchInput);
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("SEARCH_INPUT", searchInput.getText().toString());
        startActivity(intent);
    }

    public void displayFormPref(View view) {
        Intent intent = new Intent(this, PreferencesActivity.class);
        startActivity(intent);
    }
}
