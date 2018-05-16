package com.pos.posscan.bean;

public class PosPayNotifyPoJo {




    /*******************************以下几个字段是娄总调用第一个API的返回信息的原样返回*****************************/

    //POS扫PC上的订单码后，通过调用第一个API返回的心中的 业务主键
    private String bizGuid;
    //POS扫描PC上二维码得到的值
    private String preKey;

    //暂时和preKey一样
    private String notifyTransactionId;

    /*******************************以下几个字段是娄总依据实际的支付情况 所设置的信息*****************************/

    //设备的唯一标识
    private String device;

    //本次付款的总金额,即POS端实际付款金额. （如果支付成功后，会返回的话，就设置，没有就算了）
    private String totalAmountPaid;

    //POS扣款的结果，具体需要和ANDROID沟通。 成功 =  "SUCCESS" ； 失败 = "FAILED". 失败的时候设置reason
    private String notifyResultCode;

    //交易成功或者失败后，发送请求的时间yyyyMMdd hh24:mm:ss(客户端POS的时间)
    private String notifyTimeEnd;

    //失败原因，失败的时候设置
    private String failedReason;

    //用户付款类型， 由娄总定义。 可能为 银行卡- 0 ，微信扫码 - 1 ，支付宝扫码 - 2, 银联二位码 - 3
    private String payType;

    //pos交易的返回的信息的,格式形如: request 是你发起支付的时候请求体。   response：是你收到的响应
    //JSON格式的字符串 {"request": xxxxx , "response": YYYYYY }
    private String posPayDetail;


    public String getBizGuid() {
        return bizGuid;
    }

    public void setBizGuid(String bizGuid) {
        this.bizGuid = bizGuid;
    }

    public String getPreKey() {
        return preKey;
    }

    public void setPreKey(String preKey) {
        this.preKey = preKey;
    }

    public String getNotifyTransactionId() {
        return notifyTransactionId;
    }

    public void setNotifyTransactionId(String notifyTransactionId) {
        this.notifyTransactionId = notifyTransactionId;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getTotalAmountPaid() {
        return totalAmountPaid;
    }

    public void setTotalAmountPaid(String totalAmountPaid) {
        this.totalAmountPaid = totalAmountPaid;
    }

    public String getNotifyResultCode() {
        return notifyResultCode;
    }

    public void setNotifyResultCode(String notifyResultCode) {
        this.notifyResultCode = notifyResultCode;
    }

    public String getNotifyTimeEnd() {
        return notifyTimeEnd;
    }

    public void setNotifyTimeEnd(String notifyTimeEnd) {
        this.notifyTimeEnd = notifyTimeEnd;
    }

    public String getFailedReason() {
        return failedReason;
    }

    public void setFailedReason(String failedReason) {
        this.failedReason = failedReason;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPosPayDetail() {
        return posPayDetail;
    }

    public void setPosPayDetail(String posPayDetail) {
        this.posPayDetail = posPayDetail;
    }

    public PosPayNotifyPoJo(String bizGuid) {
        this.bizGuid = bizGuid;
    }
}
