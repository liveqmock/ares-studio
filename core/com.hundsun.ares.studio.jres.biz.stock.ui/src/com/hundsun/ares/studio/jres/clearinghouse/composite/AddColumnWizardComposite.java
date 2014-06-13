package com.hundsun.ares.studio.jres.clearinghouse.composite;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.CoreFactory;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.jres.clearinghouse.ui.action.IWizardComposite;
import com.hundsun.ares.studio.jres.clearinghouse.ui.page.RevisionHistoryColumnsViewerBlock;
import com.hundsun.ares.studio.jres.database.ui.actions.AddNonStdFiledColumnAction;
import com.hundsun.ares.studio.jres.model.chouse.AddTableColumnModification;
import com.hundsun.ares.studio.jres.model.chouse.ChouseFactory;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.chouse.HisTableColumn;
import com.hundsun.ares.studio.jres.model.chouse.IStock3Constant;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty;
import com.hundsun.ares.studio.jres.model.chouse.util.StockUtils;
import com.hundsun.ares.studio.jres.model.database.DatabaseFactory;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerAddAction;
import com.hundsun.ares.studio.ui.editor.editable.ActionEditableUnit;

public class AddColumnWizardComposite extends Composite implements IWizardComposite{

	private IARESResource resource;
	private EditingDomain domain;
	private AddTableColumnModification action = ChouseFactory.eINSTANCE.createAddTableColumnModification();
	private TableResourceData table;
	
	public AddColumnWizardComposite(Composite parent, EMFFormEditor editor, Modification modify) {
		super(parent, SWT.NONE);
		this.resource = editor.getARESResource();
		table = (TableResourceData) editor.getInfo();
		this.domain = editor.getEditingDomain();
		GridLayoutFactory.fillDefaults().applyTo(this);
		
		RevisionHistoryColumnsViewerBlock block = new RevisionHistoryColumnsViewerBlock(domain, resource) {
			protected EReference getEReference() {
				return ChousePackage.Literals.ADD_TABLE_COLUMN_MODIFICATION__COLUMNS;
			}
			
			protected void createAddAction() {
				addAction = new ColumnViewerAddAction(
						getColumnViewer(), 
						getEditingDomain(),
						null,
						getEReference(),
						ChousePackage.Literals.HIS_TABLE_COLUMN);
				addAction.setText("增加标准字段");
				getActionRegistry().registerAction(addAction);
				getSelectionActions().add(addAction.getId());
			}
			
			/**
			 * 新建非标准字段的Action
			 */
			protected void createAddNonStdAction() {
				addNonStdAction = new AddNonStdFiledColumnAction(
										getColumnViewer(), 
										getEditingDomain(), 
										null,
										getEReference(),
										ChousePackage.Literals.HIS_TABLE_COLUMN);
				getActionRegistry().registerAction(addNonStdAction);
				getSelectionActions().add(addNonStdAction.getId());
				//只读控制
				// 2012-05-15 sundl 编辑器外使用的时候，可能是null
				if (getEditableControl() != null) {
					getEditableControl().addEditableUnit(new ActionEditableUnit(addNonStdAction));
				}
			}
		};
		block.createControl(this, editor.getToolkit());
		GridDataFactory.fillDefaults().grab(true, true).applyTo(block.getControl());
		domain.getCommandStack().addCommandStackListener(block);
		block.setInput(action);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.clearinghouse.ui.action.IWizardComposite#getCommand()
	 */
	@Override
	public Command getCommand() {
		return new RecordingCommand((TransactionalEditingDomain) domain) {
			
			@Override
			protected void doExecute() {
				List<TableColumn> addedTC = new ArrayList<TableColumn>();
				List<HisTableColumn> addedHisTC = new ArrayList<HisTableColumn>();
				for (HisTableColumn hisCol : action.getColumns()) {
					//hisCol.getData2()只能存在一个容器中
					addedHisTC.add(EcoreUtil.copy(hisCol));
					TableColumn col = DatabaseFactory.eINSTANCE.createTableColumn();
					col.setColumnType(hisCol.getColumnType());
					col.setDefaultValue(hisCol.getDefaultValue());
					col.setMark(hisCol.getMark());
//					col.setName(hisCol.getName());
					col.setFieldName(hisCol.getFieldName());
					col.setColumnName(hisCol.getColumnName());
					col.setChineseName(hisCol.getChineseName());
					col.setDescription(hisCol.getDescription());
					col.setDataType(hisCol.getDataType());
					col.setNullable(hisCol.isNullable());
					col.setPrimaryKey(hisCol.isPrimaryKey());
					col.setComments(hisCol.getComments());
					
					col.getData().putAll(hisCol.getData());
					col.getData2().putAll(hisCol.getData2());
					
					addedTC.add(col);
				}
				table.getColumns().addAll(addedTC);
				
				//处理修改信息
				AddTableColumnModification action = ChouseFactory.eINSTANCE.createAddTableColumnModification();
				action.getColumns().addAll(addedHisTC);
				
				RevisionHistory rh = CoreFactory.eINSTANCE.createRevisionHistory();
				RevisionHistoryProperty p = ChouseFactory.eINSTANCE.createRevisionHistoryProperty();
				p.setAction(action);
				rh.setModified(StockUtils.getModificationDescription(table, action));
				rh.getData2().put(IStock3Constant.HISTORY_DATA2_KEY, p);
				// 2012-05-18 sundl 修改记录自动添加到第一条记录
				table.getHistories().add(0, rh);
			};
		};
	}

}
