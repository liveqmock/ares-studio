package com.hundsun.ares.studio.jres.clearinghouse.composite;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.command.Command;
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
import com.hundsun.ares.studio.jres.model.database.TableKey;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;

public class RemoveConstraintWizardComposite extends RemoveConstraintComposite implements IWizardComposite{
	
	TableResourceData info ;
	EditingDomain domain ;
	
	public RemoveConstraintWizardComposite(Composite parent,
			EMFFormEditor editorPart,
			Modification action) {
		super(parent, (TableResourceData)editorPart.getInfo(), editorPart.getARESResource(), action);
		info = (TableResourceData)editorPart.getInfo();
		domain = editorPart.getEditingDomain();
	}

	@Override
	public Command getCommand() {
	return new RecordingCommand((TransactionalEditingDomain) domain) {
			@Override
			protected void doExecute() {
				for (ConstraintModifyDetail detail : input) {
					TableKey key = findKey(detail);
					if (key != null) {
						info.getKeys().remove(key);
					}
				}

				//处理修改信息
				RevisionHistory rh = CoreFactory.eINSTANCE.createRevisionHistory();
				RevisionHistoryProperty p = ChouseFactory.eINSTANCE.createRevisionHistoryProperty();
				p.setAction(getAction());
				rh.setModified(StockUtils.getModificationDescription(tableData, getAction()));
				rh.getData2().put(IStock3Constant.HISTORY_DATA2_KEY, p);
				// 2012-05-18 sundl 修改记录自动添加到第一条记录
				info.getHistories().add(0, rh);
			};
		};
	}
	
	private TableKey findKey(ConstraintModifyDetail detail) {
		for (TableKey key : info.getKeys()) {
			if (key.getType() == detail.getType() && StringUtils.equals(key.getName(), detail.getName()))
				return key;
		}
		return null;
	}

}
