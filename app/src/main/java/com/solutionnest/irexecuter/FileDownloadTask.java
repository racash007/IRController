package com.solutionnest.irexecuter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by kanika on 1/1/2016.
 */
public class FileDownloadTask extends AsyncTask<String, Void, Void> {
    private
    Context ctx;
    public FileDownloadTask(Context ctx)
    {
        this.ctx = ctx;
    }
    @Override
    protected Void doInBackground(String... urls) {

        Log.i("IRController", "Service started");
        try{
        URL url = new URL("http://lirc.sourceforge.net/remotes/toshiba/CT-816");
        InputStream is = url.openStream();
        DataInputStream dis = new DataInputStream(is);

        byte[] buffer = new byte[1024];
        int length;
        //FileOutputStream fos = new FileOutputStream(new File(Environment.getExternalStorageDirectory() + "/" + "data/CT-816"));
            FileOutputStream fos =  ctx.openFileOutput("CT-816",Context.MODE_MULTI_PROCESS);
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
