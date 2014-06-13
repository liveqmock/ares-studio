
package com.hundsun.ares.studio.emfadapter.editor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.ui.ViewerPane;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.celleditor.AdapterFactoryTreeEditor;
import org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.provider.UnwrappingSelectionProvider;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.emf.edit.ui.view.ExtendedPropertySheetPage;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.views.contentoutline.ContentOutline;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheet;
import org.eclipse.ui.views.properties.PropertySheetPage;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.ui.editor.AbstractHSExtendPointFormEditor;
import com.hundsun.ares.studio.ui.editor.AbstractHSFormEditor;
import com.hundsun.ares.studio.ui.editor.AbstractHSFormEditorNoUndocontext;
import com.hundsun.ares.studio.ui.page.HSComponentbasedFormPage;

public abstract class AbstractHSFormEditorEMFAdapterNoUndocontext<T extends EObject> 
	extends AbstractHSFormEditorNoUndocontext<T>
	implements IEditingDomainProvider, IMenuListener, ISelectionProvider {
	
	/**
	 * This keeps track of the editing domain that is used to track all changes to the model.
	 */
	protected AdapterFactoryEditingDomain editingDomain;
	
	/**
	 * This is the one adapter factory used for providing views of the model.
	 */
	protected ComposedAdapterFactory adapterFactory;
	
	/**
	 * This keeps track of the active viewer pane, in the book.
	 */
	protected ViewerPane currentViewerPane;
	
	/**
	 * This is the viewer that shadows the selection in the content outline.
	 * The parent relation must be correctly defined for this to work.
	 */
	protected TreeViewer selectionViewer;
	
	/**
	 * This listens to which ever viewer is active.
	 */
	protected ISelectionChangedListener selectionChangedListener;

	/**
	 * This keeps track of all the {@link org.eclipse.jface.viewers.ISelectionChangedListener}s that are listening to this editor.
	 */
	protected Collection<ISelectionChangedListener> selectionChangedListeners = new ArrayList<ISelectionChangedListener>();
	
	/**
	 * This keeps track of the selection of the editor as a whole.
	 */
	protected ISelection editorSelection = StructuredSelection.EMPTY;
	
	/**
	 * This is the content outline page.
	 */
	protected IContentOutlinePage contentOutlinePage;
	
	/**
	 * This is the content outline page's viewer.
	 */
	protected TreeViewer contentOutlineViewer;
	
	/**
	 * This is the property sheet page.
	 */
	protected PropertySheetPage propertySheetPage;
	
	/**
	 * 源码页页签。
	 */
	protected int sourcePageIndex = -1;
	
	protected boolean isRefresh = false;
	
	/**
	 * This listens for when the outline becomes active
	 */
	protected IPartListener partListener = new IPartListener() {
		public void partActivated(IWorkbenchPart p) {
			if (p instanceof ContentOutline) {
				if (((ContentOutline)p).getCurrentPage() == contentOutlinePage) {
					getActionBarContributor().setActiveEditor(AbstractHSFormEditorEMFAdapterNoUndocontext.this);
//					setCurrentViewer(contentOutlineViewer);
				}
			}
			else if (p instanceof PropertySheet) {
				if (((PropertySheet)p).getCurrentPage() == propertySheetPage) {
					getActionBarContributor().setActiveEditor(AbstractHSFormEditorEMFAdapterNoUndocontext.this);
					handleActivate();
				}
			}
			else if (p == AbstractHSFormEditorEMFAdapterNoUndocontext.this) {
				handleActivate();
			}
		}
		public void partBroughtToTop(IWorkbenchPart p) {
			// Ignore.
		}
		public void partClosed(IWorkbenchPart p) {
			// Ignore.
		}
		public void partDeactivated(IWorkbenchPart p) {
			// Ignore.
		}
		public void partOpened(IWorkbenchPart p) {
			// Ignore.
		}
	};
	
	/**
	 * Adapter used to update the problem indication when resources are demanded loaded.
	 */
	protected EContentAdapter problemIndicationAdapter = 
		new EContentAdapter() {
			@Override
			public void notifyChanged(Notification notification) {
				handleNotifyChanged(notification);
			}

			@Override
			protected void setTarget(Resource target) {
				basicSetTarget(target);
			}

			@Override
			protected void unsetTarget(Resource target) {
				basicUnsetTarget(target);
			}
		};
		
	public AbstractHSFormEditorEMFAdapterNoUndocontext() {
		super();
		initializeEditingDomain();
	}
	
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// TODO Auto-generated method stub
		super.init(site, input);
		site.setSelectionProvider(this);
		site.getPage().addPartListener(partListener);
	}
	
	/**
	 * This sets up the editing domain for the model editor.
	 */
	protected void initializeEditingDomain() {
		// Create an adapter factory that yields item providers.
		//
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(getResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		// Create the command stack that will notify this editor as commands are executed.
		//
		final BasicCommandStack commandStack = new BasicCommandStack();
		
		// Add a listener to set the most recent command's affected objects to be the selection of the viewer with focus.
		//
		commandStack.addCommandStackListener
			(new CommandStackListener() {
				 public void commandStackChanged(final EventObject event) {
					 getContainer().getDisplay().asyncExec
						 (new Runnable() {
							  public void run() {
								  dirty.setValue(commandStack.isSaveNeeded());
//								  firePropertyChange(IEditorPart.PROP_DIRTY);

								  // Try to select the affected objects.
								  //
								  Command mostRecentCommand = ((CommandStack)event.getSource()).getMostRecentCommand();
								  if (mostRecentCommand != null) {
									  for (Object obj : mostRecentCommand.getAffectedObjects()) {
										  if (obj instanceof EObject) {
											  if (!((EObject) obj).eAdapters().contains(problemIndicationAdapter)) {
												  ((EObject) obj).eAdapters().add(problemIndicationAdapter);
											  }
										  }
									  }
									  setSelectionToViewer(mostRecentCommand.getAffectedObjects());
								  }
								  if (propertySheetPage != null && !propertySheetPage.getControl().isDisposed()) {
									  propertySheetPage.refresh();
								  }
							  }
						  });
				 }
			 });
		
		// Create the editing domain with a special command stack.
		editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack, new HashMap<Resource, Boolean>());
		
	}
	
	/**
	 * Handles activation of the editor or it's associated views.
	 */
	protected void handleActivate() {
		// Recompute the read only state.
		//
		if (editingDomain.getResourceToReadOnlyMap() != null) {
		  editingDomain.getResourceToReadOnlyMap().clear();

		  // Refresh any actions that may become enabled or disabled.
		  //
		  setSelection(getSelection());
		}
	}
	
	@Override
	protected void createPages() {
		createModel();
		super.createPages();
		if (!getEditingDomain().getResourceSet().getResources().isEmpty()) {
			createSourcePage();
		}
//		HSResoueceEMFActionBarContributor bar = (HSResoueceEMFActionBarContributor)getActionBarContributor();
//		bar.setEditdomain(editingDomain);
	}
	
	/**
	 * This is the method called to load a resource into the editing domain's resource set based on the editor's input.
	 */
	public void createModel() {
		URI resourceURI = EditUIUtil.getURI(getEditorInput());
//		try {
//			// Load the resource through the editing domain.
//			editingDomain.getResourceSet().getResource(resourceURI, false);
//		}
//		catch (Exception e) {
//			editingDomain.getResourceSet().getResource(resourceURI, false);
//		}
		XMLResourceImpl xmlResource = new XMLResourceImpl(resourceURI);
		xmlResource.getContents().add((EObject) info);
		
		editingDomain.getResourceSet().getResources().add(xmlResource);
		editingDomain.getResourceToReadOnlyMap().put(xmlResource, resource.isReadOnly());
		
		((EObject)info).eAdapters().add(problemIndicationAdapter);
	}
	
	/**
	 * 资源发生变化。
	 * 
	 * @param notification
	 */
	protected void handleNotifyChanged(Notification notification) {
		// do nothing
	}
	
	/**
	 * 创建源代码编辑页。
	 */
	protected void createSourcePage() {
		ViewerPane viewerPane =
			new ViewerPane(getSite().getPage(), AbstractHSFormEditorEMFAdapterNoUndocontext.this) {
				@Override
				public Viewer createViewer(Composite composite) {
					Tree tree = new Tree(composite, SWT.MULTI);
					TreeViewer newTreeViewer = new TreeViewer(tree);
					return newTreeViewer;
				}
				
				@Override
				protected void createTitleBar() {
					// TODO Auto-generated method stub
					
				}
			};
		viewerPane.createControl(getContainer());
		
		selectionViewer = (TreeViewer)viewerPane.getViewer();
		selectionViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));

		selectionViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		selectionViewer.setInput(editingDomain.getResourceSet().getResources().get(0));
		selectionViewer.setSelection(new StructuredSelection(/*editingDomain.getResourceSet().getResources().get(0)*/info), true);
		selectionViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			// This just notifies those things that are affected by the section.
			//
			public void selectionChanged(SelectionChangedEvent selectionChangedEvent) {
				setSelection(selectionChangedEvent.getSelection());
			}
		});
		
