package com.hundsun.ares.studio.biz.provider;

import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.biz.ARESObject;
import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.StandardObjField;
import com.hundsun.ares.studio.biz.constants.IBizRefType;
import com.hundsun.ares.studio.biz.core.BizUtil;
import com.hundsun.ares.studio.biz.core.ObjectRefTypes;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.internal.core.ARESProjectProperty;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryItem;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.editor.blocks.DisplayItem;
import com.hundsun.ares.studio.ui.editor.extend.CheckBoxLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;

/**
 * 参数的ColumnLabelProvider
 * @author gongyf
 * @author sundl  从FlowParma处拷贝改造
 */
public class ParameterColumnLabelProvider extends EObjectColumnLabelProvider {

	private static Image PRAMETER_SINGLE_ICON = BizUI.image("full/obj16/parameter.png");
	private static Image PRAMETER_SINGLE_ICON2 = BizUI.image("full/obj16/parameter2.png");
	private static Image PRAMETER_OBJ_ICON = BizUI.image("full/obj16/obj.gif");
	private static Image PARAMETER_GROUP_ICON = BizUI.image("full/obj16/parameterGroup.ico");
	private static Image PARAMETER_COMPONENT_ICON = BizUI.image("full/obj16/component.png");
	
	private static Color COLOR_GRAY = Display.getDefault().getSystemColor(SWT.COLOR_GRAY);

	private CheckBoxLabelProvider provider = new CheckBoxLabelProvider();
	protected IARESProject project;
	protected IARESResource resource;
	public String dataType = MetadataServiceProvider.C_TYPE;
	
	
	public ParameterColumnLabelProvider(IARESProject project, EStructuralFeature attribute) {
		super(attribute);
		this.project = project;
	}
	/**
	 * @param attribute
	 * @param project
	 */
	public ParameterColumnLabelProvider(IARESResource resource, EStructuralFeature attribute) {
		super(attribute);
		this.resource = resource;
		this.project = resource.getARESProject();
	}

	@Override
	public Color getBackground(Object element) {
		// DisplayItem 比如在接口中显示的对象的属性子节点，仅做展示用，任何属性都是只读的
		if (element instanceof DisplayItem) {
			return  COLOR_GRAY;
		}
		
		Parameter p = (Parameter) getOwner(element);
		EStructuralFeature feature = getEStructuralFeature(element);
		
		switch (p.getParamType()) {
		case STD_FIELD:
			if (BizPackage.Literals.PARAMETER__NAME.equals(feature) 
					|| BizPackage.Literals.PARAMETER__REAL_TYPE.equals(feature)
					|| BizPackage.Literals.PARAMETER__TYPE.equals(feature)
					|| BizPackage.Literals.PARAMETER__DESCRIPTION.equals(feature)) {
				return COLOR_GRAY;
			}
			break;
		case NON_STD_FIELD:
			if (feature.equals(BizPackage.Literals.PARAMETER__REAL_TYPE))
				return COLOR_GRAY;
			break;
		case OBJECT:
			boolean hasStdObjectList = BizUtil.hasStdObjList(project);
			if (hasStdObjectList && (BizPackage.Literals.PARAMETER__TYPE.equals(feature)
										||MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION.equals(feature)
										|| MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME.equals(feature))) {
				return COLOR_GRAY;
			}
			if (BizPackage.Literals.PARAMETER__NAME.equals(feature) 
					|| BizPackage.Literals.PARAMETER__REAL_TYPE.equals(feature)
					|| BizPackage.Literals.PARAMETER__DESCRIPTION.equals(feature)
					|| BizPackage.Literals.PARAMETER__DEFAULT_VALUE.equals(feature)) {
				return COLOR_GRAY;
			}
			break;
		case COMPONENT:
			// 组件
			if (BizPackage.Literals.PARAMETER__NAME.equals(feature) 
					|| BizPackage.Literals.PARAMETER__REAL_TYPE.equals(feature)
					|| BizPackage.Literals.PARAMETER__DESCRIPTION.equals(feature)
					|| BizPackage.Literals.PARAMETER__DEFAULT_VALUE.equals(feature)) {
				return COLOR_GRAY;
			}
			break;
		case PARAM_GROUP:
			// 参数组，只编辑类型来关联对象，其他都不可编辑
			if (BizPackage.Literals.PARAMETER__NAME.equals(feature)
					|| BizPackage.Literals.PARAMETER__ID.equals(feature)
					|| BizPackage.Literals.PARAMETER__REAL_TYPE.equals(feature)
					|| BizPackage.Literals.PARAMETER__DESCRIPTION.equals(feature)
					|| BizPackage.Literals.PARAMETER__DEFAULT_VALUE.equals(feature)) {
				return COLOR_GRAY;
			}
			break;
		default:
			break;
		}

		if (resource != null && resource.isReadOnly()) {
			return COLOR_GRAY;
		}
		return super.getBackground(element);
	}

