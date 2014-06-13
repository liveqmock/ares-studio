/**
 * 源程序名称：JRESEditorSyncManager.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.sync;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;

/**
 * 编辑器同步管理类
 * @author lvgao
 *
 */
public class JRESEditorSyncManager implements IResourceChangeListener {
	private static final Logger logger = Logger.getLogger(JRESEditorSyncManager.class);

	public static JRESEditorSyncManager instance;
	
	private List<IFileSyncnizeUnit> syncList = new ArrayList<IFileSyncnizeUnit>();
	
	private JRESEditorSyncManager(){
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}

	public static JRESEditorSyncManager getInstance(){
		if(null == instance){
			instance = new JRESEditorSyncManager();
		}
		return instance;
	}

	/**
	 * 添加同步单元
	 * @param unit
	 */
	public void addSyncUnit(IFileSyncnizeUnit unit){
		syncList.add(unit);
	}
	
	/**
	 * 移除同步单元
	 * @param unit
	 */
	public void removeSyncUnit(IFileSyncnizeUnit unit){
		syncList.remove(unit);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
	 */
	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		try {
			IResourceDelta delta= event.getDelta();
			for(IFileSyncnizeUnit unit:syncList){
				unit.handleAction(delta);
			}
		} catch (Exception e) {
			logger.info("编辑器监听管理器处理监听失败。");
		}
	}
	
}
