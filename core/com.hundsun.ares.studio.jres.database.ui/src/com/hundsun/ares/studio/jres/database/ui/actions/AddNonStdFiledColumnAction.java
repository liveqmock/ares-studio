/**
 * 源程序名称：AddNonStdFiledColumnAction.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.actions;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.jres.database.ui.DatabaseUI;
import com.hundsun.ares.studio.jres.model.database.ColumnType;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerAddAction;
import com.hundsun.ares.studio.ui.editor.actions.IUpdateAction;

/**
 * 添加非标准字段的Action
 * @author sundl
 *
 */
public class AddNonStdFiledColumnAction extends ColumnViewerAddAction implements IUpdateAction{

	public static final String ID = "add_non_std_field_column";
	
	/**
	 * @param viewer
	 * @param editingDomain
	 */
	public AddNonStdFiledColumnAction(ColumnViewer viewer,	EditingDomain editingDomain, EObject owner, EReference reference, EClass clazz) {
		super(viewer, editingDomain, owner, reference, clazz);
		setText("增加非标准字段");
		setDescription("增加非标准字段");
		setId(ID);
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(DatabaseUI.PLUGIN_ID, "icons/full/obj16/add_non_std.png"));
		setInitializer(new NewObjectInitializer() {
			@Override
			public void initialize(EObject newObject) {
				((TableColumn) newObject).setColumnType(ColumnType.NON_STD_FIELD);
			}
		});
	}
	

}
