/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.celleditor;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;

import com.hundsun.ares.studio.ui.celleditor.dialogs.AddIndexFieldModifyDialog;
import com.hundsun.ares.studio.ui.celleditor.dialogs.AddIndexModifyDialog;
import com.hundsun.ares.studio.ui.celleditor.dialogs.AddTableFieldModifyDialog;
import com.hundsun.ares.studio.ui.celleditor.dialogs.AdjustIndexOrderModifyDialog;
import com.hundsun.ares.studio.ui.celleditor.dialogs.AdjustIndexUniqueModifyDialog;
import com.hundsun.ares.studio.ui.celleditor.dialogs.ModifyTableFieldTypeModifyDialog;
import com.hundsun.ares.studio.ui.celleditor.dialogs.NewTableModifyDialog;
import com.hundsun.ares.studio.ui.celleditor.dialogs.RemoveIndexFieldModifyDialog;
import com.hundsun.ares.studio.ui.celleditor.dialogs.RemoveIndexModifyDialog;
import com.hundsun.ares.studio.ui.celleditor.dialogs.RemoveSequenceDialog;
import com.hundsun.ares.studio.ui.celleditor.dialogs.RemoveTableDialog;
import com.hundsun.ares.studio.ui.celleditor.dialogs.RemoveTableFieldModifyDialog;
import com.hundsun.ares.studio.ui.celleditor.dialogs.RenameTableFieldModifyDialog;

/**
 * 根据修订类型返回对应的dialog
 * @author xuzhen
 */
public class ModifyDialogFactory {

	/**
	 * 
	 * @param info
	 * @return
	 */
	public static Dialog getDialog(Object info , Shell shell /* ,int type, ...... */) {
		
		// TODO 根据info获取到type信息
		int type = -1;
		
		switch (type) {
		case ModifyType.NEW_TABLE:
			return new NewTableModifyDialog(shell);
		case ModifyType.ADD_INDEX:
			return new AddIndexModifyDialog(shell);
		case ModifyType.ADD_INDEX_FIELD:
			return new AddIndexFieldModifyDialog(shell);
		case ModifyType.ADD_TABLE_FIELD:
			return new AddTableFieldModifyDialog(shell);
		case ModifyType.MODIFY_FIELD_TYPE:
			return new ModifyTableFieldTypeModifyDialog(shell);
		case ModifyType.REMOVE_INDEX:
			return new RemoveIndexModifyDialog(shell);
		case ModifyType.REMOVE_INDEX_FIELD:
			return new RemoveIndexFieldModifyDialog(shell);
		case ModifyType.RENAME_TABLE_FIELD:
			return new RenameTableFieldModifyDialog(shell);
		case ModifyType.REMOVE_TABLE_FIELD:
			return new RemoveTableFieldModifyDialog(shell);
		case ModifyType.ADJUST_INDEX_ORDER:
			return new AdjustIndexOrderModifyDialog(shell);
		case ModifyType.ADJUST_INDEX_UNIQUE:
			return new AdjustIndexUniqueModifyDialog(shell);
		case ModifyType.REMOVE_TABLE:
			return new RemoveTableDialog(shell);
		case ModifyType.REMOVE_SEQUENCE:
			return new RemoveSequenceDialog(shell);
		default:
			return null;
		}
		
	}
	
	interface ModifyType {
		int NEW_TABLE = 0;
		int ADD_INDEX = 1;
		int ADD_INDEX_FIELD = 2;
		int ADD_TABLE_FIELD = 3;
		int MODIFY_FIELD_TYPE = 4;
		int REMOVE_INDEX = 5;
		int REMOVE_INDEX_FIELD = 6;
		int RENAME_TABLE_FIELD = 7;
		int REMOVE_TABLE_FIELD = 8;
		int ADJUST_INDEX_ORDER = 9;
		int AdjustIndexUniqueModifyDialog = 10;
		int REMOVE_TABLE = 11;
		int REMOVE_SEQUENCE = 12;
		int ADJUST_INDEX_UNIQUE = 13;
		
	}
}
