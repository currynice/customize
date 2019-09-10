package com.cxy.customize.core.io;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;

/**
 * IO运行时异常，常用于对IOException的包装
 */
public class IOCustomException extends RuntimeException {

	private static final long serialVersionUID = -2162623968449791000L;


	public IOCustomException(Throwable e) {
		super(ExceptionUtil.getMessage(e), e);
	}

	public IOCustomException(String message) {
		super(message);
	}

	public IOCustomException(String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params));
	}

	public IOCustomException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public IOCustomException(Throwable throwable, String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params), throwable);
	}

	/**
	 * 导致这个异常的异常是否是指定类型的异常
	 * 
	 * @param clazz 异常类
	 * @return 是否为指定类型异常
	 */
	public boolean causeInstanceOf(Class<? extends Throwable> clazz) {
		Throwable cause = this.getCause();
		if (clazz.isInstance(cause)) {
			return true;
		}
		return false;
	}
}
