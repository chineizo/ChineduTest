package mobilex.demo.com.chinedutest.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chinedu on 10/24/15.
 */
public class Content {
    private static Content sInstance = null;
    private SQLiteDatabase sqLiteDatabase;

    /**
     * Singleton instance
     *
     * @param context
     * @return DataSource
     */
    public static Content getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new Content(context);
        }
        return sInstance;
    }

    /**
     * Constructor
     *
     * @param context
     */
    private Content(Context context) {
        sqLiteDatabase = GameDatabase.getSQLiteDatabase(context.getApplicationContext());
    }


    /**
     * Sample method of adding data to the database
     */
    public long addGame(GameData gameData) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(GameDatabase.GamesColumns.DB_COL_NAME, gameData.getGameName());
        contentValues.put(GameDatabase.GamesColumns.DB_COL_CONSOLE, gameData.getConsoleName());
        contentValues.put(GameDatabase.GamesColumns.DB_COL_IMAGE, gameData.getGameIcon());
        contentValues.put(GameDatabase.GamesColumns.DB_COL_COMPLETED, gameData.isCompleted());
        contentValues.put(GameDatabase.GamesColumns.DB_COL_RATING, gameData.getRating());
        return sqLiteDatabase.insert(GameDatabase.DB_TABLE_GAMES, null, contentValues);
    }

    /**
     * Update the database with new data in the GameData object and return the number of rows updated
     */
    public int updateGameData (GameData gameData) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(GameDatabase.GamesColumns.DB_COL_NAME, gameData.getGameName());
        contentValues.put(GameDatabase.GamesColumns.DB_COL_CONSOLE, gameData.getConsoleName());
        contentValues.put(GameDatabase.GamesColumns.DB_COL_IMAGE, gameData.getGameIcon());
        contentValues.put(GameDatabase.GamesColumns.DB_COL_COMPLETED, gameData.isCompleted());
        contentValues.put(GameDatabase.GamesColumns.DB_COL_RATING, gameData.getRating());
        return sqLiteDatabase.update(GameDatabase.DB_TABLE_GAMES, contentValues,
                GameDatabase.GamesColumns._ID + " = ?", new String [] {String.valueOf(gameData.getId())});
    }

    /**
     * Sample method of retrieving data from the database
     *
     * @return list of GameData objects
     */
    public List<GameData> getGames() {
        final List<GameData> list = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.query(GameDatabase.DB_TABLE_GAMES, null, null, null, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    int id = cursor.getInt(cursor.getColumnIndex(GameDatabase.GamesColumns._ID));
                    String gameName = cursor.getString(cursor.getColumnIndex(GameDatabase.GamesColumns.DB_COL_NAME));
                    String gameConsole = cursor.getString(cursor.getColumnIndex(GameDatabase.GamesColumns.DB_COL_CONSOLE));
                    byte[] imageData = cursor.getBlob(cursor.getColumnIndex(GameDatabase.GamesColumns.DB_COL_IMAGE));
                    boolean isCompleted = cursor.getInt(cursor.getColumnIndex(GameDatabase.GamesColumns.DB_COL_COMPLETED)) == 1;
                    float rating = cursor.getFloat(cursor.getColumnIndex(GameDatabase.GamesColumns.DB_COL_RATING));
                    GameData gameData = new GameData();
                    gameData.setId(id);
                    gameData.setGameIcon(imageData);
                    gameData.setConsoleName(gameConsole);
                    gameData.setGameName(gameName);
                    gameData.setRating(rating);
                    gameData.setIsCompleted(isCompleted);
                    list.add(gameData);
                    cursor.moveToNext();
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return list;

    }

}
