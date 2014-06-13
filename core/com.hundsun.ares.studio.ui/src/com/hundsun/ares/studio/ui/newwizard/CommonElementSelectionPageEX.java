/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.newwizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.ui.AresElementComparater;
import com.hundsun.ares.studio.ui.AresModelViewer;

/**
 * 只包含选择元素的树的向导页面
 * @author lvgao
 */
public class CommonElementSelectionPageEX extends WizardPage implements IAresWizardPage {
	
	public static final String CONTEXT_KEY_SELECTION = "选择的项";
	
	protected Object selection;
	protected IWorkbench workbench;
	protected TreeViewer viewer;
	
	/**
	 * 错误检查器列表
	 */
	protected List<IWizardPageValidator> validators = new ArrayList<IWizardPageValidator>();

	public CommonElementSelectionPageEX(String pageName, IWorkbench workbench, IARESElement selection) {
		super(pageName);
		if (selection != null && selection.exists())
			this.selection = selection;
		this.workbench = workbench;
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		
		createTreeViewer(composite);
		
		addFilter();
		//错误检查
		addValidators(validators);
		
		setControl(composite);
		setPageComplete(false);
		updateComplete();
	}
	
	
	protected void createTreeViewer(Composite composite) {
		
		
		GridLayoutFactory.fillDefaults().applyTo(composite);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		viewer = new AresModelViewer(composite, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		viewer.getTree().setLayoutData(gd);

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				if (event.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection selection = (IStructuredSelection) event.getSelectionProvider().getSelection();
					if (selection.getFirstElement() == null) {
						selection = null;
						return;
					}
					if (selection.getFirstElement() instanceof IARESElement) {
						CommonElementSelectionPageEX.this.selection = (IARESElement) selection.getFirstElement();
					} else if (selection.getFirstElement() instanceof IResource){
						CommonElementSelectionPageEX.this.selection = ARESCore.create(((IResource) selection.getFirstElement()).getProject());
					} else {
						CommonElementSelectionPageEX.this.selection = selection.getFirstElement();
					}
				}
				updateComplete();
			}
		});

		viewer.setInput(ARESCore.getModel());
		viewer.setComparator(new AresElementComparater());

		if (selection != null) {
			viewer.setSelection(new StructuredSelection(selection), true);
		}
	}
	
	
	
	/**
	 * 在树上选中对象
	 * @param target
	 */
	public void select(Object target) {
		viewer.setSelection(new StructuredSelection(target), true);
	}
	
	/**
	 * 添加过滤器
	 */
	protected void addFilter(){
		if(viewer != null){
			viewer.addFilter(new ElementTypeFilter());
		}
	}
	
	/**
	 * 添加错误检查器
	 * @param validators
	 */
	protected void addValidators(List<IWizardPageValidator> validators) {
		validators.add(new ElementSelectionValidator());
	}

	protected void updateComplete() {
		if (!validate()) {
			setPageComplete(false);
		} else {
			setPageComplete(true);
		}
	}
	

	/**
	 * 返回要用来选择的资源类型; 二位数组，第一列类型第二列名字。<br>
	 * 如果返回的是null，则认为任何选择均有效/合法
	 */
	protected String[][] getSelctingElementTypes() {
		return null;
	}

	protected boolean inModule() {
		if (selection instanceof IARESModule) {
			return true;
		}
		return false;
	}

	public IARESElement getSelectedElement() {
		if (this.selection instanceof IARESElement) 
			return ((IARESElement)selection);
		else 
			return null;
	}
	
	
	public void setContext(Map<Object, Object> context) {
		context.put(CONTEXT_KEY_SELECTION, selection);
	}

	private boolean validate() {		
		setErrorMessage(null);
		Map<Object, Object> context = new HashMap<Object, Object>();
		setContext(context);
		for(IWizardPageValidator item:validators){
			IStatus status = item.validate(context);
			if(!status.isOK()){
				setErrorMessage(status.getMessage());
				return false;
			}
		}
		return true;
	}
	
}


