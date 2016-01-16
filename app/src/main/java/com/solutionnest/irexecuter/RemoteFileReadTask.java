package com.solutionnest.irexecuter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by kanika on 1/17/2016.
 */
public class RemoteFileReadTask extends AsyncTask {
    private Context ctx;
    public RemoteFileReadTask(Context ctx)
    {
        this.ctx=ctx;
    }
    @Override
    protected Object doInBackground(Object[] params) {
        try
        {
            FileInputStream fis = ctx.openFileInput("CT-816");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line =null;
            do {
                line = br.readLine();
                if(line!=null)
                {
                    Log.i("IRController",line);
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
        return null;
    }
}

