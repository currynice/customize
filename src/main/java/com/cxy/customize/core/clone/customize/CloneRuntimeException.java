package com.cxy.customize.core.clone.customize;


/**
 * 克隆自定义异常
 */
public class CloneRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 6774837422188798989L;

    public CloneRuntimeException() {
        super();
    }

    //TODO ExceptionUtil.getMessage()获得异常完整消息
    public CloneRuntimeException(Throwable cause) {
        super(cause);
    }

    public CloneRuntimeException(String message) {
        super(message);
    }

    public CloneRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }


}
