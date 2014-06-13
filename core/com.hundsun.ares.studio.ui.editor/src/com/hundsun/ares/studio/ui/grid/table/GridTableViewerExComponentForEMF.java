/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.grid.table;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import com.hundsun.ares.studio.ui.util.AresTableActionHandleProviderForEMF;

/**
 * 
 * @author gongyf
 */
public abstract class GridTableViewerExComponentForEMF<T extends EObject> extends GridTableViewerExComponent<T> {

	TransactionalEditingDomain editDomain;
	
	/**
	 * @param editDomain
	 */
	public GridTableViewerExComponentForEMF(
			TransactionalEditingDomain editDomain) {
		super();
		this.editDomain = editDomain;
		setProvider(new AresTableActionHandleProviderForEMF<T>(this, testClassInstance, editDomain));
	}
	
	private void superCommit(GridTableChangeValueOperation change) {
		super.commit(change);
	}
	
	private void superSetValue(T data, String property, Object value, boolean shouldRefresh) {
		super.setValue(data, property, value, shouldRefresh);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.grid.table.GridTableViewerEditorableComponent#commit(com.hundsun.ares.studio.ui.grid.table.GridTableChangeValueOperation)
	 */
	@Override
	public void commit(final GridTableChangeValueOperation change) {
		RecordingCommand command = new RecordingCommand(editDomain) {
			
			@Override
			protected void doExecute() {
				superCommit(change);
			}
		};
		
		editDomain.getCommandStack().execute(command);
	}
	
	public void setValue(final T data, final String property, final Object value, final boolean shouldRefresh) {
		RecordingCommand command = new RecordingCommand(editDomain) {
			@Override
			protected void doExecute() {
				
				superSetValue(data, property, value, shouldRefresh);
				
			}
			
		};
		
		editDomain.getCommandStack().execute(command);
	};
	
}
