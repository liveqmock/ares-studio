/**
* <p>Copyright: Copyright (c) 2011</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.clearinghouse.ui.util;

import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.jres.clearinghouse.composite.AddColumnWizardComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.AddConstraintWizardComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.AddIndexFieldWizardComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.AddIndexWizardComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.ModifyColumnNullableWizardComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.ModifyColumnTypeWizardComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.RemoveColumnWizardComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.RemoveConstraintWizardComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.RemoveIndexFieldWizardComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.RemoveIndexWizardComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.RenameColumnWizardComposite;
import com.hundsun.ares.studio.jres.model.chouse.AddConstraintModification;
import com.hundsun.ares.studio.jres.model.chouse.AddIndexModification;
import com.hundsun.ares.studio.jres.model.chouse.AddTableColumnModification;
import com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnNullableModifycation;
import com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnTypeModification;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.chouse.RemoveIndexModification;
import com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnModification;
import com.hundsun.ares.studio.jres.model.chouse.RenameTableColumnModification;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;

/**
 * @author qinyuan
 *
 */
public class HisModifyWizardFactory {

	//修改类型枚举
	public static enum MODIFYACTION_TYPE{
		增加表字段,
		删除表字段,
		重命名表字段,
		增加索引,
		删除索引,
		删除索引字段,
		增加索引字段,
		修改字段类型,
		修改字段可否为空,
		增加约束,
		删除约束
	}
	
	/**
	 * 根据所选修改类型生成相应的界面
	 * @param select 修改类型
	 * @param comp 
	 * @param object 
	 * @param editorPart 
	 * @return
	 */
	public static  Composite getDetailComposite(MODIFYACTION_TYPE select, Composite comp, Modification action, EMFFormEditor editorPart) {
		switch(select){
		case 增加表字段:
			return new AddColumnWizardComposite(comp, editorPart, action);
		case 删除表字段:
			return new RemoveColumnWizardComposite(comp, editorPart,action);
		case 重命名表字段:
			return new RenameColumnWizardComposite(comp, editorPart, action);
		case 增加索引:
			return new AddIndexWizardComposite(comp, editorPart, action);
		case 删除索引:
			return new RemoveIndexWizardComposite(comp, editorPart,action);
		case 删除索引字段:
			return new RemoveIndexFieldWizardComposite(comp, editorPart,action);
		case 增加索引字段:
			return new AddIndexFieldWizardComposite(comp, editorPart,action);
		case 修改字段类型:
			return new ModifyColumnTypeWizardComposite(comp, editorPart, action);
		case 修改字段可否为空:
			return new ModifyColumnNullableWizardComposite(comp, editorPart, action);
		case 增加约束:
			return new AddConstraintWizardComposite(comp, editorPart, action);
		case 删除约束:
			return new RemoveConstraintWizardComposite(comp, editorPart, action);
		default:
			return comp;
		}
	}

	
	public static String getActionName(Modification action){
		if(action instanceof AddTableColumnModification){
			return MODIFYACTION_TYPE.增加表字段.name();
		}
		if(action instanceof RemoveTableColumnModification){
			return MODIFYACTION_TYPE.删除表字段.name();
		}
		if(action instanceof RenameTableColumnModification){
			return MODIFYACTION_TYPE.重命名表字段.name();
		}
		if(action instanceof AddIndexModification){
			return MODIFYACTION_TYPE.增加索引.name();
		}
		if(action instanceof RemoveIndexModification){
			return MODIFYACTION_TYPE.删除索引.name();
		}
		if (action instanceof ChangeTableColumnTypeModification) {
			return MODIFYACTION_TYPE.修改字段类型.name();
		}
		if (action instanceof ChangeTableColumnNullableModifycation) {
			return MODIFYACTION_TYPE.修改字段可否为空.name();
		}
		if (action instanceof AddConstraintModification) {
			return MODIFYACTION_TYPE.增加约束.name();
		}
		if (action instanceof RemoveIndexModification) {
			return MODIFYACTION_TYPE.删除约束.name();
		}
		return "";
	}
	
}
