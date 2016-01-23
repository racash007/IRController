package com.solutionnest.utils.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.solutionnest.bean.Device;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kanika on 1/17/2016.
 */
public class ApplicationDBOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="IRController_DB";
    private static final String PROPERTY_TABLE_NAME="Property";
    private  static final String DEVICE_TABLE_NAME="Device";
    private static final String  PROPERTY_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + PROPERTY_TABLE_NAME + " (PROPERTY_NAME TEXT,PROPERTY_VALUE TEXT);";
    private static final String  DEVICE_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + DEVICE_TABLE_NAME + " (DEVICE_ID INTEGER PRIMARY KEY AUTOINCREMENT,DEVICE_NAME TEXT,DEVICE_TYPE TEXT,DEVICE_BRAND TEXT,DEVICE_MODEL TEXT,REMOTE_MODEL TEXT,DEVICE_FILE_PATH TEXT);";
    public ApplicationDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("IRController","ApplicationDBOpenHelper OnCreate");
        db.execSQL(PROPERTY_TABLE_CREATE);
        db.execSQL(DEVICE_TABLE_CREATE);
        db.execSQL("INSERT INTO " + PROPERTY_TABLE_NAME + " VALUES('URL','http://lirc.sourceforge.net/remotes');");

    }
    public String fetchLookUpURL()
    {
        String url = null;
        try{
        String selectQuery = "SELECT PROPERTY_VALUE FROM "+PROPERTY_TABLE_NAME + " WHERE PROPERTY_NAME =?";
        SQLiteDatabase db = getReadableDatabase();
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

    public List<Device> getDeviceList()
    {
        List<Device> deviceList=new ArrayList<>();
        try{
            String selectQuery = "SELECT * FROM "+DEVICE_TABLE_NAME;
            SQLiteDatabase db = getReadableDatabase();
            db.execSQL(DEVICE_TABLE_CREATE);
            Cursor cur = db.rawQuery(selectQuery,null);
            if (cur.moveToFirst())
            {
                do {
                    Device device = new Device();
                    device.setDeviceName(cur.getString(1));
                    device.setDeviceType(cur.getString(2));
                    device.setBrand(cur.getString(3));
                    device.setDeviceModelNumber(cur.getString(4));
                    device.setRemoteModelNumber(cur.getString(5));
                    device.setFilepath(cur.getString(6));
                    deviceList.add(device);
                }while (cur.moveToNext());
            }
        }catch (Exception e){
            Log.e("IRController","ApplicationDBOpenHelper :getDeviceList",e.fillInStackTrace());
        }
        return deviceList;
    }
    public String getDevicePath(String deivceName)
    {
        String path =null;
        try {
            String sqlQuery = "SELECT DEVICE_FILE_PATH FROM "+DEVICE_TABLE_NAME + " WHERE DEVICE_NAME =?";
            SQLiteDatabase db = getWritableDatabase();
          Cursor cur = db.rawQuery(sqlQuery, new String[]{deivceName});
            if (cur.moveToFirst())
            {
                do {
                    path = cur.getString(0);
                }while (cur.moveToNext());
            }
        }catch (Exception e)
        { Log.e("IRController","ApplicationDBOpenHelper :saveDevice",e.fillInStackTrace());
        }
        return path;
    }
    public void saveDevice(Device device)
    {
        try {
            String sqlQuery = "INSERT INTO " + DEVICE_TABLE_NAME + " (DEVICE_NAME,DEVICE_TYPE,DEVICE_BRAND,DEVICE_MODEL,REMOTE_MODEL,DEVICE_FILE_PATH) VALUES('" + device.getDeviceName() + "','" + device.getDeviceType() + "','" + device.getBrand() + "','" + device.getDeviceModelNumber() + "','" + device.getRemoteModelNumber() + "','" + device.getFilepath() + "');";
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(sqlQuery);
        }catch (Exception e)
        { Log.e("IRController","ApplicationDBOpenHelper :saveDevice",e.fillInStackTrace());
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
