package com.zx.whm.exception;

/**
 * Created by fins on 2016/4/18.
 */
public class DBException extends  Exception{
    private String errorCode;

    public DBException(String message) {
        super(message);
    }
    public DBException(String errorMsg, String errorCode) {
        super(errorMsg);
        this.errorCode = errorCode;
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
