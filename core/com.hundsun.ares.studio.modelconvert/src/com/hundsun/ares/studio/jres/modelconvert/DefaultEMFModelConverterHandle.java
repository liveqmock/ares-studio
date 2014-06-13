/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.jres.modelconvert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.IOWrappedException;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.xml.sax.SAXParseException;

import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ExtensibleModel;

/**
 * @author gongyf
 *
 */
public class DefaultEMFModelConverterHandle extends ModelConverterHandle {
	static final Logger console = ConsoleHelper.getLogger();
	private static Logger logger = Logger.getLogger(DefaultEMFModelConverterHandle.class);
	protected Resource createEMFResource(URI uri) {
		return new XMLResourceImpl(uri);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.modelconvert.ModelConverterHandle#handleRead(com.hundsun.ares.studio.core.IARESResource, byte[], java.lang.Class)
	 */
	@Override
	public Object handleRead(IARESResource resource, byte[] contents,
			Class<?> clazz) throws Exception {
		Resource emfRes = null;
		IResource rawRes = resource.getResource();
		if (rawRes == null) {
			emfRes = createEMFResource(null);
		} else {
			emfRes = createEMFResource(URI.createPlatformResourceURI(rawRes.getFullPath().toString(), true));
		}
		try{
			emfRes.load(new ByteArrayInputStream(contents), ModelConverterUtils.EMF_LOAD_OPTIONS);
		} catch (IOWrappedException e) {
			Throwable cause = e.getCause();
			String msg = String.format("解析文件%s失败， 原因可能是文件不是标准XML格式，请检查文件。\n 错误信息：%s", resource.getPath(), e.getMessage());
			if (cause instanceof SAXParseException) {
				SAXParseException saxException = (SAXParseException) cause;
				int line = saxException.getLineNumber();
				int column = saxException.getColumnNumber();
				msg += String.format("\n错误位置：%s行%s列", line, column);
			}
			cause.getLocalizedMessage();
			console.info(msg);
			logger.error(msg, e);
		}
		catch(Exception e){
			console.info("加载"+resource.getResource().getFullPath().toString()+"出错\r\n"+"出错原因:文件内容已不是标准的xml格式 2,文件内容有其他错误.请查看文件内容\r\n"+e.getMessage());
			logger.error("加载"+resource.getResource().getFullPath().toString()+"出错\r\n"+"出错原因:文件内容已不是标准的xml格式 2,文件内容有其他错误.请查看文件内容\r\n"+e.getMessage(), e);
		}
	
		EObject info = emfRes.getContents().get(0);
		
		Set<ExtensibleModel> models = new HashSet<ExtensibleModel>();
		
		if (info instanceof ExtensibleModel) {
			models.add((ExtensibleModel) info);
		}
		
		for (Iterator<Object> iterator = EcoreUtil.getAllContents(info, true); iterator.hasNext();) {
			Object obj = iterator.next();
			if (obj instanceof ExtensibleModel) {
				models.add((ExtensibleModel) obj);
			}
		}
		
		for (ExtensibleModel extensibleModel : models) {
			for (Iterator<Entry<String, EObject>> iterator = extensibleModel.getData2().iterator(); iterator.hasNext();) {
				Entry<String, EObject> entry = iterator.next();
				if (entry.getValue() == null) {
					iterator.remove();
				}
			}
		}
		
		return info;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.modelconvert.ModelConverterHandle#handleWrite(com.hundsun.ares.studio.core.IARESResource, java.lang.Object)
	 */
	@Override
	public byte[] handleWrite(IARESResource resource, Object info)
			throws Exception {
		if (info instanceof EObject) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			EObject obj = (EObject) info;
			if (obj.eResource() == null) {
				Resource emfRes = new XMLResourceImpl();
				emfRes.getContents().add(obj);
			}
			
			obj.eResource().save(out, ModelConverterUtils.EMF_SAVE_OPTIONS);
			return out.toByteArray();
		} else {
			throw new UnsupportedOperationException("info对象不是EObject类型");
		}
	}

}
