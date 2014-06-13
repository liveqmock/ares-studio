/**
 * 
 */
package com.hundsun.ares.studio.ui.editor;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.texteditor.ChainedPreferenceStore;
import org.eclipse.ui.texteditor.DefaultRangeIndicator;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;

import com.hundsun.ares.studio.ui.editor.editable.IEditableControl;
import com.hundsun.ares.studio.ui.editor.text.TextEditorInputDocumentProvider;
import com.hundsun.ares.studio.ui.validate.IProblemPool;
import com.hundsun.ares.studio.ui.validate.IValidateControl;

/**
 * 
 * 必须以{@link FormEditor#addPage(org.eclipse.ui.IEditorPart, IEditorInput)}
 * 或者{@link FormEditor#addPage(int, org.eclipse.ui.IEditorPart, IEditorInput)}添加
 * @author gongyf
 *
 */
public class TextEditorEMFFormPage extends TextEditor implements IEMFFormPage, IGotoMarker {

	private EMFFormEditor fEditor;
	private Control fControl;
	private String fId;
	private int fIndex;
	private String fTitle = StringUtils.EMPTY;
	
	public TextEditorEMFFormPage(EMFFormEditor editor, String id, String title) {
		fId = id;
		fTitle = title;
		initialize(editor);
		IPreferenceStore[] stores = new IPreferenceStore[2];
		stores[0] = EditorsUI.getPreferenceStore();
		stores[1] = ARESEditorPlugin.getDefault().getPreferenceStore();
		setPreferenceStore(new ChainedPreferenceStore(stores));
		setRangeIndicator(new DefaultRangeIndicator());
		
		setDocumentProvider(new TextEditorInputDocumentProvider());
	}
	
	@Override
	protected boolean isEditorInputIncludedInContextMenu() {
		return false;
	}
	
	@Override
	public String getPartName() {
		return fTitle;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.IFormPage#initialize(org.eclipse.ui.forms.editor.FormEditor)
	 */
	@Override
	public void initialize(FormEditor editor) {
		fEditor = (EMFFormEditor) editor;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.IFormPage#getManagedForm()
	 */
	@Override
	public IManagedForm getManagedForm() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.IFormPage#setActive(boolean)
	 */
	@Override
	public void setActive(boolean active) {

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.IFormPage#isActive()
	 */
	@Override
	public boolean isActive() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.IFormPage#canLeaveThePage()
	 */
	@Override
	public boolean canLeaveThePage() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.IFormPage#getPartControl()
	 */
	@Override
	public Control getPartControl() {
		return fControl;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.IFormPage#getId()
	 */
	@Override
	public String getId() {
		return fId;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.IFormPage#getIndex()
	 */
	@Override
	public int getIndex() {
		return fIndex;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.IFormPage#setIndex(int)
	 */
	@Override
	public void setIndex(int index) {
		fIndex = index;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.IFormPage#isEditor()
	 */
	@Override
	public boolean isEditor() {
		return true;
	}

	@Override
	protected void editorContextMenuAboutToShow(IMenuManager menu) {
		menu.add(new Separator(ITextEditorActionConstants.GROUP_UNDO));
		menu.add(new GroupMarker(ITextEditorActionConstants.GROUP_SAVE));
		menu.add(new Separator(ITextEditorActionConstants.GROUP_COPY));
		menu.add(new Separator(ITextEditorActionConstants.GROUP_PRINT));
		menu.add(new Separator(ITextEditorActionConstants.GROUP_EDIT));
		menu.add(new Separator(ITextEditorActionConstants.GROUP_FIND));
		menu.add(new Separator(IWorkbenchActionConstants.GROUP_ADD));
		menu.add(new Separator(ITextEditorActionConstants.GROUP_REST));
		menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));

		addAction(menu, ITextEditorActionConstants.GROUP_COPY, ITextEditorActionConstants.COPY);
		
		IAction preferencesAction= getAction(ITextEditorActionConstants.CONTEXT_PREFERENCES);
		menu.appendToGroup(IWorkbenchActionConstants.MB_ADDITIONS, new Separator(ITextEditorActionConstants.GROUP_SETTINGS));
		menu.appendToGroup(ITextEditorActionConstants.GROUP_SETTINGS, preferencesAction);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.IFormPage#selectReveal(java.lang.Object)
	 */
	@Override
	public boolean selectReveal(Object object) {
		if (object instanceof IMarker) {
			IDE.gotoMarker(this, (IMarker) object);
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.IEMFFormPage#infoChange()
	 */
	@Override
	public void infoChange() {

	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.IEMFFormPage#validate()
	 */
	@Override
	public void validate() {

	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.IEMFFormPage#getProblemPool()
	 */
	@Override
	public IProblemPool getProblemPool() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.IEMFFormPage#getValidateControl()
	 */
	@Override
	public IValidateControl getValidateControl() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.IEMFFormPage#getEditingDomain()
	 */
	@Override
	public TransactionalEditingDomain getEditingDomain() {
		return getEditor().getEditingDomain();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.IEMFFormPage#getEditor()
	 */
	@Override
	public EMFFormEditor getEditor() {
		return fEditor;
	}

	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		Control[] children = parent.getChildren();
		fControl = children[children.length - 1];
		StyledText styledText= getSourceViewer().getTextWidget();
		styledText.setFont(JFaceResources.getTextFont());
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.IEMFFormPage#setEditableControl(com.hundsun.ares.studio.jres.ui.editors.editable.IEditableControl)
	 */
	@Override
	public void setEditableControl(IEditableControl editableControl) {

	}


}
