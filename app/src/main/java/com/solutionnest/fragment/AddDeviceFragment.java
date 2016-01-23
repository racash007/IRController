package com.solutionnest.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.solutionnest.activity.R;
import com.solutionnest.bean.Device;
import com.solutionnest.irexecuter.FileDownloadTask;
import com.solutionnest.irexecuter.RemoteFileReadTask;
import com.solutionnest.utils.constant.AppConstants;
import com.solutionnest.utils.db.ApplicationDBOpenHelper;

import java.io.File;
import java.util.concurrent.ExecutionException;


/**
 * A placeholder fragment containing a simple view.
 */
public class AddDeviceFragment extends Fragment {

    public AddDeviceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v;
        v = inflater.inflate(R.layout.fragment_add_device, container, false);
        Spinner spinner = (Spinner) v.findViewById(R.id.deviceType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.device_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return v;
    }

}
