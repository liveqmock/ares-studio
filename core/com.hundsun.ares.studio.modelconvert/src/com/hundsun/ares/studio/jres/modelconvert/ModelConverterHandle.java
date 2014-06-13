/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.jres.modelconvert;

import com.hundsun.ares.studio.core.IARESResource;

/**
 * @author gongyf
 *
 */
public abstract class ModelConverterHandle {
	
	private ModelConverterHandle nextHandle;
	
	/**
	 * @param nextHandle the nextHandle to set
	 */
	public void setNextHandle(ModelConverterHandle nextHandle) {
		this.nextHandle = nextHandle;
	}
	
	/**
	 * @return the nextHandle
	 */
	public ModelConverterHandle getNextHandle() {
		return nextHandle;
	}
	
	/**
	 * 读取资源文件到模型对象
	 * 
	 * @param resource 需要读取的资源对象，字节数组内容已经读取，不需要再进行读取
	 * @param contents 资源文件的内容
	 * @param clazz 指定读取出的模型类型，也就是返回的类型
	 * @return
	 * @throws Exception 
	 */
	public abstract Object handleRead(IARESResource resource, byte[] contents, Class<?> clazz) throws Exception;
	
	/**
	 * 返回需要写入该资源的字节数组
	 * 
	 * @param resource 需要写入的资源对象，不需要进行真正的写入操作
	 * @param info 需要写入的模型
	 * @return
	 * @throws Exception 
	 */
	public abstract byte[] handleWrite(IARESResource resource, Object info) throws Exception;
}
