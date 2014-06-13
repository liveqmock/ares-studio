/**
 * 源程序名称：MetadataOverviewHeaderColumnLabelProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.viewer;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.ui.MetadataUI;
import com.hundsun.ares.studio.jres.metadata.ui.model.MetadataOverviewElement;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.ConstantItem;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.util.CircularReferenceException;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.IEStructuralFeatureProvider;

/**
 * @author wangxh
 *
 */
public class MetadataOverviewHeaderColumnLabelProvider extends
		EObjectColumnLabelProvider {

	private static Image IMG_TYPE_DEFAULT = MetadataUI.getDefault().getImage("icons/full/obj16/defaultValueFile.png");
	
	private static Image IMG_BIZ_TYPE = MetadataUI.getDefault().getImage("icons/full/obj16/bizTypeFile.png");
	
	private static Image IMG_CONST = MetadataUI.getDefault().getImage("icons/full/obj16/cnstFile.png");
	
	private static Image IMG_DICT= MetadataUI.getDefault().getImage("icons/full/obj16/dictFile.png");
	
	private static Image IMG_ERRORNO = MetadataUI.getDefault().getImage("icons/full/obj16/errornoFile.png");
	
	private static Image IMG_STD_FIELD = MetadataUI.getDefault().getImage("icons/full/obj16/stdFieldFile.png");
	
	private static Image IMG_STD_TYPE = MetadataUI.getDefault().getImage("icons/full/obj16/stdTypeFile.png");
	
	
	public MetadataOverviewHeaderColumnLabelProvider(
			IEStructuralFeatureProvider attributeProvider) {
		super(attributeProvider);
	}

	public MetadataOverviewHeaderColumnLabelProvider(EAttribute attribute) {
		super(attribute);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.viewers.EObjectColumnLabelProvider#doGetImage(java.lang.Object)
	 */
	@Override
	protected Image doGetImage(Object element) {
		
		MetadataItem item = ((MetadataOverviewElement) element).getItem();;
		
		if (item instanceof TypeDefaultValue) {
			return IMG_TYPE_DEFAULT;
		}
		else if (item instanceof BusinessDataType) {
			return IMG_BIZ_TYPE;
		}
		else if (item instanceof ConstantItem) {
			return IMG_CONST;
		}
		else if (item instanceof DictionaryType) {
			return IMG_DICT;
		}
		else if (item instanceof ErrorNoItem) {
			return IMG_ERRORNO;
		}
		else if (item instanceof StandardField) {
			return IMG_STD_FIELD;
		}
		else if (item instanceof StandardDataType) {
			return IMG_STD_TYPE;
		}			
		return super.doGetImage(element);
	}

	@Override
	public Color getBackground(Object element) {
		MetadataOverviewElement moe = (MetadataOverviewElement) element;
		if (moe.isConflict()) {
			return Display.getDefault().getSystemColor(SWT.COLOR_YELLOW);
		}
		return super.getBackground(element);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataColumnLabelProvider#getOwner(java.lang.Object)
	 */
	@Override
	protected EObject getOwner(Object element) {
		// 对于引用的处理
		MetadataItem item = ((MetadataOverviewElement) element).getItem();
		IARESResource resource = ((MetadataOverviewElement) element).getResource();
		if (MetadataUtil.isReferencableFeature(item, getEStructuralFeature(element))) {
			MetadataItem entity = null;
			try {
				entity = MetadataUtil.defaultResolve(item, resource).first;
			} catch (CircularReferenceException e) {
			}
			return entity == null ? item : entity;
		}
		
		return item;
	}

}
