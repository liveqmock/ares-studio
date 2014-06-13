/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.dialog;

import java.util.Comparator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;

import com.hundsun.ares.studio.ui.ARESUI;

/**
 * 带过滤功能的对象选择框。
 * 
 * @author mawb
 */
public abstract class ARESFilteredItemsSelectionDialog<T> extends FilteredItemsSelectionDialog {
	private static final String DIALOG_SETTINGS= "com.hundsun.ares.studio.ui.dialog.ARESFilteredItemsSelectionDialog"; //$NON-NLS-1$
	
	private List<T> input;
	
	public ARESFilteredItemsSelectionDialog(Shell shell, String title) {
		super(shell);
		setTitle(title);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.FilteredItemsSelectionDialog#createExtendedContentArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createExtendedContentArea(Composite parent) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.FilteredItemsSelectionDialog#createFilter()
	 */
	@Override
	protected ItemsFilter createFilter() {
		return new ItemsFilter() {

			@Override
			public boolean isConsistentItem(Object item) {
				return true;
			}

			@Override
			public boolean matchItem(Object item) {
				return matches(getElementName(item));
			}
			
		};
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.FilteredItemsSelectionDialog#fillContentProvider(org.eclipse.ui.dialogs.FilteredItemsSelectionDialog.AbstractContentProvider, org.eclipse.ui.dialogs.FilteredItemsSelectionDialog.ItemsFilter, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected void fillContentProvider(AbstractContentProvider contentProvider,
			ItemsFilter itemsFilter, IProgressMonitor progressMonitor)
			throws CoreException {
		if (input != null) {
			progressMonitor.beginTask("Searching", input.size()); //$NON-NLS-1$
	        for (T item : input) {
	            contentProvider.add(item, itemsFilter);
	            progressMonitor.worked(1);
	        }
	        progressMonitor.done();
		}
	}
	
	public void setInput(List<T> input){
        this.input = input;
    }

	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.FilteredItemsSelectionDialog#getDialogSettings()
	 */
	@Override
	protected IDialogSettings getDialogSettings() {
		IDialogSettings settings= ARESUI.getPlugin().getDialogSettings().getSection(DIALOG_SETTINGS);
		if (settings == null) {
			settings= ARESUI.getPlugin().getDialogSettings().addNewSection(DIALOG_SETTINGS);
		}
		return settings;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.FilteredItemsSelectionDialog#getItemsComparator()
	 */
	@Override
	protected Comparator<T> getItemsComparator() {
		return new Comparator<T>() {
            public int compare(Object arg0, Object arg1) {
               return getElementName(arg0).compareTo(getElementName(arg1));
            }
         };
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.FilteredItemsSelectionDialog#validateItem(java.lang.Object)
	 */
	@Override
	protected IStatus validateItem(Object item) {
		return Status.OK_STATUS;
	}

}
