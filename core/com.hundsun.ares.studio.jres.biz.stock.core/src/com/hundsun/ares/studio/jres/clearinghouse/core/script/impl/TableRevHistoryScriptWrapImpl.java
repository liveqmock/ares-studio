/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.RevisionHistory;
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
import com.hundsun.ares.studio.jres.model.chouse.RemoveIndexModification;
import com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnModification;
import com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnPKModification;
import com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnUniqueModifycation;
import com.hundsun.ares.studio.jres.model.chouse.RenameTableColumnModification;
import com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty;
import com.hundsun.ares.studio.jres.model.chouse.impl.AddConstraintModificationImpl;
import com.hundsun.ares.studio.jres.model.chouse.impl.AddIndexFieldModificationImpl;
import com.hundsun.ares.studio.jres.model.chouse.impl.AddIndexModificationImpl;
import com.hundsun.ares.studio.jres.model.chouse.impl.ChangeTableColumnTypeModificationImpl;
import com.hundsun.ares.studio.jres.model.chouse.impl.RemoveIndexFieldModificationImpl;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableKey;
import com.hundsun.ares.studio.jres.script.api.database.ITableColScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.ITableIndexScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.ITableKeyScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.ITableScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IModificationScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.ITableRevHistoryScriptWrap;

/**
 * @author yanwj06282
 *
 */
public class TableRevHistoryScriptWrapImpl extends RevHistoryScriptWrapImpl implements ITableRevHistoryScriptWrap {

	public TableRevHistoryScriptWrapImpl(RevisionHistory history ,IARESResource resource) {
		super(history ,resource);
	}

	public String getActionType (){
		EObject stock3 = getOriginalInfo().getData2().get("Stock3");
		if (stock3 instanceof RevisionHistoryProperty) {
			Modification modify = ((RevisionHistoryProperty) stock3).getAction();
			if (modify != null) {
				return modify.eClass().getName();
			}
		}
		return StringUtils.EMPTY;
	}
	
	public ITableScriptWrap getTableInfo (){
		ITableScriptWrap table = new TableScriptWrapImpl(resource);
		EObject stock3 = getOriginalInfo().getData2().get("Stock3");
		if (stock3 instanceof RevisionHistoryProperty) {
			Modification modify = ((RevisionHistoryProperty) stock3).getAction();
			if (modify instanceof AddTableModification) {
				List<ITableIndexScriptWrap> tableIndexs = new ArrayList<ITableIndexScriptWrap>();
				List<ITableColScriptWrap> tableCols = new ArrayList<ITableColScriptWrap>();
				List<ITableKeyScriptWrap> tableKeys = new ArrayList<ITableKeyScriptWrap>();
				for (TableIndex index : ((AddTableModification)modify).getIndexes()){
					tableIndexs.add(new TableIndexScriptWrapImpl(table,index, resource));
				}
				table.setTableIndexs(tableIndexs);
				for (TableColumn column : ((AddTableModification)modify).getColumns()){
					tableCols.add(new TableColScriptWrapImpl(column, resource));
				}
				table.setTableColumns(tableCols);
				for (TableKey tk : ((AddTableModification)modify).getKeys()){
					tableKeys.add(new TableKeyScriptWrapImpl(tk, resource));
				}
				table.setTableKeys(tableKeys);
			}
		}
		return table;
	}
	
	public IModificationScriptWrap getAction (){
		EObject stock3 = getOriginalInfo().getData2().get("Stock3");
		if (stock3 instanceof RevisionHistoryProperty) {
			Modification modify = ((RevisionHistoryProperty) stock3).getAction();
			if (modify instanceof AddIndexModification) {
				return new AddIndexModificationScriptWrapImpl((AddIndexModificationImpl)modify, resource);
			}else if (modify instanceof AddTableColumnModification) {
				return new AddColModificationScriptWrapImpl((AddTableColumnModification)modify, resource);
			}else if (modify instanceof AddTableModification) {
				return new AddTableModificationScriptWrapImpl((AddTableModification)modify, resource);
			}else if (modify instanceof ChangeTableColumnTypeModificationImpl) {
				return new TableColTypeModificationScriptWrapImpl((ChangeTableColumnTypeModification)modify, resource);
			}else if (modify instanceof ChangeTableColumnNullableModifycation) {
				return new TableColNullableModificationScriptWrapImpl((ChangeTableColumnNullableModifycation)modify, resource);
			}else if (modify instanceof ChangeTableColumnPrimaryKeyModifycation) {
				return new TableColPKModificationScriptWrapImpl((ChangeTableColumnPrimaryKeyModifycation)modify, resource);
			}else if (modify instanceof RemoveTableColumnPKModification) {
				return new RemovePKModificationScriptWrapImpl((RemoveTableColumnPKModification)modify, resource);
			}else if (modify instanceof AddTableColumnPKModification) {
				return new AddPKModificationScriptWrapImpl((AddTableColumnPKModification)modify, resource);
			}else if (modify instanceof AddTableColumnUniqueModifycation) {
				return new AddUniqueModificationScriptWrapImpl((AddTableColumnUniqueModifycation)modify, resource);
			}else if (modify instanceof ChangeTableColumnUniqueModifycation) {
				return new TableColUniqueModificationScriptWrapImpl((ChangeTableColumnUniqueModifycation)modify, resource);
			}else if (modify instanceof RemoveTableColumnUniqueModifycation) {
				return new RemoveUniqueModificationScriptWrapImpl((RemoveTableColumnUniqueModifycation)modify, resource);
			}else if (modify instanceof RemoveIndexModification) {
				return new RemoveIndexModificationScriptWrapImpl((RemoveIndexModification)modify, resource);
			}else if (modify instanceof RemoveTableColumnModification) {
				return new RemoveColModificationScriptWrapImpl((RemoveTableColumnModification)modify, resource);
			}else if (modify instanceof RenameTableColumnModification) {
				return new RenameTableColModificationScriptWrapImpl((RenameTableColumnModification)modify, resource);
			}else if (modify instanceof AddConstraintModificationImpl) {
				return new AddConstraintModificationScriptWrapImpl((AddConstraintModificationImpl) modify, resource);
			} else if (modify instanceof RemoveConstraintModification) {
				return new RemoveConstraintModificationScriptWrap((RemoveConstraintModification)modify, resource);
			}else if (modify instanceof AddIndexFieldModificationImpl) {
				return new AddIndexFieldModificationScriptWrapImpl((AddIndexFieldModificationImpl) modify, resource);
			} else if (modify instanceof RemoveIndexFieldModificationImpl) {
				return new RemoveIndexIndexModificationScriptWrapImpl((RemoveIndexFieldModificationImpl)modify, resource);
			}
		}
		return null;
	}
	
}
