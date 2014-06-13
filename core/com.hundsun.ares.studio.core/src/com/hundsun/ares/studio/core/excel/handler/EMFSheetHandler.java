/**
 * 源程序名称：EMFBasedSheetHandler.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel.handler;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.hundsun.ares.studio.core.excel.AbstractSheetHandler;
import com.hundsun.ares.studio.core.excel.BlockTypes;

/**
 * 封装一些处理EMF
 * @author sundl
 *
 */
public abstract class EMFSheetHandler extends AbstractSheetHandler{
	
	private static final Logger logger = Logger.getLogger(EMFSheetHandler.class);
	
	protected EObject owner;
	protected String[] header;

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#keyValue(java.lang.String, java.lang.String)
	 */
	@Override
	public void keyValue(String key, String value) {
		EObject model = getOwner();
		if (model == null) {
			super.keyValue(key, value);
			return;
		}
		
		IPropertyHandler handler = getHandler(model.eClass(), key);
		if (handler != null)
			handler.setValue(getOwner(), value);
		else 
			super.keyValue(key, value);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#header(java.lang.String[])
	 */
	@Override
	public void header(String[] header) {
		this.header = header;
	} 
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#row(java.lang.String[])
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void row(String[] row) {
		if (header == null)
			return;
		
		// 暂时不处理空行
		if (isAllEmpty(row)) 
			return;
		
		EClass eClass = getEClass();
		if (eClass == null)
			return;
		
		EStructuralFeature feature = getTableFeature();
		if (feature == null)
			return;
		
		EObject owner = getOwner();
		EObject newObject = eClass.getEPackage().getEFactoryInstance().create(eClass);
		List list = (List) owner.eGet(feature);
		list.add(newObject);
		
		int cellCount = row.length;
		int headerCount = header.length;
		
		// 如果行比表头多几列，如果这多出来的几列是空列，也无所谓； 如果非空，说明可能出问题了。
		if (cellCount > headerCount) {
			for (int i = headerCount; i < cellCount; i++) {
				if (!StringUtils.isEmpty(row[i])) {
					log.warn(String.format("表头和行的列数不一致，部分值可能无法导入，\n表头：%s,\n列：%s", StringUtils.join(header, ','), StringUtils.join(row, ',')), generateLocation());
					break;
				}
			}
		} // else if ...  反之，如果行比表头少几列，认为是空值而已，不做错误处理
		
		for (int i = 0; i < headerCount; i++) {
			IPropertyHandler handler = getHandler(eClass, header[i].toUpperCase());
			// 接上所述，行可能会比表头少几列
			String value = null;
			if (i >= cellCount) {
				value = StringUtils.EMPTY;
			} else {
				value = row[i];
			}
			
			if (handler != null) 
				handler.setValue(newObject, value);
			else
				log.error("无法处理的属性：" + header[i], generateLocation());
			
			if (StringUtils.isEmpty(value) && StringUtils.equals(header[i], "参数名")) {
				log.error("输入输出参数的参数名为空", generateLocation());
			}
		}
	}
	
	/**
	 * 一行解析结束后调用，子类可以在此处进行一些处理
	 * @param object
	 */
	protected void newRowObjectFound(EObject object) {}
	
	private boolean isAllEmpty(String[] strings) {
		for (String str : strings) {
			if (!StringUtils.isEmpty(str))
				return false;
		}
		return true;
	}
	
	protected IPropertyHandler getHandler(EClass eClass, String property) {
		IPropertyHandlerFactory factory = PropertyHandlerFactoryManager.getPropertyHandlerFactory(eClass);
		if (factory == null) 
			logger.error(eClass.getName() + "的PropertyHandlerFactory为null");
		IPropertyHandler handler =  factory.getPropertyHandler(property, getProject());
		if (handler == null)
			logger.error(String.format("%s -- %s的PropertyHandler为null", eClass.getName(), property));
		return handler;
	}
	
	/**
	 * 当前的owner对象； 处于不同的area或block中的时候这个值都可能是不同的。
	 * @return
	 */
	protected abstract EObject getOwner();
	
	/**
	 * 表格区域对应于owner的哪个列表属性
	 * @param tableTag
	 * @return
	 */
	protected abstract EStructuralFeature getTableFeature();
	
	/**
	 * 当前解析进度对应的EClass
	 * @return
	 */
	protected abstract EClass getEClass();


	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#endBlock()
	 */
	@Override
	public void endBlock() {
		if (parser.getCurrentBlockType() == BlockTypes.TABLE) {
			header = null;
		} else if (parser.getCurrentBlockType() == BlockTypes.TEXT) {
			IPropertyHandler handler = getHandler(getEClass(), parser.getCurrentBlockTag());
			if (handler != null) {
				handler.setValue(getOwner(), parser.getText());
			} else {
				log.error("属性无法处理：" + parser.getCurrentBlockTag(), generateLocation());
			}
		}
	}
	
}
