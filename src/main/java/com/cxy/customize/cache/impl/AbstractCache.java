package com.cxy.customize.cache.impl;


import com.cxy.customize.cache.Cache;
import com.cxy.customize.core.collection.CopiedIterator;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * 超时和限制大小的缓存的默认实现<br>
 * 继承此抽象缓存需要：<br>
 * <ul>
 * <li>创建一个新的Map</li>
 * <li>实现 <code>slim</code> 瘦身策略</li>
 * </ul>
 * @param <K> 键类型
 * @param <V> 值类型
 */
public abstract class AbstractCache<K, V> implements Cache<K, V> {

	protected Map<K, CacheObj<K, V>> cacheMap;

	private final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock();
	//读锁
	private final ReadLock readLock = cacheLock.readLock();

	//写锁
	private final WriteLock writeLock = cacheLock.writeLock();


	/** 返回缓存容量，<code>-1</code>表示无大小限制 */
	protected int capacity;
	/** 缓存失效时长， <code>-1</code> 表示无限制，单位毫秒 */
	protected long timeout;

	/** 每个对象是否有单独的失效时长，用于决定清理过期对象是否有必要。 */
	protected boolean existCustomTimeout;

	/** 命中数 */
	protected int hitCount;
	/** 丢失数 */
	protected int missCount;

	// ---------------------------------------------------------------- put start
	@Override
	public void put(K key, V object) {
		put(key, object, timeout);
	}

	@Override
	public void put(K key, V object, long timeout) {
		writeLock.lock();

		try {
			CacheObj<K, V> co = new CacheObj<>(key, object, timeout);
			if (timeout != -1&&timeout!=0) {
				existCustomTimeout = true;
			}
			if (isFull()) {
				slimCache();
			}
			cacheMap.put(key, co);
		} finally {
			writeLock.unlock();
		}
	}
	// ---------------------------------------------------------------- put end

	// ---------------------------------------------------------------- get start
	@Override
	public boolean containsKey(K key) {
		readLock.lock();

		try {
			// 不存在或已移除
			final CacheObj<K, V> co = cacheMap.get(key);
			if (co == null) {
				return false;
			}
			//懒惰策略,查询时查看是否过期
			if (!co.isExpired()) {
				// 命中
				return true;
			}
		} finally {
			readLock.unlock();
		}

		// 过期删除
		remove(key, true);
		return false;
	}

	/**
	 * @return 命中数
	 */
	public int getHitCount() {
		this.readLock.lock();
		try {
			return hitCount;
		} finally {
			this.readLock.unlock();
		}
	}

	/**
	 * @return 丢失数
	 */
	public int getMissCount() {
		this.readLock.lock();
		try {
			return missCount;
		} finally {
			this.readLock.unlock();
		}
	}

	@Override
	public V get(K key) {
		return get(key, true);
	}

	@Override
	public V get(K key, boolean isUpdateLastAccess) {
		readLock.lock();

		try {
			// 不存在或已移除
			final CacheObj<K, V> co = cacheMap.get(key);
			if (co == null) {
				missCount++;
				return null;
			}
			//懒惰策略,获取时查看是否过期
			if (!co.isExpired()) {
				// 命中
				hitCount++;
				return co.get(isUpdateLastAccess);
			}
		} finally {
			readLock.unlock();
		}

		// 过期删除
		remove(key, true);
		return null;
	}

	// ---------------------------------------------------------------- get end

	@Override
	//返回v Iterator
	@SuppressWarnings("unchecked")
	public Iterator<V> iterator() {
		CacheObjIterator<K, V> copiedIterator = (CacheObjIterator<K, V>) this.cacheObjIterator();
		return new CacheValuesIterator<V>(copiedIterator);
	}

	//返回kv  Iterator
	@Override
	public Iterator<CacheObj<K, V>> cacheObjIterator() {
		CopiedIterator<CacheObj<K, V>> copiedIterator;
		readLock.lock();
		try {
			copiedIterator = CopiedIterator.copyOf(this.cacheMap.values().iterator());
		} finally {
			readLock.unlock();
		}
		return new CacheObjIterator<>(copiedIterator);
	}

	// ---------------------------------------------------------------- slim start
	/**
	 * 清理实现
	 * 
	 * @return 清理数
	 */
	protected abstract int slimCache();

	@Override
	public final int slim() {
		writeLock.lock();
		try {
			return slimCache();
		} finally {
			writeLock.unlock();
		}
	}
	// ---------------------------------------------------------------- slim end

	// ---------------------------------------------------------------- common start
	@Override
	public int capacity() {
		return capacity;
	}

	/**
	 * @return 默认缓存失效时长。<br>
	 *         每个对象可以单独设置失效时长
	 */
	@Override
	public long timeout() {
		return timeout;
	}

	/**
	 * 只有设置公共缓存失效时长或每个对象单独的失效时长时清理可用
	 * 
	 * @return 过期对象清理是否可用，内部使用
	 */
	protected boolean isSlimExpiredActive() {
		this.readLock.lock();
		try {
			return (timeout != 0 && timeout!=-1) || existCustomTimeout;
		} finally {
			this.readLock.unlock();
		}
	}

	//缓存满了
	@Override
	public boolean isFull() {
		this.readLock.lock();
		try {
			return (capacity > 0) && (cacheMap.size() >= capacity);
		} finally {
			this.readLock.unlock();
		}
	}

	@Override
	public void remove(K key) {
		remove(key, false);
	}

	@Override
	public void clear() {
		writeLock.lock();
		try {
			cacheMap.clear();
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	public int size() {
		this.readLock.lock();
		try {
			return cacheMap.size();
		} finally {
			this.readLock.unlock();
		}
	}

	@Override
	public boolean isEmpty() {
		this.readLock.lock();
		try {
			return cacheMap.isEmpty();
		} finally {
			this.readLock.unlock();
		}
	}

	@Override
	public String toString() {
		this.readLock.lock();
		try {
			return this.cacheMap.toString();
		} finally {
			this.readLock.unlock();
		}
	}
	// ---------------------------------------------------------------- common end

	/**
	 * 对象移除回调。默认无动作
	 * 
	 * @param key 键
	 * @param cachedObject 被缓存的对象
	 */
	protected void onRemove(K key, V cachedObject) {
		// ignore
	}

	/**
	 * 移除key对应的对象
	 * 
	 * @param key 键
	 * @param withMissCount 是否计数丢失数
	 */
	private void remove(K key, boolean withMissCount) {
		writeLock.lock();
		CacheObj<K, V> co;
		try {
			co = cacheMap.remove(key);
			if (withMissCount) {
				this.missCount--;
			}
		} finally {
			writeLock.unlock();
		}
		if (null != co) {
			onRemove(co.key, co.obj);
		}
	}
}