	@Override
	protected Image doGetImage(Object element) {
		Parameter p = (Parameter) getOwner(element);
		EStructuralFeature feature = getEStructuralFeature(element);
		
		if (feature.equals(BizPackage.Literals.PARAMETER__FLAGS)) {
			switch (p.getParamType()) {
			case STD_FIELD:
				return PRAMETER_SINGLE_ICON;
			case NON_STD_FIELD:
				return PRAMETER_SINGLE_ICON2;
			case OBJECT:
				return PRAMETER_OBJ_ICON;
			case PARAM_GROUP:
				return PARAMETER_GROUP_ICON;
			case COMPONENT:
				return PARAMETER_COMPONENT_ICON;
			default:
				return PRAMETER_SINGLE_ICON;
			}
		}
		return super.doGetImage(element);
	}

	@Override
	public String getText(Object element) {
		Parameter p = (Parameter) getOwner(element);
		EStructuralFeature feature = getEStructuralFeature(element);
		
		if (feature.getEType().equals(EcorePackage.Literals.EBOOLEAN) 
				|| feature.getEType().equals(EcorePackage.Literals.EBOOLEAN_OBJECT)) {
			return StringUtils.EMPTY;
		}
		// 不同参数类型分开处理
		switch (p.getParamType()) {
		case NON_STD_FIELD:
			// 非标准字段参数的真实类型也不可编辑，显示对应的业务数据类型的设置的语言的值（在项目属性中设置）
			if (feature.equals(BizPackage.Literals.PARAMETER__REAL_TYPE)) {
				String bizType = p.getType();
				if (StringUtils.isEmpty(bizType))
					break;
				
				ReferenceInfo ref = ReferenceManager.getInstance().getFirstReferenceInfo(project, IMetadataRefType.BizType, bizType, true);
				if (ref != null) {
					BusinessDataType bizDataType = (BusinessDataType) ref.getObject();
					if (bizDataType == null) 
						break;
					
					String stdType = bizDataType.getStdType();
					if (StringUtils.isEmpty(stdType))
						break;
					
					ReferenceInfo stdTypeRef = ReferenceManager.getInstance().getFirstReferenceInfo(project, IMetadataRefType.StdType, stdType, true);
					if (stdTypeRef == null)
						break;
					
					StandardDataType stdDataType = (StandardDataType) stdTypeRef.getObject();
					return stdDataType.getData().get(dataType);
				}
			}else if (BizPackage.Literals.PARAMETER__DEFAULT_VALUE.equals(feature)) {
				return getNonStdParameterDefaultValue(p.getDefaultValue() , p);
			}
			break;
		case STD_FIELD:
			if (BizPackage.Literals.PARAMETER__NAME.equals(feature) 
				|| BizPackage.Literals.PARAMETER__REAL_TYPE.equals(feature)
				|| BizPackage.Literals.PARAMETER__TYPE.equals(feature)
				|| BizPackage.Literals.PARAMETER__DESCRIPTION.equals(feature)
				|| BizPackage.Literals.PARAMETER__DEFAULT_VALUE.equals(feature)) {
				ReferenceInfo  referenceInfo = ReferenceManager.getInstance().getFirstReferenceInfo(project,IMetadataRefType.StdField,p.getId(),true);
				if (referenceInfo != null) {
					StandardField field = (StandardField) referenceInfo.getObject();
					if (field == null ) {
						return StringUtils.EMPTY;
					}
					if (BizPackage.Literals.PARAMETER__NAME.equals(feature)) {
						return field.getChineseName();
					} else if (BizPackage.Literals.PARAMETER__REAL_TYPE.equals(feature)) {//一般不存在此情况,如果要取真实类型可以继承此类重新实现
						try {
							StandardDataType sdt = MetadataServiceProvider.getStandardDataTypeOfStdFieldByName(project, field.getName());
							if (sdt != null) {
								return sdt.getData().get(dataType);
							}
						} catch (Exception e) {
							//e.printStackTrace();
						}
					} else if (BizPackage.Literals.PARAMETER__TYPE.equals(feature)) {
						return field.getDataType();
					} else if (BizPackage.Literals.PARAMETER__DESCRIPTION.equals(feature)) {
						StringBuffer text = new StringBuffer();
						String dictTypeStr = field.getDictionaryType();
						if(StringUtils.isNotBlank(dictTypeStr)){
							ReferenceInfo  dictReferenceInfo = ReferenceManager.getInstance().getFirstReferenceInfo(project,IMetadataRefType.Dict,dictTypeStr,true);
							if(dictReferenceInfo != null){
								DictionaryType objDictionaryType = (DictionaryType) dictReferenceInfo.getObject();
								if(objDictionaryType!=null){
									for(DictionaryItem item : objDictionaryType.getItems()){
										String value = StringUtils.defaultString(item.getValue());
										String chineseName = StringUtils.defaultString(item.getChineseName());
										text.append(value);
										text.append(":");
										text.append(chineseName);
										text.append(" ");
									}
								}
							}
						}
						if(StringUtils.isNotBlank(text.toString()) && StringUtils.isNotBlank(field.getDescription())){
								text.append("\r\n");
								text.append(field.getDescription());
						}
						return StringUtils.defaultString(StringUtils.defaultIfBlank(text.toString(), field.getDescription())) ;
					}else if (BizPackage.Literals.PARAMETER__DEFAULT_VALUE.equals(feature)) {
						return getParameterDefaultValue(p.getDefaultValue() , field);
					}
				}
			}
			break;
		case OBJECT:
			// 如果有对象标准字段列表资源
			if (BizUtil.hasStdObjList(project)) {
				String refId = p.getId();
				ReferenceInfo ref = ReferenceManager.getInstance().getFirstReferenceInfo(project, IBizRefType.Std_Obj, refId, true);
				if (ref == null)
					break;
				StandardObjField field = (StandardObjField) ref.getObject();
				String objId = field.getType();
				ARESObject obj = BizUtil.getObject(objId, project);
				if (feature.equals(BizPackage.Literals.PARAMETER__TYPE)) {
					if (StringUtils.contains(objId, '.'))
						return StringUtils.substringAfterLast(objId, ".");
				} else if (feature.equals(BizPackage.Literals.PARAMETER__NAME)) {
					return obj == null ? StringUtils.EMPTY : obj.getChineseName();
				} else if (feature.equals(BizPackage.Literals.PARAMETER__DESCRIPTION)) {
					return obj == null ? StringUtils.EMPTY : obj.getDescription();
				}
			} else {
				String objId = p.getType();
				ReferenceInfo ref = null;//ReferenceManager.getInstance().getFirstReferenceInfo(project, IBizRefType.Object, objId, true);
				List<String> refTypes = ObjectRefTypes.getRefTypes();
				for (String refType : refTypes) {
					ref = ReferenceManager.getInstance().getFirstReferenceInfo(project, refType, objId, true);
					if(ref != null){
						break;
					}
				}
				// 2012-10-18 sundl 对象类型参数显示的时候，只显示最后一个点后面的部分
				if (feature.equals(BizPackage.Literals.PARAMETER__TYPE)) {
					String type = super.getText(element);
					if (StringUtils.indexOf(type, '.') != -1) 
						return StringUtils.substringAfterLast(type, ".");
				} else if (feature.equals(BizPackage.Literals.PARAMETER__NAME)) {
					if (ref != null) {
						ARESObject obj = (ARESObject) ref.getObject();
						return obj.getChineseName();
					}
				} else if (feature.equals(BizPackage.Literals.PARAMETER__DESCRIPTION)) {
					if (ref != null) {
						ARESObject obj = (ARESObject) ref.getObject();
						return obj.getDescription();
					}
				}
			}
			break;
		case PARAM_GROUP:
			String objId = p.getType();
			ReferenceInfo ref = ReferenceManager.getInstance().getFirstReferenceInfo(project, IBizRefType.Object, objId, true);
			// 2012-10-18 sundl 对象类型参数显示的时候，只显示最后一个点后面的部分
			if (feature.equals(BizPackage.Literals.PARAMETER__TYPE)) {
				String type = super.getText(element);
				if (StringUtils.indexOf(type, '.') != -1) 
					return StringUtils.substringAfterLast(type, ".");
			} else if (feature.equals(BizPackage.Literals.PARAMETER__NAME)) {
				if (ref != null) {
					ARESObject obj = (ARESObject) ref.getObject();
					return obj.getChineseName();
				}
			} else if (feature.equals(BizPackage.Literals.PARAMETER__DESCRIPTION)) {
				if (ref != null) {
					ARESObject obj = (ARESObject) ref.getObject();
					return obj.getDescription();
				}
			} else if (feature.equals(BizPackage.Literals.PARAMETER__ID)) {
				if (ref != null) {
					ARESObject obj = (ARESObject) ref.getObject();
					return obj.getName();
				}
			}
		default:
			break;
		}
		
		return super.getText(element);
	}
	
