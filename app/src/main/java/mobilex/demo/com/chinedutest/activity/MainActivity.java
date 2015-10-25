package mobilex.demo.com.chinedutest.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import java.util.ArrayList;

import mobilex.demo.com.chinedutest.R;
import mobilex.demo.com.chinedutest.adapter.GamesListAdapter;
import mobilex.demo.com.chinedutest.data.GameData;

/**
 * Activity as an entry point allowing user to View a listing of Video games.
 * Two buttons allow the user to launch the ListGames Activity and RateGames Activity.
 * Both activities leverage one Fragment. The Fragment is configurable to displaying a List of Games and
 * a Forms to allow user to add some more games to the games database
 */

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

        // Add some animation to the buttons to fall from the top to a 1/2 of the screen's height
        listGameTextView.clearAnimation();
        listGameTextView.animate()
                .translationY(getDisplayHeight()/2)
                .setDuration(2000)
                .setInterpolator(new BounceInterpolator());


        rateGameTextView.clearAnimation();
        rateGameTextView.animate()
                .translationY(getDisplayHeight()/2)
                .setDuration(2000)
                .setInterpolator(new BounceInterpolator());


    }

    private int getDisplayHeight() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.list_game_textview) {
            Intent intent = new Intent(MainActivity.this, ListGamesActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.rate_game_textview) {
            Intent intent = new Intent(MainActivity.this, RateGamesActivity.class);
            startActivity(intent);
        }
    }
}
