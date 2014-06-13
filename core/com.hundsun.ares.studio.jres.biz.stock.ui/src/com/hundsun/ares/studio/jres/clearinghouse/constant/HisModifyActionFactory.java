/**
 * 源程序名称：DBHisDetailDialogFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.clearinghouse.constant;

import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.clearinghouse.composite.AddColumnComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.AddColumnPKComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.AddColumnUniqueComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.AddConstraintComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.AddIndexComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.AddIndexFieldComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.ModifyColumnNullableComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.ModifyColumnPrimaryKeyComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.ModifyColumnTypeComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.ModifyColumnUniqueComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.NewTableComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.RemoveColumnComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.RemoveColumnPKComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.RemoveColumnUniqueComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.RemoveConstraintComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.RemoveIndexComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.RemoveIndexFieldComposite;
import com.hundsun.ares.studio.jres.clearinghouse.composite.RenameColumnComposite;
import com.hundsun.ares.studio.jres.model.chouse.AddConstraintModification;
import com.hundsun.ares.studio.jres.model.chouse.AddIndexFieldModification;
import com.hundsun.ares.studio.jres.model.chouse.AddIndexModification;
import com.hundsun.ares.studio.jres.model.chouse.AddTableColumnModification;
import com.hundsun.ares.studio.jres.model.chouse.AddTableColumnPKModification;
import com.hundsun.ares.studio.jres.model.chouse.AddTableColumnUniqueModifycation;
import com.hundsun.ares.studio.jres.model.chouse.AddTableModification;
import com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnNullableModifycation;
import com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnPrimaryKeyModifycation;
import com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnTypeModification;
import com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnUniqueModifycation;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.chouse.RemoveConstraintModification;
import com.hundsun.ares.studio.jres.model.chouse.RemoveIndexFieldModification;
import com.hundsun.ares.studio.jres.model.chouse.RemoveIndexModification;
import com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnModification;
import com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnPKModification;
import com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnUniqueModifycation;
import com.hundsun.ares.studio.jres.model.chouse.RenameTableColumnModification;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;

/**
 * @author wangxh
 *
 */
public class HisModifyActionFactory {
	
	//修改类型枚举
	public static enum MODIFYACTION_TYPE{
		新建表,
		增加表字段,
		删除表字段,
		重命名表字段,
		增加索引,
		删除索引,
		增加索引字段,
		删除索引字段,
		修改表字段类型,
		增加主键,
		修改主键,
		删除主键,
		修改表字段为空,
		增加约束,
		删除约束,
		增加唯一约束,
		修改唯一约束,
		删除唯一约束
	}
	
	/**
	 * 根据所选修改类型生成相应的界面
	 * @param select 修改类型
	 * @param Comp 
	 * @param object 
	 * @param resource 
	 * @return
	 */
	public static  Composite getDetailComposite(MODIFYACTION_TYPE select, Composite Comp, TableResourceData tableData, Modification action, IARESResource resource) {
		switch(select){
		case 新建表:
			return new NewTableComposite(Comp,tableData, resource, action);
		case 增加表字段:
			return new AddColumnComposite(Comp, tableData, resource, action);
		case 删除表字段:
			return new RemoveColumnComposite(Comp,tableData, resource,action);
		case 重命名表字段:
			return new RenameColumnComposite(Comp,tableData, resource, action);
		case 增加索引:
			return new AddIndexComposite(Comp, tableData,resource, action);
		case 删除索引:
			return new RemoveIndexComposite(Comp,tableData, resource,action);
		case 增加索引字段:
			return new AddIndexFieldComposite(Comp,tableData, resource,action);	
		case 删除索引字段:
			return new RemoveIndexFieldComposite(Comp,tableData, resource,action);
		case 修改表字段类型:
			return new ModifyColumnTypeComposite(Comp,tableData,resource, action);
		case 增加主键:
			return new AddColumnPKComposite(Comp,tableData,resource, action);
		case 修改主键:
			return new ModifyColumnPrimaryKeyComposite(Comp,tableData,resource, action);
		case 删除主键:
			return new RemoveColumnPKComposite(Comp,tableData,resource, action);
		case 修改表字段为空:
			return new ModifyColumnNullableComposite(Comp,tableData,resource, action);
		case 增加唯一约束:
			return new AddColumnUniqueComposite(Comp,tableData,resource, action);
		case 修改唯一约束:
			return new ModifyColumnUniqueComposite(Comp,tableData,resource, action);
		case 删除唯一约束:
			return new RemoveColumnUniqueComposite(Comp,tableData,resource, action);
		case 增加约束:
			return new AddConstraintComposite(Comp, tableData, resource, action);
		case 删除约束:
			return new RemoveConstraintComposite(Comp, tableData, resource, action);
		default:
			return Comp;
		}
	}

	
	public static String getActionName(Modification action){
		if(action instanceof AddTableModification){
			return MODIFYACTION_TYPE.新建表.name();
		}
		if(action instanceof AddTableColumnModification){
			return MODIFYACTION_TYPE.增加表字段.name();
		}
		if(action instanceof RemoveTableColumnModification){
			return MODIFYACTION_TYPE.删除表字段.name();
		}
		if(action instanceof RenameTableColumnModification){
			return MODIFYACTION_TYPE.重命名表字段.name();
		}
		if(action instanceof ChangeTableColumnTypeModification){
			return MODIFYACTION_TYPE.修改表字段类型.name();
		}
		if(action instanceof AddIndexModification){
			return MODIFYACTION_TYPE.增加索引.name();
		}
		if(action instanceof RemoveIndexModification){
			return MODIFYACTION_TYPE.删除索引.name();
		}
		
		if(action instanceof RemoveIndexFieldModification){
			return MODIFYACTION_TYPE.删除索引字段.name();
		}
		if(action instanceof AddIndexFieldModification){
			return MODIFYACTION_TYPE.增加索引字段.name();
		}
		if(action instanceof ChangeTableColumnPrimaryKeyModifycation){
			return MODIFYACTION_TYPE.修改主键.name();
		}
		if(action instanceof AddTableColumnPKModification){
			return MODIFYACTION_TYPE.增加主键.name();
		}
		if(action instanceof RemoveTableColumnPKModification){
			return MODIFYACTION_TYPE.删除主键.name();
		}
		if(action instanceof ChangeTableColumnNullableModifycation){
			return MODIFYACTION_TYPE.修改表字段为空.name();
		}
		if(action instanceof AddTableColumnUniqueModifycation){
			return MODIFYACTION_TYPE.增加唯一约束.name();
		}
		if(action instanceof ChangeTableColumnUniqueModifycation){
			return MODIFYACTION_TYPE.修改唯一约束.name();
		}
		if(action instanceof RemoveTableColumnUniqueModifycation){
			return MODIFYACTION_TYPE.删除唯一约束.name();
		}
		if (action instanceof AddConstraintModification) {
			return MODIFYACTION_TYPE.增加约束.name();
		}
		if (action instanceof RemoveConstraintModification) {
			return MODIFYACTION_TYPE.删除约束.name();
		}
		return "";
	}
	
}
