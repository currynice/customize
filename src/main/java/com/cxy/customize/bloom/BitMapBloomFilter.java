package com.cxy.customize.bloom;



import com.cxy.customize.bloom.FilterByHash.*;
import com.cxy.customize.core.math.NumberSystemUtil;

/**
 * BlommFilter 实现 <br>
 * 1.构建hash算法 <br>
 * 2.散列hash映射到数组的bit位置 <br>
 * 3.验证<br>
 * 此实现方式可以指定Hash算法,使用乞丐版BitMap实现
 */
public class BitMapBloomFilter implements BloomFilter{


	private  BloomFilter[] filters;

	//filter个数,hash个数
	private int filterNum = 5;



	/**
	 * 构造，使用默认的5种过滤器(hash)
	 * @param m M值决定BitMap的大小
	 */
	public BitMapBloomFilter(int m) {
		filterNum = 5;
		int mNum = NumberSystemUtil.exactDiv(String.valueOf(m), String.valueOf(filterNum)).intValue();
		//计算单个数据结构大小
		long size = (long) (1L * mNum * 1024 * 1024 * 8);
		filters = new BloomFilter[]{
				new DefaultFilter(size),
				new ELFFilter(size),
				new JSFilter(size),
				new PJWFilter(size),
				new SDBMFilter(size)
		};
	}

	/**
	 * 多个过滤器建立BloomFilter,大小自己控制好
	 * @param filters
	 */
	public BitMapBloomFilter(int m,BloomFilter... filters) {
		this(m);
		this.filters = filters;
	}


	/**
	 * 增加字符串到Filter映射中
	 * @param str 字符串
	 */
	@Override
	public boolean add(String str) {
		for (BloomFilter filter : filters) {
			if(!filter.add(str)){
				return false;
			}
		}
		return true;
	}

	/**
	 * 是否可能包含此字符串，此处存在误判(上层可以选择是否进行处理)
	 * @param str 字符串
	 * @return 是否存在
	 */
	@Override
	public boolean contains(String str) {
		for (BloomFilter filter : filters) {
			if (!filter.contains(str)) {
				return false;
			}
		}
		return true;
	}





}