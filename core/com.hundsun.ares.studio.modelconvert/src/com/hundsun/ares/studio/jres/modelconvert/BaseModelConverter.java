/**
 * 源程序名称：BaseModelConverter.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.modelconvert
 * 功能说明：文件读取和反序列中的适配扩展实现
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.modelconvert;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Assert;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.registry.ARESResRegistry;
import com.hundsun.ares.studio.core.registry.IResDescriptor;

/**
 * 提供了读写适配的支持
 * @author gongyf
 *
 */
public abstract class BaseModelConverter extends AbstractModelConverter {
	
	private Logger log = Logger.getLogger(getClass());
	
	private Map<String, ModelConverterHandle> chainHeaderMap = new HashMap<String, ModelConverterHandle>();
	protected ModelConverterHandle getModelConverterChain(String type) {
		ModelConverterHandle chainHeader = chainHeaderMap.get(type);
		if (chainHeader == null) {
			chainHeader = createModelConverterChain(type);
			chainHeaderMap.put(type, chainHeader);
		}
		return chainHeader;
	}
	
	/**
	 * 创建模型转换职责链
	 * @param type
	 * @return
	 */
	protected ModelConverterHandle createModelConverterChain(String type) {
		List<ModelConverterHandle> handles = ModelConverterUtils.CONVERTER_HANDLE_MAP.get(type);
		ModelConverterHandle result = getDefaultModelConverterHandle(type);
		// 从最后一个节点反向创建职责链
		for (ModelConverterHandle handle : handles) {
			handle.setNextHandle(result);
			result = handle;
		}
		return result;
	}
	
	/**
	 * 获取默认的处理器，会被放在职责链的最后
	 * @param type
	 * @return
	 */
	protected abstract ModelConverterHandle getDefaultModelConverterHandle(String type);
	
	/**
	 * 在模型从输入流中读取后进行一些处理
	 * @param info
	 * @param resource
	 */
	protected abstract void processInfoOnRead(Object info, IARESResource resource);
	
	/**
	 * 在模型要写入文件前进行一些处理
	 * @param info
	 * @param resource
	 */
	protected abstract void processInfoOnWrite(Object info, IARESResource resource);
	
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.converter.IModelConverter2#read(com.hundsun.ares.studio.core.IARESResource)
	 */
	@Override
	final public Object read(IARESResource resource) throws Exception {
		InputStream is = null;
		Object info = null;
		IResDescriptor resDesc = ARESResRegistry.getInstance().getResDescriptor(resource.getType());
		Class<?> infoClass = resDesc.createInfo().getClass();
		
		try {
			is = resource.openStream();
			byte[] contents = IOUtils.toByteArray(is);
			
			ModelConverterHandle handle = getModelConverterChain(resource.getType());
			
			Assert.isNotNull(handle);
			
			info = handle.handleRead(resource, contents, infoClass);
			
			processInfoOnRead(info, resource);
			
		} catch (Exception e) {
			log.error("读取info对象出错：" + resource.getFullyQualifiedName(),e);
			throw e;
		}finally {
			IOUtils.closeQuietly(is);
			
			if (info == null) {
				log.error("读取info对象为null：" + resource.getFullyQualifiedName());
			}
		}
		
		return info;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.converter.IModelConverter2#write(com.hundsun.ares.studio.core.IARESResource, java.lang.Object)
	 */
	@Override
	final public byte[] write(IARESResource resource, Object info) throws Exception {
		
		processInfoOnWrite(info, resource);
		
		byte[] contents = null;
		
		ModelConverterHandle handle = getModelConverterChain(resource.getType());
		
		Assert.isNotNull(handle);
		
		contents = handle.handleWrite(resource, info);
		
		Assert.isNotNull(contents);
		
		return contents;
	}
}
