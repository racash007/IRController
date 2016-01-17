package com.solutionnest.irexecuter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.solutionnest.bean.Device;
import com.solutionnest.utils.constant.AppConstants;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by kanika on 1/1/2016.
 */
public class FileDownloadTask extends AsyncTask<Device, Void, Void> {
    private
    Context ctx;
    public FileDownloadTask(Context ctx)
    {
        this.ctx = ctx;
    }
    @Override
    protected Void doInBackground(Device... devices) {
        Log.i("IRController", "Service started");
        try{
        URL url = new URL(AppConstants.URL+"/"+devices[0].getBrand()+"/"+devices[0].getRemoteModelNumber());
        InputStream is = url.openStream();
        DataInputStream dis = new DataInputStream(is);

        byte[] buffer = new byte[1024];
        int length;
            FileOutputStream fos =  ctx.openFileOutput(devices[0].getRemoteModelNumber(),Context.MODE_MULTI_PROCESS);
        while ((length = dis.read(buffer))>0) {
            fos.write(buffer, 0, length);
        }
            fos.close();
    }catch (MalformedURLException e){
Log.e("IRController", "FileDownloadTask MalFormed URL", e.fillInStackTrace());

    }
    catch (IOException ioe) {
        Log.e("IRController","FileDownloadTask IO Error",ioe.fillInStackTrace());
    }
    catch (SecurityException se) {
        Log.e("IRController","FileDownloadTask SecurityException",se.fillInStackTrace());
    }
        catch (Exception e) {
            Log.e("IRController", "FileDownloadTask Exception", e.fillInStackTrace());
        }
        return null;
    }

}
