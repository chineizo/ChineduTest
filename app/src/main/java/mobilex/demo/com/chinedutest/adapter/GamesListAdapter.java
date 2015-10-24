package mobilex.demo.com.chinedutest.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mobilex.demo.com.chinedutest.R;
import mobilex.demo.com.chinedutest.data.GameData;

/**
 * Created by chinedu.nweze on 10/22/15.
 */
public class GamesListAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<GameData> gameDataList;
    private Context context;
    // Constructor
    public GamesListAdapter (Context context) {
        this.context = context;
        gameDataList = new ArrayList<>();
        layoutInflater = LayoutInflater.from(context);
    }

    public void addGameData (GameData gameData) {
        gameDataList.add(gameData);
    }

    public void addGameDataList (List<GameData> gameDataList) {
            this.gameDataList = gameDataList;
    }

    public void clearGameData (){
        gameDataList.clear();
    }


    @Override
    public int getCount() {
        return gameDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return gameDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.game_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.consoleName = (TextView)convertView.findViewById(R.id.game_console_title);
            viewHolder.gameImage = (ImageView)convertView.findViewById(R.id.game_image_view);
            viewHolder.gameTitle = (TextView)convertView.findViewById(R.id.game_title);
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        GameData gameData = gameDataList.get(position);
        if (gameData != null) {
            if (gameData.getGameIcon() != null) {
                final Bitmap bitmap  = BitmapFactory.decodeByteArray(gameData.getGameIcon(), 0, gameData.getGameIcon().length);
                viewHolder.gameImage.setImageBitmap(bitmap);
            }else{
                // place a generic image in the view
                viewHolder.gameImage.setImageResource(R.mipmap.ic_launcher);
            }

            if (!TextUtils.isEmpty(gameData.getGameName())) {
                viewHolder.gameTitle.setText(gameData.getGameName());
            }else{
                viewHolder.gameTitle.setText(R.string.unknown);
            }

            if (!TextUtils.isEmpty(gameData.getConsoleName())) {
                viewHolder.consoleName.setText(gameData.getConsoleName());
            } else {
                viewHolder.consoleName.setText(R.string.unknown);
            }
        }
        return convertView;
    }

    /**
     * View holder pattern for better list view recycling of game item objects
     */
    private static class ViewHolder {
        ImageView gameImage;
        TextView gameTitle;
        TextView consoleName;
        boolean isRated;


    }
}
