/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.model.converter;

import java.io.InputStream;
import java.io.OutputStream;


/**
 * 一个资源的读写器
 * @author maxh
 */
public interface IModelConverter {
	/**
	 * 从in中读取信息，填充info
	 * @param in
	 * @param info
	 */
	void read(InputStream in,Object info) throws Exception; 	// 异常有待商榷，最好是明确哪几种异常
	/**
	 * 把一个对象从in中读出来
	 * @param in
	 * @return
	 */
	Object read(InputStream in) throws Exception;
	/**
	 * 将一个对象写到out中
	 * @param out
	 * @param info
	 */
	void write(OutputStream out,Object info) throws Exception;
}
