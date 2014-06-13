/**
 * 源程序名称：JRESReferencProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.core.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.context.statistic.IResourceTable;
import com.hundsun.ares.studio.model.reference.RelationInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

public class JRESReferencProvider implements /*IResourceChangeListener,*/IResReferenceProvider{

	IARESProject project;
	
	public JRESReferencProvider(IARESProject project){
		this.project = project;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.context.IResReferenceProvider#getReferList(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Object[] getReferList(String masterUniqueName,
			String masterNamespace, String masterType) {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.context.IResReferenceProvider#getLinkList(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Object[] getLinkList(String uniqueName, String namespace, String type) {
		List<RelationInfo> infoList = null;
		// FIXME JRES目前不使用命名空间
		infoList = ReferenceManager.getInstance().getRelationInfoByTarget(type, uniqueName, project);
		
//		if (StringUtils.equals(namespace, IResourceTable.Scope_IGNORE_NAMESPACE) || StringUtils.isBlank(namespace)) {
//			infoList = ReferenceManager.getInstance().getRelationInfoByTarget(type, uniqueName, project);
//		} else {
//			infoList = ReferenceManager.getInstance().getRelationInfoByTarget(type, uniqueName, namespace, project);
//		}
		
		return Collections2.transform(infoList, new Function<RelationInfo, Map<Object, Object>>() {

			@Override
			public Map<Object, Object> apply(RelationInfo from) {
				return transform(from);
			}
		}).toArray();
	}

	private static Map<Object, Object> transform(RelationInfo rel) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put(IResourceTable.RES_NAME, rel.getUsedRefName());
		map.put(IResourceTable.RES_NAMESPACE, rel.getUsedRefNamespace());
		map.put(IResourceTable.RES_TYPE, rel.getUsedRefType());
//		map.put(IResourceTable.TARGET_OWNER, rel.get);
		map.put(IResourceTable.TARGET_RESOURCE, rel.getHostResource());
		return map;
	}
	
