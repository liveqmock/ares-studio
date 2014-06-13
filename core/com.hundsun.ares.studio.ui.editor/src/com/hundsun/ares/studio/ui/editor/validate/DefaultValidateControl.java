/**
 * 源程序名称：DefaultValidateControl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.validate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hundsun.ares.studio.ui.validate.IProblemPool;
import com.hundsun.ares.studio.ui.validate.IValidateControl;
import com.hundsun.ares.studio.ui.validate.IValidateUnit;

public class DefaultValidateControl implements IValidateControl{
	
	ValidateJob job;
	Map<Object, Object> context = new HashMap<Object, Object>();
	IProblemPool problempool;
	
	public DefaultValidateControl(){
	}
	
	protected List<IValidateUnit> unitList = new ArrayList<IValidateUnit>();
	

	@Override
	public void addValidateUnit(IValidateUnit unit) {
		unitList.add(unit);
	}

	@Override
	public void removeValidateUnit(IValidateUnit unit) {
		unitList.remove(unit);
	}

	protected ValidateJob getJob() {
		if (job == null) {
			job = new ValidateJob("错误检查");
			job.setSystem(true);
		}
		return job;
	}
	
	@Override
	public void refresh() {
		getJob().cancel();
		getJob().setProblempool(getProblemPool());
		// 上下文对象会在检查过程中修改
		getJob().setContext(new HashMap<Object, Object>(getContext()));
		getJob().setUnits(unitList.toArray(new IValidateUnit[0]));
		getJob().schedule(getCheckJobDelay());
	}
	
	protected long getCheckJobDelay() {
		return 200;
	}
	
	public IProblemPool getProblemPool() {
		return problempool;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.validate.IValidateControl#setProblemPool(com.hundsun.ares.studio.jres.ui.validate.IProblemPool)
	 */
	@Override
	public void setProblemPool(IProblemPool pool) {
		this.problempool = pool;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.validate.IValidateControl#setContext(java.util.Map)
	 */
	@Override
	public void setContext(Map<Object, Object> context) {
		this.context = context;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.validate.IValidateControl#getContext()
	 */
	@Override
	public Map<Object, Object> getContext() {
		return context;
	}

	@Override
	public void destroyAll() {
		unitList.clear();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.validate.IValidateControl#refresh(com.hundsun.ares.studio.jres.ui.validate.IValidateUnit)
	 */
	@Override
	public void refresh(IValidateUnit validateUnit) {
		getJob().cancel();
		getJob().setProblempool(getProblemPool());
		getJob().setContext(new HashMap<Object, Object>(getContext()));
		getJob().setUnits(new IValidateUnit[]{validateUnit});
		getJob().schedule(getCheckJobDelay());
	}

}
