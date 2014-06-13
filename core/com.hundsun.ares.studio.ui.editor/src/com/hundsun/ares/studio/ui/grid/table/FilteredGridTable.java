/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
/*******************************************************************************
 * Copyright (c) 2004, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.hundsun.ares.studio.ui.grid.table;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.nebula.jface.gridviewer.GridTableViewer;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.nebula.widgets.grid.GridItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.progress.WorkbenchJob;

import com.hundsun.ares.studio.ui.grid.GridViewerStyleControl;
/**
 * A simple control that provides a text widget and a tree viewer. The contents
 * of the text widget are used to drive a PatternFilter that is on the viewer.
 * 
 * @see org.eclipse.ui.dialogs.PatternFilter
 * @since 3.2
 */
public class FilteredGridTable extends Composite {

	/**
	 * The filter text widget to be used by this tree. This value may be
	 * <code>null</code> if there is no filter widget, or if the controls have
	 * not yet been created.
	 */
    protected Text filterText;
    
	/**
	 * The control representing the clear button for the filter text entry. This
	 * value may be <code>null</code> if no such button exists, or if the
	 * controls have not yet been created.
	 */
    protected ToolBarManager filterToolBar;

	/**
	 * The viewer for the filtered tree. This value should never be <code>null</code>
	 * after the widget creation methods are complete.
	 */
    protected GridTableViewer tableViewer;

    /**
     * The Composite on which the filter controls are created. This is used to set 
     * the background color of the filter controls to match the surrounding controls.
     */
    protected Composite filterComposite;
    
	/**
	 * The pattern filter for the tree. This value must not be <code>null</code>.
	 */
    private HSGridTableFilter patternFilter;

    /**
     * The text to initially show in the filter text control. 
     */
    protected String initialText = ""; //$NON-NLS-1$
    
    /**
     * The job used to refresh the tree.
     */
    private Job refreshJob;
    
    /**
     * The parent composite of the filtered tree.
     * @since 3.3
     */
    protected Composite parent;

    /**
     * Whether or not to show the filter controls (text and clear button).
     * The default is to show these controls.  This can be overridden by 
     * providing a setting in the product configuration file.  The setting  
     * to add to not show these controls is:
     * 
     * org.eclipse.ui/SHOW_FILTERED_TEXTS=false
     */
    protected boolean showFilterControls;

	/**
	 * @since 3.3
	 */
	protected Composite tableComposite;
    
    /**
     * Image descriptor for enabled clear button.
     */
    private static final String CLEAR_ICON = "org.eclipse.ui.internal.dialogs.CLEAR_ICON"; //$NON-NLS-1$

    /**
     * Image descriptor for disabled clear button.
     */
    private static final String DCLEAR_ICON = "org.eclipse.ui.internal.dialogs.DCLEAR_ICON"; //$NON-NLS-1$

    /**
     * Maximum time spent expanding the tree after the filter text has been updated
     * (this is only used if we were able to at least expand the visible nodes)
     */

    /**
     * Get image descriptors for the clear button.
     */
    static {
        ImageDescriptor descriptor = AbstractUIPlugin
                .imageDescriptorFromPlugin(PlatformUI.PLUGIN_ID,
                        "$nl$/icons/full/etool16/clear_co.gif"); //$NON-NLS-1$
        if (descriptor != null) {
            JFaceResources.getImageRegistry().put(CLEAR_ICON, descriptor);
        }
        descriptor = AbstractUIPlugin.imageDescriptorFromPlugin(
                PlatformUI.PLUGIN_ID, "$nl$/icons/full/dtool16/clear_co.gif"); //$NON-NLS-1$
        if (descriptor != null) {
            JFaceResources.getImageRegistry().put(DCLEAR_ICON, descriptor);
        }
    }
    GridViewerStyleControl styleControler;
   
    /**
	 * Create a new instance of the receiver.
	 * 
	 * @param parent
	 *            the parent <code>Composite</code>
	 * @param treeStyle
	 *            the style bits for the <code>Tree</code>
	 * @param filter
	 *            the filter to be used
	 */
	public FilteredGridTable(Composite parent, int treeStyle, HSGridTableFilter filter) {
		super(parent, SWT.NONE);
		this.parent = parent;
		init(treeStyle, filter);
	}
    
