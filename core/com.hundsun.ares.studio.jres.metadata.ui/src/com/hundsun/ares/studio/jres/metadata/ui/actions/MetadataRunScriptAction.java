/**
 * 源程序名称：RunScriptAction.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.actions;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.ui.PlatformUI;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.jres.metadata.ui.MetadataUI;
import com.hundsun.ares.studio.jres.model.metadata.Operation;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataScriptUtil;
import com.hundsun.ares.studio.jres.script.engin.RunScriptAction;
import com.hundsun.ares.studio.jres.script.engin.ScriptUtils;

/**
 * @author liaogc
 * 
 */
public class MetadataRunScriptAction extends RunScriptAction {

	private IARESResource res;
	private JRESResourceInfo info;
	private Operation operation;
	
	/**
	 * @param op
	 */
	public MetadataRunScriptAction() {
		super();
	}
	
	public IARESResource getRes() {
		return res;
	}

	public void setRes(IARESResource res) {
		this.res = res;
	}

	public JRESResourceInfo getInfo() {
		return info;
	}

	public void setInfo(JRESResourceInfo info) {
		this.info = info;
	}
	
	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		try {
			String scriptFileLocation = operation.getFile();
			IARESResource jsResource = MetadataScriptUtil.getJSResource(scriptFileLocation);
			ScriptUtils.excuteJS(ScriptUtils.MODE_EDITOR_BUTTON, jsResource, res, info, getClass().getClassLoader(), context);
		} catch (Exception e) {
			ErrorDialog.openError(PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getShell(), "脚本执行错误", e
					.getMessage(),
					new Status(Status.ERROR, MetadataUI.PLUGIN_ID, e.getMessage(), e));
		}
	}
	
	@Override
	public String getId() {
		return toString();
	}
}