//	private static Logger log = Logger.getLogger(JRESReferencProvider.class);
//	
//	IARESProject project;
//	
//	public ReferenceTable table = new ReferenceTable();
//	
//	private List<ITableDataProvider> tabledataprovider = new ArrayList<ITableDataProvider>();
//	
//	public JRESReferencProvider(IARESProject project){
//		this.project = project;
//		//建表
//		try {
//			Connection connect = null;
//			try {
//				connect = MemDBConnectionPool.getInstance()
//				.getConnection();
////			table.setTextTable(true);   //用文本方式存储
//				table.createTable(connect, MemTable.genID());
//				
//			} finally {
//				MemDBConnectionPool.getInstance().returnConnection(connect);
//			}
//			
//			//默认表数据提供器
//			DefaultReferenceProvider defaultReferenceProvider = new DefaultReferenceProvider();
//			defaultReferenceProvider.setArgs(project,table);
//			tabledataprovider.add(defaultReferenceProvider);
//			
//			ITableProviderFactory[] providers = TableDataProviderRegistry.getInstance().getAdapterByType(IValidateConstant.EXTENTION_TYPE_REFERENCE_TABLE_PROVIDER);
//			for(ITableProviderFactory factory:providers){
//				for(ITableDataProvider item:factory.createProviders()){
//					item.setArgs(project,table);
//					tabledataprovider.add(item);
//				}
//			}
//			//初始化
//			ArrayList<IContextUpdateSource> tmplist = new ArrayList<IContextUpdateSource>();
//			tmplist.add(new DefaultContextUpdateSource(ITableDataProvider.UPDATETYPE_INIT,null));
//			//初始化时锁住表
//			synchronized (table) {
//				update(tmplist);
//			}
//			ARESCore.addElementListener(new IARESElementChangeListener() {
//				@Override
//				public void elementChanged(ARESElementChangedEvent event) {
//					if (event.getType() == ARESElementChangedEvent.RES_PATH) {
//						IARESProject changedProject = (IARESProject) event.getElement();
//						if (changedProject.equals(JRESReferencProvider.this.project)) {
//							ArrayList<IContextUpdateSource> tmplist = new ArrayList<IContextUpdateSource>();
//							tmplist.add(new DefaultContextUpdateSource(IJRESContext.UPDATETYPE_RESPATH,new Object[]{event.getElement().getResource()}));
//							update(tmplist);
//						}
//					}
//				}
//			});
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void update(final Object context){
//		synchronized (IJRESContext.REFERENCE_UPDATE_SYNC_SIGNAL) {
//			for(ITableDataProvider provider:tabledataprovider){
//				provider.update(context);
//			}
//		}
//	}
//	
//	private Object getResourceByID(String index){
//		for(ITableDataProvider provider:tabledataprovider){
//			if(provider.contains(index)){
//				return provider.getDataByIndex(index);
//			}
//		}
//		return null;
//	}
//
//	public void clear(){
//		Connection connect = null;
//		try {
//			connect = MemDBConnectionPool.getInstance().getConnection();
//			Statement statement = connect.createStatement();
//			//statement.executeUpdate(String.format("drop table if exists %s;", tablename));
//			statement.executeUpdate(String.format("drop table %s;", table.getTablename()));
//			statement.close();
//		} catch (Throwable e) {
////			System.out.println("清空引用表数据失败。"+e.getMessage());
//		}finally{
//			MemDBConnectionPool.getInstance().returnConnection(connect);
//		}
//	}
//	
//	public void clearData(){
//		Connection connect = null;
//		try {
//			connect = MemDBConnectionPool.getInstance().getConnection();
//			Statement statement = connect.createStatement();
//			//statement.executeUpdate(String.format("drop table if exists %s;", tablename));
//			int lines = statement.executeUpdate(String.format("delete from %s;", table.getTablename()));
////			System.out.println("hhhhhhhhhhhhhh清空表"+lines);
//			statement.close();
//		} catch (Throwable e) {
//			System.out.println("清空引用表数据失败。"+e.getMessage());
//		}finally{
//			MemDBConnectionPool.getInstance().returnConnection(connect);
//		}
//	}
//	
//	@Override
//	protected void finalize() throws Throwable {
//		try {
//			clear();
//		} catch (Exception e) {
//		}
//		super.finalize();
//	}
//
//	@Override
//	public void resourceChanged(IResourceChangeEvent event) {
//		IResourceDelta delta = event.getDelta();
//		// 项目关闭的时候有时候会出现null？
//		if (delta == null)
//			return;
//		
//		IResource resource = event.getResource();
//
//		// 2012-05-25 sundl 添加项目删除/关闭前处理
//		switch (event.getType()) {
//		case IResourceChangeEvent.PRE_DELETE:
//		case IResourceChangeEvent.PRE_CLOSE:
//			// project-delete event should be processed before the underlying resource is really deleted.
//			if (resource.getType() == IResource.PROJECT ) {
//				IProject project = resource.getProject();
//				IARESProject aresProj = ARESCore.create(project);
//				if (aresProj.exists() && aresProj.equals(this.project)) {
//					// 项目删除/关闭
//					log.debug(String.format("检测到项目 %s 关闭或删除，清除数据中...", project.getName()));
//					// ContextManager中保存了项目对应Provider的列表，从列表中删除，下次再取的时候，就会重新初始化一个
//					log.debug(String.format("----清除项目 %s 在ContextProviderManager中的缓存...", project.getName()));
//					ContextProviderManager.getInstance().removeContextProvider(aresProj, IValidateConstant.KEY_REFERENCE_PROVIDER);
//					// 由于JRESContextManager中建了视图，而找不到合适的机会drop，所以导致表无法drop,暂时只清空数据
//					clearData();
//				}
//			}
//			return;
//		case IResourceChangeEvent.POST_CHANGE:
//			//checkProjectChanges(delta);
//			handleResourceChangeDelta(delta);
//			break;
//		}
//	}
//	
//	private void handleResourceChangeDelta(IResourceDelta delta) {
//		final Map<Object, Object> changemap = new HashMap<Object, Object>();
//		try {
//			delta.accept(new IResourceDeltaVisitor() {
//
//				public boolean visit(IResourceDelta delta) throws CoreException {
//					if (delta.getResource().getType() == IResource.FILE) {
//						IProject tmp = (IProject) delta.getResource().getProject();
//						if (!changemap.containsKey(tmp)) {
//							changemap.put(tmp, new ArrayList());
//						}
//						
//						if (delta.getKind() == IResourceDelta.REMOVED ) {
//							((List)changemap.get(tmp)).add(
//									new DefaultContextUpdateSource(IJRESContext.UPDATETYPE_DEL,new Object[]{delta.getResource()}
//							));
//						}else if(delta.getKind() == IResourceDelta.ADDED){
//							((List)changemap.get(tmp)).add(
//									new DefaultContextUpdateSource(IJRESContext.UPDATETYPE_ADD,new Object[]{delta.getResource()}
//							));
//						}else if (delta.getKind() == IResourceDelta.CHANGED) {
//							// 如果不是内容变化则不需要收集信息
//							if ( (delta.getFlags() & IResourceDelta.CONTENT) == 0) {
//								return false;
//							}
//							String filename = delta.getResource().getName();
////							if (IJRESContext.RESPATH.equals(filename)) {
////								//更新引用资源包等
////								((List)changemap.get(tmp)).add(
////										new DefaultContextUpdateSource(IJRESContext.UPDATETYPE_RESPATH,new Object[]{delta.getResource()}
////								));
////							}
//							if (IJRESContext.PROJECT_XML.equals(filename)) {
//								//更新引用资源包等
//								((List)changemap.get(tmp)).add(
//										new DefaultContextUpdateSource(IJRESContext.PROJECT_XML,new Object[]{delta.getResource()}
//								));
//							}
//							String fileExtension = delta.getResource().getFileExtension();
//							((List)changemap.get(tmp)).add(
//									new DefaultContextUpdateSource(IJRESContext.UPDATETYPE_FILE_CHANGE,new Object[]{delta.getResource()}
//									));
//						}
//						return false; // 已更新，不用再遍历
//				}
//					return true;
//			}
//			});
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		//如果资源变化的工程包含本工程，更新
//		if(changemap.containsKey(this.project.getProject())){
//			update(changemap.get(project.getProject()));
//		}
//	}
//
//	/* (non-Javadoc)
//	 * @see com.hundsun.ares.studio.jres.context.IResReferenceProvider#getReferList(java.lang.String, java.lang.String, java.lang.String)
//	 */
//	@Override
//	public Object[] getReferList(String masterUniqueName,
//			String masterNamespace, String masterType) {
//		return null;
//	}
//
//	/* (non-Javadoc)
//	 * @see com.hundsun.ares.studio.jres.context.IResReferenceProvider#getLinkList(java.lang.String, java.lang.String, java.lang.String)
//	 */
//	@Override
//	public Object[] getLinkList(String uniqueName, String namespace, String type) {
//		synchronized (IJRESContext.REFERENCE_UPDATE_SYNC_SIGNAL) {
//			Statement statement = null;
//			ResultSet rs = null;
//			Connection connect = null;
//			List<Object> tlist = new ArrayList<Object>();
//			try {
//				connect = MemDBConnectionPool.getInstance().getConnection();
//				statement = connect.createStatement();
//				
//				StringBuffer sql = new StringBuffer();
//				sql.append(String.format("select %s from %s where",IReferenceTable.MASTER_INDEX, table.getTablename()));
//				if (StringUtils.isNotBlank(uniqueName)) {
//					sql.append(String.format(" %s='%s' and", IReferenceTable.SLAVE_NAME,uniqueName));
//				}
//				sql.append(String.format(" %s='%s'",IReferenceTable.SLAVE_TYPE,type ));
//				sql.append(String.format(" and %s='%s'",IReferenceTable.SLAVE_NAMESPACE,namespace ));
//				
//				rs = statement.executeQuery(sql.toString());
//				
//				// 判断有无记录
//				while (rs.next()) {
//					tlist.add(getResouceByIndex(rs.getString(IReferenceTable.MASTER_INDEX)));
//				} 
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					// 归还连接
//					try {rs.close();} catch (Exception e2) {}
//					try {statement.close();} catch (Exception e2) {}
//					MemDBConnectionPool.getInstance().returnConnection(connect);
//				} catch (Exception e2) {
//					e2.printStackTrace();
//				}
//			}
//			return tlist.toArray();
//		}
//	}
//	
//	public Object getResouceByIndex(String index) {
//		for(ITableDataProvider provider:tabledataprovider){
//			if(provider.contains(index)){
//				return provider.getDataByIndex(index);
//			}
//		}
//		return null;
//	}

}
