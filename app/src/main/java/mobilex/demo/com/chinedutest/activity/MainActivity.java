package mobilex.demo.com.chinedutest.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import mobilex.demo.com.chinedutest.R;
import mobilex.demo.com.chinedutest.adapter.GamesListAdapter;
import mobilex.demo.com.chinedutest.data.GameData;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView listGameTextView;
    private TextView rateGameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        listGameTextView = (TextView) findViewById(R.id.list_game_textview);
        listGameTextView.setOnClickListener(this);

        rateGameTextView = (TextView) findViewById(R.id.rate_game_textview);
        rateGameTextView.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.list_game_textview) {
            Intent intent = new Intent(MainActivity.this, ListGamesActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.rate_game_textview){
            Intent intent = new Intent(MainActivity.this, RateGamesActivity.class);
            startActivity(intent);
        }
    }
}
