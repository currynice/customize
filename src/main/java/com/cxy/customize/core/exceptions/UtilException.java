package com.cxy.customize.core.exceptions;


import cn.hutool.core.util.StrUtil;

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


    public UtilException(Throwable e,String printTemplate,Object...parms){
        super(StrUtil.format(printTemplate,parms),e);
    }




}
