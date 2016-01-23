package com.solutionnest.fragment;

import android.app.Activity;
import android.app.ListFragment;
import android.content.ContentUris;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.solutionnest.activity.R;
import com.solutionnest.listener.OnDeviceSelectionListener;


/**
 * A placeholder fragment containing a simple view.
 */
public class RemoteFragment extends ListFragment {
    OnDeviceSelectionListener onDeviceSelectionListener;
    public RemoteFragment() {
    }
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            onDeviceSelectionListener = (OnDeviceSelectionListener) activity;
        }
        catch (Exception e)
        {throw new ClassCastException(activity.toString()+"must implements OnDeviceSelectionListener");}
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //TODO Sending selected item from the list back to Activity
        // Append the clicked item's row ID with the content provider Uri
      //  Uri noteUri = ContentUris.withAppendedId(ArticleColumns.CONTENT_URI, id);
        // Send the event and Uri to the host activity
        //onDeviceSelectionListener.onDeviceSelected(noteUri);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_remote, container, false);
    }


}
