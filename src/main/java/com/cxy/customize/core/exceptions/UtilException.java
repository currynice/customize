package com.cxy.customize.core.exceptions;

public class UtilException extends RuntimeException {
    private static final long serialVersionUID = -1862257739038645376L;

    public UtilException() {
    }

    public UtilException(String message) {
        super(message);
    }

    public UtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public UtilException(Throwable cause) {
        super(cause);
    }


}
