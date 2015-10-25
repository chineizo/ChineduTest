package mobilex.demo.com.chinedutest.data;

import android.graphics.Bitmap;

import java.util.Arrays;

/**
 * Created by chinedu.nweze on 10/22/15.
 *
 *  Class representing data model of the Games application rendered to the GameListAdapter
 */
public class GameData {
    private int id;
    private String gameName;
    private String consoleName;
    private boolean isCompleted;
    private byte [] gameIcon;
    private float rating = 0f;

    // Class constructor
    public GameData () {

    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getConsoleName() {
        return consoleName;
    }

    public void setConsoleName(String consoleName) {
        this.consoleName = consoleName;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setGameCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    public byte [] getGameIcon() {
        return gameIcon;
    }

    public void setGameIcon(byte [] gameIcon) {
        this.gameIcon = gameIcon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "GameData{" +
                "id=" + id +
                ", gameName='" + gameName + '\'' +
                ", consoleName='" + consoleName + '\'' +
                ", isCompleted=" + isCompleted +
                ", rating=" + rating +
                '}';
    }
}
