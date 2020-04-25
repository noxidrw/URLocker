package com.example.urlocker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author William Dixon
 * @version 1.0
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_URLOCKER = "urlocker";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ADDRESS = "urlAddress";
    public static final String COLUMN_FILTER = "website";

    private static final String DATABASE_NAME = "urlocker.db";
    private static final int DATABASE_VERSION = 3;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_URLOCKER + "( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_ADDRESS + " text not null, "
            + COLUMN_FILTER + " text);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_URLOCKER);
        onCreate(db);
    }

}