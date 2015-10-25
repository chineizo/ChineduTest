package mobilex.demo.com.chinedutest.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import mobilex.demo.com.chinedutest.R;
import mobilex.demo.com.chinedutest.adapter.GamesListAdapter;
import mobilex.demo.com.chinedutest.data.Content;
import mobilex.demo.com.chinedutest.data.GameData;
import mobilex.demo.com.chinedutest.data.GameDatabase;
import mobilex.demo.com.chinedutest.utility.Utility;

/**
 * Created by chinedu.nweze on 10/22/15.
 * <p/>
 * Class that represents the Games data, such as Console name, Game image, Game name and Ratings
 */
public class GamesFragment extends Fragment implements View.OnClickListener {
    private final String TAG = GamesFragment.class.getName();
    private final static int PHOTO_CODE = 101;
    private ListView listView;
    private EditText addGameNameEditText;
    private EditText consoleNameEditText;
    private ImageView gameIconView;
    private View addNewGameLayout;
    private Bitmap gameIcon;
    private GamesListAdapter gamesListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceSstate) {
        super.onCreate(savedInstanceSstate);
        setHasOptionsMenu(true);


    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(layoutInflater, container, savedInstanceState);
        return layoutInflater.inflate(R.layout.fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find view and set up click listeners on the views
        Button addGameButton = (Button) view.findViewById(R.id.add_game_button);
        addGameButton.setOnClickListener(this);
        Button cancelButton = (Button) view.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(this);
        Button changeGameIcon = (Button) view.findViewById(R.id.add_game_icon);
        changeGameIcon.setOnClickListener(this);

        consoleNameEditText = (EditText) view.findViewById(R.id.game_console_title);
        addGameNameEditText = (EditText) view.findViewById(R.id.game_title);
        gameIconView = (ImageView) view.findViewById(R.id.game_icon_view);
        listView = (ListView) view.findViewById(R.id.game_listview);
        addNewGameLayout = view.findViewById(R.id.add_new_game_layout);

        // Sample data to be displayed in the listview
        GameData gameData = new GameData();
        gameData.setConsoleName("Nintendo");
        gameData.setGameName("Game Boy");
        //        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.vx_04_post_btn_video);
//        byte[] data = getBytes(bitmap);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        gameData.setGameIcon(Utility.getByteArray(bitmap));


        gamesListAdapter = new GamesListAdapter(getActivity());
        gamesListAdapter.addGameData(gameData);
        listView.setAdapter(gamesListAdapter);

        // Update the listview with the list of games in the sqlite database
        final List<GameData> gameDataList = Content.getInstance(getActivity()).getGames();
        if (gameDataList != null) {
            gamesListAdapter.addGameDataList(gameDataList);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_menu_option) {
            showAddGameForm();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showAddGameForm () {
        // swap out the layouts to allow user interact with game add form
        listView.setVisibility(View.GONE);
        addNewGameLayout.setVisibility(View.VISIBLE);
    }

    public void addNewGame() {
        listView.setVisibility(View.VISIBLE);
        addNewGameLayout.setVisibility(View.GONE);

        // Collecting game data to be store in the Game object
        final String consoleName = consoleNameEditText.getText().toString();
        final String gameName = addGameNameEditText.getText().toString();
        final byte[] gameIcon = Utility.getByteArray(getGameIcon());

        // Prepare game object to be stored
        GameData gameData = new GameData();
        gameData.setConsoleName(consoleName);
        gameData.setGameName(gameName);
        gameData.setGameIcon(gameIcon);

        // Store game data in sqlite database
        Content.getInstance(getActivity()).addGame(gameData);

        // Refresh the list view with new data from SQlite
        List<GameData> gameDataList = Content.getInstance(getActivity()).getGames();
        gamesListAdapter.addGameDataList(gameDataList);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cancel_button) {
            // Restore ListView and hide the Add Game Layout
            listView.setVisibility(View.VISIBLE);
            addNewGameLayout.setVisibility(View.GONE);
        } else if (v.getId() == R.id.add_game_button) {
            // Add game to the sqlite storage
            addNewGame();
        } else if (v.getId() == R.id.add_game_icon) {
            //Launch the photo library to select a game icon
            final Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, getString(R.string.select_picture)), PHOTO_CODE);
        }
    }

    public void setBitmap(Bitmap bitmap) {
        this.gameIcon = bitmap;
    }

    public Bitmap getGameIcon() {
        return gameIcon;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PHOTO_CODE) {
                Uri uri = data.getData();
                if (uri != null) {
                    try {
                        final Bitmap bitmap = Utility.getBitmap(getActivity(), uri);
                        setBitmap(bitmap);
                        gameIconView.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        Log.e(TAG, "File not found:" + e);
                    }
                }
            }
        }
    }

}
