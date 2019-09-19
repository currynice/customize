package com.cxy.customize.core.collection;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * {@link Enumeration}对象转{@link Iterator}对象
 * 模仿迭代器
 *
 * @param <E> 元素类型
 */
public class EnumerationIter<E> implements Iterator<E>, Iterable<E>{
	
	private final Enumeration<E> e;
	
	/**
	 * 构造
	 * @param enumeration {@link Enumeration}对象
	 */
	public EnumerationIter(Enumeration<E> enumeration) {
		this.e = enumeration;
	}

	@Override
	public boolean hasNext() {
		return e.hasMoreElements();
	}

	@Override
	public E next() {
		return e.nextElement();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<E> iterator() {
		return this;
	}

}
