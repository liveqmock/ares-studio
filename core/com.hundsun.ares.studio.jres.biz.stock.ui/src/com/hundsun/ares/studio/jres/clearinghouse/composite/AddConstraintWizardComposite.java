package com.hundsun.ares.studio.jres.clearinghouse.composite;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.core.model.CoreFactory;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.jres.clearinghouse.ui.action.IWizardComposite;
import com.hundsun.ares.studio.jres.model.chouse.ChouseFactory;
import com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail;
import com.hundsun.ares.studio.jres.model.chouse.IStock3Constant;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty;
import com.hundsun.ares.studio.jres.model.chouse.util.StockUtils;
import com.hundsun.ares.studio.jres.model.database.DatabaseFactory;
import com.hundsun.ares.studio.jres.model.database.ForeignKey;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableKey;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.database.key_type;
import com.hundsun.ares.studio.jres.model.database.util.DatabaseUtil;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;

public class AddConstraintWizardComposite extends AddConstraintComposite implements IWizardComposite {
	
	private EditingDomain domain;
	//private AddTableColumnModification action = ChouseFactory.eINSTANCE.createAddTableColumnModification();
	private TableResourceData table;
	
	public AddConstraintWizardComposite(Composite comp, EMFFormEditor editorPart, Modification action) {
		super(comp, (TableResourceData)editorPart.getInfo(), editorPart.getARESResource(), action);
		this.resource = editorPart.getARESResource();
		table = (TableResourceData) editorPart.getInfo();
		this.domain = editorPart.getEditingDomain();
	}

	@Override
	public Command getCommand() {
		return new RecordingCommand((TransactionalEditingDomain) domain) {
			@Override
			protected void doExecute() {
				for (ConstraintModifyDetail detail : input) {
					TableKey key = DatabaseFactory.eINSTANCE.createTableKey();
					key.setMark(detail.getMark());
					key.setName(detail.getName());
					for (TableColumn column : detail.getColumns()) {
						TableColumn col = DatabaseUtil.findColumn(column.getName(), table);
						if (col != null) {
							key.getColumns().add(col);
						}
					}
					key.setType(detail.getType());
					if (detail.getType() == key_type.FOREIGN) {
						for (ForeignKey fk : detail.getForeignKey()) {
							key.getForeignKey().add(EcoreUtil.copy(fk));
						}
					}
					table.getKeys().add(key);
				}

				//处理修改信息
				RevisionHistory rh = CoreFactory.eINSTANCE.createRevisionHistory();
				RevisionHistoryProperty p = ChouseFactory.eINSTANCE.createRevisionHistoryProperty();
				p.setAction(getAction());
				rh.setModified(StockUtils.getModificationDescription(table, getAction()));
				rh.getData2().put(IStock3Constant.HISTORY_DATA2_KEY, p);
				// 2012-05-18 sundl 修改记录自动添加到第一条记录
				table.getHistories().add(0, rh);
			};
		};
	}
	
}
