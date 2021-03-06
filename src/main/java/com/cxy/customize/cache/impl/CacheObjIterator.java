package com.cxy.customize.cache.impl;



import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * {@link AbstractCache} 的CacheObj迭代器.
 *
 *
 * @param <K> 键
 * @param <V> 值
 */
public class CacheObjIterator<K, V> implements Iterator<CacheObj<K, V>> {

	private final Iterator<CacheObj<K, V>> iterator;
	private CacheObj<K, V> nextValue;

	/**
	 * 构造
	 *
	 * @param iterator 原{@link Iterator}
	 */
	CacheObjIterator(Iterator<CacheObj<K, V>> iterator) {
		this.iterator = iterator;
		nextValue();
	}

	/**
	 * @return 是否有下一个值
	 */
	@Override
	public boolean hasNext() {
		return nextValue != null;
	}

	/**
	 * @return 下一个值
	 */
	@Override
	public CacheObj<K, V> next() {
		if (! hasNext()) {
			throw new NoSuchElementException();
		}
		final CacheObj<K, V> cachedObject = nextValue;
		nextValue();
		return cachedObject;
	}

	/**
	 * 从缓存中移除没有过期的当前值，此方法不支持
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException("Cache values Iterator is not support to modify.");
	}

	/**
	 * 下一个值，当不存在(过期)则下一个值为null
	 */
	private void nextValue() {
		while (iterator.hasNext()) {
			nextValue = iterator.next();
			if (!nextValue.isExpired()) {
				return;
			}
		}
		nextValue = null;
	}
}
