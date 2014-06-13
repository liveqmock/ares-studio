/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.refactoring;

import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.ltk.core.refactoring.Refactoring;
import org.eclipse.ltk.core.refactoring.participants.MoveRefactoring;
import org.eclipse.ltk.core.refactoring.participants.ProcessorBasedRefactoring;
import org.eclipse.ltk.ui.refactoring.UserInputWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.ui.AresModelViewer;
import com.hundsun.ares.studio.ui.AresResourceCategoryFilter;
import com.hundsun.ares.studio.ui.AresResourceFilter;
import com.hundsun.ares.studio.ui.LibFilter;

/**
 * 
 * @author sundl
 */
public class MoveAresElementWizardPage extends UserInputWizardPage {

	private TreeViewer viewer;
	private MoveRefactoring refactoring;
	
	public MoveAresElementWizardPage(String name,MoveRefactoring refactoring) {
		super(name);
		this.refactoring  = refactoring;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		initializeDialogUnits(parent);

		Composite composite= new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.minimumHeight = 200;
		composite.setLayoutData(gd);
		composite.setFont(parent.getFont());

		Label label= new Label(composite, SWT.NONE);
		label.setText("选择目标位置:");
		label.setLayoutData(new GridData());
		
		viewer = new AresModelViewer(composite, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		gd = new GridData(GridData.FILL_BOTH);
		gd.minimumHeight = 200;
		gd.widthHint = 260;
		viewer.getTree().setLayoutData(gd);
		viewer.addFilter(new AresResourceCategoryFilter());
		final Object[] elements = refactoring.getMoveProcessor().getElements();
		if (checkResourceOrModel(elements)) {
			viewer.addFilter(new AresResourceFilter(){
				@Override
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					for(Object obj : elements){
						if (obj instanceof IARESModule) {
							if (obj.equals(element)) {
								return false;
							}
						}
					}
					if (element instanceof IARESResource) {
						return false;
					}
					return true;
				}
				
			});
		}else {
			viewer.addFilter(new AresResourceFilter());
		}

		viewer.addFilter(new LibFilter());
		viewer.setInput(getProject());
		
		if (getDestination() != null)
			viewer.setSelection(new StructuredSelection(getDestination()), true);
		else
			viewer.setSelection(new StructuredSelection(getParent()), true);
		
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				updateSelection();
			}
		});
		
		setControl(composite);
		updateSelection();
	}
	
	private boolean checkResourceOrModel(Object[] objects){
		boolean status = false;
		for (Object object :objects) {
			if (object instanceof IARESModule) {
				status = true; 
				break;
			}
		}
		return status;
	}
	
	/**
	 * 取element节点的最上层moduleRoot节点
	 * 
	 * @param element
	 * @return
	 */
	private IARESElement getParentModuleRoot(IARESElement element){
		if(element instanceof IARESModuleRoot){
			return element;
		}
		element = element.getParent();
		if (element == null) {
			return null;
		}
		return getParentModuleRoot(element);
	}
	
	protected void updateSelection() {
		IStructuredSelection ss = (IStructuredSelection) viewer.getSelection();
		Object obj = ss.getFirstElement();
		setPageComplete(getProcessor().verifyDestination(obj)); 
	}
	
	@Override
	protected boolean performFinish() {
		IStructuredSelection ss = (IStructuredSelection) viewer.getSelection();
		Object obj = ss.getFirstElement();
		getProcessor().setDestination(obj);
		return super.performFinish();
	}
	
	public IWizardPage getNextPage() {
		IStructuredSelection ss = (IStructuredSelection) viewer.getSelection();
		Object obj = ss.getFirstElement();
		getProcessor().setDestination(obj);
		return super.getNextPage();
	}

	// 目前只能单项目内移动，所以总有一个项目
	private IARESProject getProject() {
		ARESMoveProcessor processor = getProcessor();
		List<IARESElement> elements = processor.getElementsToMove();
		return ARESElementUtil.getParent(elements.toArray(new IARESElement[0])).getARESProject();
	}
	
	private Object getDestination() {
		ARESMoveProcessor processor = getProcessor();
		return processor.getDestination();
	}
	
	private IARESElement getParent() {
		ARESMoveProcessor processor = getProcessor();
		List<IARESElement> elements = processor.getElementsToMove();
		return ARESElementUtil.getParent(elements.toArray(new IARESElement[0]));
	}
	
	private ARESMoveProcessor getProcessor() {
		Refactoring refactoring = getRefactoring();
		if (refactoring instanceof ProcessorBasedRefactoring) {
			return (ARESMoveProcessor) ((ProcessorBasedRefactoring)refactoring).getProcessor();
		}
		return null;
	}
	
}
