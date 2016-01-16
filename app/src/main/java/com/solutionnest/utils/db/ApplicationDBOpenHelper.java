package com.solutionnest.utils.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kanika on 1/17/2016.
 */
public class ApplicationDBOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="IRController_DB";
    private static final String PROPERTY_TABLE_NAME="Property";
    private static final String  PROPERTY_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS" + PROPERTY_TABLE_NAME + " (PROPERTY_NAME TEXT,PROPERTY_VALUE TEXT);";
    public ApplicationDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PROPERTY_TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
