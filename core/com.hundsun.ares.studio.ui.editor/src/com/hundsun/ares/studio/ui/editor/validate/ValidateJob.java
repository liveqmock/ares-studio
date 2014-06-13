/**
 * 源程序名称：ValidateJob.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.validate;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IValidateConstant;
import com.hundsun.ares.studio.core.preferences.ErrorCheckPreferenceHelper;
import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;
import com.hundsun.ares.studio.ui.validate.IProblemPool;
import com.hundsun.ares.studio.ui.validate.IValidateUnit;

/**
 * 错误验证Job，对于同一类型的错误验证不会多次运行
 * @author gongyf
 *
 */
public class ValidateJob extends Job {

	/**
	 * 错误验证JOB所在的家族
	 */
	public final static Object FAMILY = new Object();
	
	private IValidateUnit[] units;
	private IProblemPool problempool;
	private Map<Object, Object> context;
	
	/**
	 * @param name
	 */
	public ValidateJob(String name) {
		super(name);
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(Map<Object, Object> context) {
		this.context = context;
	}
	
	/**
	 * @param problempool the problempool to set
	 */
	public void setProblempool(IProblemPool problempool) {
		this.problempool = problempool;
	}
	
	/**
	 * @param units the units to set
	 */
	public void setUnits(IValidateUnit[] units) {
		this.units = units;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.jobs.Job#belongsTo(java.lang.Object)
	 */
	@Override
	public boolean belongsTo(Object family) {
		// 所有的错误检查job都属于一个组
		return family == FAMILY;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.jobs.Job#shouldRun()
	 */
	@Override
	public boolean shouldRun() {
		// 如果有类似的job存在，则不需要运行了，把运行的机会给下一次类似的job
//		Job[] jobs = getJobManager().find(FAMILY);
//		for (Job job : jobs) {
//			if (job != this && job.equals(this)) {
//				return false;
//			}
//		}
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ValidateJob) {
			ValidateJob other = (ValidateJob)obj;
			if (Arrays.equals(units, other.units) 
					&& ObjectUtils.equals(problempool, other.problempool)
					/*&& ObjectUtils.equals(context, other.context)  暂时忽略上下文*/) {
				return true;
			}
		}
		
		return super.equals(obj);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected IStatus run(IProgressMonitor monitor) {
		// 2012-06-25 sundl 添加异常ConcurrentModificationException特殊处理，重新进行错误检查
		monitor.beginTask("check", IProgressMonitor.UNKNOWN);
		while(true) {
			Object resource  = context.get(IValidateConstant.KEY_RESOUCE);
			if(resource!=null && resource instanceof IARESResource){
				IARESResource aresResource = (IARESResource) resource;
				if(ErrorCheckPreferenceHelper.getInstance().isErrorCheck(aresResource.getType())){
					try {
						for (IValidateUnit unit : units) {
							unit.updateProblemPool(problempool, context);
							monitor.worked(1);
						}
					} catch(ConcurrentModificationException e) {
						continue;
					} catch (Exception e) {
						e.printStackTrace();
						return new Status(Status.ERROR, ARESEditorPlugin.PLUGIN_ID, "错误检查发生异常", e);
					} finally {
						monitor.done();
					}
				}
			}
			
			return Status.OK_STATUS;
		}
	}

}
