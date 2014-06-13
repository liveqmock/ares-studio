/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.grid.tree;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * 
 * @author gongyf
 */
public abstract class GridTreeViewerExComponentForEMF<T> extends
		GridTreeViewerExComponent<T> {
	
	
	private TransactionalEditingDomain domain;
	
	
	
	/**
	 * @param domain
	 */
	public GridTreeViewerExComponentForEMF(TransactionalEditingDomain domain) {
		super();
		this.domain = domain;
	}

	private void superPaste() {
		super.paste();
	}
	
	private void superInsert() {
		super.insert();
	}
	
	private void superDeleteWithOutConfirmed() {
		super.deleteWithOutConfirmed();
	}
	
	private void superCommit(Object parent, Object child,
			GridTreeChangeValueOperation change) {
		super.commit(parent, child, change);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.grid.tree.GridTreeViewerExComponent#paste()
	 */
	@Override
	public void paste() {
		RecordingCommand command = new RecordingCommand(domain) {

			@Override
			protected void doExecute() {
				superPaste();
			}
			
		};
		
		domain.getCommandStack().execute(command);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.grid.tree.GridTreeViewerExComponent#insert()
	 */
	@Override
	public void insert() {
		RecordingCommand command = new RecordingCommand(domain) {

			@Override
			protected void doExecute() {
				superInsert();
			}
			
		};
		
		domain.getCommandStack().execute(command);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.grid.tree.GridTreeViewerExComponent#deleteWithOutConfirmed()
	 */
	@Override
	public void deleteWithOutConfirmed() {
		RecordingCommand command = new RecordingCommand(domain) {

			@Override
			protected void doExecute() {
				superDeleteWithOutConfirmed();
			}
			
		};
		
		domain.getCommandStack().execute(command);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.grid.tree.GridTreeViewerEditorableComponent#commit(java.lang.Object, java.lang.Object, com.hundsun.ares.studio.ui.grid.tree.GridTreeChangeValueOperation)
	 */
	@Override
	protected void commit(final Object parent, final Object child,
			final GridTreeChangeValueOperation change) {
		RecordingCommand command = new RecordingCommand(domain) {

			@Override
			protected void doExecute() {
				TreeCommitOperation operation = new TreeCommitOperation("commit",parent,child);
				try {
					operation.execute(null, null);
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
			
		};
		
		domain.getCommandStack().execute(command);
		
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.grid.tree.GridTreeViewerBasicComponent#setValue(java.lang.Object, java.lang.String, java.lang.Object)
	 */
	@Override
	final public void setValue(final Object data, final String property, final Object value) {
		RecordingCommand command = new RecordingCommand(domain) {

			@Override
			protected void doExecute() {
				doSetValue(data, property, value);
			}
			
		};
		
		domain.getCommandStack().execute(command);
	}
	
	protected abstract void doSetValue(Object data, String property, Object value);

}
