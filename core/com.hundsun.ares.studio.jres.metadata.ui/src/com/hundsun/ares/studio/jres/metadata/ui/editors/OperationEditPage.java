/**
 * 源程序名称：OperationEditPage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.editors;

import java.util.EventObject;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.ui.forms.IManagedForm;

import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.Operation;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.EMFFormPage;
import com.hundsun.ares.studio.ui.editor.viewers.RefreshViewerJob;

/**
 * 对于可保存用户操作的模型进行操作信息的编辑
 * @author gongyf
 *
 */
public class OperationEditPage extends EMFFormPage {
	
	private static final Logger logger = Logger.getLogger(OperationEditPage.class); 
	
	 
	private OperationBlock block;

	public OperationEditPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	@Override
	protected void doCreateFormContent(IManagedForm managedForm) {
		// TODO#界面逻辑#龚叶峰#困难#秦元#代码状态 #完成时间 #代码行(不包括空白行和注释行) #工时(精确到分钟) #建一个master/Detail页面，master上是操作名列表，detail上是界面xml和代码
	
		block = new OperationBlock(this,getEditableControl());
		 
		block.createContent(managedForm);
		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#commandStackChanged(java.util.EventObject)
	 */
	@Override
	public void commandStackChanged(EventObject event) {
		super.commandStackChanged(event);
		RefreshViewerJob.refresh(block.getViewer());
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#isNeedValidate(org.eclipse.emf.transaction.ResourceSetChangeEvent)
	 */
	@Override
	protected boolean isNeedValidate(ResourceSetChangeEvent event) {
		for (Notification notification : event.getNotifications()) {
			if (notification.getFeature() == MetadataPackage.Literals.METADATA_RESOURCE_DATA__OPERATIONS
					|| notification.getNotifier() instanceof Operation) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return the block
	 */
	public OperationBlock getBlock() {
		return block;
	}
	
	@Override
	public void infoChange() {
		block.setInput(getInfo());
		super.infoChange();
	}

	@Override
	public void setActive(boolean active) {
		super.setActive(active);
		if (active) {
			ColumnViewer viewer = block.getViewer();
			if (viewer != null) {
				getSite().setSelectionProvider(viewer);
				logger.debug("Selection provider: " + viewer.hashCode() + " Site: " + getSite().hashCode());
			} else {
				logger.debug("Page actived, but no viewer!");
			}
		} 
	}
	
}
