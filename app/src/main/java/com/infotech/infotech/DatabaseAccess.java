package com.infotech.infotech;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class DatabaseAccess {
    private final SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context Should be valid context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public List<String> getName() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT name FROM language", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<String> getType() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT type FROM language", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<String> getDescription() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT description FROM language", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<String> getDescriptionEn() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT descriptionEn FROM language", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<byte[]> getImg() {
        List<byte[]> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT img FROM language", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String bg = cursor.getString(0);
            list.add(Base64.getDecoder().decode(cursor.getString(0)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}