    /**
	 * Create a new instance of the receiver.
	 * 
	 * @param parent
	 *            the parent <code>Composite</code>
	 * @param treeStyle
	 *            the style bits for the <code>Tree</code>
	 * @param filter
	 *            the filter to be used
	 */
	public FilteredGridTable(Composite parent, int treeStyle, HSGridTableFilter filter,GridViewerStyleControl styleControler) {
		super(parent, SWT.NONE);
		this.parent = parent;
		this.styleControler = styleControler;
		init(treeStyle, filter);
	}
	
	/**
	 * Create a new instance of the receiver. Subclasses that wish to override
	 * the default creation behavior may use this constructor, but must ensure
	 * that the <code>init(composite, int, PatternFilter)</code> method is
	 * called in the overriding constructor.
	 * 
	 * @param parent
	 *            the parent <code>Composite</code>
	 * @see #init(int, PatternFilter)
	 * 
	 * @since 3.3
	 */
	protected FilteredGridTable(Composite parent) {
		super(parent, SWT.NONE);
		this.parent = parent;
	}

	/**
	 * Create the filtered tree.
	 * 
	 * @param treeStyle
	 *            the style bits for the <code>Tree</code>
	 * @param filter
	 *            the filter to be used
	 * 
	 * @since 3.3
	 */
	protected void init(int treeStyle, HSGridTableFilter filter) {
		patternFilter = filter;
		showFilterControls = PlatformUI.getPreferenceStore().getBoolean(
				IWorkbenchPreferenceConstants.SHOW_FILTERED_TEXTS);
		createControl(parent, treeStyle);
		createRefreshJob();
		setInitialText("输入过滤文本");
		setFont(parent.getFont());
	}

