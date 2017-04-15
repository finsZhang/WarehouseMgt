package com.zx.whm.exception;

/**
 * Created by fins on 2016/4/18.
 */
public class ParameterException extends  Exception{
    private String errorCode;

    public ParameterException(String message) {
        super(message);
    }
    public ParameterException(String errorMsg, String errorCode) {
        super(errorMsg);
        this.errorCode = errorCode;
    }

    public ParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParameterException(String message, Throwable cause, String errorCode) {
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
