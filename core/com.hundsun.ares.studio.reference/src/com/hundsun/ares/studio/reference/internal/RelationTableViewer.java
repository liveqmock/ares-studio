/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.reference.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ObjectUtils;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESElementChangedEvent;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESElementChangeListener;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IReferencedLibrary;
import com.hundsun.ares.studio.model.reference.ProjectRelationCollection;
import com.hundsun.ares.studio.model.reference.ReferenceFactory;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.model.reference.RelationInfo;
import com.hundsun.ares.studio.model.reference.RelationTable;
import com.hundsun.ares.studio.reference.ViewerUtils;

/**
 * 提供对引用关系的查看视图
 * 
 * @author gongyf
 *
 */
public class RelationTableViewer {
	
	private static class RemoveProjectInfo extends RecordingCommand {

		private RelationTable table;
		private IARESProject project;
		
		/**
		 * @param domain
		 * @param table
		 * @param project
		 */
		public RemoveProjectInfo(TransactionalEditingDomain domain,
				RelationTable table, IARESProject project) {
			super(domain);
			this.table = table;
			this.project = project;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
		 */
		@Override
		protected void doExecute() {
			table.getProjects().remove(project);
		}
		
	}
	
	private static class RemoveResourceInfo extends RecordingCommand {
		
		private RelationTable table;
		private IARESResource resource;
		
		
		/**
		 * @param domain
		 * @param table
		 * @param resource
		 */
		public RemoveResourceInfo(TransactionalEditingDomain domain,
				RelationTable table, IARESResource resource) {
			super(domain);
			this.table = table;
			this.resource = resource;
		}



		/* (non-Javadoc)
		 * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
		 */
		@Override
		protected void doExecute() {
			ProjectRelationCollection pi = table.getProjects().get(resource.getARESProject());
			pi.getRelations().getRelationOperator().removeRelationsByResource(resource);
		}
	}
	
	private static class ChangeResourceInfo extends RecordingCommand {
		private RelationTable table;
		private IARESResource resource;
		private Map<Object, Object> context;
		
		
		/**
		 * @param domain
		 * @param table
		 * @param resource
		 * @param context
		 */
		public ChangeResourceInfo(TransactionalEditingDomain domain,
				RelationTable table, IARESResource resource,
				Map<Object, Object> context) {
			super(domain);
			this.table = table;
			this.resource = resource;
			this.context = context;
		}


		/* (non-Javadoc)
		 * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
		 */
		@Override
		protected void doExecute() {
			ProjectRelationCollection pi = table.getProjects().get(resource.getARESProject());
			List<RelationInfo> oldList = ViewerUtils.getRelationInfos(table, resource);
			List<RelationInfo> newList = ViewerUtils.getRelationInfos(resource, context);
			
			// 找出新增的项目和删除的项目
			 HashSet<RelationInfo> tempSetAll = new HashSet<RelationInfo>();
		        for (int i=0; i<oldList.size(); i++) {
		        	tempSetAll.add(oldList.get(i));
		        }
		        HashSet<RelationInfo> setDup = new HashSet<RelationInfo>();
		        ArrayList<RelationInfo>  newClean = new ArrayList<RelationInfo>();
		        for (RelationInfo relationInfo: newList) {
		            if (tempSetAll.add(relationInfo)) {  
		            	newClean.add(relationInfo);
		            } else {
		            	setDup.add(relationInfo);  
		            }
		        }
		        ArrayList<RelationInfo> oldListClean = new ArrayList<RelationInfo>();
		        for (RelationInfo relationInfo: oldList ) {
		            if (setDup.add(relationInfo)) {  
		            	oldListClean.add(relationInfo);
		            }
		        }
		        pi.getRelations().getRelationOperator().updateRelationsByResourceChange(resource, oldListClean, newClean);
		        
		}
	}
	
	private static class AddProjectInfo extends RecordingCommand {
		private RelationTable table;
		private IARESProject project;
		private List<RelationInfo> infoList;
		
