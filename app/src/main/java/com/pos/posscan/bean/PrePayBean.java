package com.pos.posscan.bean;

/***
 * @author  loujin
 * 支付信息bean
 */
public class PrePayBean {

    private String cbUrl;
    private String bizGuid;
    private String totalAmountDue;
    private String tenant;
    private String info;

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

    public String getTotalAmountDue() {
        return totalAmountDue;
    }

    public void setTotalAmountDue(String totalAmountDue) {
        this.totalAmountDue = totalAmountDue;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
