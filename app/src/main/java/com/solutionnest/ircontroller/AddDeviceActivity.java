package com.solutionnest.ircontroller;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.solutionnest.bean.Device;
import com.solutionnest.irexecuter.FileDownloadTask;
import com.solutionnest.irexecuter.RemoteFileReadTask;
import com.solutionnest.irservice.ConfigurationFileService;
import com.solutionnest.utils.constant.AppConstants;
import com.solutionnest.utils.db.ApplicationDBOpenHelper;
import com.solutionnest.utils.receiver.DeviceValueReceiver;

import java.io.File;
import java.util.concurrent.ExecutionException;


public class AddDeviceActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        Spinner spinner = (Spinner) findViewById(R.id.deviceType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.device_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
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
    public void backToList(View v)
    {
       finish();
    }
    public void addRemote(View v)
    {
       // Intent remoteFileDownloadService = new Intent(this,ConfigurationFileService.class);
        Device device = new Device();
        device.setDeviceName(((EditText) findViewById(R.id.deviceName)).getText().toString());
        device.setDeviceType(((Spinner) findViewById(R.id.deviceType)).getSelectedItem().toString());
        device.setBrand(((EditText) findViewById(R.id.brandName)).getText().toString());
        device.setRemoteModelNumber(((EditText) findViewById(R.id.modelNo)).getText().toString());
       // remoteFileDownloadService.putExtra(AppConstants.DEVICE,device);
       // startService(remoteFileDownloadService);
        fetchDeviceDetails(device);
        /*Intent intent = new Intent(this, RemoteDeviceActivity.class);
        intent.putExtra(AppConstants.DEVICE,device);
        startActivity(intent);*/
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
