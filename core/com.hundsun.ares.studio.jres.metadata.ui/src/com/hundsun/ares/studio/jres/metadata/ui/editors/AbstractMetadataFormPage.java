/**
 * 源程序名称：AbstractMetadataFormPage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.editors;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.core.IValidateConstant;
import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.EMFFormPage;
import com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerBlock;

/**
 * @author yanwj06282
 *
 */
public abstract class AbstractMetadataFormPage extends EMFFormPage {

	private static final Logger logger = Logger.getLogger(AbstractMetadataFormPage.class);
	
	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public AbstractMetadataFormPage(EMFFormEditor editor, String id,
			String title) {
		super(editor, id, title);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#isNeedValidate(org.eclipse.emf.transaction.ResourceSetChangeEvent)
	 */
	@Override
	protected boolean isNeedValidate(ResourceSetChangeEvent event) {
		for (Notification notification : event.getNotifications()) {
			if (notification.getFeature() == MetadataPackage.Literals.METADATA_CATEGORY__CHILDREN
					|| notification.getFeature() == MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS
					|| notification.getNotifier() instanceof MetadataItem
					|| notification.getNotifier() instanceof MetadataCategory) {
				return true;
			}else if(null != notification.getFeature() &&notification.getFeature() instanceof  EStructuralFeature){
				//获取是否需要检查的元信息
				EAnnotation annotaion = ((EStructuralFeature)notification.getFeature()).getEAnnotation(IValidateConstant.EXTENTION_VALIDATE_SOURCE);
				if(null != annotaion){ //没有元信息
					String value = annotaion.getDetails().get(IValidateConstant.EXTENTION_VALIDATE_KEY_NEEDVALIDATE);
					if(StringUtils.equals("true", value)){
						return true;
					}
				}
			}
		}
		return false;
	}

	//获取元数据列表
	protected MetadataResourceData<?> getInfo() {
		return (MetadataResourceData<?>) getEditor().getInfo();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#doCreateFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void doCreateFormContent(IManagedForm managedForm) {

		Composite body = managedForm.getForm().getBody();
		FormToolkit toolkit = managedForm.getToolkit();
		
		createMetadataComposite(body, toolkit);
		body.setLayout(new FillLayout());
		
	}

	/**
	 * 构件编辑区域
	 * 
	 * @param body
	 * @param toolkit
	 */
	protected abstract void createMetadataComposite(Composite body, FormToolkit toolkit);
	
	protected abstract ColumnViewerBlock getViewerBlock();
	
	@Override
	public void setActive(boolean active) {
		super.setActive(active);
		if (active) {
			ColumnViewerBlock block = getViewerBlock();
			ColumnViewer viewer = block == null ? null : block.getColumnViewer();
			if (viewer != null) {
				getSite().setSelectionProvider(viewer);
				logger.debug("Selection provider: " + viewer.hashCode() + " Site: " + getSite().hashCode());
			} else {
				logger.debug("Page actived, but no viewer!");
			}
		} 
	}
}
