package com.cxy.customize.bloom.FilterByHash;


import com.cxy.customize.core.util.HashFunctionUtil;

public class TianlFilter extends AbstractFilter {

	public TianlFilter(long maxValue, int machineNum) {
		super(maxValue, machineNum);
	}

	public TianlFilter(long maxValue) {
		super(maxValue);
	}

	@Override
	public long hash(String str) {
		return HashFunctionUtil.tianlHash(str) % size;
	}

}