		/**
		 * @param domain
		 * @param table
		 * @param project
		 * @param collecion
		 */
		public AddProjectInfo(TransactionalEditingDomain domain,
				RelationTable table, IARESProject project,
				List<RelationInfo> infoList) {
			super(domain);
			this.table = table;
			this.project = project;
			this.infoList = infoList;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
		 */
		@Override
		protected void doExecute() {
			ProjectRelationCollection value = table.getProjects().get(project);
			if (value == null) {
				value = ReferenceFactory.eINSTANCE.createProjectRelationCollection();
				table.getProjects().put(project, value);
			}
			value.getRelations().getRelationOperator().initProjectRelation(infoList);
			
		}
	}
	
	/**
	 * 编辑内存统计引用信息的编辑域
	 */
	private TransactionalEditingDomain editingDomain;
	
	private RelationTable table;
	
	/**
	 * 用于保存进行过预处理的范围
	 */
	private Set<IARESProject> processedProjects = new HashSet<IARESProject>();
	
	private IResourceChangeListener listener = new IResourceChangeListener() {

		@Override
		public void resourceChanged(IResourceChangeEvent event) {
			IResourceDelta delta = event.getDelta();
			if (delta != null) {
				ARESElementDeltaVisitor visitor = new ARESElementDeltaVisitor();
				try {
					delta.accept(visitor);
					
					synchronized (processedProjects) {
						CompoundCommand command = new CompoundCommand();
						
						for (IARESElement element : visitor.getRemovedElements()) {
							if (element instanceof IARESProject) {
								// 工程被删除则需要删除所有相关的内容
								processedProjects.remove(element);
								
								// 删除内容
								command.append(new RemoveProjectInfo(editingDomain, table, (IARESProject)element));
							} else if (element instanceof IARESResource) {
								// 如果是已经处理的范围内则作增量处理
								IARESResource aresResource = (IARESResource) element;
								if (processedProjects.contains(aresResource.getARESProject())) {
									command.append(new RemoveResourceInfo(editingDomain, table, aresResource));
								}
							}
						}
						
						for (IARESElement element : visitor.getAddedOrChangedElements()) {
							// 添加和修改工程是不需要处理的
							if (element instanceof IARESResource) {
								IARESResource aresResource = (IARESResource) element;
								if (processedProjects.contains(aresResource.getARESProject())) {
									Map<Object, Object> context = createRelationProviderContext();
									command.append(new ChangeResourceInfo(editingDomain, table, aresResource, context));
								}
							}
						}
						
						excute(command);
					}
				} catch (CoreException e) {
				}
				
				
			}
							
		}
		
	};
	
	/**
	 * 用于监听引用变化
	 */
	private IARESElementChangeListener listener2 = new IARESElementChangeListener() {

		@Override
		public void elementChanged(ARESElementChangedEvent event) {
			if (event.getType() == ARESElementChangedEvent.RES_PATH) {
				IARESProject changedProject = (IARESProject) event.getElement();
				synchronized (processedProjects) {
					processedProjects.remove(changedProject);
					excute(new RemoveProjectInfo(editingDomain, table, changedProject));
				}
			}
		}};
	
	/**
	 * 
	 */
	public RelationTableViewer() {
		// 创建模型
		editingDomain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain();
		Resource xmlRes = new XMLResourceImpl();
		xmlRes.getContents().add(table = ReferenceFactory.eINSTANCE.createRelationTable());
		editingDomain.getResourceSet().getResources().add(xmlRes);
		
		ResourcesPlugin.getWorkspace().addResourceChangeListener(listener);
		ARESCore.addElementListener(listener2);
	}
	
	
	public void dispose() {
		ARESCore.removeElementListener(listener2);
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(listener);
		editingDomain.dispose();
		editingDomain = null;
	}
	
	/**
	 * @return the table
	 */
	public RelationTable getTable() {
		return table;
	}
	
	/**
	 * 创建获取资源引用信息时的上下文
	 * @return
	 */
	private Map<Object, Object> createRelationProviderContext() {
		return Collections.emptyMap();
	}
	
	// 以下的接口目前没用，所以暂时注释掉
//	/**
//	 * 查找出指定内容所引用的引用信息
//	 * 
//	 * @param path 一个工作空间的相对路径
//	 * @param location 定位字符串，可以为null
//	 * @return
//	 */
//	public List<RelationInfo> getRelationInfoBySource(IARESResource resource) {
//		
//		return null;
//	}
	
