package mobilex.demo.com.chinedutest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mobilex.demo.com.chinedutest.R;

/**
 * Created by chinedu.nweze on 10/22/15.
 * <p/>
 * <p/>
 * Class that allows user rate the games played on their device
 */
public class RateGamesActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_content_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(RateGamesActivity.class.getSimpleName());
    }

}



