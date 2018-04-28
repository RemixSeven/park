package com.ymwang.park.utils;

public class FailureResult implements Result{
    private String errorCode;
    private String errorMsg;

    public FailureResult(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public FailureResult(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return false;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
