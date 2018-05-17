package com.pos.posscan.bean;

/**
 * @author Administrator
 * @date 创建时间 2018/5/17
 * @Description
 */

public class PosPayNotifyFeed {

    /**
     * count : 1
     * failedCount : 0
     * message : SUCCESS
     * code : null
     * success : true
     */

    private int count;
    private int failedCount;
    private String message;
    private Object code;
    private boolean success;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(int failedCount) {
        this.failedCount = failedCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
