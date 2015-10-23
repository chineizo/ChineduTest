package mobilex.demo.com.chinedutest.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import mobilex.demo.com.chinedutest.R;
import mobilex.demo.com.chinedutest.adapter.GamesListAdapter;
import mobilex.demo.com.chinedutest.data.GameData;

/**
 * Created by chinedu.nweze on 10/22/15.
 *
 * Class that represents the Games data, such as Console name, Game image, Game name and Ratings
 */
public class GamesFragment extends Fragment{

    @Override
    public void onCreate(Bundle savedInstanceSstate) {
        super.onCreate(savedInstanceSstate);






    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(layoutInflater, container, savedInstanceState);
        return layoutInflater.inflate(R.layout.fragment_layout, container, false);
    }

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ListView listView = (ListView)view.findViewById(R.id.game_listview);





        GameData gameData = new GameData();
        gameData.setConsoleName("Nintendo");
        gameData.setGameName("Game Boy");
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        gameData.setGameIcon(bitmap);


        GamesListAdapter gamesListAdapter = new GamesListAdapter(getActivity());
        gamesListAdapter.addGameData(gameData);
        listView.setAdapter(gamesListAdapter);
    }



}
