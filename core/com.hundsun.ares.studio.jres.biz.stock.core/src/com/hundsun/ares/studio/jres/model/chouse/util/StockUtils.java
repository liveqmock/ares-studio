/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.jres.model.chouse.util;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.hundsun.ares.studio.jres.model.chouse.AddConstraintModification;
import com.hundsun.ares.studio.jres.model.chouse.AddIndexField;
import com.hundsun.ares.studio.jres.model.chouse.AddIndexFieldModification;
import com.hundsun.ares.studio.jres.model.chouse.AddIndexModification;
import com.hundsun.ares.studio.jres.model.chouse.AddTableColumnModification;
import com.hundsun.ares.studio.jres.model.chouse.AddTableColumnPKModification;
import com.hundsun.ares.studio.jres.model.chouse.AddTableColumnUniqueModifycation;
import com.hundsun.ares.studio.jres.model.chouse.AddTableModification;
import com.hundsun.ares.studio.jres.model.chouse.CTCPMDetail;
import com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnNullableModifycation;
import com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnPrimaryKeyModifycation;
import com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnTypeModification;
import com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnUniqueModifycation;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.chouse.RemoveConstraintModification;
import com.hundsun.ares.studio.jres.model.chouse.RemoveIndexField;
import com.hundsun.ares.studio.jres.model.chouse.RemoveIndexFieldModification;
import com.hundsun.ares.studio.jres.model.chouse.RemoveIndexModification;
import com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnModification;
import com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnPKModification;
import com.hundsun.ares.studio.jres.model.chouse.RemoveTableColumnUniqueModifycation;
import com.hundsun.ares.studio.jres.model.chouse.RenameTableColumnModification;
import com.hundsun.ares.studio.jres.model.database.DatabaseFactory;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.ForeignKey;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;
import com.hundsun.ares.studio.jres.model.database.TableKey;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;

/**
 * 三部扩展相关的工具类方法
 * @author gongyf
 *
 */
public class StockUtils {
	
	private static Logger logger = Logger.getLogger(StockUtils.class);
	
	/**
	 * 说明描述中的分隔符
	 */
	private static final String SEPARATOR = ",";
	
