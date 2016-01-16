package com.solutionnest.irservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.solutionnest.irexecuter.FileDownloadTask;
import com.solutionnest.irexecuter.RemoteFileReadTask;
import com.solutionnest.utils.db.ApplicationDBOpenHelper;

public class ConfigurationFileService extends Service {
    public ConfigurationFileService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate()
    {
//new ApplicationDBOpenHelper();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.i("IRController service","Please start the service");
        new FileDownloadTask(this).execute("");
        new RemoteFileReadTask(this).execute();
        Log.i("IRController service", "Please end the service");
        return Service.START_NOT_STICKY;
    }



}
