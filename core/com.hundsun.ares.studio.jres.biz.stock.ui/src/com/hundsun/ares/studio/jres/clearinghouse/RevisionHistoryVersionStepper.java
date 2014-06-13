/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TriggerListener;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.core.model.util.RevisionHistoryUtil;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseResType;

/**
 * @author gongyf
 *
 */
public class RevisionHistoryVersionStepper extends TriggerListener implements
		ResourceSetListener {

	/* (non-Javadoc)
	 * @see org.eclipse.emf.transaction.TriggerListener#trigger(org.eclipse.emf.transaction.TransactionalEditingDomain, org.eclipse.emf.common.notify.Notification)
	 */
	@Override
	protected Command trigger(TransactionalEditingDomain domain,
			Notification notification) {
		if (CorePackage.Literals.JRES_RESOURCE_INFO__HISTORIES.equals(notification.getFeature()) 
				&& notification.getNewValue() instanceof RevisionHistory 
				/*&& notification.getNotifier() instanceof TableResourceData*/) {
			RevisionHistory rh = (RevisionHistory) notification.getNewValue();
			if (StringUtils.isBlank(rh.getVersion())) {
				// 增加了修订历史后，把版本增加
				return new IncreaseVersionCommand(domain, (JRESResourceInfo) notification.getNotifier(), rh);
			}
			
			
		}
		return null;
	}
	
	static class IncreaseVersionCommand extends RecordingCommand {
		
		private JRESResourceInfo table;
		private RevisionHistory history;

		/**
		 * @param domain
		 * @param table
		 * @param history
		 */
		public IncreaseVersionCommand(TransactionalEditingDomain domain,
				JRESResourceInfo table, RevisionHistory history) {
			super(domain);
			this.table = table;
			this.history = history;
		}
		
		@Override
		protected void doExecute() {
			// 查找项目属性
			IARESProject project = null;
			IFile file = null;
			try {
				String path = table.eResource().getURI().toPlatformString(true);
				
				file = (IFile) ResourcesPlugin.getWorkspace().getRoot().findMember(path);
				project = ARESCore.create(file.getProject());
			} catch (Exception e) {
			}
			
			history.setVersion(getMaxVersion(project, file));
		}
		
		private String getMaxVersion (IARESProject project ,IFile file ){
			String versionStr = "1.0.0.0";
			IARESModule topModule = null; 
			IARESResource resource = null;
			if (project == null) {
				return versionStr;
			} else {
				IPath path = file.getFullPath().makeRelativeTo(project.getPath());
				try {
					String fullName = StringUtils.substringAfter(path.toOSString(), File.separator);
					fullName = StringUtils.replace(fullName, File.separator, ".");
					fullName = StringUtils.substringBeforeLast(fullName, ".");
					resource = project.findResource(fullName, IDatabaseResType.Table);
				} catch (ARESModelException e) {
					e.printStackTrace();
				}
				if (resource == null) {
					return versionStr;
				}else {
					String rootType = resource.getRoot().getType(); 
					if (ARESElementUtil.isDatabaseRoot(rootType)) {
						topModule = ARESElementUtil.getTopModule(resource);
					} else if (ARESElementUtil.isMetadataRoot(rootType)) {
						// topModule为null的效果就是不计算模块
						topModule = null;
					} else {
						topModule = resource.getModule();
					}
				}
			}
			
			// 当前已经保存的资源中的最大版本
			String currentVersion = RevisionHistoryUtil.getMaxVersion(topModule);
			// 当前编辑器中的最大版本
			String maxInEditor = RevisionHistoryUtil.getMaxVersion((List<RevisionHistory>)table.eGet(CorePackage.Literals.JRES_RESOURCE_INFO__HISTORIES));

			// 项目属性
			String projectVersion = RevisionHistoryUtil.getProjectPropertyVersion(resource.getARESProject());
			
			// 找上述3者最大值
			versionStr = RevisionHistoryUtil.max(Arrays.asList(currentVersion, maxInEditor, projectVersion));
			// 第一次找不到任何记录的时候
			if (StringUtils.isEmpty(versionStr)) {
				versionStr = "1.0.0.0";
			}
			return versionStr;
		}
		
	}

	
}
