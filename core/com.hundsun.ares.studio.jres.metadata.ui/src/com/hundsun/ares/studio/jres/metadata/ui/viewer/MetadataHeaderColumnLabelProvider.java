/**
 * 源程序名称：MetadataHeaderColumnLabelProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.viewer;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.ui.MetadataUI;
import com.hundsun.ares.studio.jres.model.metadata.BizPropertyConfig;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.ConstantItem;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.IEStructuralFeatureProvider;

/**
 * 提供元数据条目图标
 * @author gongyf
 *
 */
public class MetadataHeaderColumnLabelProvider extends EObjectColumnLabelProvider {

	private static Image IMG_FOLDER = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
	
	private static Image IMG_TYPE_DEFAULT = MetadataUI.getDefault().getImage("icons/full/obj16/defaultValueFile.png");
	
	private static Image IMG_BIZ_TYPE = MetadataUI.getDefault().getImage("icons/full/obj16/bizTypeFile.png");
	
	private static Image IMG_CONST = MetadataUI.getDefault().getImage("icons/full/obj16/cnstFile.png");
	
	private static Image IMG_DICT= MetadataUI.getDefault().getImage("icons/full/obj16/dictFile.png");
	
	private static Image IMG_ERRORNO = MetadataUI.getDefault().getImage("icons/full/obj16/errornoFile.png");
	
	private static Image IMG_STD_FIELD = MetadataUI.getDefault().getImage("icons/full/obj16/stdFieldFile.png");
	
	private static Image IMG_STD_TYPE = MetadataUI.getDefault().getImage("icons/full/obj16/stdTypeFile.png");
	
	private static Image IMG_BIZ_CONFIG = MetadataUI.getDefault().getImage("icons/full/obj16/bizconfig.png");
	
	private IARESResource resource;
	
	/**
	 * @param attribute
	 */
	public MetadataHeaderColumnLabelProvider(EAttribute attribute , IARESResource resource) {
		super(attribute);
		this.resource = resource;
	}

	/**
	 * @param attributeProvider
	 */
	public MetadataHeaderColumnLabelProvider(
			IEStructuralFeatureProvider attributeProvider) {
		super(attributeProvider);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.viewers.EObjectColumnLabelProvider#doGetImage(java.lang.Object)
	 */
	@Override
	protected Image doGetImage(Object element) {
		if (element instanceof MetadataCategory) {
			return IMG_FOLDER;
		} else if (element instanceof TypeDefaultValue) {
			return IMG_TYPE_DEFAULT;
		}
		else if (element instanceof BusinessDataType) {
			return IMG_BIZ_TYPE;
		}
		else if (element instanceof ConstantItem) {
			return IMG_CONST;
		}
		else if (element instanceof DictionaryType) {
			return IMG_DICT;
		}
		else if (element instanceof ErrorNoItem) {
			return IMG_ERRORNO;
		}
		else if (element instanceof StandardField) {
			return IMG_STD_FIELD;
		}
		else if (element instanceof StandardDataType) {
			return IMG_STD_TYPE;
		}
		else if(element instanceof BizPropertyConfig){
			return IMG_BIZ_CONFIG;
		}
		/*
		 * TODO#界面逻辑#龚叶峰#简单 #王彬#代码状态 #完成时间 #代码行(不包括空白行和注释行) #工时(精确到分钟) #显示ID列图标
		 *
		 * 根据元数据的类型返回对于的图标，总7种元数据类型，返回的图标是他们的文件类型图标
		 */
			
		return super.doGetImage(element);
	}
}
