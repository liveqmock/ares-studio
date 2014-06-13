package com.hundsun.ares.studio.ui.editor.wizard;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.ui.ARESElementSorter;
import com.hundsun.ares.studio.ui.ARESResourceCategory;
import com.hundsun.ares.studio.ui.CommonElementContentProvider;
import com.hundsun.ares.studio.ui.CommonElementLabelProvider;
import com.hundsun.ares.studio.ui.control.CheckboxTreeViewerEx;
import com.hundsun.ares.studio.ui.editor.blocks.BatchAddModifyLogBlock;

public class BatchAddModifyLogPage extends WizardPage {

	private CheckboxTreeViewerEx selectViewer;
	IARESProject project;
	List<RevisionHistory>viewerInput = new ArrayList<RevisionHistory>();
	List<Object>resources = new ArrayList<Object>();
	
	protected BatchAddModifyLogPage(String pageName,IARESProject project) {
		super(pageName);
		this.project = project;
	}

	@Override
	public void createControl(Composite parent) {
		SashForm client = new SashForm(parent, SWT.VERTICAL);
		
		FormToolkit toolkit = new FormToolkit(client.getDisplay());
		
		Composite top = toolkit.createComposite(client,SWT.BORDER);
		BatchAddModifyLogBlock block = new BatchAddModifyLogBlock();
		block.setProject(project);
		block.createControl(top, toolkit);
		block.setInput(viewerInput);

		selectViewer = new CheckboxTreeViewerEx(client);

		CommonElementContentProvider cp = new CommonElementContentProvider();
	    selectViewer.setContentProvider(cp);
	    selectViewer.setLabelProvider(new CommonElementLabelProvider(cp));
	    selectViewer.setInput(project);
	    selectViewer.setFilters(new ViewerFilter[] { new ModifyLogFilter() });
	    selectViewer.setSorter(new ARESElementSorter());
		
	    //布局
	    GridLayoutFactory.swtDefaults().numColumns(1).applyTo(client);
	    GridLayoutFactory.swtDefaults().numColumns(2).applyTo(top);
	    GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.TOP).grab(false, true).span(1, 1).applyTo(top);
	    GridDataFactory.fillDefaults().grab(true, true).applyTo(block.getControl());
	    GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.TOP).grab(false, false).span(2, 1).applyTo(selectViewer.getControl());

	    setControl(client);
	    setTitle("批量添加修改记录");
	    setDescription("选择需要添加修改记录的资源然后添加");
	    
	    
	    selectViewer.addCheckStateListener(new ICheckStateListener() {
			
			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				refreshFinishState();
			}
		});
	    refreshFinishState();
	}
	
	protected void refreshFinishState() {
		resources.clear();
		Object[] selections = selectViewer.getCheckedElements();
		for(Object sel : selections){
			if(sel instanceof IARESModule){
				IARESModule tModule = (IARESModule)sel;
				try {
					if(tModule.getSubModules().length == 0){
						resources.add(tModule);
					}
				} catch (ARESModelException e) {
					e.printStackTrace();
				}
			}
			if(sel instanceof IARESResource){
				resources.add((IARESResource) sel);
			}
		}
		if(resources.size()>0){
			setPageComplete(true);
		}else{
			setPageComplete(false);
		}
	}


	
	
	public List<RevisionHistory> getViewerInput() {
		return viewerInput;
	}

	public List<Object> getSelection() {
		return resources;
	}



	class ModifyLogFilter extends ViewerFilter{

		@Override
		public boolean select(Viewer viewer, Object parentElement,Object element) {
			if(element instanceof IARESModuleRoot){
				String type = ((IARESModuleRoot) element).getType();
				if(StringUtils.equals(type, "com.hundsun.ares.studio.jres.moduleroot.tools") || 
						StringUtils.equals(type, "com.hundsun.ares.studio.jres.others")){
					return false;
				}
				return true;
			}else if(element instanceof IARESModule){
				return true;
			}else if(element instanceof ARESResourceCategory){
				return false;
			}
			else if(element instanceof IARESResource){
				IARESResource res =  ((IARESResource) element);
				//元数据资源
				if(res.getModule().getElementName().equals(IARESModule.DEFAULT_MODULE_NAME)){
					return true;
				}
			}
			return false;
		}
		
	}
}
