/**
 * 源程序名称：AbstractModelConverter.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.modelconvert
 * 功能说明：文件读取和反序列中的适配扩展实现
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.modelconvert;

import java.io.InputStream;
import java.io.OutputStream;

import com.hundsun.ares.studio.core.model.converter.IModelConverter2;

/**
 * 所有JRES资源序列化和反序列化统一基类
 * @author gongyf
 *
 */
public abstract class AbstractModelConverter implements IModelConverter2 {
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.converter.IModelConverter2#read(java.io.InputStream)
	 */
	@Override
	final public Object read(InputStream in) throws Exception {
		throw new UnsupportedOperationException("必须使用read(IARESResource)");
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.converter.IModelConverter2#read(java.io.InputStream, java.lang.Object)
	 */
	@Override
	final public void read(InputStream in, Object info) throws Exception {
		throw new UnsupportedOperationException("必须使用read(IARESResource)");
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.converter.IModelConverter#write(java.io.OutputStream, java.lang.Object)
	 */
	@Override
	final public void write(OutputStream out, Object info) throws Exception {
		throw new UnsupportedOperationException("必须使用write(IARESResource, Object)");
	}
}
