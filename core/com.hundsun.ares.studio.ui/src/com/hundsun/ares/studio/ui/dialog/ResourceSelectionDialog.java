/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.dialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceProxy;
import org.eclipse.core.resources.IResourceProxyVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.ArrayUtil;
import com.hundsun.ares.studio.ui.ARESUI;

/**
 * 文件资源选择对话框。
 * 
 * @author mawb
 */
public class ResourceSelectionDialog extends FilteredItemsSelectionDialog {
	private IContainer[] containers;
	private String[] extensions;
	
	public static final String DIALOG_SETTINGS = "com.hundsun.ares.studio.ui.dialog.ResourceSelectionDialog";
	public static final String TAG_RESOURCE_PATH = "Reource_path";
	public static final String TAG_RESOURCE_EXTENSION = "Reource_extension";
	
	public ResourceSelectionDialog(Shell shell, IContainer container, String extension) {
		this(shell, false, container, extension);
	}
	
	public ResourceSelectionDialog(Shell shell, boolean multi, IContainer container, String extension) {
		super(shell, multi);
		this.containers = new IContainer[] {container};
		this.extensions = new String[] {extension};
	}
	
	public ResourceSelectionDialog(Shell shell, IContainer[] containers, String[] extensions) {
		this(shell, false, containers, extensions);
	}
	
