package com.solutionnest.irexecuter;

import android.os.AsyncTask;

/**
 * Created by kanika on 1/16/2016.
 */
public class TaskFactory
{

    private TaskFactory()
    {}
    public AsyncTask getTask(Enum TaskType)
    {
       return null;
    }
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
