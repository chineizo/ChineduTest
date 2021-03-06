package mobilex.demo.com.chinedutest.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import mobilex.demo.com.chinedutest.R;
import mobilex.demo.com.chinedutest.fragment.GamesFragment;

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

        GamesFragment gamesFragment = (GamesFragment)getSupportFragmentManager().findFragmentById(R.id.container);
        if (gamesFragment != null) {
            gamesFragment.setHasOptionsMenu(true);
        }
    }


}
