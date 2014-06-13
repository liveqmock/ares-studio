/**
 * 源程序名称：FastFindArrayList.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.core.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gongyf
 *
 */
public class FastFindArrayList<K, E> extends ArrayList<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1202420129062602544L;

	private IKeyProvider<K, E> keyProvider;
	private Map<K, Integer > map;
	
	/**
	 * 
	 */
	public FastFindArrayList(IKeyProvider<K, E> keyProvider) {
		this.keyProvider = keyProvider;
	}
	
	/* (non-Javadoc)
	 * @see java.util.ArrayList#trimToSize()
	 */
	@Override
	public void trimToSize() {
		didChange();
		super.trimToSize();
	}

	/* (non-Javadoc)
	 * @see java.util.ArrayList#set(int, java.lang.Object)
	 */
	@Override
	public E set(int index, E element) {
		didChange();
		return super.set(index, element);
	}

	/* (non-Javadoc)
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	@Override
	public boolean add(E e) {
		didChange();
		return super.add(e);
	}

	/* (non-Javadoc)
	 * @see java.util.ArrayList#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, E element) {
		didChange();
		super.add(index, element);
	}

	/* (non-Javadoc)
	 * @see java.util.ArrayList#remove(int)
	 */
	@Override
	public E remove(int index) {
		didChange();
		return super.remove(index);
	}

	/* (non-Javadoc)
	 * @see java.util.ArrayList#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object o) {
		didChange();
		return super.remove(o);
	}

	/* (non-Javadoc)
	 * @see java.util.ArrayList#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		didChange();
		return super.addAll(c);
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		didChange();
		return super.removeAll(c);
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		didChange();
		return super.retainAll(c);
	}

	/* (non-Javadoc)
	 * @see java.util.ArrayList#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		didChange();
		return super.addAll(index, c);
	}
	
	/* (non-Javadoc)
	 * @see java.util.ArrayList#clear()
	 */
	@Override
	public void clear() {
		didChange();
		super.clear();
	}
	
	private void didChange() {
		map = null;
	}
	
	/**
	 * 快速查找对象
	 * @param key
	 * @return
	 */
	public E find(K key) {
		if (map == null) {
			map = new HashMap<K, Integer>();
			for (int i = 0; i < size(); i++) {
				E e = get(i);
				K k = keyProvider.getKey(e);
				map.put(k, i);
			}
		}

		return get(map.get(key));
	}
}
