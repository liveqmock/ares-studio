/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.page.masterdetail;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.core.util.StringUtil;
import com.hundsun.ares.studio.ui.page.FromPageWithMyDirtySystem;

public abstract class AresBasicMasterDetailsBlock extends MasterDetailsBlock {
	protected FromPageWithMyDirtySystem page;
	protected Viewer viewer;
	public AresBasicMasterDetailsBlock(FromPageWithMyDirtySystem page) {
		this.page = page;
	}
	
	@Override
	protected void createMasterPart(final IManagedForm managedForm, Composite parent) {		
		FormToolkit toolkit = managedForm.getToolkit();
		Section section = toolkit.createSection(parent, Section.TITLE_BAR);
		section.setText(StringUtil.getStringSafely(getSectionName()));
		
		Composite client = toolkit.createComposite(section, SWT.WRAP);
		client.setLayout(new GridLayout());
		toolkit.createLabel(client, StringUtil.getStringSafely(getDescription()));
		
		final SectionPart sPart = new SectionPart(section);
		managedForm.addPart(sPart);
		
		viewer = createViewer(client,managedForm,page);
		viewer.getControl().setLayoutData(new GridData(GridData.FILL_BOTH));
		viewer.addSelectionChangedListener(new ISelectionChangedListener(){
			public void selectionChanged(SelectionChangedEvent event) {
				managedForm.fireSelectionChanged(sPart, event.getSelection());
				viewer.refresh();
			}
		});

		section.setClient(client);
	}
	
	@Override
	protected void registerPages(DetailsPart detailsPart) {
		for(Object type:getElementTypes()){
			IDetailsPage page = getDetailPage(type);
			if(this.page != null && page != null){
				if(page instanceof AresDetailsPage){
					((AresDetailsPage)page).setPage(this.page);
				}
				detailsPart.registerPage(type, page);
			}
		}
	}
	
	@Override
	public void createContent(IManagedForm managedForm) {
		super.createContent(managedForm);
//		ScrolledForm form = managedForm.getForm();
//		managedForm.getToolkit().decorateFormHeading(form.getForm());
//		form.setText(getPageHeadName());
//		form.setImage(getPageHeadImage());
//		managedForm.getForm().getBody().setLayout(FormLayoutFactory.createFormGridLayout(false, 1));
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
	protected abstract Viewer createViewer(Composite client,final IManagedForm managedForm,FromPageWithMyDirtySystem page);

}
