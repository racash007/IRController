package com.solutionnest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.solutionnest.activity.R;
import com.solutionnest.bean.Device;

import java.util.ArrayList;
import java.util.List;




public class DeviceListAdaper extends BaseAdapter {
    private Context context;
    private List<Device> deviceList;
    private  LayoutInflater inflater;

    public DeviceListAdaper(Context context, List<Device> deviceList) {
        this.context = context;
        this.deviceList = new ArrayList<Device>(deviceList);
        this.inflater  = LayoutInflater.from(context);
    }

    private static class ViewHolder {
        TextView name;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Device device = (Device)getItem(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_remote_list,parent ,false);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.deviceName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        } holder.name.setText(device.getDeviceName());


        return convertView;

    }

    @Override
    public int getCount() {
        return deviceList.size();
    }

    @Override
    public Object getItem(int position) {
        return deviceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }
}
