package com.hundsun.ares.studio.core.model;

/**
 * 可复制的模型
 * @author maxh
 *
 * @param <T>
 */
public interface ICreateInstance<T> {
	public T getNewInstance();
}
