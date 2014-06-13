/**
 * 源程序名称：ModifyColumnNullableCommand.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.jres.biz.stock.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.clearinghouse.command;

import java.util.List;

import com.hundsun.ares.studio.core.model.CoreFactory;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.jres.model.chouse.CTCNMDetail;
import com.hundsun.ares.studio.jres.model.chouse.ChangeTableColumnNullableModifycation;
import com.hundsun.ares.studio.jres.model.chouse.ChouseFactory;
import com.hundsun.ares.studio.jres.model.chouse.IStock3Constant;
import com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty;
import com.hundsun.ares.studio.jres.model.chouse.util.StockUtils;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;

/**
 * @author sundl
 *
 */
public class ModifyColumnNullableCommand extends ColumnChangeCommand<CTCNMDetail>{

	public ModifyColumnNullableCommand(TableResourceData tableData, List<CTCNMDetail> changes) {
		super(tableData, changes);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
	 */
	@Override
	protected void doExecute() {
		for (CTCNMDetail change : changes) {
			TableColumn c = findColumn(change, tableData);
			c.setNullable(change.isNullable());
			changedColumns.add(c);
		}
		
		RevisionHistory rh = CoreFactory.eINSTANCE.createRevisionHistory();
		RevisionHistoryProperty p = ChouseFactory.eINSTANCE.createRevisionHistoryProperty();
		ChangeTableColumnNullableModifycation action = ChouseFactory.eINSTANCE.createChangeTableColumnNullableModifycation();
		action.getDetails().addAll(changes);
		p.setAction(action);
		rh.setModified(StockUtils.getModificationDescription(tableData, action));
		rh.getData2().put(IStock3Constant.HISTORY_DATA2_KEY, p);
		tableData.getHistories().add(0, rh);
	}

}
