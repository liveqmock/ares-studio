/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.basicdata.manage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataRestypes;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.EpacakgeDefineList;

/**
 * @author lvgao
 * 
 */
public class BasicdataDefineUpdator implements IResourceChangeListener,
		IResourceDeltaVisitor {

	Map<IARESProject, List<BasicdataDefineUpdateEvent>> changeMap = new HashMap<IARESProject, List<BasicdataDefineUpdateEvent>>();

	class BasicdataDefineUpdateEvent {

		public String filePath;
		public String moveToPath;
		public int flag;
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org
	 * .eclipse.core.resources.IResourceChangeEvent)
	 */
	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		IResourceDelta delta = event.getDelta();
		try {
			changeMap.clear();
			delta.accept(this);
			
			//修改资源
			 Job saveJob = new saveJob();
			 saveJob.setSystem(true);
			 saveJob.schedule();
		} catch (Exception e) {
		}
	}

	private void addFile(IARESProject project,int flag, String filepath,String moveToPath) {
		if (!changeMap.containsKey(project)) {
			changeMap.put(project, new ArrayList<BasicdataDefineUpdateEvent>());
		}
		BasicdataDefineUpdateEvent event = new  BasicdataDefineUpdateEvent();
		event.filePath = filepath;
		event.flag = flag;
		event.moveToPath = moveToPath;
		changeMap.get(project).add(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core
	 * .resources.IResourceDelta)
	 */
	@Override
	public boolean visit(IResourceDelta delta) throws CoreException {
		IResource resource = delta.getResource();

		if (resource instanceof IContainer) {// 文件夹
			return true;
		}

		if (IResourceDelta.REMOVED == delta.getKind()) {

			if (resource instanceof IFile) { // 文件
				IFile file = (IFile) resource;
				if (StringUtils.equals(IBasicDataRestypes.singleTable,
						file.getFileExtension())
						|| StringUtils.equals(
								IBasicDataRestypes.MasterSlaveTable,
								file.getFileExtension())
						|| StringUtils.equals(
								IBasicDataRestypes.MasterSlaveLinkTable,
								file.getFileExtension())) {
					IARESProject project = ARESCore.create(file.getProject());
					
					//排除移动时的删除
					if((IResourceDelta.MOVED_TO & delta.getFlags()) == 0) {
						addFile(project,delta.getFlags(),
						file.getProjectRelativePath().toOSString(),
						"");
					}
					
				}
			}
			return false;

		} else {
			return false;
		}

	}

	class saveJob extends Job {
		/**
		 * @param name
		 */
		public saveJob() {
			super("保存配置");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.
		 * IProgressMonitor)
		 */
		@Override
		protected IStatus run(IProgressMonitor monitor) {
			for (Entry<IARESProject, List<BasicdataDefineUpdateEvent>> entry : changeMap.entrySet()) {
				try {
					IARESResource defResource = BasicdataDefineManageUtil
							.getDefineResource(entry.getKey(), false);
					EpacakgeDefineList defList = defResource
							.getInfo(EpacakgeDefineList.class);

					// 如果已存在此路径,删除相关定义
					boolean fileChanged = false;
					for(BasicdataDefineUpdateEvent event:entry.getValue()){
						for (int i = defList.getItems().size() - 1; i >= 0; i--) {
								if(StringUtils.equals(event.filePath, defList.getItems().get(i).getUrl())){
									defList.getItems().remove(i);
									fileChanged = true;
								}
						}
					}
					
					if (fileChanged) {
						defResource.save(defList, true,
								new NullProgressMonitor());
					}
				} catch (Exception e) {
				}
			}
			return Status.OK_STATUS;
		}

	}

}
