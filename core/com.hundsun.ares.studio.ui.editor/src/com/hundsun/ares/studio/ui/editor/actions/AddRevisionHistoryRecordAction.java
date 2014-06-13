package com.hundsun.ares.studio.ui.editor.actions;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.core.model.util.RevisionHistoryUtil;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;

/**
 * 加记录
 * @author sundl
 *
 */
public class AddRevisionHistoryRecordAction extends AddRevisionHistoryAction {

	public static final String ID = "add_revision_history_id";
	
	private IARESResource aresResource;
	
	public AddRevisionHistoryRecordAction(ColumnViewer viewer, EditingDomain editingDomain, EObject info, EReference eReference, IARESResource aresResource) {
		super(viewer, editingDomain, info, eReference);
		this.aresResource = aresResource;
		
		setId(ID);
		setText("加记录");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(ARESEditorPlugin.PLUGIN_ID, "icons/full/obj16/addRevisionRecord.png"));
	}
	
	public void setInfo(JRESResourceInfo info) {
		this.info = info;
	}

	@Override
	protected String getVersion() {
		// 2012-09-28 sundl 计算最大版本的时候只取资源所在的当前层的模块
		// 2012-11-21 sundl 数据库下的资源按顶层模块计算； 其他地方的资源仍然取当前模块
		// 2012-12-28 sundl 元数据下的 资源不计算模块，只在本资源和项目属性中取最大值
		// 2014年4月9日16:50:15 秦元 UFT对象，需要取所有对象资源的版本
		
		// 模块根下所有资源的最大版本
		String moduleRootVersion = null;
		IARESModule topModule = null; 
		if (aresResource == null) {
			topModule = null; 
		} else {
			String rootType = aresResource.getRoot().getType(); 
			if (ARESElementUtil.isDatabaseRoot(rootType)) {
				topModule = ARESElementUtil.getTopModule(aresResource);
			} else if (ARESElementUtil.isMetadataRoot(rootType)) {
				// topModule为null的效果就是不计算模块
				topModule = null;
			} else if(ARESElementUtil.isUFTStructureRoot(rootType)) {
				topModule = null;
				moduleRootVersion = RevisionHistoryUtil.getMaxVersionByModuleRoot(aresResource.getRoot());
			} else {
				topModule = aresResource.getModule();
			}
		}
		
		// 当前已经保存的资源中的最大版本
		String currentVersion = RevisionHistoryUtil.getMaxVersion(topModule);
		// 当前编辑器中的最大版本
		String maxInEditor = RevisionHistoryUtil.getMaxVersion((List<RevisionHistory>)info.eGet(eReference));

		// 项目属性
		String projectVersion = RevisionHistoryUtil.getProjectPropertyVersion(aresResource.getARESProject());
		
		// 找上述3者最大值
		String versionStr = RevisionHistoryUtil.max(Arrays.asList(currentVersion, maxInEditor, projectVersion,moduleRootVersion));
		// 第一次找不到任何记录的时候
		if (StringUtils.isEmpty(versionStr)) {
			versionStr = "1.0.0.0";
		} 
		return versionStr;
	}

	// 因为这个这个Action的执行结果依赖于项目属性，但是无法监听，所以每次执行都重新创建，保证刷新。
	@Override
	public Command getCommand() {
		return createCommand();
	}
	
}
