package com.cxy.customize.bloom.FilterByHash;

import com.cxy.customize.core.util.HashFunctionUtil;

public class ELFFilter extends AbstractFilter {

	public ELFFilter(long maxValue, int MACHINENUM) {
		super(maxValue, MACHINENUM);
	}
	
	public ELFFilter(long maxValue) {
		super(maxValue);
	}
	
	@Override
	public long hash(String str) {
		return HashFunctionUtil.elfHash(str) % size;
	}

}
