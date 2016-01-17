package com.solutionnest.utils.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;

/**
 * Created by kanika on 1/17/2016.
 */
public class ApplicationDBOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="IRController_DB";
    private static final String PROPERTY_TABLE_NAME="Property";
    private static final String  PROPERTY_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + PROPERTY_TABLE_NAME + " (PROPERTY_NAME TEXT,PROPERTY_VALUE TEXT);";
    public ApplicationDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PROPERTY_TABLE_CREATE);
        db.execSQL("INSERT INTO "+PROPERTY_TABLE_NAME+" VALUES('URL','http://lirc.sourceforge.net/remotes');");

    }
    public String fetchLookUpURL()
    {
        String url = null;
        try{
        String selectQuery = "SELECT PROPERTY_VALUE FROM "+PROPERTY_TABLE_NAME + " WHERE PROPERTY_NAME =?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery(selectQuery, new String[]{"URL"});
        if (cur.moveToFirst())
        {
            do {
                    url = cur.getString(0);
            }while (cur.moveToNext());
        }}
        catch (Exception e)
        {
            Log.e("IRController","ApplicationDBOpenHelper :fetchLookUpURL",e.fillInStackTrace());
        }
        return url;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
