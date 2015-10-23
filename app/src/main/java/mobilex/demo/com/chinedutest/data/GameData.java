package mobilex.demo.com.chinedutest.data;

import android.graphics.Bitmap;

/**
 * Created by chinedu.nweze on 10/22/15.
 *
 *  Class representing data model of the Games application rendered to the GameListAdapter
 */
public class GameData {
    private String gameName;
    private String consoleName;
    private boolean isRated;
    private Bitmap gameIcon;

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

    public boolean isRated() {
        return isRated;
    }

    public void setIsRated(boolean isRated) {
        this.isRated = isRated;
    }

    public Bitmap getGameIcon() {
        return gameIcon;
    }

    public void setGameIcon(Bitmap gameIcon) {
        this.gameIcon = gameIcon;
    }

    @Override
    public String toString() {
        return "GameData{" +
                "gameName='" + gameName + '\'' +
                ", consoleName='" + consoleName + '\'' +
                ", isRated=" + isRated +
                '}';
    }
}
