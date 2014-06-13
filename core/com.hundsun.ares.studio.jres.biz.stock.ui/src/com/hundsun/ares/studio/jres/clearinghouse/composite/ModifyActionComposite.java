/**
 * 源程序名称：ModifyActionComposite.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.clearinghouse.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;

/**
 * @author wangxh
 *
 */
public abstract class ModifyActionComposite extends Composite {

	//修改详细信息
	Modification action ;
	protected IARESResource resource;
	
	protected TableResourceData tableData;

	/**
	 * 创建修订内容的一般弹出框形式
	 * @param parent
	 * @param resource
	 * @param modification
	 */
	public ModifyActionComposite(Composite parent, TableResourceData tableData, IARESResource resource,Modification modification) {
		super(parent, SWT.None);
		this.resource = resource;
		this.tableData = tableData;
		initAction(modification);
		setLayout(new GridLayout(2,false));
		creatDetailComposite(this,resource);
	}
	
	/**
	 * 初始化修改信息
	 * @param action
	 */
	protected abstract void initAction(Modification action);
	
	/**
	 * 创建详细界面
	 * @param parent
	 * @param resource
	 */
	protected abstract void creatDetailComposite(Composite parent, IARESResource resource);
	
	/**
	 * 获取最终修改信息
	 * @return the action
	 */
	public abstract Modification getAction();
	
	public boolean canFinish() {
		return false;
	}
}
