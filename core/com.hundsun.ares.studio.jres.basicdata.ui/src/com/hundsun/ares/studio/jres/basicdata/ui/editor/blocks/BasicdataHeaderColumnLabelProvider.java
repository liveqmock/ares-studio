/**
 * 源程序名称：MetadataHeaderColumnLabelProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.basicdata.ui.editor.blocks;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.basicdata.logic.util.BasicDataEpackageUtil;
import com.hundsun.ares.studio.jres.basicdata.logic.util.JRESResProviderUtil;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryItem;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.IEStructuralFeatureProvider;

/**
 * 提供元数据条目图标
 * @author gongyf
 *
 */
public class BasicdataHeaderColumnLabelProvider extends EObjectColumnLabelProvider {

	private static Image IMG_FOLDER = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
	
	private static Image IMG_ITEM = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
	
	IARESResource resource;
	
	
	/**
	 * @param attribute
	 */
	public BasicdataHeaderColumnLabelProvider(EAttribute attribute) {
		super(attribute);
	}

	/**
	 * @param attributeProvider
	 * @param resource 
	 */
	public BasicdataHeaderColumnLabelProvider(
			IEStructuralFeatureProvider attributeProvider, IARESResource resource) {
		super(attributeProvider);
		this.resource = resource;
	}

	@Override
	public Color getBackground(Object element) {
		if (resource.isReadOnly()) {
			return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
		}
		return super.getBackground(element);
	}
	
	@Override
	protected Image doGetImage(Object element) {
		if (element instanceof MetadataCategory) {
			return IMG_FOLDER;
		} else{
			return IMG_ITEM;
		}
	}
	
	@Override
	public String getText(Object element) {
		String text = StringUtils.EMPTY;
		{
			EStructuralFeature attribute = getEStructuralFeature(element);
			EObject owner = getOwner(element);
			Map<String , EAttribute> attMap = new HashMap<String, EAttribute>();
			for(EAttribute att : owner.eClass().getEAllAttributes()){
				attMap.put(att.getName(), att);
			}
			if (attribute != null && owner != null && attMap.get(attribute.getName()) != null) {
				Object value = owner.eGet(attMap.get(attribute.getName()));
				if (value == null) {
					value = "";
				}
				text = String.valueOf(value );
			}
		}
		//避免没有属性时出现NullPointer
		if(null == getEStructuralFeature(element)){
			return "";
		}
		
		EStructuralFeature basicDataEAttribute = getEStructuralFeature(element);
		
		if(basicDataEAttribute instanceof EAttribute && BasicDataEpackageUtil.isStandardField((EAttribute)basicDataEAttribute)){
			StandardField stdField = JRESResProviderUtil.getMetadataModel(resource.getARESProject(), getEStructuralFeature(element).getName(),IMetadataRefType.StdField , StandardField.class);
			if(stdField != null && StringUtils.isNotBlank(stdField.getDictionaryType())){
				DeDictionaryType dicType = MetadataUtil.decrypt(stdField, resource).getDictionaryType();
				EList<DeDictionaryItem> items = dicType.getItems();
				String[] dictItemValues = StringUtils.split(StringUtils.defaultIfBlank(text, ""),",");
				StringBuffer sb = new StringBuffer();
				for(int i=0;i<dictItemValues.length;i++){
					String value = dictItemValues[i];
					for(DeDictionaryItem item : items){
						if(StringUtils.equals(item.getValue(), value)){
							if(i==dictItemValues.length-1){
								sb.append(item.getValue() + "_" + item.getChineseName());
							}else{
								sb.append(item.getValue() + "_" + item.getChineseName()).append("||");
							}
							
						}
					}	
				}
				text = sb.toString();
			}
		}
		
		return text;
	}
}
