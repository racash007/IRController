package com.solutionnest.irservice;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.solutionnest.bean.Device;
import com.solutionnest.irexecuter.FileDownloadTask;
import com.solutionnest.irexecuter.RemoteFileReadTask;
import com.solutionnest.utils.constant.AppConstants;
import com.solutionnest.utils.db.ApplicationDBOpenHelper;
import com.solutionnest.utils.receiver.DeviceValueReceiver;

import java.io.File;
import java.util.concurrent.ExecutionException;

public class ConfigurationFileService extends Service {

    ApplicationDBOpenHelper dbAdapter =null;

    public ConfigurationFileService() {

    }


    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }



    @Override
    public void onCreate()
    {
        dbAdapter = new ApplicationDBOpenHelper(this);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        return Service.START_NOT_STICKY;
    }



}
