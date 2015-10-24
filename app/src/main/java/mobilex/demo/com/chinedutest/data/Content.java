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
         *
         * @param context
         */
        public long addGame(GameData gameData) {
//        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.vx_04_post_btn_video);
//        byte[] data = getBytes(bitmap);
            ContentValues contentValues = new ContentValues();
            contentValues.put(GameDatabase.GamesColumns.DB_COL_NAME, gameData.getGameName());
            contentValues.put(GameDatabase.GamesColumns.DB_COL_CONSOLE, gameData.getConsoleName());
            contentValues.put(GameDatabase.GamesColumns.DB_COL_IMAGE,gameData.getGameIcon() );
            long rowId = sqLiteDatabase.insert(GameDatabase.DB_TABLE_GAMES, null, contentValues);
            return rowId;
        }

        /**
         * Sample method of retrieving data from the database
         * @return list of GameData objects
         */
        public List<GameData> getGames() {
            final List<GameData> list = new ArrayList<>();
            Cursor cursor = null;
            try {
                cursor = sqLiteDatabase.query(GameDatabase.DB_TABLE_GAMES, null, null, null, null, null, null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    //int id = cursor.getInt(cursor.getColumnIndex(GameDatabase.GamesColumns._ID));
                    String gameName = cursor.getString(cursor.getColumnIndex(GameDatabase.GamesColumns.DB_COL_NAME));
                    String gameConsole = cursor.getString(cursor.getColumnIndex(GameDatabase.GamesColumns.DB_COL_CONSOLE));
                    byte[] imageData = cursor.getBlob(cursor.getColumnIndex(GameDatabase.GamesColumns.DB_COL_IMAGE));
                    GameData gameData = new GameData();
                    gameData.setGameIcon(imageData);
                    gameData.setConsoleName(gameConsole);
                    gameData.setGameName(gameName);
                    list.add(gameData);
                    cursor.moveToNext();
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
            return list;

        }

}
