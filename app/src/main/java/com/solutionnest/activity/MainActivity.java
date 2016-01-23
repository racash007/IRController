package com.solutionnest.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.solutionnest.bean.Device;
import com.solutionnest.fragment.AddDeviceFragment;
import com.solutionnest.fragment.RemoteListFragment;
import com.solutionnest.irexecuter.FileDownloadTask;
import com.solutionnest.irexecuter.RemoteFileReadTask;
import com.solutionnest.listener.OnDeviceSelectionListener;
import com.solutionnest.listener.OnFragmentInteractionListener;
import com.solutionnest.utils.constant.AppConstants;
import com.solutionnest.utils.db.ApplicationDBOpenHelper;

import java.io.File;
import java.util.concurrent.ExecutionException;


public class MainActivity extends FragmentActivity implements OnDeviceSelectionListener,OnFragmentInteractionListener {

    private static final int  CONTENT_VIEW_ID = 10101010;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        getSupportFragmentManager().beginTransaction().add(R.id.mainActivity,new RemoteListFragment()).commit();
       /* */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_device, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onDeviceSelected(Uri uri) {

    }

    @Override
    public void onFragmentInteraction(String id) {

    }

    public void addDevice(View v)
    {
         getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new AddDeviceFragment()).commit();
    }
    public void deleteDevice(View v)
    {

    }
    public void backToList(View v)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new RemoteListFragment()).commit();
    }

    public void addRemote(View v)
    {
        Device device = new Device();
        device.setDeviceName(((EditText) this.findViewById(R.id.deviceName)).getText().toString());
        device.setDeviceType(((Spinner) this.findViewById(R.id.deviceType)).getSelectedItem().toString());
        device.setBrand(((EditText) this.findViewById(R.id.brandName)).getText().toString());
        device.setRemoteModelNumber(((EditText) this.findViewById(R.id.modelNo)).getText().toString());
        fetchDeviceDetails(device);
    }
    public Device fetchDeviceDetails(Device device)
    {
        ApplicationDBOpenHelper dbAdapter =  new ApplicationDBOpenHelper(this);

        Log.i("IRController", "fetchDeviceDetails Start");
        AppConstants.URL  =  dbAdapter.fetchLookUpURL();
        if(AppConstants.URL!=null) {
            File f = new File(device.getRemoteModelNumber());
            try {
                if(!f.exists()) {
                    //TODO Add timeout for download
                    new FileDownloadTask(this).execute(device).get();
                }
                device =  new RemoteFileReadTask(this).execute(device).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        Log.i("IRController", "fetchDeviceDetails Exit");
        return device;
    }

}
