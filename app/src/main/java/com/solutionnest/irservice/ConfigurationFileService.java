package com.solutionnest.irservice;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.solutionnest.ircontroller.R;

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

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        return Service.START_NOT_STICKY;
    }

}
