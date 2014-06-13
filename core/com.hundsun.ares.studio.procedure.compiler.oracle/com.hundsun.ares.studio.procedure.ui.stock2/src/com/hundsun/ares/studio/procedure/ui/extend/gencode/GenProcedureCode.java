/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.ui.extend.gencode;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProblem;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.SqlFormater;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.GenCresModuleCode;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.util.ModuleGeneratorHelper;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procedure.ui.module.code.GenProcedureModuleCode;
import com.hundsun.ares.studio.ui.ARESUI;

/**
 * @author qinyuan
 *
 */
public class GenProcedureCode extends GenCresModuleCode {
	
	protected Queue<IARESProblem> msgQueue = new ArrayDeque<IARESProblem>();
	
	public Queue<IARESProblem> getARESProblems(){
		return msgQueue;
	}
	
	public String genProcedureCode(IARESResource res, 
			boolean isWithPath, boolean isPathWithCname, IProgressMonitor monitor) {
		StringBuffer codeBuffer = new StringBuffer();
		try {
			//生成代码时，错误信息输出的消息队列
			
			GenProcedureModuleCode genCodeUtil = new GenProcedureModuleCode();
			Procedure procedure = res.getInfo(Procedure.class);
			genCodeUtil.setIsHeadCode(isHeadCode);
			genCodeUtil.setIsEndCode(isEndCode);
			codeBuffer.append(genCodeUtil.genProcedureCode(res,procedure,msgQueue,genCodeUtil.getContext(res.getARESProject())));
			codeBuffer.append("\n");
			codeBuffer.append("\n");

		} catch (Exception e) {
			e.printStackTrace();
			monitor.setCanceled(true);
		}
		return codeBuffer.toString();
	}
	

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.cres.extend.ui.module.gencode.IGenCresModuleCode#genModuleCode(com.hundsun.ares.studio.core.IARESModule, java.util.Map, boolean, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void genModuleCode(IARESModule module, Map<Object, Object> context,
			boolean isWithPath, boolean isPathWithCname,
			IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canGenCode(IARESModule module) {
		// TODO Auto-generated method stub
		return false;
	}

}
