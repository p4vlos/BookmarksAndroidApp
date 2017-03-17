import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import pavlosnicolaou.bookmarks.BookmarkAdapter;

/**
 * Created by vl1s on 17/03/2017.
 */

public class BookmarksDBHelper extends SQLiteOpenHelper {

    //DB settings
    public static final String TABLE_NAME = "bookmarks";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_URL = "url";

    private static final String DATABASE_NAME = "bookmarks.db";
    private static final int DATABASE_VERSION = 1;

    //DB create sql command
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TITLE + " VARCHAR(80) NOT NULL, "
            + COLUMN_URL + " VARCHAR(255) NOT NULL;";

    private static BookmarksDBHelper instance;

    //DB fields
    private SQLiteDatabase database;
    private Cursor dbCursor;

    public static BookmarksDBHelper getInstance(Context context) {
        if (instance == null)
            instance = new BookmarksDBHelper(context);
        return instance;
    }

    //Constructor
    public BookmarksDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() throws SQLException {
        database = getWritableDatabase();

        if (isEmpty()) {
            addBookmark("Google search", "http://www.google.com/");
            addBookmark("Gmail", "http://mail.google.com/");
            addBookmark("Google Calendar", "http://www.google.com/calendar/");
            addBookmark("Facebook", "http://www.facebook.com/");
            addBookmark("Twitter", "http://www.twitter.com/");
            addBookmark("Flickr", "http://www.flickr.com/");
            addBookmark("Guardian news", "http://www.guardian.co.uk/");
            addBookmark("BBC News", "http://www.bbc.co.uk/news/");
        }
        //data model is ready
    }

    public void close() {
        if (database != null)
            database.close();
        database = null;
    }

    public void addBookmark(String title, String url) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_URL, url);

        long insertId = database.insert(TABLE_NAME, null, values);
    }

    public Cursor getBookmarks() {
        String[] allColumns = {
                COLUMN_ID,
                COLUMN_TITLE,
                COLUMN_URL
        };

        dbCursor = database.query(TABLE_NAME, allColumns, null, null, null, null, null);
        return dbCursor;
    }

    public boolean isEmpty() {
        dbCursor = getBookmarks();
        long size = dbCursor.getCount();
        dbCursor.close();
        return(size == 0);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
