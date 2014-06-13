/**
 * 源程序名称：MetadataListPage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.editors;

import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;
import com.hundsun.ares.studio.jres.script.engin.ScriptUtils;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.actions.ButtonGroupManager;
import com.hundsun.ares.studio.ui.editor.blocks.ColumnViewerListPage;

/**
 * 针对元数据的编辑器基类<br>
 * 在表格按钮区域增加了和脚本关联的按钮，能够根据脚本动态的添加和删除按钮<br>
 * 提供了{@link #createScriptContext()} 创建脚本上下文
 * 
 * @author gongyf
 * @author sundl mark Deprecated 
 */
@Deprecated 
public abstract class MetadataListPage extends ColumnViewerListPage {

	private OperationButtonGroupControl obgc;
	
	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public MetadataListPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	protected IARESResource getARESResource() {
		return getEditor().getARESResource();
	}
	
	/**
	 * @return the obgc
	 */
	protected OperationButtonGroupControl getOperationControl() {
		return obgc;
	}

	//创建一般按钮和根据脚本关联动态生成的按钮
	protected Control createViewerButtons(Composite parent, FormToolkit toolkit) {
		Composite client = toolkit.createComposite(parent);
		
		ButtonGroupManager btnGroupManager = new ButtonGroupManager();
		createButtons(btnGroupManager);
		btnGroupManager.createControl(client);
		
		ButtonGroupManager operationGroupManager = new ButtonGroupManager();
		//obgc = new OperationButtonGroupControl(operationGroupManager);
		operationGroupManager.createControl(client);
		
		toolkit.adapt(btnGroupManager.getControl());
		toolkit.adapt(operationGroupManager.getControl());
		
		GridLayoutFactory.fillDefaults().applyTo(client);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(btnGroupManager.getControl());
		GridDataFactory.fillDefaults().grab(true, true).applyTo(operationGroupManager.getControl());
		
		return client;
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
			}
		}
		return false;
	}

	//获取元数据列表
	protected MetadataResourceData<?> getInfo() {
		return (MetadataResourceData<?>) getEditor().getInfo();
	}
	

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.ColumnViewerListPage#infoChange()
	 */
	@Override
	public void infoChange() {
		getOperationControl().setData(getInfo());
		super.infoChange();
		//getOperationControl().setContext(createScriptContext());
	}
	
	//创建脚本上下文
	protected Map<String, Object> createScriptContext() {
		return ScriptUtils.createDefaultScriptContext(ScriptUtils.MODE_EDITOR_BUTTON,getARESResource(), getARESResource(), getInfo(), getClass().getClassLoader());
	}
}