	/**
	 * 获取表修改记录的详细说明信息
	 * @param tableData 2014年3月25日 sundl添加； 有些情况下，modify还没添加，所以不能通过modify直接获取到tabledata，需要传入。 允许为null，如果为null的话，就会试着从modify通过emf获取tabledata
	 * @param modify
	 * @return
	 */
	public static String getModificationDescription(TableResourceData tableData, Modification modify) {
		if (modify != null) {
			StringBuffer sb = new StringBuffer();
			if (modify instanceof AddIndexModification) {
				// 添加表索引
				AddIndexModification m = (AddIndexModification) modify;			

				sb.append("增加索引（ ");
				for (TableIndex index : m.getIndexs() ) {
					sb.append(String.format("%s:[",index.getName()));
					join(sb, index.getColumns(), DatabasePackage.Literals.TABLE_INDEX__NAME, SEPARATOR);
					sb.append("],");
				}
				sb = new StringBuffer( sb.subSequence(0, sb.length() - 1 ) );
				sb.append("）");
				// 添加索引字段
				
			} else if (modify instanceof AddTableColumnModification) {
				// 添加表字段
				AddTableColumnModification m = (AddTableColumnModification) modify;
				
				sb.append("添加了表字段（");
				join(sb, m.getColumns(), DatabasePackage.Literals.TABLE_COLUMN__NAME, SEPARATOR);
				sb.append("）");
			} else if (modify instanceof AddTableModification) {
				AddTableModification m = (AddTableModification) modify;
				
				if (m.isNewSelfTable()) {
					sb.append("添加表");
				}
				if (m.isNewHistoryTable()) {
					sb.append(" 添加历史表");
				}
			} else if (modify instanceof ChangeTableColumnTypeModification) {
				ChangeTableColumnTypeModification m = (ChangeTableColumnTypeModification) modify;
				
				sb.append("修改了表字段类型（");
				join(sb, m.getDetails(), ChousePackage.Literals.CTCTM_DETAIL__NAME, SEPARATOR);
				sb.append("）");
				
			} else if (modify instanceof RemoveIndexModification) {
				RemoveIndexModification m = (RemoveIndexModification) modify;
				
				sb.append("删除了表索引（");
				join(sb, m.getIndexs(), ChousePackage.Literals.REMOVED_INDEX__NAME, SEPARATOR);
				sb.append("）");
			
			}else if (modify instanceof RemoveIndexFieldModification) {
				RemoveIndexFieldModification m = (RemoveIndexFieldModification) modify;
				
				sb.append("删除索引字段（");
				for(RemoveIndexField removeIndexField:m.getIndexs()){
					sb.append("索引"+ removeIndexField.getName()+":"+"删除了索引字段：");
					for(int i=0;i< removeIndexField.getIndexFields().size();i++){
						TableIndexColumn tableIndexColumn =  removeIndexField.getIndexFields().get(i);
						if(i!=  removeIndexField.getIndexFields().size()-1){
							sb.append(tableIndexColumn.getColumnName()).append(",");
						}else{
							sb.append(tableIndexColumn.getColumnName());
						}
						
					}
				}
				sb.append("）");
			
			} else if (modify instanceof AddIndexFieldModification) {
				AddIndexFieldModification m = (AddIndexFieldModification) modify;
				
				sb.append("增加索引字段（");
				for(AddIndexField addIndexField:m.getIndexs()){
					sb.append("索引"+ addIndexField.getName()+":"+"增加了索引字段：");
					for(int i = 0;i< addIndexField.getIndexFields().size();i++){
						TableIndexColumn tableIndexColumn = addIndexField.getIndexFields().get(i);
						if(i!=addIndexField.getIndexFields().size()-1){
							sb.append(tableIndexColumn.getColumnName()).append(",");
						}else{
							sb.append(tableIndexColumn.getColumnName());
						}
						
					}
					
				}
				sb.append("）");
			
			} else if (modify instanceof RemoveTableColumnModification) {
				RemoveTableColumnModification m = (RemoveTableColumnModification) modify;
				
				sb.append("删除了表字段（");
				join(sb, m.getColumns(), DatabasePackage.Literals.TABLE_COLUMN__NAME, SEPARATOR);
				sb.append("）");
				
			} else if (modify instanceof RenameTableColumnModification) {
				RenameTableColumnModification m = (RenameTableColumnModification) modify;
				
				sb.append("重命名了表字段（");
				for (int i = 0; i < m.getDetails().size(); i++) {
					if (i > 0) {
						sb.append(SEPARATOR);
					}
					sb.append(m.getDetails().get(i).getOldName());
					sb.append("->");
					sb.append(m.getDetails().get(i).getNewName());
				}
				sb.append("）");
			}else if (modify instanceof ChangeTableColumnNullableModifycation) {
				ChangeTableColumnNullableModifycation m = (ChangeTableColumnNullableModifycation) modify;
				
				sb.append("修改了表字段允许空（");
				join(sb, m.getDetails(), ChousePackage.Literals.MODIFY_DETAIL__NAME, SEPARATOR);
				sb.append("）");
				
			}else if (modify instanceof ChangeTableColumnPrimaryKeyModifycation) {
				ChangeTableColumnPrimaryKeyModifycation m = (ChangeTableColumnPrimaryKeyModifycation) modify;
				
				sb.append("修改了表字段主键（");
				EList<CTCPMDetail> results = new BasicEList<CTCPMDetail>();
				for (CTCPMDetail detail : m.getDetails()) {
					if (detail.isPrimarkKey()) {
						results.add(detail);
					}
				}
				join(sb, results, ChousePackage.Literals.MODIFY_DETAIL__NAME, SEPARATOR);
				sb.append("）");
				
			}else if (modify instanceof RemoveTableColumnPKModification) {
				sb.append("删除了表主键");
			}else if (modify instanceof AddTableColumnPKModification) {
				AddTableColumnPKModification m = (AddTableColumnPKModification) modify;
				
				sb.append("增加了表字段主键（");
				join(sb, m.getDetails(), ChousePackage.Literals.MODIFY_DETAIL__NAME, SEPARATOR);
				sb.append("）");
				
			}else if (modify instanceof ChangeTableColumnUniqueModifycation) {
				ChangeTableColumnUniqueModifycation m = (ChangeTableColumnUniqueModifycation) modify;
				
				sb.append("修改了表字段唯一约束（");
				join(sb, m.getDetails(), ChousePackage.Literals.MODIFY_DETAIL__NAME, SEPARATOR);
				sb.append("）");
				
			}else if (modify instanceof AddTableColumnUniqueModifycation) {
				AddTableColumnUniqueModifycation m = (AddTableColumnUniqueModifycation) modify;
				
				sb.append("增加了表字段唯一约束（");
				join(sb, m.getDetails(), ChousePackage.Literals.MODIFY_DETAIL__NAME, SEPARATOR);
				sb.append("）");
				
			}else if (modify instanceof RemoveTableColumnUniqueModifycation) {
				sb.append("删除了表唯一约束");
			} else if (modify instanceof AddConstraintModification) {
				sb.append("新增了约束");
				try {
					AddConstraintModification add = (AddConstraintModification) modify;
					List<ConstraintModifyDetail> details = add.getDetails();
					if (details.size() > 0) {
						for (int i = 0; i < details.size(); i++) {
							sb.append(details.get(i).getName());
							if (i < details.size() -1)
								sb.append(",");
						}
					}
				} catch (Exception e) {
					logger.error(e);
				}
			} else if (modify instanceof RemoveConstraintModification) {
				sb.append("删除了约束");
				try {
					RemoveConstraintModification add = (RemoveConstraintModification) modify;
					List<ConstraintModifyDetail> details = add.getDetails();
					if (details.size() > 0) {
						for (int i = 0; i < details.size(); i++) {
							sb.append(details.get(i).getName());
							if (i < details.size() -1)
								sb.append(",");
						}
					}
				} catch (Exception e) {
					logger.error(e);
				}
			}
			TableResourceData table = tableData == null ? getTable(modify) : tableData;
			if (table != null) {
				StringBuffer bf = new StringBuffer("数据表");
				bf.append(table.getName());
				bf.append("，");
				sb = bf.append(sb);
			}
			return sb.toString();
		}
		
		return StringUtils.EMPTY;
	}
	
	private static TableResourceData getTable (EObject modify){
		if (modify != null) {
			if (modify.eContainer() instanceof TableResourceData) {
				return (TableResourceData) modify.eContainer();
			}else {
				return getTable(modify.eContainer());
			}
		}
		return null;
	}
	
	/**
	 * 将字符串用一种分隔符组合起来
	 * @param sb
	 * @param list
	 * @param attribute
	 * @param separator
	 */
	private static void join(StringBuffer sb, List<? extends EObject> list, EAttribute attribute, String separator) {
		for (int i = 0; i < list.size(); i++) {
			if (i > 0) {
				sb.append(separator);
			}
			sb.append(list.get(i).eGet(attribute));
		}
	}
	
	public static TableKey toTableKey(ConstraintModifyDetail constraint) {
		TableKey key = DatabaseFactory.eINSTANCE.createTableKey();
		key.setMark(constraint.getMark());
		key.setName(constraint.getName());
		key.setType(constraint.getType());
		for (TableColumn c : constraint.getColumns()) {
			key.getColumns().add(EcoreUtil.copy(c));
		}
		for (ForeignKey fk : constraint.getForeignKey()) {
			key.getForeignKey().add(EcoreUtil.copy(fk));
		}
		return key;
	}
}
