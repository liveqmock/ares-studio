/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.dialog;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.widgets.Shell;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;

/**
 * 不能选择自己的资源选择框
 * 
 * @author yanyl
 */
public abstract class ConditionalResourceSelectionDialog extends ARESResourceSelectionDialog {

	public ConditionalResourceSelectionDialog(Shell shell, IARESProject project) {
		super(shell, project);
	}

	/**
	 * @param shell
	 * @param project
	 * @param resourceType
	 */
	public ConditionalResourceSelectionDialog(Shell shell, IARESProject project, String resourceType) {
		super(shell, project, resourceType);
	}

	public ConditionalResourceSelectionDialog(Shell shell, IARESProject project, String[] resourceTypes) {
		super(shell, project, resourceTypes);
	}

	public ConditionalResourceSelectionDialog(Shell shell, IARESProject project, String[] resourceTypes, boolean multi) {
		super(shell, project, resourceTypes, multi);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hundsun.ares.studio.ui.dialog.ARESResourceSelectionDialog#createFilter
	 * ()
	 */
	@Override
	protected ItemsFilter createFilter() {
		return getFilter();
	}

	public ItemsFilter getFilter() {
		return new ConditionalFilter(getFilterExceptResources());
	}

	public abstract String[] getFilterExceptResources();

	private class ConditionalFilter extends ItemsFilter {
		private Map<String, String> exceptResources = null;

		public ConditionalFilter(String[] exceptResources) {
			this.exceptResources = new HashMap<String, String>();
			if (exceptResources != null) {
				for (String res : exceptResources) {
					this.exceptResources.put(res, null);
				}
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ui.dialogs.FilteredItemsSelectionDialog.ItemsFilter#
		 * isConsistentItem(java.lang.Object)
		 */
		@Override
		public boolean isConsistentItem(Object item) {
			return true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.ui.dialogs.FilteredItemsSelectionDialog.ItemsFilter#matchItem
		 * (java.lang.Object)
		 */
		@Override
		public boolean matchItem(Object item) {
			if (item instanceof IARESResource) {
				String fullyQualifiedName = ((IARESResource) item).getFullyQualifiedName();

				if (isExcepted(fullyQualifiedName+((IARESResource) item).getType())) {
					return false;
				}

				fullyQualifiedName += "." + ((IARESResource) item).getType();
				String name = ((IARESResource) item).getName();

				return matches(fullyQualifiedName) || matches(name);
			}
			return true;
		}

		private boolean isExcepted(String resName) {
			return exceptResources.containsKey(resName);
		}
	}
}
