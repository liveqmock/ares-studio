package com.hundsun.ares.studio.jres.basicdata.logic.epackage;

import org.eclipse.emf.ecore.EPackage;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.PackageDefine;

public interface IBaiscDataEpackageFactory {

	public  static final String EXTPointID = "com.hundsun.ares.studio.jres.basicdata.core.EpachageFactory";
	
	public  static final String EXTAttr_ID = "id";
	public  static final String EXTAttr_Name = "name";
	public  static final String EXTAttr_Class = "class";
	

	/**
	 * ´´½¨EPackage
	 * @param define
	 * @return
	 * @throws Exception
	 */
	public EPackage createEPackage(IARESProject project,PackageDefine define)throws Exception;
}
