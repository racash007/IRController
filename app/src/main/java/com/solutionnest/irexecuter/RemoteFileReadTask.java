package com.solutionnest.irexecuter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.solutionnest.bean.Device;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by kanika on 1/17/2016.
 */
public class RemoteFileReadTask extends AsyncTask<Device,Void,Device> {
    private Context ctx;
    private Device device;
    public RemoteFileReadTask(Context ctx)
    {
        this.ctx=ctx;
    }
    @Override
    protected Device doInBackground(Device... devices) {
        try
        {
            FileInputStream fis = ctx.openFileInput(devices[0].getRemoteModelNumber());
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line =null;
            do {
                line = br.readLine();
                if(line!=null)
                {

                }
            }while (line!=null);
        }
        catch (FileNotFoundException fnfe)
        {
            Log.e("IRController","RemoteFileReadTask: File not Found",fnfe.fillInStackTrace());
        }
        catch (IOException ioe)
        {
            Log.e("IRController","RemoteFileReadTask: IOException",ioe.fillInStackTrace());
        }
        return devices[0];
    }

    @Override
    protected void onPostExecute(Device device) {

    }


}

