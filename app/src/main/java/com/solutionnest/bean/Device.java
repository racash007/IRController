package com.solutionnest.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by kanika on 1/16/2016.
 */
public class Device implements Serializable{
    private String deviceName;
    private String deviceModelNumber;
    private String remoteModelNumber;
    private String brand;
    private String deviceType;
    private Map<String,String> remoteIRConfiguration;
    private Map<String,String> remoteKeyCodes;
    private String filepath;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceModelNumber() {
        return deviceModelNumber;
    }

    public void setDeviceModelNumber(String deviceModelNumber) {
        this.deviceModelNumber = deviceModelNumber;
    }

    public String getRemoteModelNumber() {
        return remoteModelNumber;
    }

    public void setRemoteModelNumber(String remoteModelNumber) {
        this.remoteModelNumber = remoteModelNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Map<String, String> getRemoteIRConfiguration() {
        return remoteIRConfiguration;
    }

    public void setRemoteIRConfiguration(Map<String, String> remoteIRConfiguration) {
        this.remoteIRConfiguration = remoteIRConfiguration;
    }

    public Map<String, String> getRemoteKeyCodes() {
        return remoteKeyCodes;
    }

    public void setRemoteKeyCodes(Map<String, String> remoteKeyCodes) {
        this.remoteKeyCodes = remoteKeyCodes;
    }

    public String getFilepath() {
        return filepath;
    }

    @Override
    public String toString() {
        return "Device{" +
                "deviceName='" + deviceName + '\'' +
                '}';
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
