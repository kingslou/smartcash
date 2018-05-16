package com.pos.posscan.bean;

/**
 * @author Administrator
 * @date 创建时间 2018/5/16
 * @Description
 */

public class ScanPoJo {
    private String device;
    private String preKey;

    public ScanPoJo(String device, String preKey) {
        this.device = device;
        this.preKey = preKey;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getPreKey() {
        return preKey;
    }

    public void setPreKey(String preKey) {
        this.preKey = preKey;
    }
}
