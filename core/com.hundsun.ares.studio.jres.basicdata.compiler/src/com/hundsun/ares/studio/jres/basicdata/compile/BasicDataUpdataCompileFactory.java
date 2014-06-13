package com.hundsun.ares.studio.jres.basicdata.compile;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicdataConstants;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel;
import com.hundsun.ares.studio.jres.compiler.IResourceCompiler;
import com.hundsun.ares.studio.jres.compiler.IResourceCompilerFactory;

public class BasicDataUpdataCompileFactory implements IResourceCompilerFactory {

	@Override
	public boolean isSupport(IARESProject project) {
		return true;
	}

	@Override
	public IResourceCompiler create(Object resource) {
		if(resource instanceof BasicDataBaseModel){
			EObject model = (EObject)resource;
			EPackage epackage = model.eClass().getEPackage();
			if(epackage.getEClassifier(IBasicDataEpacakgeConstant.InfoItem) != null){
				//主从关联表
				return new BasicDataInfoCompiler(IBasicdataConstants.GEN_MASTER_SLAVE_LINK_UPDATE_FUNCTION);
			}else if(epackage.getEClassifier(IBasicDataEpacakgeConstant.SlaveItem) != null){
				//主从表
				return new BasicDataInfoCompiler(IBasicdataConstants.GEN_MASTER_SLAVE_UPDATE_FUNCTION);
			}else{
				//二维表
				return new BasicDataInfoCompiler(IBasicdataConstants.GEN_SINGLE_UPDATE_FUNCTION);
			}
		}
		return null;
	}

}
