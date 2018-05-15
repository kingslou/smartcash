package com.pos.posscan.bean;

/***
 * @author  loujin
 * 支付信息bean
 */
public class PrePayBean {

    private String cbUrl;
    private String bizGuid;

    public void setBizGuid(String bizGuid) {
        this.bizGuid = bizGuid;
    }

    public String getBizGuid() {
        return bizGuid;
    }

    public void setCbUrl(String cbUrl) {
        this.cbUrl = cbUrl;
    }

    public String getCbUrl() {
        return cbUrl;
    }
}