	public ResourceSelectionDialog(Shell shell, boolean multi, IContainer[] containers, String[] extensions) {
		super(shell, multi);
		this.containers = containers;
		this.extensions = extensions;
		setSelectionHistory(new ResourceSelectionHistory());
		setDetailsLabelProvider(new ResourceItemDetailsLabelProvider());
		setListLabelProvider(new ResourceItemLabelProvider());
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
		return new ResourceFilter();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.FilteredItemsSelectionDialog#fillContentProvider(org.eclipse.ui.dialogs.FilteredItemsSelectionDialog.AbstractContentProvider, org.eclipse.ui.dialogs.FilteredItemsSelectionDialog.ItemsFilter, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected void fillContentProvider(AbstractContentProvider contentProvider,
			ItemsFilter itemsFilter, IProgressMonitor progressMonitor)
			throws CoreException {
		if (itemsFilter instanceof ResourceFilter) {
			for (IContainer container : containers) {
				container.accept(new ResourceProxyVisitor(container,
						contentProvider, (ResourceFilter) itemsFilter, progressMonitor),
						IResource.NONE);
			}
		}
		if (progressMonitor != null) {
			progressMonitor.done();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.FilteredItemsSelectionDialog#getDialogSettings()
	 */
	@Override
	protected IDialogSettings getDialogSettings() {
		IDialogSettings settings = ARESUI.getPlugin().getDialogSettings().getSection(DIALOG_SETTINGS);

		if (settings == null) {
			settings = ARESUI.getDefault().getDialogSettings()
					.addNewSection(DIALOG_SETTINGS);
		}
		
		return settings;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.FilteredItemsSelectionDialog#getElementName(java.lang.Object)
	 */
	@Override
	public String getElementName(Object item) {
		IResource resource = (IResource) item;
		return resource.getName();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.FilteredItemsSelectionDialog#getItemsComparator()
	 */
	@Override
	protected Comparator<IResource> getItemsComparator() {
		return new Comparator<IResource>() {

			public int compare(IResource o1, IResource o2) {
				// 先按扩展名排序，再按名字排序
				int result = o1.getFileExtension().compareTo(o2.getFileExtension());
				if (result == 0) { 
					return o1.getName().compareToIgnoreCase(o2.getName());
				}
				return result;
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
	
	/**
	 * 获取资源在指定目录下的相对路径（路径由点符号（"."）分割），不存在时返回null。
	 * 
	 * @param resource
	 * @return
	 */
	public String getRelativePath(IResource resource) {
		int index = ArrayUtil.findInArray(extensions, resource.getFileExtension());
		if (index != -1) {
			String resourcePath = resource.getParent().getFullPath().makeRelative().toString();
			String containerPath = containers[index].getFullPath().makeRelative().toString() + "/";
			if (resourcePath.startsWith(containerPath)) {
				return resourcePath.substring(containerPath.length()).replaceAll("/", "\\.");
			}
		}
		return null;
	}
	
	/**
	 * 获取资源在指定目录下带相对路径和后缀的文件全名（路径由点符号（"."）分割）
	 * 
	 * @param resource
	 * @return
	 */
	public String getFullyQualifiedName(IResource resource) {
		int index = ArrayUtil.findInArray(extensions, resource.getFileExtension());
		if (index != -1) {
			String resourcePath = resource.getParent().getFullPath().makeRelative().toString();
			String containerPath = containers[index].getFullPath().makeRelative().toString() + "/";
			if (resourcePath.startsWith(containerPath)) {
				return resourcePath.substring(containerPath.length()).replaceAll("/", "\\.");
			}
		}
		return null;
	}
	
	/**
	 * A label provider for ResourceDecorator objects. It creates labels with a
	 * resource full path for duplicates. It uses the Platform UI label
	 * decorator for providing extra resource info.
	 */
	private class ResourceItemLabelProvider extends LabelProvider implements
			ILabelProviderListener, IStyledLabelProvider {

		// Need to keep our own list of listeners
		private ListenerList listeners = new ListenerList();

		WorkbenchLabelProvider provider = new WorkbenchLabelProvider();

		/**
		 * Creates a new instance of the class
		 */
		public ResourceItemLabelProvider() {
			super();
			provider.addListener(this);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
		 */
		public Image getImage(Object element) {
			if (!(element instanceof IResource)) {
				return super.getImage(element);
			}

			IResource res = (IResource) element;

			return provider.getImage(res);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
		 */
		public String getText(Object element) {
			if (!(element instanceof IResource)) {
				return super.getText(element);
			}

			IResource res = (IResource) element;
			String str = res.getName();
			
			String path = getRelativePath(res);
			if (!StringUtils.isEmpty(path)) {
				str = str + " - " + path;
			}

			return str;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider#getStyledText(java.lang.Object)
		 */
		public StyledString getStyledText(Object element) {
			if (!(element instanceof IResource)) {
				return new StyledString(super.getText(element));
			}

			IResource res = (IResource) element;

			StyledString str = new StyledString(res.getName());
			
			String path = getRelativePath(res);
			if (!StringUtils.isEmpty(path)) {
				str.append(" - ", StyledString.QUALIFIER_STYLER); //$NON-NLS-1$
				str.append(path, StyledString.QUALIFIER_STYLER);
			}
			
			return str;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.LabelProvider#dispose()
		 */
		public void dispose() {
			provider.removeListener(this);
			provider.dispose();

			super.dispose();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
		 */
		public void addListener(ILabelProviderListener listener) {
			listeners.add(listener);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.LabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
		 */
		public void removeListener(ILabelProviderListener listener) {
			listeners.remove(listener);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.ILabelProviderListener#labelProviderChanged(org.eclipse.jface.viewers.LabelProviderChangedEvent)
		 */
		public void labelProviderChanged(LabelProviderChangedEvent event) {
			Object[] l = listeners.getListeners();
			for (int i = 0; i < listeners.size(); i++) {
				((ILabelProviderListener) l[i]).labelProviderChanged(event);
			}
		}

	}
	
	/**
	 * A label provider for details of ResourceItem objects.
	 */
	private class ResourceItemDetailsLabelProvider extends
			ResourceItemLabelProvider {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
		 */
		public Image getImage(Object element) {
			if (!(element instanceof IResource)) {
				return super.getImage(element);
			}

			IResource parent = ((IResource) element).getParent();
			return provider.getImage(parent);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
		 */
		public String getText(Object element) {
			if (!(element instanceof IResource)) {
				return super.getText(element);
			}

			IResource parent = ((IResource) element).getParent();

			if (parent.getType() == IResource.ROOT) {
				// Get readable name for workspace root ("Workspace"), without
				// duplicating language-specific string here.
				return null;
			}

			return parent.getFullPath()	.makeRelative().toString();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.ILabelProviderListener#labelProviderChanged(org.eclipse.jface.viewers.LabelProviderChangedEvent)
		 */
		public void labelProviderChanged(LabelProviderChangedEvent event) {
			Object[] l = super.listeners.getListeners();
			for (int i = 0; i < super.listeners.size(); i++) {
				((ILabelProviderListener) l[i]).labelProviderChanged(event);
			}
		}
	}
	
	private class ResourceSelectionHistory extends SelectionHistory {

		@Override
		protected Object restoreItemFromMemento(IMemento memento) {
			String path = memento.getString(TAG_RESOURCE_PATH);
			String extension = memento.getString(TAG_RESOURCE_EXTENSION);
			int index = ArrayUtil.findInArray(extensions, extension);
			if (index != -1) {
				return containers[index].getFile(Path.fromOSString(path));
			}
			return null;
		}

		@Override
		protected void storeItemToMemento(Object item, IMemento memento) {
			if (item instanceof IARESResource) {
				memento.putString(TAG_RESOURCE_PATH, ((IResource) item).getFullPath().toOSString());
				memento.putString(TAG_RESOURCE_EXTENSION, ((IResource) item).getFileExtension());
			}
		}
	}
	
	private class ResourceFilter extends ItemsFilter {
		
		/* (non-Javadoc)
		 * @see org.eclipse.ui.dialogs.FilteredItemsSelectionDialog.ItemsFilter#isConsistentItem(java.lang.Object)
		 */
		@Override
		public boolean isConsistentItem(Object item) {
			return true;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.ui.dialogs.FilteredItemsSelectionDialog.ItemsFilter#matchItem(java.lang.Object)
		 */
		@Override
		public boolean matchItem(Object item) {
			if (item instanceof IResource) {
				IResource resource = (IResource) item;
				String fullyQualifiedName = getFullyQualifiedName(resource);
				if (fullyQualifiedName != null) {
					String name = resource.getName();
					return matches(fullyQualifiedName) || matches(name);
				}
			}
			return false;
		}
		
	}
	
	/**
	 * ResourceProxyVisitor to visit resource tree and get matched resources.
	 * During visit resources it updates progress monitor and adds matched
	 * resources to ContentProvider instance.
	 */
	private class ResourceProxyVisitor implements IResourceProxyVisitor {

		private AbstractContentProvider proxyContentProvider;

		private ResourceFilter resourceFilter;

		private IProgressMonitor progressMonitor;

		private List<IResource> projects;

		/**
		 * Creates new ResourceProxyVisitor instance.
		 * 
		 * @param contentProvider
		 * @param resourceFilter
		 * @param progressMonitor
		 * @throws CoreException
		 */
		public ResourceProxyVisitor(IContainer container, AbstractContentProvider contentProvider,
				ResourceFilter resourceFilter, IProgressMonitor progressMonitor)
				throws CoreException {
			super();
			this.proxyContentProvider = contentProvider;
			this.resourceFilter = resourceFilter;
			this.progressMonitor = progressMonitor;
			IResource[] resources = container.members();
			this.projects = new ArrayList<IResource>(Arrays.asList(resources));

			if (progressMonitor != null)
				progressMonitor
						.beginTask(
								WorkbenchMessages.FilteredItemsSelectionDialog_searchJob_taskName,
								projects.size());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.resources.IResourceProxyVisitor#visit(org.eclipse.core.resources.IResourceProxy)
		 */
		public boolean visit(IResourceProxy proxy) {

			if (progressMonitor.isCanceled()) {
				return false;
			}

			IResource resource = proxy.requestResource();

			if (this.projects.remove((resource.getProject()))
					|| this.projects.remove((resource))) {
				progressMonitor.worked(1);
			}

			if (resource.getType() == IResource.FILE) {
				proxyContentProvider.add(resource, resourceFilter);
				return false;
			}

			return true;
		}
	}
	
}