	protected String getNonStdParameterDefaultValue(String defValue ,Parameter p ){
		return StringUtils.EMPTY;
	}
	
	/**
	 * 
	 */
	protected String getParameterDefaultValue(String defValue , StandardField field) {
		if (StringUtils.isBlank(defValue)) {
			try{
				TypeDefaultValue tdv = MetadataServiceProvider.getTypeDefaultValueOfStdFieldByName(project, field.getName());
				if (tdv != null) {
					if (resource != null && StringUtils.equals(resource.getType(), "procedure")) {
						String dt = (String) ((ARESProjectProperty)project.getProjectProperty()).getProperties().get("dbType");
						if (StringUtils.isNotBlank(dt)) {
							return tdv.getData().get(dt.toLowerCase());
						}
					}else {
						return tdv.getData().get(dataType);
					}
				}
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		return defValue;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.viewers.BaseEObjectColumnLabelProvider#getCopyText(java.lang.Object)
	 */
	@Override
	public String getCopyText(Object element) {
		Parameter p = (Parameter) getOwner(element);
		
		EStructuralFeature feature = getEStructuralFeature(element);
		
		if (feature.getEType().equals(EcorePackage.Literals.EBOOLEAN) 
				|| feature.getEType().equals(EcorePackage.Literals.EBOOLEAN_OBJECT)) {
			return BooleanUtils.toStringTrueFalse((Boolean) p.eGet(feature)) ;
		} else {
			return super.getCopyText(element);
		}
		
	}
	
	/**
	 * 获取需要操作的EObject
	 * @param element
	 * @return
	 */
	protected EObject getOwner(Object element) {
		if (element instanceof DisplayItem) {
			return ((DisplayItem) element).eObject;
		} 
		return super.getOwner(element);
	}
	
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

}
