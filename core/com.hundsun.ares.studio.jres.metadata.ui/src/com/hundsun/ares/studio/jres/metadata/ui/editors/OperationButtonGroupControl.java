/**
 * 源程序名称：OperationButtonGroupControl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.editors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.ui.actions.MetadataRunScriptAction;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;
import com.hundsun.ares.studio.jres.model.metadata.Operation;
import com.hundsun.ares.studio.jres.script.engin.RunScriptAction;

/**
 * 根据获取用户操作信息来得到编辑器的和脚本相关的动态按钮
 * 
 * @author gongyf
 * 
 */
public class OperationButtonGroupControl {

	public static final String GROUP_ID = "jres.meta.operation";
	
	private MetadataRunScriptAction[] actions;
	private IARESResource res;
	private MetadataResourceData<?> data;
	private ToolBarManager manager;
	private EContentAdapter adapter = new EContentAdapter() {
		public void notifyChanged(Notification notification) {
			super.notifyChanged(notification);
			// 处理的消息包括用户操作列表的变更
			if (notification.getNotifier() == data
					&& notification.getFeature() == MetadataPackage.Literals.METADATA_RESOURCE_DATA__OPERATIONS) {
				refresh();
			} else if (data.getOperations()
					.contains(notification.getNotifier())) {
				refresh();
			}

		};
	};

	/**
	 * @param manager
	 */
	public OperationButtonGroupControl(ToolBarManager manager) {
		super();
		this.manager = manager;
	}

	/**
	 * @return the manager
	 */
	public ToolBarManager getManager() {
		return manager;
	}

	public void setARESResource(IARESResource res) {
		this.res = res;
	}
	
	protected MetadataRunScriptAction createAction() {
		return new MetadataRunScriptAction();
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(MetadataResourceData<?> data) {
		if (this.data != null) {
			data.eAdapters().remove(adapter);
		}
		this.data = data;
		if (this.data != null) {
			data.eAdapters().add(adapter);
		}
	}

	public void refresh() {
		// 原则是尽量保持原先的Action对象
		RunScriptAction[] oldActions = actions;
		actions = new MetadataRunScriptAction[data.getOperations().size()];
		if (oldActions != null) {
			System.arraycopy(oldActions, 0, actions, 0,
					oldActions.length > actions.length ? actions.length
							: oldActions.length);
		}
		for (int i = 0; i < actions.length; i++) {
			Operation op = data.getOperations().get(i);
			if (actions[i] == null) {
				actions[i] = createAction();
				actions[i].setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_FILE));
			}

			actions[i].setRes(res);
			actions[i].setInfo(data);
			actions[i].setOperation(op);
			
			actions[i].setText(op.getTitle());
			String script = getFileContent(op.getFile());
			actions[i].setScript(script);
		}
		
		updateToolbarItems();
		manager.update(true);
	}
	
	private void updateToolbarItems() {
		// 先删除原来的
		boolean inOperationGroup = false;
		List<IContributionItem> toRemove = new ArrayList<IContributionItem>();
		for (IContributionItem item : manager.getItems()) {
			if (item.isGroupMarker()) {
				if (StringUtils.equals(item.getId(), GROUP_ID)) {
					inOperationGroup = true;
					continue;
				} else if (inOperationGroup) {
					break;
				}
				continue;
			}
			if (inOperationGroup) {
				toRemove.add(item);
			}
		}
		
		for (IContributionItem item : toRemove) {
			manager.remove(item);
		}
		
		for (IAction a : actions) {
			manager.appendToGroup(GROUP_ID, a);
		}
	}

	private String getFileContent(String fileName) {
		FileInputStream inputStream = null;
		try {
			File scriptFile = new File(fileName);
			inputStream = new FileInputStream(scriptFile);
			String scriptCode = IOUtils.toString(inputStream, "UTF-8");
			return scriptCode;
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} catch (Exception e) {
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
		return "";
	}

}
