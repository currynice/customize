package com.cxy.customize.core.clone.customize;


/**
 * 克隆自定义异常
 */
public class CloneRunTimeException extends RuntimeException {
    private static final long serialVersionUID = 6774837422188798989L;

    public CloneRunTimeException() {
        super();
    }

    //TODO ExceptionUtil.getMessage()获得异常完整消息
    public CloneRunTimeException(Throwable cause) {
        super(cause);
    }

    public CloneRunTimeException(String message) {
        super(message);
    }

    public CloneRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }


}
