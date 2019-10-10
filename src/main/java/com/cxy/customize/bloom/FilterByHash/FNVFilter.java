package com.cxy.customize.bloom.FilterByHash;


import com.cxy.customize.core.util.HashMethodsUtil;

public class FNVFilter extends AbstractFilter {

	public FNVFilter(long maxValue, int machineNum) {
		super(maxValue, machineNum);
	}

	public FNVFilter(long maxValue) {
		super(maxValue);
	}

	@Override
	public long hash(String str) {
		return HashMethodsUtil.fnvHash(str);
	}

}