	/**
	 * 通过被引用的信息查找出使用了这些引用信息的引用关系
	 * 
	 * @param refType 不能为null
	 * @param refName 不能为null
	 * @param project 作为引用的起点位置，查找范围是该工程以及直接或间接依赖该工程的工程
	 * @return
	 */
	public List<RelationInfo> getRelationInfoByTarget(final String refType, final String refName, final IARESProject project) {
		process(project, true);
		
		try {
			return TransactionUtil.runExclusive(editingDomain, new RunnableWithResult.Impl<List<RelationInfo>>(){

				@Override
				public void run() {
					List<RelationInfo> result = new ArrayList<RelationInfo>();
					for (IARESProject p : ViewerUtils.getAllRelatedProjects(project)) {
						ProjectRelationCollection prc = table.getProjects().get(p);
						if (prc != null) {
							result.addAll(prc.getRelationInfos(refType, refName));
						}
					}
					
					setResult(result);
				}
				
			});
		} catch (InterruptedException e) {
			return Collections.emptyList();
		}
	}
	
	/**
	 * 通过被引用的信息查找出使用了这些引用信息的引用关系
	 * 
	 * @param refType 不能为null
	 * @param refName 不能为null
	 * @param refNamespace 不能为null
	 * @param project 作为引用的起点位置，查找范围是该工程以及直接或间接依赖该工程的工程
	 * @return
	 */
	public List<RelationInfo> getRelationInfoByTarget(final String refType, final String refName, final String refNamespace, final IARESProject project) {
		process(project, true);
		
		try {
			return TransactionUtil.runExclusive(editingDomain, new RunnableWithResult.Impl<List<RelationInfo>>(){

				@Override
				public void run() {
					List<RelationInfo> result = new ArrayList<RelationInfo>();
					for (IARESProject p : ViewerUtils.getAllRelatedProjects(project)) {
						ProjectRelationCollection prc = table.getProjects().get(p);
						if (prc != null) {
							result.addAll(prc.getRelationInfos(refType, refName, refNamespace));
						}
					}
					
					setResult(result);
				}
				
			});
		} catch (InterruptedException e) {
			return Collections.emptyList();
		}
	}
	
	/**
	 * 处理需要的工程，如果一个工程已经处理过则不会再进行处理
	 * @param project
	 * @param inRelatedProjects 是否处理相关的工程，即直接或间接依赖该工程的工程
	 */
	private void process(IARESProject project, boolean inRelatedProjects) {
		synchronized (processedProjects) {
			List<IARESProject> needProcessProjects = new ArrayList<IARESProject>();
			if (inRelatedProjects) {
				needProcessProjects.addAll(Arrays.asList(ViewerUtils.getAllRelatedProjects(project)));
			} else {
				needProcessProjects.add(project);
			}
			
			
			Map<Object, Object> context = createRelationProviderContext();
			
			CompoundCommand command = new CompoundCommand();
			
			for (IARESProject p : needProcessProjects) {
				
				if (!processedProjects.contains(p)) {
					
					List<RelationInfo>infoList = new ArrayList<RelationInfo>();
					
					// 需要收集工程本身和引用包本身
					List<IARESResource> resourceList = new ArrayList<IARESResource>();
					
					try {
						resourceList.addAll(Arrays.asList(p.getResources()));
						for (IReferencedLibrary lib : p.getReferencedLibs()) {
							resourceList.addAll(Arrays.asList(lib.getResources()));
						}
					} catch (ARESModelException e) {
					}
					
					for (IARESResource res : resourceList) {
						infoList.addAll(ViewerUtils.getRelationInfos(res, context));
					}
					command.append(new AddProjectInfo(editingDomain, table, p, infoList));
					
					// 设置标记
					processedProjects.add(p);
				}
			}
			
			excute(command);
		}
	}
	
	/**
	 * 执行数据修改命令
	 * @param command
	 */
	private void excute(Command command) {
		editingDomain.getCommandStack().execute(command);
		editingDomain.getCommandStack().flush();
	}
}
