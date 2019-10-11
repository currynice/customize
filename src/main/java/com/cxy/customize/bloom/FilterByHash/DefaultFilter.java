package com.cxy.customize.bloom.FilterByHash;


import com.cxy.customize.core.util.HashFunctionUtil;

/**
 * 默认Bloom过滤器，使用Java自带的Hash算法
 *
 */
public class DefaultFilter extends AbstractFilter {

	public DefaultFilter(long maxValue, int MACHINENUM) {
		super(maxValue, MACHINENUM);
	}
	
	public DefaultFilter(long maxValue) {
		super(maxValue);
	}
	
	@Override
	public long hash(String str) {
		return HashFunctionUtil.javaDefaultHash(str) % size;
	}
}
