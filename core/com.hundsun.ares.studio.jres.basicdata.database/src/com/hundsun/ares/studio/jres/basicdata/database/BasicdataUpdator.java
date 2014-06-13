/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.basicdata.database;

import java.util.ArrayList;
import java.util.List;

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
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataRestypes;
import com.hundsun.ares.studio.jres.basicdata.logic.epackage.BasicDataEpackageFactory;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseRefType;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseResType;
import com.hundsun.ares.studio.model.reference.RelationInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author wangxh
 * 修改数据库表，更新对应的基础数据模型
 */
public class BasicdataUpdator implements IResourceChangeListener,
		IResourceDeltaVisitor {
	
	List<IARESResource> basicDataResources = new ArrayList<IARESResource>();
	
	@Override
	public boolean visit(IResourceDelta delta) throws CoreException {
		IResource resource = delta.getResource();
		
		if (resource instanceof IContainer) {// 文件夹
			return true;
		}
		
		String type = resource.getFileExtension();
		if(resource instanceof IFile && StringUtils.equals(type, IDatabaseResType.Table)){
			//排除移动时的删除
			if((IResourceDelta.MOVED_TO & delta.getFlags()) == 0) {
				String name = resource.getName().substring(0, resource.getName().lastIndexOf("."));
				List<RelationInfo> infos = ReferenceManager.getInstance().getRelationInfoByTarget(IDatabaseRefType.Table, name, ARESCore.create(resource.getProject()));
				for(RelationInfo info : infos){
					IARESResource host = info.getHostResource();
					if (StringUtils.equals(IBasicDataRestypes.singleTable,host.getType())
							|| StringUtils.equals(IBasicDataRestypes.MasterSlaveTable,host.getType())
							|| StringUtils.equals(IBasicDataRestypes.MasterSlaveLinkTable,host.getType())){
						basicDataResources.add(info.getHostResource());
					}
				}
			}
		}
		return false;
	}

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		IResourceDelta delta = event.getDelta();
		try {
			basicDataResources.clear();
			delta.accept(this);
			
			//修改资源
			 Job saveJob = new UpdateJob();
			 saveJob.setSystem(true);
			 saveJob.schedule();
		} catch (Exception e) {
		}
	}

	class UpdateJob extends Job{

		public UpdateJob() {
			super("更新基础数据模型");
		}

		@Override
		protected IStatus run(IProgressMonitor monitor) {
			for(IARESResource res : basicDataResources){
				BasicDataEpackageFactory.eINSTANCE.clearEPackage(res);
			}
			return Status.OK_STATUS;
		}
		
	}
}
