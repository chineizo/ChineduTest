package mobilex.demo.com.chinedutest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mobilex.demo.com.chinedutest.R;

/**
 * Created by chinedu.nweze on 10/22/15.
 *
 *
 *  Class that displays the list of games the user has installed on their device
 */
public class ListGamesActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_content_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(ListGamesActivity.class.getSimpleName());
    }
}
