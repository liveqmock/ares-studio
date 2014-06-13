/**
 * 源程序名称：DatabaseEMFFormEditor.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.database.ui.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.page.ExtendPageWithMyDirtySystem;

/**
 * 数据库资源编辑器的基类
 * @author gongyf
 *
 */
public abstract class DatabaseEMFFormEditor extends EMFFormEditor {
	
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormEditor#getInfoClass()
	 */
	@Override
	protected EClass getInfoClass() {
		return DatabasePackage.Literals.DATABASE_RESOURCE_DATA;
	}

	
}
