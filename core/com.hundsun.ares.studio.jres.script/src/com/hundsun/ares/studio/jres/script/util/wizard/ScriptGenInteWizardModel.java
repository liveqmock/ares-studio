/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.util.wizard;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.script.internal.useroption.UserOptionRoot;

/**
 * 统一生成向导对应的模型
 * @author liaogc
 *
 */
public class ScriptGenInteWizardModel {
	protected static final Object[] NO_CHILDREN = new Object[0];

	private static ScriptGenInteWizardModel root = getRoot();

	private List<ScriptGenInteWizardModel> lstChildren = new ArrayList<ScriptGenInteWizardModel>();
	private ScriptGenInteWizardModel parent = root;
	private boolean hasChild = false;
	private String scriptDesc;//脚本对应的简短说明
	private String scriptPath;//脚本路径(相对工具资源)
	private IARESResource jsResource;//脚本资源
	private UserOptionRoot optionRoot;//脚本对应的配置信息

	
	

	public ScriptGenInteWizardModel(String scriptDesc,String scriptPath) {
		this.scriptDesc = scriptDesc;
		this.scriptPath = scriptPath;
	}

	public static ScriptGenInteWizardModel getRoot() {
		if (root == null) {
			root = new ScriptGenInteWizardModel("ROOT","");
		}
		return root;
	}

	
	
	public Object[] getChildren() {
		if (!this.hasChild) {
			return NO_CHILDREN;
		}
		return (ScriptGenInteWizardModel[]) this.lstChildren
				.toArray(new ScriptGenInteWizardModel[this.lstChildren.size()]);
	}

	public void addChildren(ScriptGenInteWizardModel child) {
		this.lstChildren.add(child);
		child.setParent(this);
		setHasChild(true);
	}

	public ScriptGenInteWizardModel getParent() {
		return this.parent;
	}

	private void setParent(ScriptGenInteWizardModel parent) {
		this.parent = parent;
	}

	public boolean isHasChild() {
		return this.hasChild;
	}

	private void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}
	
	/**
	 * @return the scriptDesc
	 */
	public String getScriptDesc() {
		return scriptDesc;
	}

	/**
	 * @param scriptDesc the scriptDesc to set
	 */
	public void setScriptDesc(String scriptDesc) {
		this.scriptDesc = scriptDesc;
	}

	/**
	 * @return the scriptPath
	 */
	public String getScriptPath() {
		return scriptPath;
	}

	/**
	 * @param scriptPath the scriptPath to set
	 */
	public void setScriptPath(String scriptPath) {
		this.scriptPath = scriptPath;
	}
	/**
	 * @return the jsResource
	 */
	public IARESResource getJsResource() {
		return jsResource;
	}

	/**
	 * @param jsResource the jsResource to set
	 */
	public void setJsResource(IARESResource jsResource) {
		this.jsResource = jsResource;
	}
	/**
	 * @return the optionRoot
	 */
	public UserOptionRoot getOptionRoot() {
		return optionRoot;
	}

	/**
	 * @param optionRoot the optionRoot to set
	 */
	public void setOptionRoot(UserOptionRoot optionRoot) {
		this.optionRoot = optionRoot;
	}

}
