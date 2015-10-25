package mobilex.demo.com.chinedutest.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mobilex.demo.com.chinedutest.R;
import mobilex.demo.com.chinedutest.data.Content;
import mobilex.demo.com.chinedutest.data.GameData;

/**
 *  Created by chinedu.nweze on 10/22/15.


 /**
 * Adapter class that renders the Games Data to the ListView
 */
public class GamesListAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<GameData> gameDataList;
    private boolean isShowRatings;
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
        notifyDataSetChanged();
    }

    public void clearGameData (){
        gameDataList.clear();
    }

   public void setShowRatings (boolean showRatings) {
       this.isShowRatings = showRatings;
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
            viewHolder.checkedBox = (CheckBox)convertView.findViewById(R.id.checkbox);
            viewHolder.ratingBar = (RatingBar)convertView.findViewById(R.id.games_rating);
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        final GameData gameData = gameDataList.get(position);
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

            viewHolder.checkedBox.setVisibility(isShowRatings ? View.GONE : View.VISIBLE);
            viewHolder.checkedBox.setChecked(gameData.isCompleted());

            viewHolder.ratingBar.setVisibility(isShowRatings ? View.VISIBLE : View.GONE);
            viewHolder.ratingBar.setRating(gameData.getRating());

            viewHolder.checkedBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    gameData.setIsCompleted(isChecked);
                    Content.getInstance(context).updateGameData(gameData);
                }
            });

            viewHolder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    gameData.setRating(rating);
                    Content.getInstance(context).updateGameData(gameData);
                }
            });
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
        RatingBar ratingBar;
        CheckBox checkedBox;

    }
}
