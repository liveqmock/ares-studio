/**
 * 源程序名称：GenSQLSelectPage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.wizard.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.ui.ARESElementSorter;
import com.hundsun.ares.studio.ui.CommonElementContentProvider;
import com.hundsun.ares.studio.ui.CommonElementLabelProvider;
import com.hundsun.ares.studio.ui.control.CheckboxTreeViewerEx;

/**
 * @author wangxh
 *
 */
public class GenSQLSelectPage extends WizardPage {

	/**选择列表*/
	CheckboxTreeViewerEx viewer;
	/**右键选中的元素*/
	ISelection select;
	/**生成的路径*/
	String path;
	/**路径*/
	Text txtPath;
	
	CommonElementContentProvider cp;
	
	List<IARESResource>result = new ArrayList<IARESResource>();
	/**
	 * @param pageName
	 */
	public GenSQLSelectPage(String pageName,ISelection select) {
		super(pageName);
		this.select = select;
		setTitle(pageName);
		setDescription("请选择要生成的对象和路径");
	}

	private IARESElement[] getCheckedElements(Object[] selected) {
		Set<IARESElement> checkedElements = new HashSet<IARESElement>();
		
		Stack<Object> selectElements = new Stack<Object>();
		Collections.addAll(selectElements, selected);
		try {
			while (!selectElements.isEmpty()) {
				Object element = selectElements.pop();
				if (element instanceof IARESModuleRoot) {
					Collections.addAll(selectElements, ((IARESModuleRoot) element).getChildren());
				} else if (element instanceof IARESModule) {
					Collections.addAll(selectElements, ((IARESModule) element).getSubModules());
					Collections.addAll(checkedElements, ((IARESModule) element).getChildren());
					checkedElements.add((IARESModule) element);
				} else if (element instanceof IARESResource) {
					checkedElements.add((IARESResource) element);
				}
			}
		} catch (ARESModelException e) {
			// do nothing
		}
		
		return checkedElements.toArray(new IARESElement[checkedElements.size()]);
	}
	
	private IARESModuleRoot getModuleRoot(IARESElement element) {
		if (element instanceof IARESModuleRoot) {
			return (IARESModuleRoot) element;
		} else if (element instanceof IARESModule) {
			return ((IARESModule) element).getRoot();
		} else if (element instanceof IARESResource) {
			return ((IARESResource) element).getRoot();
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {

		Composite client = new Composite(parent,SWT.None);
		Group viewerGroup = createResouceTreeviewer(client);
		Group buttonGroup = createButtons(client);
		
		// 将选择的元素转换为树中选中的元素
		IARESElement[] elements = getCheckedElements(((IStructuredSelection)select).toArray());
		
		IARESModuleRoot root = getModuleRoot(elements[0]);
		Assert.isNotNull(root);
		
		viewer.setInput(root);
		
		viewer.setCheckedElements(elements);

		viewer.setFilters(new ViewerFilter[]{new DBFilter()});
		
		Group pathGroup = new Group(client, SWT.None);
		pathGroup.setText("选择生成路径");
		pathGroup.setLayout(new GridLayout(2, false));
		
		txtPath = new Text(pathGroup, SWT.BORDER);
		Button btnPath = new Button(pathGroup, SWT.None);
		btnPath.setText("选择路径");
		
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(client);
		GridDataFactory.fillDefaults().hint(200, 300).grab(true, true).applyTo(viewerGroup);
		GridDataFactory.fillDefaults().grab(false, true).hint(80, -1).applyTo(buttonGroup);
		GridDataFactory.fillDefaults().span(2, -1).grab(true, false).applyTo(pathGroup);
		
		GridDataFactory.fillDefaults().grab(true, false).applyTo(txtPath);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(btnPath);
		
		setControl(client);
		UpdatePageComplete();
		
		btnPath.addSelectionListener(new SelectionAdapter() {

			/* (non-Javadoc)
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(getShell());
				String path = dialog.open();
				if(StringUtils.isNotBlank(path)){
					txtPath.setText(path);
				}
			}
			
		});
		
		txtPath.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				path = txtPath.getText();
				UpdatePageComplete();
			}
		});
	}

	
	private void setAllChecked(final boolean state) {
		BusyIndicator.showWhile(viewer.getTree().getShell().getDisplay(), 
				new Runnable(){

					@Override
					public void run() {
						for (Object obj : cp.getElements(viewer.getInput())) {
							viewer.checkChange(new CheckStateChangedEvent(viewer, obj, state));
						}
					}
			
		});
		
	}
	
	
	/**
	 * @param client
	 * @return
	 */
	private Group createButtons(Composite client) {
		Group buttonGroup = new Group(client, SWT.None);
		Button btnSelectAll = new Button(buttonGroup, SWT.PUSH);
		btnSelectAll.setText("全选");
		btnSelectAll.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				setAllChecked(true);
				UpdatePageComplete();
			}});
		
		Button btnDeselectAll = new Button(buttonGroup, SWT.PUSH);
		btnDeselectAll.setText("取消全选");
		btnDeselectAll.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				setAllChecked(false);
				UpdatePageComplete();
			}});
		
