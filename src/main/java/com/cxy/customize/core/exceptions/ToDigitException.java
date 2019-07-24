package com.cxy.customize.core.exceptions;

public class ToDigitException extends RuntimeException {

    private static final long serialVersionUID = 8369406083495581301L;

    public ToDigitException() {
    }

    public ToDigitException(String message) {
        super(message);
    }

    public ToDigitException(String message, Throwable cause) {
        super(message, cause);
    }

    public ToDigitException(Throwable cause) {
        super(cause);
    }


}
