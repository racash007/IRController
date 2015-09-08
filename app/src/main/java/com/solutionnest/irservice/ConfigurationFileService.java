package com.solutionnest.irservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ConfigurationFileService extends Service {
    public ConfigurationFileService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
