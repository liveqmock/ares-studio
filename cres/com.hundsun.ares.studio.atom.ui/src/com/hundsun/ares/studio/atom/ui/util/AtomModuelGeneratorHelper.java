/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.ui.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.AtomService;
import com.hundsun.ares.studio.atom.constants.IAtomResType;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.util.ModuleGeneratorHelper;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procdure.constants.IProcedureResType;

/**
 * @author qinyuan
 *
 */
public class AtomModuelGeneratorHelper extends ModuleGeneratorHelper{
	
	/**
	 * 获取原子模块最新修订记录
	 * @param module
	 * @param project
	 * @return
	 * @throws Exception
	 */
	public static String getAtomModuleLastVersion(IARESModule module) throws Exception{
		List<RevisionHistory> hiss = getAtomModuleHistorys(module);
		if(hiss.size() > 0) {
			return hiss.get(0).getVersion();
		}
		return module.getARESProject().getProjectProperty().getVersion();
	}

	/**
	 * 获取原子模块下所有修改记录
	 * 包括原子函数修改记录、原子服务修改记录、模块修改记录
	 * @param module
	 * @return
	 * @throws Exception
	 */
	public static List<RevisionHistory> getAtomModuleHistorys(IARESModule module) throws Exception{
		List<RevisionHistory> hiss = new ArrayList<RevisionHistory>();//模块下所有的修改记录
		IARESResource[] ass = module.getARESResources(IAtomResType.ATOM_SERVICE);
		for (IARESResource as : ass) {
			EList<RevisionHistory> his = as.getInfo(AtomService.class).getHistories();//原子服务修改记录
			hiss.addAll(his);
		}
		IARESResource[] afs = module.getARESResources(IAtomResType.ATOM_FUNCTION);
		for (IARESResource af : afs) {
			EList<RevisionHistory> his = af.getInfo(AtomFunction.class).getHistories();//原子函数修改记录
			hiss.addAll(his);
		}
		
		hiss.addAll(getModuleHistorys(module));//模块修改记录
		
		sortHistoryByVersion(hiss);//排序
		return hiss;
	}
}
