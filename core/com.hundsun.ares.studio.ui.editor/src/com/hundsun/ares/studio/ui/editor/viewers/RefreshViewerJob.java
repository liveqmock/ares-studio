/**
 * 源程序名称：RefreshViewerJob.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.viewers;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.ui.progress.UIJob;

/**
 * @author gongyf
 *
 */
public class RefreshViewerJob extends UIJob {
	
	private static Set<RefreshViewerJob> jobSet = new HashSet<RefreshViewerJob>();
	
	private ColumnViewer viewer;
	private Object[] elements;
	private boolean updateLables;
	
	/**
	 * 指定刷新一个视图，刷新过程不一定是即时的，但是刷新保证不影响用户在视图上的编辑行为
	 * TODO 相同的刷新操作被反复调用后应该只运行一次
	 * @param viewer
	 */
	public static void refresh(ColumnViewer viewer, Object[] elements,  boolean updateLables) {
		RefreshViewerJob job = new RefreshViewerJob(viewer, elements, updateLables);
		if (!jobSet.contains(job)) {
			jobSet.add(job);
			job.schedule();
			job.addJobChangeListener(new JobChangeAdapter(){
				/* (non-Javadoc)
				 * @see org.eclipse.core.runtime.jobs.JobChangeAdapter#done(org.eclipse.core.runtime.jobs.IJobChangeEvent)
				 */
				@Override
				public void done(IJobChangeEvent event) {
					jobSet.remove(event.getJob());
				}
			});
		}
		
	}
	
	public static void refresh(ColumnViewer viewer, Object[] elements) {
		refresh(viewer, elements, true);
	}
	
	public static void refresh(ColumnViewer viewer) {
		refresh(viewer, null, true);
	}
	
	/**
	 * @param name
	 */
	private RefreshViewerJob(ColumnViewer viewer, Object[] elements,  boolean updateLables) {
		super("刷新视图");
		this.viewer = viewer;
		
		setSystem(true);
		setPriority(SHORT);
		
		this.elements = elements;
		this.updateLables = updateLables;
	}
	
	@Override
	public boolean shouldRun() {
		return !viewer.getControl().isDisposed();
	}
	
	@Override
	public boolean shouldSchedule() {
		return !viewer.getControl().isDisposed();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.progress.UIJob#runInUIThread(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public IStatus runInUIThread(IProgressMonitor monitor) {
		if (viewer != null && !viewer.getControl().isDisposed()) {
			if (viewer.isCellEditorActive() || viewer.isBusy()) {
				this.schedule(getJobDelay());
			} else {
				if (elements == null) {
					viewer.refresh(updateLables);
				} else {
					if (elements.length > 10) {
						viewer.refresh();
					} else {
						viewer.update(elements, null);
					}
					
				}
			}
		}
		return Status.OK_STATUS;
	}
	
	protected long getJobDelay() {
		return 100;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof RefreshViewerJob) {
			RefreshViewerJob other = (RefreshViewerJob) obj;
			if (this.updateLables = other.updateLables 
					&& this.viewer == other.viewer 
					&& ArrayUtils.isEquals(this.elements, other.elements)) {
				return true;
			}
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(updateLables).append(viewer).append(elements).toHashCode();
//		return (updateLables ? 1 : -1) * (viewer.hashCode() + ArrayUtils.hashCode(elements));
	}
}