    /**
     * Create the filtered tree's controls.  
     * Subclasses should override.
     * 
     * @param parent
     * @param tableStyle
     */
    protected void createControl(Composite parent, int tableStyle){
    	GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		setLayout(layout);
		setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        if (showFilterControls){
        	filterComposite = new Composite(this, SWT.NONE);
            GridLayout filterLayout = new GridLayout(2, false);
            filterLayout.marginHeight = 0;
            filterLayout.marginWidth = 0;
            filterComposite.setLayout(filterLayout);
            filterComposite.setFont(parent.getFont());
            
        	createFilterControls(filterComposite);
        	filterComposite.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING,
					true, false));
        }
        
        tableComposite = new Composite(this, SWT.NONE);
        GridLayout tableCompositeLayout = new GridLayout();
        tableCompositeLayout.marginHeight = 0;
        tableCompositeLayout.marginWidth = 0;
		tableComposite.setLayout(tableCompositeLayout);
        GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
        tableComposite.setLayoutData(data);
        createTreeControl(tableComposite, tableStyle); 
    }
    
    /**
     * Create the filter controls.  By default, a text and corresponding tool bar 
     * button that clears the contents of the text is created.
     * Subclasses may override.  
     * 
     * @param parent parent <code>Composite</code> of the filter controls
     * @return the <code>Composite</code> that contains the filter controls
     */
    protected Composite createFilterControls(Composite parent){
        createFilterText(parent);
        createClearText(parent);
        if (filterToolBar != null) {
        filterToolBar.update(false);
        // initially there is no text to clear
        filterToolBar.getControl().setVisible(false);
        }
        return parent;
    }
    
    /**
	 * Creates and set up the tree and tree viewer. This method calls
	 * {@link #doCreateTableViewer(Composite, int)} to create the tree viewer.
	 * Subclasses should override {@link #doCreateTableViewer(Composite, int)}
	 * instead of overriding this method.
	 * 
	 * @param parent
	 *            parent <code>Composite</code>
	 * @param style
	 *            SWT style bits used to create the tree
	 * @return the tree
	 */
    protected Control createTreeControl(Composite parent, int style){
        tableViewer = doCreateTableViewer(parent, style);
        GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
        tableViewer.getControl().setLayoutData(data);
        tableViewer.getControl().addDisposeListener(new DisposeListener(){
        	/* (non-Javadoc)
        	 * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
        	 */
        	public void widgetDisposed(DisposeEvent e) {
        		refreshJob.cancel();
        	}
        });
        tableViewer.addFilter(patternFilter);  
        return tableViewer.getControl();
    }

	/**
	 * Creates the tree viewer.  Subclasses may override.
	 * 
	 * @param parent the parent composite
	 * @param style SWT style bits used to create the tree viewer
	 * @return the tree viewer
	 * 
	 * @since 3.3
	 */
	protected GridTableViewer doCreateTableViewer(Composite parent, int style) {
		return new GridTableViewer(createGrid(parent, style));
	}
	
	private Grid createGrid(Composite parent, int style) {
		if (styleControler != null) {
			return styleControler.createGrid(parent, style);
		}
		return new Grid(parent, style);
	}
    
    /**
     * Return the first item in the tree that matches the filter pattern.
     * 
     * @param items
     * @return the first matching TreeItem
     */
    private GridItem getFirstMatchingItem(GridTableViewer viewer, GridItem[] items){
		for (int i = 0; i < items.length; i++){
			if (patternFilter.select(viewer, null, items[i].getData())) {
				return items[i];
			}
		}
		return null;
    }
    
    /**
     * Create the refresh job for the receiver.
     *
     */
	private void createRefreshJob() {
		refreshJob = new WorkbenchJob("Refresh Filter"){//$NON-NLS-1$
			/* (non-Javadoc)
			 * @see org.eclipse.ui.progress.UIJob#runInUIThread(org.eclipse.core.runtime.IProgressMonitor)
			 */
			public IStatus runInUIThread(IProgressMonitor monitor) {
				if(tableViewer.getControl().isDisposed()) {
					return Status.CANCEL_STATUS;
				}
				
				String text = getFilterString();
				if (text == null) {
					return Status.OK_STATUS;
				}
				
		        boolean initial = initialText != null && initialText.equals(text); 
		        if (initial) {
		            patternFilter.setPattern(null);
		        } else if (text != null){
		            patternFilter.setPattern(text);
		        }
		        
		        Control redrawFalseControl = tableComposite != null ? tableComposite : tableViewer.getControl();
		        try {
			        // don't want the user to see updates that will be made to the tree
		        	// we are setting redraw(false) on the composite to avoid dancing scrollbar
		        	redrawFalseControl.setRedraw(false);
			        if (!narrowingDown) {
				        // collapse all
				        
			        }
			        tableViewer.refresh(true);
			       
			        if (text.length() > 0 && !initial) {
			        	/* Expand elements one at a time.  After each is expanded, check
			        	 * to see if the filter text has been modified.  If it has, then 
			        	 * cancel the refresh job so the user doesn't have to endure 
			        	 * expansion of all the nodes.
			        	 */								            
			            // enabled toolbar - there is text to clear
			            // and the list is currently being filtered		
			            updateToolbar(true);
			        } else {
						// disabled toolbar - there is no text to clear
				        // and the list is currently not filtered		        	
			        	updateToolbar(false);
			        }
	        	}
	        	finally {
			        
			        redrawFalseControl.setRedraw(true);
	        	}	
		        return Status.OK_STATUS;
			}			
		};
		refreshJob.setSystem(true);
	}

	protected void updateToolbar(boolean visible){
		if (filterToolBar != null) {
			filterToolBar.getControl().setVisible(visible);
		}
	}
	
	/**
	 * Creates the filter text and adds listeners. This method calls
	 * {@link #doCreateFilterText(Composite)} to create the text control.
	 * Subclasses should override {@link #doCreateFilterText(Composite)}
	 * instead of overriding this method.
	 * 
	 * @param parent <code>Composite</code> of the filter text
	 */
	protected void createFilterText(Composite parent) {
		filterText =  doCreateFilterText(parent);
		filterText.getAccessible().addAccessibleListener(
				new AccessibleAdapter(){
					/* (non-Javadoc)
					 * @see org.eclipse.swt.accessibility.AccessibleListener#getName(org.eclipse.swt.accessibility.AccessibleEvent)
					 */
					public void getName(AccessibleEvent e) {
						String filterTextString = filterText.getText();
						if(filterTextString.length() == 0){
							e.result = initialText;
						} else {
							e.result = filterTextString;
						}
					}
				});

		filterText.addFocusListener(
				new FocusAdapter(){
					/* (non-Javadoc)
					 * @see org.eclipse.swt.events.FocusListener#focusLost(org.eclipse.swt.events.FocusEvent)
					 */
					public void focusGained(FocusEvent e) {
						/* Running in an asyncExec because the selectAll() does not   
						 * appear to work when using mouse to give focus to text.
						 */
						Display display = filterText.getDisplay();
						display.asyncExec(new Runnable() {
		                    public void run() {
		                    	if (!filterText.isDisposed()){
									if (getInitialText().equals(filterText.getText().trim())){
										filterText.selectAll();
									}
		                    	}
		                    }
						});
					}
				});
		
        filterText.addKeyListener(new KeyAdapter() {
            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.events.KeyAdapter#keyReleased(org.eclipse.swt.events.KeyEvent)
             */
            public void keyPressed(KeyEvent e) {
            	// on a CR we want to transfer focus to the list
            	boolean hasItems = getViewer().getGrid().getItemCount() > 0;
            	if(hasItems && e.keyCode == SWT.ARROW_DOWN){
                    	tableViewer.getGrid().setFocus();
            	} else if (e.character == SWT.CR){
					return;
            	}
            }
        });
        
        // enter key set focus to tree
        filterText.addTraverseListener(new TraverseListener () {
			public void keyTraversed(TraverseEvent e) {
				if (e.detail == SWT.TRAVERSE_RETURN) {
					e.doit = false;
					if (getViewer().getGrid().getItemCount() == 0) {
						Display.getCurrent().beep();
					} else {
						// if the initial filter text hasn't changed, do not try to match
						boolean hasFocus = getViewer().getGrid().setFocus();
						boolean textChanged = !getInitialText().equals(
								filterText.getText().trim());
						if (hasFocus && textChanged
								&& filterText.getText().trim().length() > 0) {
							GridItem item = getFirstMatchingItem(tableViewer, getViewer().getGrid().getItems());
							if (item != null) {
								getViewer().getGrid().setSelection(new GridItem[] { item });
								ISelection sel = getViewer().getSelection();
								getViewer().setSelection(sel, true);
							}
						}						
					} 
				}
			}
		});
        
        filterText.addModifyListener(new ModifyListener(){
        	/* (non-Javadoc)
        	 * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
        	 */
        	public void modifyText(ModifyEvent e) {
        		textChanged();
        	}
        });

        GridData gridData = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
		// if the text widget supported cancel then it will have it's own
		// integrated button. We can take all of the space.
		if ((filterText.getStyle() & SWT.CANCEL) != 0) {
			gridData.horizontalSpan = 2;
			gridData.widthHint = 200;
		}
		filterText.setLayoutData(gridData);
	}

	/**
	 * Creates the text control for entering the filter text. Subclasses may
	 * override.
	 * 
	 * @param parent
	 *            the parent composite
	 * @return the text widget
	 * 
	 * @since 3.3
	 */
	protected Text doCreateFilterText(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		return toolkit.createText(parent, initialText, SWT.SINGLE | SWT.BORDER | SWT.SEARCH | SWT.CANCEL);
		//return new Text(parent, SWT.SINGLE | SWT.BORDER | SWT.SEARCH | SWT.CANCEL);
	}

	private String previousFilterText;

	private boolean narrowingDown;
	
	/**
     * Update the receiver after the text has changed.
     */
    protected void textChanged() {
    	narrowingDown = previousFilterText==null || getFilterString().startsWith(previousFilterText);
    	previousFilterText = getFilterString();
    	// cancel currently running job first, to prevent unnecessary redraw
    	refreshJob.cancel();
    	refreshJob.schedule(200);
    }

    /**
     * Set the background for the widgets that support the filter text area.
     * 
     * @param background background <code>Color</code> to set
     */
    public void setBackground(Color background) {
        super.setBackground(background);
        if (filterComposite != null) {
			filterComposite.setBackground(background);
		}
        if (filterToolBar != null && filterToolBar.getControl() != null) {
			filterToolBar.getControl().setBackground(background);
		}
    }

    /**
     * Create the button that clears the text.
     * 
     * @param parent parent <code>Composite</code> of toolbar button 
     */
    private void createClearText(Composite parent) {
    	// only create the button if the text widget doesn't support one natively
    	if ((filterText.getStyle() & SWT.CANCEL) == 0) {
			filterToolBar = new ToolBarManager(SWT.FLAT | SWT.HORIZONTAL);
			filterToolBar.createControl(parent);

			IAction clearTextAction = new Action("", IAction.AS_PUSH_BUTTON) {//$NON-NLS-1$
				/*
				 * (non-Javadoc)
				 * 
				 * @see org.eclipse.jface.action.Action#run()
				 */
				public void run() {
					clearText();
				}
			};

			clearTextAction
					.setToolTipText(WorkbenchMessages.FilteredTree_ClearToolTip);
			clearTextAction.setImageDescriptor(JFaceResources
					.getImageRegistry().getDescriptor(CLEAR_ICON));
			clearTextAction.setDisabledImageDescriptor(JFaceResources
					.getImageRegistry().getDescriptor(DCLEAR_ICON));

			filterToolBar.add(clearTextAction);
		}
    }

    /**
	 * Clears the text in the filter text widget. Also removes the optional
	 * additional filter that is provided via addFilter(ViewerFilter).
	 */
    protected void clearText() {
        setFilterText(""); //$NON-NLS-1$
        textChanged();
    }

    /**
     * Set the text in the filter control.
	 * @param string 
	 */
	protected void setFilterText(String string) {
		if (filterText != null){
			filterText.setText(string);
			selectAll();		
		}
	}

	/**
	 * Returns the pattern filter used by this tree.
	 * 
	 * @return The pattern filter; never <code>null</code>.
	 */
	public final HSGridTableFilter getPatternFilter() {
		return patternFilter;
	}
	
	/**
     * Get the tree viewer of the receiver.
     * 
     * @return the tree viewer
     */
    public GridTableViewer getViewer() {
        return tableViewer;
    }

    /**
     * Get the filter text for the receiver, if it was created. 
     * Otherwise return <code>null</code>.
     * 
     * @return the filter Text, or null if it was not created
     */
    public Text getFilterControl() {
        return filterText;
    }
    
    /**
     * Convenience method to return the text of the filter control.
     * If the text widget is not created, then null is returned.
     * 
     * @return String in the text, or null if the text does not exist
     */
    protected String getFilterString(){
    	return filterText != null ? filterText.getText() : null;
    }

    /**
     * Set the text that will be shown until the first focus.
     * A default value is provided, so this method only need be 
     * called if overriding the default initial text is desired.
     * 
     * @param text initial text to appear in text field
     */
    public void setInitialText(String text) {
        initialText = text;
    	setFilterText(initialText);
        textChanged();
    }

    /**
     * Select all text in the filter text field.
     *
     */
	protected void selectAll() {
		if (filterText != null) {
			filterText.selectAll();
		}
	}

	/**
	 * Get the initial text for the receiver.
	 * @return String
	 */
	protected String getInitialText() {
		return initialText;
	}

	/**
	 * Return a bold font if the given element matches the given pattern.
	 * Clients can opt to call this method from a Viewer's label provider to get
	 * a bold font for which to highlight the given element in the tree.
	 * 
	 * @param element
	 *            element for which a match should be determined
	 * @param table
	 *            FilteredTree in which the element resides
	 * @param filter
	 *            PatternFilter which determines a match
	 * 
	 * @return bold font
	 */
	public static Font getBoldFont(Object element, FilteredGridTable table,
			PatternFilter filter) {
		String filterText = table.getFilterString();

		if (filterText == null) {
			return null;
		}
		
		// Do nothing if it's empty string
		String initialText = table.getInitialText();
		if (!("".equals(filterText) || initialText.equals(filterText))) {//$NON-NLS-1$
			boolean initial = initialText != null
					&& initialText.equals(filterText);
			if (initial) {
				filter.setPattern(null);
			} else if (filterText != null){
				filter.setPattern(filterText);
			}

			if (filter.isElementVisible(table.getViewer(), element)) {
				return JFaceResources.getFontRegistry().getBold(
						JFaceResources.DIALOG_FONT);
			}
		}
		return null;
	}
	

}
