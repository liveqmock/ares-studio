/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.clearinghouse.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.core.model.CoreFactory;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.jres.clearinghouse.ui.action.IWizardComposite;
import com.hundsun.ares.studio.jres.model.chouse.AddIndexField;
import com.hundsun.ares.studio.jres.model.chouse.ChouseFactory;
import com.hundsun.ares.studio.jres.model.chouse.IStock3Constant;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty;
import com.hundsun.ares.studio.jres.model.chouse.util.StockUtils;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;

/**
 * @author liaogc
 *
 */
public class AddIndexFieldWizardComposite extends AddIndexFieldComposite implements IWizardComposite{

	EditingDomain domain ;

	public AddIndexFieldWizardComposite(Composite parent, EMFFormEditor editorPart,
			Modification action) {
		super(parent, (TableResourceData)editorPart.getInfo(), editorPart.getARESResource(), action);
		
		domain = editorPart.getEditingDomain();
		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.clearinghouse.ui.action.IWizardComposite#getCommand()
	 */
	@Override
	public Command getCommand() {

		return new RecordingCommand((TransactionalEditingDomain) domain) {
			List<TableIndex> affectedObject = new ArrayList<TableIndex>();
			
			@Override
			protected void doExecute() {
				
				for (TableIndex idx : tableData.getIndexes()) {
					for (AddIndexField rmidx : input) {
						if (idx.getName().equals(rmidx.getName())) {
							String mark = rmidx.getMark();
							if(StringUtils.isNotBlank(mark)){
								if(StringUtils.equals(mark, idx.getMark())){
									EList<TableIndexColumn> indexFields = rmidx.getIndexFields();
									for(TableIndexColumn tableIndexColumn:indexFields){
										idx.getColumns().add(EcoreUtil.copy(tableIndexColumn));
									}
									affectedObject.add(idx);
									break;
								}
							}else{
								EList<TableIndexColumn> indexFields = rmidx.getIndexFields();
								for(TableIndexColumn tableIndexColumn:indexFields){
									idx.getColumns().add(EcoreUtil.copy(tableIndexColumn));
								}
								affectedObject.add(idx);
								break;
							}
							
						}
					}
				}
				
				//处理修改信息
				RevisionHistory rh = CoreFactory.eINSTANCE.createRevisionHistory();
				RevisionHistoryProperty p = ChouseFactory.eINSTANCE.createRevisionHistoryProperty();
				p.setAction(getAction());
				rh.setModified(StockUtils.getModificationDescription(tableData, getAction()));
				rh.getData2().put(IStock3Constant.HISTORY_DATA2_KEY, p);
				tableData.getHistories().add(0, rh);
			};
			
			/* (non-Javadoc)
			 * @see org.eclipse.emf.common.command.AbstractCommand#getAffectedObjects()
			 */
			@Override
			public Collection<?> getAffectedObjects() {
				return affectedObject;
			}
		};
	
	}
	
	

}
