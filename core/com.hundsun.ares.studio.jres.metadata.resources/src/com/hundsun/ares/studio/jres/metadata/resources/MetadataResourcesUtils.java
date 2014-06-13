/**
 * 
 */
package com.hundsun.ares.studio.jres.metadata.resources;

import java.util.Arrays;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataResType;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataTypeList;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.StandardFieldList;

/**
 * @author gongyf
 *
 */
public class MetadataResourcesUtils {
	
	/**
	 * 添加标准字段
	 * @param project
	 * @param fields
	 * @throws ARESModelException
	 */
	public static void addStandardField(IARESProject project, StandardField... fields) throws ARESModelException {
		IARESResource resource = project.findResource(IMetadataResType.StdField, IMetadataResType.StdField);
		StandardFieldList list = resource.getInfo(StandardFieldList.class);
		list.getItems().addAll(EcoreUtil.copyAll(Arrays.asList(fields)));
		resource.save(list, true, new NullProgressMonitor());
	}
	
	/**
	 * 添加标准数据类型
	 * @param project
	 * @param types
	 * @throws ARESModelException
	 */
	public static void addStandardDataType(IARESProject project, StandardDataType... types) throws ARESModelException {
		IARESResource resource = project.findResource(IMetadataResType.StdType, IMetadataResType.StdType);
		StandardDataTypeList list = resource.getInfo(StandardDataTypeList.class);
		list.getItems().addAll(EcoreUtil.copyAll(Arrays.asList(types)));
		resource.save(list, true, new NullProgressMonitor());
	}
}
