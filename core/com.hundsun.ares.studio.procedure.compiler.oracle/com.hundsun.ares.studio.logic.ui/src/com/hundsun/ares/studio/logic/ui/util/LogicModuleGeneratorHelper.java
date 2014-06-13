/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.logic.ui.util;

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
import com.hundsun.ares.studio.logic.LogicFunction;
import com.hundsun.ares.studio.logic.LogicService;
import com.hundsun.ares.studio.logic.constants.ILogicResType;

/**
 * @author qinyuan
 *
 */
public class LogicModuleGeneratorHelper extends ModuleGeneratorHelper{
	
	/**
	 * 获取逻辑模块最新修订记录
	 * @param module
	 * @param project
	 * @return
	 * @throws Exception
	 */
	public static String getLogicModuleLastVersion(IARESModule module) throws Exception{
		List<RevisionHistory> hiss = getLogicModuleHistorys(module);
		if(hiss.size() > 0) {
			return hiss.get(0).getVersion();
		}
		return module.getARESProject().getProjectProperty().getVersion();
	}

	/**
	 * 获取逻辑模块下所有修改记录
	 * 包括逻辑函数修改记录、逻辑服务修改记录、模块修改记录
	 * @param module
	 * @return
	 * @throws Exception
	 */
	public static List<RevisionHistory> getLogicModuleHistorys(IARESModule module) throws Exception{
		List<RevisionHistory> hiss = new ArrayList<RevisionHistory>();//模块下所有的修改记录
		IARESResource[] lss = module.getARESResources(ILogicResType.LOGIC_SERVICE);
		for (IARESResource ls : lss) {
			EList<RevisionHistory> his = ls.getInfo(LogicService.class).getHistories();//逻辑服务修改记录
			hiss.addAll(his);
		}
		IARESResource[] lfs = module.getARESResources(ILogicResType.LOGIC_FUNCTION);
		for (IARESResource lf : lfs) {
			EList<RevisionHistory> his = lf.getInfo(LogicFunction.class).getHistories();//逻辑函数修改记录
			hiss.addAll(his);
		}
		
		hiss.addAll(getModuleHistorys(module));//模块修改记录
		
		sortHistoryByVersion(hiss);//排序
		return hiss;
	}

}
