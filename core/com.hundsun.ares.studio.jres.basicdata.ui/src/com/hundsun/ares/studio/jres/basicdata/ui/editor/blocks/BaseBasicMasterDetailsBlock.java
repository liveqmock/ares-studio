/**
 * 源程序名称：JresBasicMasterDetailsBlock.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.basicdata.ui.editor.blocks;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.core.util.StringUtil;
import com.hundsun.ares.studio.ui.editor.EMFFormPage;
import com.hundsun.ares.studio.ui.editor.IEMFFormPage;
import com.hundsun.ares.studio.ui.editor.blocks.JresDetailsPage;

/**
 * @author lvgao
 *
 */
public abstract class BaseBasicMasterDetailsBlock  extends MasterDetailsBlock {
	protected EMFFormPage page;
	protected Viewer viewer;
	public BaseBasicMasterDetailsBlock(EMFFormPage page) {
		this.page = page;
	}
	
	@Override
	protected void createMasterPart(final IManagedForm managedForm, Composite parent) {		
		FormToolkit toolkit = managedForm.getToolkit();
		Section section = toolkit.createSection(parent, Section.TITLE_BAR);
		section.setText(StringUtil.getStringSafely(getSectionName()));
		
		Composite client = toolkit.createComposite(section, SWT.WRAP);
		section.setDescription(StringUtils.defaultString(getDescription()));
		final SectionPart sPart = new SectionPart(section);
		managedForm.addPart(sPart);
		
		Composite tableArea = toolkit.createComposite(client, SWT.NONE);
		Composite buttonArea = toolkit.createComposite(client, SWT.NONE);
		
		viewer = createViewer(tableArea,managedForm,page);
	
		viewer.addSelectionChangedListener(new ISelectionChangedListener(){
			public void selectionChanged(SelectionChangedEvent event) {
				managedForm.fireSelectionChanged(sPart, event.getSelection());
				//viewer.refresh();
			}
		});

		createButtons(buttonArea);
		
		createMenu(viewer);
		//布局
		buttonArea.setLayout(new GridLayout(1,true));
		tableArea.setLayout(new GridLayout(1,true));
		
		GridDataFactory.swtDefaults().grab(true, false).applyTo(section);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(client);
		GridDataFactory.fillDefaults().hint(100,350).grab(true, true).applyTo(viewer.getControl());
		GridDataFactory.fillDefaults().grab(true, true).applyTo(tableArea);
		GridDataFactory.swtDefaults().grab(false, false).align(SWT.FILL, SWT.BEGINNING).applyTo(buttonArea);
//		viewer.getControl().setLayoutData(new GridData(GridData.FILL_BOTH));
//		GridLayoutFactory.fillDefaults().applyTo((Composite) viewer.getControl());
		
//		setMasterLayout(client);
		
		section.setClient(client);
	}
	
	/**
	 * 设置master界面布局
	 */
	protected void setMasterLayout(Composite client) {
		client.setLayout(new GridLayout());
	}
	
	/**
	 * 创建右键菜单
	 * @param client
	 */
	protected void createMenu(Viewer viewer) {
		MenuManager menuManager = new MenuManager("#popup");
		menuManager.addMenuListener(new IMenuListener() {
			
			@Override
			public void menuAboutToShow(IMenuManager manager) {
				manager.removeAll();
				createMenus(manager);
			}
		});

		Menu menu = menuManager.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
	}

	/**
	 * 创建右键菜单
	 * @param manager
	 */
	protected void createMenus(IMenuManager manager) {
		
	}

	/**
	 * 创建右边按钮
	 * @param client
	 */
	protected void createButtons(Composite client) {
		
	}

	@Override
	protected void registerPages(DetailsPart detailsPart) {
		for(Object type:getElementTypes()){
			IDetailsPage page = getDetailPage(type);
			if(this.page != null && page != null){
				if(page instanceof JresDetailsPage){
					((JresDetailsPage)page).setPage(this.page);
				}
				detailsPart.registerPage(type, page);
			}
		}
	}
	
	@Override
	public void createContent(IManagedForm managedForm) {
		super.createContent(managedForm);
		ScrolledForm form = managedForm.getForm();
		managedForm.getToolkit().decorateFormHeading(form.getForm());
		form.setText(getPageHeadName());
		form.setImage(getPageHeadImage());
		managedForm.getForm().getBody().setLayout(new GridLayout(1,false));
		
	}
	
	/**
	 * 获取页面的标题图片
	 * @return
	 */
	protected Image getPageHeadImage(){
		return null;
	}

	/**
	 * 获取页面的标题
	 * @return
	 */
	protected abstract String getPageHeadName();
	/**
	 * 获取左边section的标题
	 * @return
	 */
	protected abstract String getSectionName();
	/**
	 * 获取左边section的描述
	 * @return
	 */
	protected abstract String getDescription();
	/**
	 * 获取所有需要关联detial页面的类型
	 * @return
	 */
	protected abstract Object[] getElementTypes();
	/**
	 * 获取某个特定类型的detial页面
	 * @param type
	 * @return
	 */
	protected abstract IDetailsPage getDetailPage(Object type);
	/**
	 * 获取右边的视图
	 * @param client
	 * @param managedForm
	 * @param page
	 * @return
	 */
	protected abstract Viewer createViewer(Composite client,final IManagedForm managedForm,IEMFFormPage page);

}
