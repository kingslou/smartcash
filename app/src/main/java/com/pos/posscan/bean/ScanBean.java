package com.pos.posscan.bean;

/**
 * @author Administrator
 * @date 创建时间 2018/5/16
 * @Description
 */

public class ScanBean {


    /**
     * result : {"cbUrl":"propertyapi\/pos\/billings\/paidNotify","bizGuid":"402883826360fbb701636132df810000","totalAmountDue":"0.01","tenant":"this_is_for_test_only","info":"绿地,5栋,101"}
     */

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