//		viewerPane.setTitle(editingDomain.getResourceSet());

		new AdapterFactoryTreeEditor(selectionViewer.getTree(), adapterFactory);

		createContextMenuFor(selectionViewer);
		sourcePageIndex = addPage(viewerPane.getControl());
		setPageText(sourcePageIndex, "source");
	}
	
	/**
	 * This creates a context menu for the viewer and adds a listener as well registering the menu for extension.
	 */
	protected void createContextMenuFor(StructuredViewer viewer) {
		MenuManager contextMenu = new MenuManager("#PopUp");
		contextMenu.add(new Separator("additions"));
		contextMenu.setRemoveAllWhenShown(true);
		contextMenu.addMenuListener(this);
		Menu menu= contextMenu.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(contextMenu, new UnwrappingSelectionProvider(viewer));

		int dndOperations = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
		Transfer[] transfers = new Transfer[] { LocalTransfer.getInstance() };
		viewer.addDragSupport(dndOperations, transfers, new ViewerDragAdapter(viewer));
		viewer.addDropSupport(dndOperations, transfers, new EditingDomainViewerDropAdapter(editingDomain, viewer));
	}
	
	public AdapterFactory getAdapterFactory() {
		return adapterFactory;
	}
	
	public EditingDomain getEditingDomain() {
		return editingDomain;
	}
	
	/**
	 * 获取模型资源对应的适配器工厂对象。
	 * 
	 * @return
	 */
	protected abstract ComposeableAdapterFactory getResourceItemProviderAdapterFactory();
	
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.add(listener);
	}
	
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.remove(listener);
	}
	
	public ISelection getSelection() {
		return editorSelection;
	}
	
	public void setSelection(ISelection selection) {
		editorSelection = selection;

		for (ISelectionChangedListener listener : selectionChangedListeners) {
			listener.selectionChanged(new SelectionChangedEvent(this, selection));
		}
//		setStatusLineManager(selection);
	}
	
	/**
	 * This accesses a cached version of the content outliner.
	 */
	public IContentOutlinePage getContentOutlinePage() {
		if (contentOutlinePage == null) {
			// The content outline is just a tree.
			//
			class MyContentOutlinePage extends ContentOutlinePage {
				@Override
				public void createControl(Composite parent) {
					super.createControl(parent);
					contentOutlineViewer = getTreeViewer();
					contentOutlineViewer.addSelectionChangedListener(this);

					// Set up the tree viewer.
					//
					contentOutlineViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
					contentOutlineViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
					contentOutlineViewer.setInput(editingDomain.getResourceSet());

					// Make sure our popups work.
					//
					createContextMenuFor(contentOutlineViewer);

					if (!editingDomain.getResourceSet().getResources().isEmpty()) {
					  // Select the root object in the view.
					  //
					  contentOutlineViewer.setSelection(new StructuredSelection(editingDomain.getResourceSet().getResources().get(0)), true);
					}
				}

				@Override
				public void makeContributions(IMenuManager menuManager, IToolBarManager toolBarManager, IStatusLineManager statusLineManager) {
					super.makeContributions(menuManager, toolBarManager, statusLineManager);
//					contentOutlineStatusLineManager = statusLineManager;
				}

				@Override
				public void setActionBars(IActionBars actionBars) {
					super.setActionBars(actionBars);
					getActionBarContributor().shareGlobalActions(this, actionBars);
				}
			}

			contentOutlinePage = new MyContentOutlinePage();

			// Listen to selection so that we can handle it is a special way.
			//
			contentOutlinePage.addSelectionChangedListener
				(new ISelectionChangedListener() {
					 // This ensures that we handle selections correctly.
					 //
					 public void selectionChanged(SelectionChangedEvent event) {
						 handleContentOutlineSelection(event.getSelection());
					 }
				 });
		}

		return contentOutlinePage;
	}

	/**
	 * This accesses a cached version of the property sheet.
	 */
	public IPropertySheetPage getPropertySheetPage() {
		if (propertySheetPage == null) {
			propertySheetPage =
				new ExtendedPropertySheetPage(editingDomain) {
					@Override
					public void setSelectionToViewer(List<?> selection) {
						AbstractHSFormEditorEMFAdapterNoUndocontext.this.setSelectionToViewer(selection);
						AbstractHSFormEditorEMFAdapterNoUndocontext.this.setFocus();
					}

					@Override
					public void setActionBars(IActionBars actionBars) {
						super.setActionBars(actionBars);
						getActionBarContributor().shareGlobalActions(this, actionBars);
					}
				};
			propertySheetPage.setPropertySourceProvider(new AdapterFactoryContentProvider(adapterFactory));
		}

		return propertySheetPage;
	}
	
	/**
	 * This sets the selection into whichever viewer is active.
	 */
	public void setSelectionToViewer(Collection<?> collection) {
		final Collection<?> theSelection = collection;
		// Make sure it's okay.
		//
		if (theSelection != null && !theSelection.isEmpty()) {
			// I don't know if this should be run this deferred
			// because we might have to give the editor a chance to process the viewer update events
			// and hence to update the views first.
			//
			//
			Runnable runnable =
				new Runnable() {
					public void run() {
						// Try to select the items in the current content viewer of the editor.
						//
						if (selectionViewer != null) {
							selectionViewer.setSelection(new StructuredSelection(theSelection.toArray()), true);
						}
					}
				};
			runnable.run();
		}
	}
	
	/**
	 * This deals with how we want selection in the outliner to affect the other views.
	 */
	public void handleContentOutlineSelection(ISelection selection) {
		if (selectionViewer != null && !selection.isEmpty() && selection instanceof IStructuredSelection) {
			Iterator<?> selectedElements = ((IStructuredSelection)selection).iterator();
			if (selectedElements.hasNext()) {
				// Get the first selected element.
				//
				Object selectedElement = selectedElements.next();

				// If it's the selection viewer, then we want it to select the same selection as this selection.
				//
				ArrayList<Object> selectionList = new ArrayList<Object>();
				selectionList.add(selectedElement);
				while (selectedElements.hasNext()) {
					selectionList.add(selectedElements.next());
				}

				// Set the selection to the widget.
				//
				selectionViewer.setSelection(new StructuredSelection(selectionList));
			}
		}
	}
	
	public void menuAboutToShow(IMenuManager manager) {
		((IMenuListener)getEditorSite().getActionBarContributor()).menuAboutToShow(manager);
	}
	
	@Override
	protected void pageChange(int pageIndex) {
		super.pageChange(pageIndex);
		
		// 从源码页转到其他页面时需要刷新，其他情况不刷新
		if (isRefresh) {
			Object page = getSelectedPage();
			if (page instanceof HSComponentbasedFormPage) {
				((HSComponentbasedFormPage) page).refresh();
			}
		}
		isRefresh = (pageIndex == sourcePageIndex);
		
		if (contentOutlinePage != null) {
			handleContentOutlineSelection(contentOutlinePage.getSelection());
		}
	}
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		editingDomain.getResourceSet().getResources().clear();
		doSaveFile(monitor);
		if (info.eResource().getURI() == null) {
			URI uri = EditUIUtil.getURI(getEditorInput());
			info.eResource().setURI(uri);
		}
		editingDomain.getResourceSet().getResources().add(info.eResource());
		selectionViewer.setInput(info.eResource());
		//重置saveindex，保证undo/redo正常运行
		((BasicCommandStack)editingDomain.getCommandStack()).saveIsDone();
		dirty.setValue(false);
	}
	
	@Override
	public Object getAdapter(Class key) {
		if (key.equals(IContentOutlinePage.class)) {
			return showOutlineView() ? getContentOutlinePage() : null;
		}
		else if (key.equals(IPropertySheetPage.class)) {
			return getPropertySheetPage();
		}
		else {
			return super.getAdapter(key);
		}
	}
	
	/**
	 * set focus.
	 */
	@Override
	public void setFocus() {
		if (currentViewerPane != null) {
			currentViewerPane.setFocus();
		}
		else {
			getControl(getActivePage()).setFocus();
		}
	}
	
	/**
	 * get editor action bar contributor.
	 */
	public EditingDomainActionBarContributor getActionBarContributor() {
		return (EditingDomainActionBarContributor)getEditorSite().getActionBarContributor();
	}
	
	/**
	 * Returns whether the outline view should be presented to the user.
	 */
	protected boolean showOutlineView() {
		return true;
	}
	
	@Override
	public void dispose() {
//		updateProblemIndication = false;
		
//		ResourcesPlugin.getWorkspace().removeResourceChangeListener(resourceChangeListener);

		getSite().getPage().removePartListener(partListener);

		adapterFactory.dispose();

		if (getActionBarContributor().getActiveEditor() == this) {
			getActionBarContributor().setActiveEditor(null);
		}

		if (propertySheetPage != null) {
			propertySheetPage.dispose();
		}

		if (contentOutlinePage != null) {
			contentOutlinePage.dispose();
		}

		super.dispose();
	}
	
}
