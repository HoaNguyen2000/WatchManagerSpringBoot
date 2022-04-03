package com.company.exception;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private SysError sysError;

    public UserNotFoundException(SysError sysError) {
        this.sysError = sysError;
    }

    public UserNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public UserNotFoundException(Throwable throwable) {
        super(throwable);
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public SysError getSysError() {
        return sysError;
    }

    public void setSysError(SysError sysError) {
        this.sysError = sysError;
    }
}
