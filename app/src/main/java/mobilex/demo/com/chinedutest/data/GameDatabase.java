package mobilex.demo.com.chinedutest.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by chinedu on 10/24/15.
 */
public class GameDatabase extends SQLiteOpenHelper {
    private static final String TAG = GameDatabase.class.getName();
    private static final String DATABASE_NAME = "game.db";
    private static final int DATABASE_VERSION = 1;
    private static GameDatabase gameDbHelper = null;

    // Table names
    public static final String DB_TABLE_GAMES = "games";

    // List of all tables
    public static final String[] ALL_TABLES = {DB_TABLE_GAMES};


    private GameDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Single instance of the the database helper
     *
     * @param applicationContext
     * @return
     */
    protected static synchronized SQLiteDatabase getSQLiteDatabase(Context applicationContext) throws SQLException {
        if (gameDbHelper == null) {
            Log.d(TAG, "db_helper created!");
            gameDbHelper = new GameDatabase(applicationContext);
        }
        return gameDbHelper.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            TableSpec[] tables = createTableSpecs();
            for (TableSpec tableSpec : tables) {
                tableSpec.create(db);
            }

            // Sample data to be displayed in the listview
            GameData gameData = new GameData();
            gameData.setConsoleName("Nintendo 64");
            gameData.setGameName("Super Mario World");
            gameData.setIsCompleted(false);
            gameData.setRating(2.5f);

            ContentValues contentValues = new ContentValues();
            contentValues.put(GameDatabase.GamesColumns.DB_COL_NAME, gameData.getGameName());
            contentValues.put(GameDatabase.GamesColumns.DB_COL_CONSOLE, gameData.getConsoleName());
            contentValues.put(GameDatabase.GamesColumns.DB_COL_IMAGE, gameData.getGameIcon());
            contentValues.put(GameDatabase.GamesColumns.DB_COL_COMPLETED, gameData.isCompleted());
            contentValues.put(GameDatabase.GamesColumns.DB_COL_RATING, gameData.getRating());
            db.insert(GameDatabase.DB_TABLE_GAMES, null, contentValues);


        } catch (SQLException e) {
            Log.e(TAG, "SQL Exception - failed to create database tables.", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG,
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        try {
            dropAllTable(db);
            onCreate(db);
        } catch (Exception e) {
            Log.w(TAG, "Error deleting old tables :", e);
        }
    }


    protected void dropAllTable(SQLiteDatabase db) {
        TableSpec[] tableSpecs = createTableSpecs();
        for (TableSpec spec : tableSpecs) {
            dropTable(db, spec.getTableName());
        }
    }

    protected void dropTable(SQLiteDatabase db, String tableName) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
    }

    protected void addTable(SQLiteDatabase db, TableSpec tableSpec) {
        tableSpec.create(db);
    }

    public void addColumn(SQLiteDatabase db, String tableName, String colName, String colType) {
        String sqlAlterTable = "ALTER TABLE " + tableName + "ADD COLUMN " + colName + " " + colType + ";";
        db.execSQL(sqlAlterTable);
    }

    /**
     * Tabe columns definition
     * @return Table Specifications representing the table columns
     */
    protected TableSpec[] createTableSpecs() {
        TableSpec[] tables = {
                new TableSpec(DB_TABLE_GAMES, new String[]{
                        GamesColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT",
                        GamesColumns.DB_COL_NAME + " TEXT NOT NULL",
                        GamesColumns.DB_COL_CONSOLE + " TEXT NOT NULL",
                        GamesColumns.DB_COL_IMAGE + " BLOB",
                        GamesColumns.DB_COL_COMPLETED + " INTEGER",
                        GamesColumns.DB_COL_RATING + " FLOAT",

                })
        };
        return tables;
    }

    /**
     * A table model that helps in creating the table columns
     */
    protected static class TableSpec {
        private final String tableName;
        private final String[] statement;

        public TableSpec(String tableName, String[] statement) {
            this.tableName = tableName;
            this.statement = statement;
        }

        public String getTableName() {
            return tableName;
        }

        public String[] getStatement() {
            return statement;
        }

        public void create(SQLiteDatabase db) {
            int length = statement.length;
            StringBuilder sql = new StringBuilder();
            sql.append("CREATE TABLE IF NOT EXISTS " + tableName);
            sql.append(" (");
            for (int i = 0; i < length; i++) {
                sql.append(statement[i]);
                if (i < length - 1) {
                    sql.append(", ");
                }
            }
            sql.append(");");
            db.execSQL(sql.toString());
            Log.d(TAG, "Query:" + sql.toString());
        }
    }
    /**
     * Column definitions while inheriting base column definitions e.g _id
     */
    public static abstract class GamesColumns implements BaseColumns {
        // Columns names
        public static final String DB_COL_NAME = "name";
        public static final String DB_COL_CONSOLE = "console";
        public static final String DB_COL_IMAGE = "image";
        public static final String DB_COL_COMPLETED = "completed";
        public static final String DB_COL_RATING = "rating";
    }
}