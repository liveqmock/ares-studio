package com.hundsun.ares.studio.jres.basicdata.database.factory;

import org.eclipse.emf.ecore.EPackage;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveLinkTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.PackageDefine;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.SingleTable;
import com.hundsun.ares.studio.jres.basicdata.logic.epackage.IBaiscDataEpackageFactory;

public class DataBaseEPackageFactory implements IBaiscDataEpackageFactory{

	@Override
	public EPackage createEPackage(IARESProject project,PackageDefine define) throws Exception {
		if(define instanceof SingleTable){
			return SingleEPackageFactory.eInstance.create(project, define);
		}else if(define instanceof MasterSlaveTable){
			return MasterSlaveEPackageFactory.eInstance.create(project, define);
		}else if(define instanceof MasterSlaveLinkTable){
			return MasterSlavelinkEPackageFactory.eInstance.create(project, define);
		}
		
		return null;
	}

}