		buttonGroup.setLayout(new GridLayout(1, true));
		
		GridDataFactory.fillDefaults().grab(true, false).applyTo(btnSelectAll);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(btnDeselectAll);
		return buttonGroup;
	}


	/**
	 * @param client
	 * @return
	 */
	private Group createResouceTreeviewer(Composite client) {
		Group viewerGroup = new Group(client, SWT.None);
		viewerGroup.setText("选择资源");
		viewerGroup.setLayout(new GridLayout(4,false));
		viewerGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		viewer = new CheckboxTreeViewerEx(viewerGroup, SWT.CHECK |SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		 cp= new CommonElementContentProvider(){

			/* (non-Javadoc)
			 * @see com.hundsun.ares.studio.ui.CommonElementContentProvider#getModuleChildren(com.hundsun.ares.studio.core.IARESModule)
			 */
			@Override
			protected Object[] getModuleChildren(IARESModule module)
					throws ARESModelException {
				List<Object> list= new ArrayList<Object>();
				list.addAll(Arrays.asList(module.getARESResources()));
				list.addAll(Arrays.asList(super.getModuleChildren(module)));
				return list.toArray(new Object[0]);
			}

			/* (non-Javadoc)
			 * @see com.hundsun.ares.studio.ui.ARESElementContentProvider#getElements(java.lang.Object)
			 */
			@Override
			public Object[] getElements(Object element) {
				if(input instanceof IARESResource){
					return new Object[]{input};
				}
				return super.getElements(element);
			}
			
			

		};
		viewer.setContentProvider(cp);
		viewer.setLabelProvider(new CommonElementLabelProvider(cp));
		viewer.setComparator(new ARESElementSorter());
		viewer.setUseHashlookup(true);
		
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 4;
		gd.widthHint = 10;
		viewer.getTree().setLayoutData(gd);

		viewer.addCheckStateListener(new ICheckStateListener(){

			public void checkStateChanged(
					final CheckStateChangedEvent event) {
				UpdatePageComplete();  //更新页面是否完成
			}});
		return viewerGroup;
	}

	private void UpdatePageComplete() {
		result = getSelectedResouces();
		
		if(result.size() > 0 && StringUtils.isNotBlank(path)){		
			setPageComplete(true);
		}else{
			setPageComplete(false);
		}
	}
	
	/**
	 * 获取被选中的资源
	 * @return
	 */
	private List<IARESResource> getSelectedResouces(){
		List<IARESResource> resources = new ArrayList<IARESResource>();
		Object obj[] = viewer.getCheckedElements();
		for (Object object : obj) {
			if (object instanceof IARESResource) {
				resources.add((IARESResource) object);
			}
		}
		return resources;
	}
	
	
	/**
	 * 获取最终选中的资源
	 * @return 
	 */
	public List<IARESResource> getResult() {
		return result;
	}

	/**
	 * 获取路径
	 * @return the path
	 */
	public String getPath() {
		return path;
	}


	class DBFilter extends ViewerFilter {

		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			 if (element instanceof IARESModule) {
				return true;
			} else if ((element instanceof IARESResource)){
				if(StringUtils.equals("module.xml", ((IARESResource)element).getElementName())){
					return false;
				}
				return true;
			}
			return false;
		}
	}
}
