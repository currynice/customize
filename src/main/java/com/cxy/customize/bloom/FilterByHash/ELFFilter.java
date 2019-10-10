package com.cxy.customize.bloom.FilterByHash;

import com.cxy.customize.core.util.HashMethodsUtil;

public class ELFFilter extends AbstractFilter {

	public ELFFilter(long maxValue, int MACHINENUM) {
		super(maxValue, MACHINENUM);
	}
	
	public ELFFilter(long maxValue) {
		super(maxValue);
	}
	
	@Override
	public long hash(String str) {
		return HashMethodsUtil.elfHash(str) % size;
	}

}
