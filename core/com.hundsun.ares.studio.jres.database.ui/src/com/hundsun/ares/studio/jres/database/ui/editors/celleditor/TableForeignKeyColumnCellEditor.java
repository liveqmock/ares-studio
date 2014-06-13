/**
 * 源程序名称：TableForeignKeyColumnCellEditor.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.editors.celleditor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESBundle;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseResType;
import com.hundsun.ares.studio.jres.database.ui.editors.dialog.ForeignKeyFieldSelectDialog;
import com.hundsun.ares.studio.jres.model.database.ForeignKey;
import com.hundsun.ares.studio.jres.model.database.TableKey;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;

/**外健CellEditor
 * @author liaogc
 * 
 */
public class TableForeignKeyColumnCellEditor extends DialogCellEditor {
	
	private IARESResource resource;//当前编辑的资源
	private Collection<ForeignKey> foreignKeies = new ArrayList<ForeignKey>();
	private TableKey tablekey;//要编辑的行
	ForeignKeyFieldSelectDialog dialog;
	int returnCode;

	public TableForeignKeyColumnCellEditor(ColumnViewer columnViewer,
			IARESResource resource, String fromText, String toText) {
		super((Composite) columnViewer.getControl());
		this.resource = resource;
	}

	@Override
	protected Object doGetValue() {
		return foreignKeies;
	}

	@Override
	protected void doSetValue(Object value) {
		if (returnCode == Window.CANCEL) {
			setValueValid(false);
		}else {
			setValueValid(true);
		}
		if (value instanceof EObjectContainmentEList) {
			tablekey = (TableKey) ((EObjectContainmentEList) value).getEObject();
		}

		foreignKeies.clear();
		foreignKeies.addAll(EcoreUtil.copyAll((Collection<ForeignKey>) value));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(org.eclipse.
	 * swt.widgets.Control)
	 */
	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		dialog = new ForeignKeyFieldSelectDialog(
				cellEditorWindow.getShell(), 
				resource.getARESProject(),
				tablekey);
		returnCode = dialog.open();
		Object obj = (returnCode == Window.OK) ? dialog.getResult() : null;
		if(obj == null){
			setValueValid(false);
		}
		return obj;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.DialogCellEditor#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createControl(Composite parent) {
		Control control = super.createControl(parent);
		if (returnCode == Window.CANCEL) {
			setValueValid(false);
		}else {
			setValueValid(true);
		}
		return control;
	}
	
	/**
	 * 获得外健对应的表格路径
	 * @return
	 */
	private String getSelectedForeginKeyTableFullPath() {
		TableResourceData trd = getCurrTableResourceData();
		if (trd != null) {
			if (tablekey != null) {
				EList<ForeignKey> foreignKeies = tablekey.getForeignKey();
				if (foreignKeies.size() > 0)
					return foreignKeies.get(0).getTableName();
			}
		}
		return null;

	}

	/**
	 * 获得已经设置好的外键字段
	 * 
	 * @return
	 */
	private List<String> getSelectedForeginKeyFeilds() {
		TableResourceData trd = getCurrTableResourceData();
		List<String> fields = new ArrayList<String>();
		if (trd != null) {
			if (tablekey != null) {
				EList<ForeignKey> foreignKeies = tablekey.getForeignKey();
				for (ForeignKey fk : foreignKeies) {
					fields.add(fk.getFieldName());
				}
			}
		}
		return fields;

	}

	/**
	 * 获得当前要设置外键的表格资源
	 * 
	 * @return
	 */
	private TableResourceData getCurrTableResourceData() {
		try {
			return resource.getInfo(TableResourceData.class);
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获得外键对应表格对应的模型
	 * 
	 * @return
	 */
	private TableResourceData getForgienKeyTableResourceData() {
		try {
			IARESBundle[] bundles = ARESElementUtil.getRefARESProjects(getARESProject());
			for (IARESBundle bundle : bundles) {
				IARESResource resource;

				resource = bundle.findResource(
						getSelectedForeginKeyTableFullPath(),
						IDatabaseResType.Table);
				if (resource != null) {

					return resource.getInfo(TableResourceData.class);
				}

			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return null;
	}


	private IARESProject getARESProject() {
		IARESResource res = this.resource;
		if (res != null) {
			return res.getARESProject();
		}

		return null;
	}
}
