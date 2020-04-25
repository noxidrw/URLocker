package com.example.urlocker;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
/**
 * TheLockerDataSource class.
 *
 * @author William Dixon
 * @version 1.0
 */
public class TheLockerDataSource
{
    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_ADDRESS,MySQLiteHelper.COLUMN_FILTER };

    public TheLockerDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public TheLocker createTheLocker(String urlAddress, String filter) {
        ContentValues values = new ContentValues();
        database = dbHelper.getWritableDatabase();
        values.put(MySQLiteHelper.COLUMN_ADDRESS, urlAddress);
        values.put(MySQLiteHelper.COLUMN_FILTER, filter);
        long insertId = database.insert(MySQLiteHelper.TABLE_URLOCKER, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_URLOCKER,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        TheLocker newTheLocker = cursorToTheLocker(cursor);
        cursor.close();
        return newTheLocker;
    }

    public void deleteTheLocker(TheLocker urlAddress) {
        long id = urlAddress.getId();
        System.out.println("URL deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_URLOCKER, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<TheLocker> getAllUrlocker() {
        List<TheLocker> urlocker = new ArrayList<TheLocker>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_URLOCKER,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TheLocker urlAddress = cursorToTheLocker(cursor);
            urlocker.add(urlAddress);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return urlocker;
    }

    private TheLocker cursorToTheLocker(Cursor cursor) {
        TheLocker urlAddress = new TheLocker();
        urlAddress.setId(cursor.getLong(cursor.getColumnIndex(MySQLiteHelper.COLUMN_ID)));
        urlAddress.setFilter(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_FILTER)));
        urlAddress.setUrlAddress(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_ADDRESS)));
        return urlAddress;
    }
}
