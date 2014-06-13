package com.hundsun.ares.studio.jres.basicdata.core.basicdata.validate.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IValidateConstant;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataEAttribute;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicdataPackage;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldColumn;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData;
import com.hundsun.ares.studio.jres.basicdata.logic.util.BasicDataEpackageUtil;
import com.hundsun.ares.studio.jres.basicdata.logic.util.JRESResProviderUtil;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryItem;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;


public class BasicDataViewerValidator {

	public static final BasicDataViewerValidator INSTANCE = new BasicDataViewerValidator();
	
	public static final String DIAGNOSTIC_SOURCE = "com.hundsun.ares.studio.jres.basicdata";
	
	private static final String KEY_ATTRIBUTES = "keyattributes";
	
	public void validate(EObject value,String featureName, BasicDiagnostic diagnostics, Map<Object, Object> context) {
		//检查metadataItem
		if(featureName.equals(IBasicDataEpacakgeConstant.Attr_Master_Items)){
			validateMetadataItem(value,diagnostics,context);
		}else if (StringUtils.equals(featureName, IBasicDataEpacakgeConstant.StandardField_Package_Define)) {
			validateStandardFieldDefine(value, diagnostics, context);
		}else{
			validateInfoItem(value,diagnostics,context);
		}
	}
	
	private void validateStandardFieldDefine(EObject value,
			BasicDiagnostic diagnostics, Map<Object, Object> context) {
		IARESProject project =(IARESProject) context.get(IValidateConstant.KEY_RESOUCE_PROJECT);

		if (value instanceof StandardFieldModelAndData) {
			for(StandardFieldColumn standardFieldColumn : ((StandardFieldModelAndData) value).getModel().getFields()){
		
				ReferenceInfo info = ReferenceManager.getInstance().getFirstReferenceInfo(project, IMetadataRefType.StdField, standardFieldColumn.getStandardField(), true);
				if (info == null) {
					if(diagnostics != null){
						diagnostics.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								"未知标准字段：["+standardFieldColumn.getStandardField()+"]",
								new Object[] {
									standardFieldColumn,
										BasicdataPackage.Literals.STANDARD_FIELD_COLUMN__STANDARD_FIELD}));
					}
				}
			}
		}
	}
	
	private void validateInfoItem(EObject value, BasicDiagnostic diagnostics,
			Map<Object, Object> context) {
		EStructuralFeature reference = value.eClass().getEStructuralFeature(IBasicDataEpacakgeConstant.Attr_Info_Items);
		if(reference != null){
			List<EObject>objs = (List<EObject>) value.eGet(reference);
			for(EObject obj : objs){
				validateDictItemExist(obj,diagnostics, context);
			}
		}
	}

	private void validateInfoItemExist(EObject obj,
			BasicDiagnostic diagnostics, Map<Object, Object> context) {
		IKeyTableLocator helper = (IKeyTableLocator) context.get(IBasicDataEpacakgeConstant.Info_Table_Locator_Helper);
		List<EAttribute> attrs = new ArrayList<EAttribute>();
		EClass eClass = obj.eClass();
		if(!context.containsKey(KEY_ATTRIBUTES)){
			List<String> keys = Arrays.asList(BasicDataEpackageUtil.getMasterKeyAnnotation((EClass) eClass.getEPackage().getEClassifier(IBasicDataEpacakgeConstant.InfoItem)));
			for(EAttribute attr : eClass.getEAllAttributes()){
				if(keys.contains(attr.getName())){
					attrs.add(attr);
				}
			}
			context.put(KEY_ATTRIBUTES, attrs);
		}
		try {
			if(helper.getLinkObjectCount(obj)<=0){
				
				if(diagnostics != null){
					for(EAttribute attr : (List<EAttribute>)context.get(KEY_ATTRIBUTES)){
						diagnostics.add(new BasicDiagnostic(
								Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE,
								0,
								"关联的信息表数据不存在",
								new Object[] {
										obj,
										attr}));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void validateMetadataItem(EObject value,
			BasicDiagnostic diagnostics, Map<Object, Object> context) {
		List<EObject>objs = (List<EObject>) value.eGet(MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS);
		for(EObject obj : objs){
			validateDictItemExist(obj,diagnostics, context);
			EStructuralFeature reference = obj.eClass().getEStructuralFeature(IBasicDataEpacakgeConstant.Attr_Slave_Items);
			if(reference != null){
				List<EObject>items = (List<EObject>) obj.eGet(reference);
				for(EObject item : items){
					validateDictItemExist(item,diagnostics, context);
					if(context.containsKey(IBasicDataEpacakgeConstant.Info_Table_Locator_Helper)){
						validateInfoItemExist(item,diagnostics,context);
					}
				}
			}
		}
	}

	private void validateDictItemExist(EObject value, BasicDiagnostic diagnostics, Map<Object, Object> context){
		IARESResource resource = (IARESResource) context.get(IValidateConstant.KEY_RESOUCE);
		IARESProject project = resource.getARESProject();
		EAttribute[] attrArray = BasicDataEpackageUtil.filterAttr(value.eClass());
		for(EAttribute attr : attrArray){
			String text = (String) value.eGet(attr);
			if (StringUtils.isBlank(text)) {
				continue;
			}
			if(attr instanceof BasicDataEAttribute){
				if(BasicDataEpackageUtil.isStandardField(attr)){
					StandardField stdField = JRESResProviderUtil.getMetadataModel(project, attr.getName(),IMetadataRefType.StdField , StandardField.class);
					if(stdField != null && StringUtils.isNotBlank(stdField.getDictionaryType())){
						DeDictionaryType dicType = MetadataUtil.decrypt(stdField, resource).getDictionaryType();
						boolean find = true;
						EList<DeDictionaryItem> items = dicType.getItems();
						String[] dictItemValues = StringUtils.split(StringUtils.defaultIfBlank(text, ""),",");
						StringBuffer sb = new StringBuffer();
						for(int i=0;i<dictItemValues.length;i++){
							String dictValue = dictItemValues[i];
							boolean inFind = false;
							for(DeDictionaryItem item : items){
								if(StringUtils.equals(item.getValue(), dictValue)){
									inFind = true;
									break;
								}
							}
							if(!inFind){
								sb.append(dictValue).append(",");
								find = false;
							}
							
						}
						
						if(!find && diagnostics != null){
							diagnostics.add(new BasicDiagnostic(
									Diagnostic.ERROR,
									DIAGNOSTIC_SOURCE,
									0,
									"属性:"+attr.getName()+"对应标准字段关联的数据字典子项"+sb.toString()+"不存在",
									new Object[] {
											value,
											attr }));
						}
					}
				}
			}

				
		}
	}
}
