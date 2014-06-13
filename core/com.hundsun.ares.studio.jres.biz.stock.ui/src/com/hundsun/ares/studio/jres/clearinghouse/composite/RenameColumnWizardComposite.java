/**
* <p>Copyright: Copyright (c) 2011</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.clearinghouse.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.core.model.CoreFactory;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.jres.clearinghouse.ui.action.IWizardComposite;
import com.hundsun.ares.studio.jres.model.chouse.ChouseFactory;
import com.hundsun.ares.studio.jres.model.chouse.IStock3Constant;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.chouse.RTCMDetail;
import com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty;
import com.hundsun.ares.studio.jres.model.chouse.util.StockUtils;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;

/**
 * @author qinyuan
 *
 */
public class RenameColumnWizardComposite extends RenameColumnComposite implements IWizardComposite{

	TableResourceData info ;
	EditingDomain domain ;
	
	/**
	 * @param parent
	 * @param editorPart
	 * @param action
	 */
	public RenameColumnWizardComposite(Composite parent,
			EMFFormEditor editorPart, Modification action) {
		super(parent, (TableResourceData)editorPart.getInfo(), editorPart.getARESResource(), action);
		
		info = (TableResourceData)editorPart.getInfo();
		domain = editorPart.getEditingDomain();
	}

	@Override
	public Command getCommand() {
		return new RecordingCommand((TransactionalEditingDomain) domain) {
			List<TableColumn> changedColumns = new ArrayList<TableColumn>();
			@Override
			protected void doExecute() { 
				
//				List<TableColumn> changedColumns = new ArrayList<TableColumn>();
				for (TableColumn col : info.getColumns()) {
//					boolean isChanged = false;
					for (RTCMDetail detail : input) {
						if(detail.getOldName().equals(col.getName())) {
							
//							TableColumn tc = DatabaseFactory.eINSTANCE.createTableColumn();
//							tc.setDefaultValue(col.getDefaultValue());
//							tc.setName(detail.getNewName());
//							tc.setNullable(col.isNullable());
//							tc.setPrimaryKey(col.isPrimaryKey());
//							
//							tc.getData().putAll(col.getData());
//							tc.getData2().putAll(col.getData2());
//							
//							changedColumns.add(tc);
							col.setName(detail.getNewName());
							changedColumns.add(col);
//							isChanged = true;
							break;
						}
					}
//					if(!isChanged) {
//						changedColumns.add(col);
//					}
				}
//				info.getColumns().clear();
//				info.getColumns().addAll(changedColumns);
				
				
				//处理修改信息
				RevisionHistory rh = CoreFactory.eINSTANCE.createRevisionHistory();
				RevisionHistoryProperty p = ChouseFactory.eINSTANCE.createRevisionHistoryProperty();
				p.setAction(getAction());
				rh.setModified(StockUtils.getModificationDescription(tableData, getAction()));
				rh.getData2().put(IStock3Constant.HISTORY_DATA2_KEY, p);
				// 2012-05-18 sundl 修改记录自动添加到第一条记录
				info.getHistories().add(0, rh);
			}
			
			@Override
			public Collection<?> getAffectedObjects() {
				return changedColumns;
			}
		};
	}

}
