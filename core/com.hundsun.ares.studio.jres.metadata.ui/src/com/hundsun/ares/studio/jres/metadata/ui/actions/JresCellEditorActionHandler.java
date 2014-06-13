package com.hundsun.ares.studio.jres.metadata.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.ui.IActionBars;

public class JresCellEditorActionHandler {

	private IAction cutAction;

	private IAction copyAction;

	private IAction pasteAction;

	private IAction deleteAction;

	private IAction selectAllAction;

	private IAction findAction;

	private IAction undoAction;

	private IAction redoAction;


//	private CellEditor activeEditor;
	private ColumnViewer viewer;

	/**
	 * Creates a <code>CellEditor</code> action handler for the global Cut,
	 * Copy, Paste, Delete, Select All, Find, Undo, and Redo of the action bar.
	 * 
	 * @param actionBar
	 *            the action bar to register global action handlers.
	 */
	public JresCellEditorActionHandler(IActionBars actionBar) {
		super();
//		actionBar.setGlobalActionHandler(ActionFactory.CUT.getId(), cellCutAction);
//		actionBar.setGlobalActionHandler(ActionFactory.COPY.getId(), cellCopyAction);
//		actionBar.setGlobalActionHandler(ActionFactory.PASTE.getId(), cellPasteAction);
//		actionBar.setGlobalActionHandler(ActionFactory.DELETE.getId(), cellDeleteAction);
//		actionBar.setGlobalActionHandler(ActionFactory.SELECT_ALL.getId(), cellSelectAllAction);
//		actionBar.setGlobalActionHandler(ActionFactory.FIND.getId(), cellFindAction);
//		actionBar.setGlobalActionHandler(ActionFactory.UNDO.getId(), cellUndoAction);
//		actionBar.setGlobalActionHandler(ActionFactory.REDO.getId(), cellRedoAction);
	}

	/**
	 * Adds a <code>CellEditor</code> to the handler so that the Cut, Copy,
	 * Paste, Delete, Select All, Find, Undo, and Redo actions are redirected to
	 * it when active.
	 * 
	 * @param editor
	 *            the <code>CellEditor</code>
	 */
	public void addCellEditor(CellEditor editor) {
		if (editor == null) {
			return;
		}
	}

	/**
	 * Disposes of this action handler
	 */
	public void dispose() {
		setCutAction(null);
		setCopyAction(null);
		setPasteAction(null);
		setDeleteAction(null);
		setSelectAllAction(null);
		setFindAction(null);
		setUndoAction(null);
		setRedoAction(null);


	}

	/**
	 * Removes a <code>CellEditor</code> from the handler so that the Cut, Copy,
	 * Paste, Delete, Select All, Find Undo, and Redo actions are no longer
	 * redirected to it.
	 * 
	 * @param editor
	 *            the <code>CellEditor</code>
	 */
	public void removeCellEditor(CellEditor editor) {
		if (editor == null) {
			return;
		}
	}

	/**
	 * Sets the default <code>IAction</code> handler for the Copy action. This
	 * <code>IAction</code> is run only if no active cell editor control.
	 * 
	 * @param action
	 *            the <code>IAction</code> to run for the Copy action, or
	 *            <code>null</code> if not interested.
	 */
	public void setCopyAction(IAction action) {
		if (copyAction == action) {
			return;
		}

		copyAction = action;
	}

	/**
	 * Sets the default <code>IAction</code> handler for the Cut action. This
	 * <code>IAction</code> is run only if no active cell editor control.
	 * 
	 * @param action
	 *            the <code>IAction</code> to run for the Cut action, or
	 *            <code>null</code> if not interested.
	 */
	public void setCutAction(IAction action) {
		if (cutAction == action) {
			return;
		}

		cutAction = action;
	}

	/**
	 * Sets the default <code>IAction</code> handler for the Delete action. This
	 * <code>IAction</code> is run only if no active cell editor control.
	 * 
	 * @param action
	 *            the <code>IAction</code> to run for the Delete action, or
	 *            <code>null</code> if not interested.
	 */
	public void setDeleteAction(IAction action) {
		if (deleteAction == action) {
			return;
		}

		deleteAction = action;
	}

	/**
	 * Sets the default <code>IAction</code> handler for the Find action. This
	 * <code>IAction</code> is run only if no active cell editor control.
	 * 
	 * @param action
	 *            the <code>IAction</code> to run for the Find action, or
	 *            <code>null</code> if not interested.
	 */
	public void setFindAction(IAction action) {
		if (findAction == action) {
			return;
		}

		findAction = action;
	}

	/**
	 * Sets the default <code>IAction</code> handler for the Paste action. This
	 * <code>IAction</code> is run only if no active cell editor control.
	 * 
	 * @param action
	 *            the <code>IAction</code> to run for the Paste action, or
	 *            <code>null</code> if not interested.
	 */
	public void setPasteAction(IAction action) {
		if (pasteAction == action) {
			return;
		}

		pasteAction = action;
	}

	/**
	 * Sets the default <code>IAction</code> handler for the Redo action. This
	 * <code>IAction</code> is run only if no active cell editor control.
	 * 
	 * @param action
	 *            the <code>IAction</code> to run for the Redo action, or
	 *            <code>null</code> if not interested.
	 */
	public void setRedoAction(IAction action) {
		if (redoAction == action) {
			return;
		}

		redoAction = action;
	}

	/**
	 * Sets the default <code>IAction</code> handler for the Select All action.
	 * This <code>IAction</code> is run only if no active cell editor control.
	 * 
	 * @param action
	 *            the <code>IAction</code> to run for the Select All action, or
	 *            <code>null</code> if not interested.
	 */
	public void setSelectAllAction(IAction action) {
		if (selectAllAction == action) {
			return;
		}

		selectAllAction = action;
	}

	/**
	 * Sets the default <code>IAction</code> handler for the Undo action. This
	 * <code>IAction</code> is run only if no active cell editor control.
	 * 
	 * @param action
	 *            the <code>IAction</code> to run for the Undo action, or
	 *            <code>null</code> if not interested.
	 */
	public void setUndoAction(IAction action) {
		if (undoAction == action) {
			return;
		}

		undoAction = action;

	}

}
