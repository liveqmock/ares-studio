/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.dialog;

import java.util.Comparator;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.ui.ARESElementLabelProvider;
import com.hundsun.ares.studio.ui.ARESUI;

/**
 * 提供了资源选择的通用对话框<BR>
 * 一般需要设定初始值{@link FilteredItemsSelectionDialog#setInitialPattern(String)}
 * 
 * @author gongyf
 */
public class ARESResourceSelectionDialog extends FilteredItemsSelectionDialog {

	public static final String DIALOG_SETTINGS = "com.hundsun.ares.studio.ui.dialog.ARESResourceSelectionDialog";
	public static final String TAG_RESOURCE_ID = "Reource_fullyQualifiedName";
	public static final String TAG_RESOURCE_TYPE = "Reource_type";

	protected IARESProject project;
	protected String[] resourceTypes;
	protected IARESResource[] resources;

	/**
	 * @param shell
	 * @param project
	 */
	public ARESResourceSelectionDialog(Shell shell, IARESProject project) {
		this(shell, project, "");
	}

	/**
	 * @param shell
	 * @param project
	 * @param resourceType
	 */
	public ARESResourceSelectionDialog(Shell shell, IARESProject project, String resourceType) {
		this(shell, project, new String[] { resourceType });
	}

	public ARESResourceSelectionDialog(Shell shell, IARESProject project, String[] resourceTypes) {
		this(shell, project, resourceTypes, false);
	}

	public ARESResourceSelectionDialog(Shell shell, IARESProject project, String[] resourceTypes, boolean multi) {
		super(shell, multi);
		this.project = project;
		this.resourceTypes = resourceTypes;
		setSelectionHistory(new ARESResourceSelectionHistory());
		setDetailsLabelProvider(new ARESResourceDetailsLabelProvider());
		setListLabelProvider(new ARESResourceLabelProvider());
	}

	// /**
	// * @param resourceType the resourceType to set
	// */
	// public void setResourceType(String resourceType) {
	// this.resourceType = resourceType;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.dialogs.FilteredItemsSelectionDialog#createExtendedContentArea
	 * (org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createExtendedContentArea(Composite parent) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.dialogs.FilteredItemsSelectionDialog#createFilter()
	 */
	@Override
	protected ItemsFilter createFilter() {
		return new ARESResourceFilter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.dialogs.FilteredItemsSelectionDialog#fillContentProvider
	 * (org
	 * .eclipse.ui.dialogs.FilteredItemsSelectionDialog.AbstractContentProvider,
	 * org.eclipse.ui.dialogs.FilteredItemsSelectionDialog.ItemsFilter,
	 * org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected void fillContentProvider(AbstractContentProvider contentProvider, ItemsFilter itemsFilter,
			IProgressMonitor progressMonitor) throws CoreException {
		IARESResource _resources[] = getResources();
		progressMonitor.beginTask("查找资源：" + resourceTypes, _resources.length);
		for (IARESResource resource : _resources) {
			contentProvider.add(resource, itemsFilter);
			progressMonitor.worked(1);
		}
		progressMonitor.done();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.dialogs.FilteredItemsSelectionDialog#getDialogSettings()
	 */
	@Override
	protected IDialogSettings getDialogSettings() {
		IDialogSettings settings = ARESUI.getPlugin().getDialogSettings().getSection(DIALOG_SETTINGS);

		if (settings == null) {
			settings = ARESUI.getPlugin().getDialogSettings().addNewSection(DIALOG_SETTINGS);
		}

		return settings;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.dialogs.FilteredItemsSelectionDialog#getElementName(java
	 * .lang.Object)
	 */
	@Override
	public String getElementName(Object item) {
		return ((IARESResource) item).getFullyQualifiedName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.dialogs.FilteredItemsSelectionDialog#getItemsComparator()
	 */
	@Override
	protected Comparator getItemsComparator() {
		return new Comparator<IARESResource>() {

			public int compare(IARESResource o1, IARESResource o2) {
				return o1.getElementName().compareTo(o2.getElementName());
			}

		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.dialogs.FilteredItemsSelectionDialog#validateItem(java
	 * .lang.Object)
	 */
	@Override
	protected IStatus validateItem(Object item) {
		return Status.OK_STATUS;
	}

	/**
	 * @return the resources
	 */
	public IARESResource[] getResources() {
		if (resources == null) {
			try {
				resources = project.getResources(resourceTypes);
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		return resources;
	}

	private class ARESResourceDetailsLabelProvider extends ARESElementLabelProvider {
		@Override
		public String getText(Object element) {
			return super.getText(element);
		}
	}

	private class ARESResourceLabelProvider extends ARESElementLabelProvider implements IStyledLabelProvider {

		public StyledString getStyledText(Object element) {
			if (element == null) {
				return new StyledString();
			}
			String text = getText(element);
			String moduleName = ((IARESResource) element).getParent().getElementName();
			StyledString result = new StyledString(text);
			result.append(new StyledString(" - " + moduleName, StyledString.DECORATIONS_STYLER));
			return result;
		}

	}

	private class ARESResourceSelectionHistory extends SelectionHistory {

		@Override
		protected Object restoreItemFromMemento(IMemento memento) {
			String name = memento.getString(TAG_RESOURCE_ID);
			String type = memento.getString(TAG_RESOURCE_TYPE);
			if (resourceTypes == null || !ArrayUtils.contains(resourceTypes, type)) {
				return null;
			}
			try {
				return project.findResource(name, type);
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void storeItemToMemento(Object item, IMemento memento) {
			if (item instanceof IARESResource) {
				memento.putString(TAG_RESOURCE_ID, ((IARESResource) item).getFullyQualifiedName());
				memento.putString(TAG_RESOURCE_TYPE, ((IARESResource) item).getType());
			}
		}
	}

	private class ARESResourceFilter extends ItemsFilter {

		@Override
		public boolean isConsistentItem(Object item) {
			return true;
		}

		@Override
		public boolean matchItem(Object item) {
			if (item instanceof IARESResource) {
				String fullyQualifiedName = ((IARESResource) item).getFullyQualifiedName();
				fullyQualifiedName += "." + ((IARESResource) item).getType();
				String name = ((IARESResource) item).getName();
				return matches(fullyQualifiedName) || matches(name);
			}
			return true;
		}

	}
}
