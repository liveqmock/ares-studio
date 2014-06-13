/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.page.masterdetail;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.core.util.StringUtil;
import com.hundsun.ares.studio.ui.control.ControlWithShowControl;
import com.hundsun.ares.studio.ui.page.FromPageWithMyDirtySystem;

public abstract class AresDetailsPage implements IDetailsPage {

	protected FromPageWithMyDirtySystem page;
	protected IManagedForm form;
	protected Section detail;
	protected Object input;
	
	public void setPage(FromPageWithMyDirtySystem page) {
		this.page = page;
	}
	
	public void createContents(Composite parent) {
		parent.setLayout(new FillLayout());
		detail = form.getToolkit().createSection(parent, Section.TITLE_BAR);
		detail.setText(StringUtil.getStringSafely(getSectionName()));

		GridLayout groupLayout = new GridLayout(2, false);
		detail.setLayout(groupLayout);

		Composite client = form.getToolkit().createComposite(detail, SWT.WRAP);
		client.setLayout(getSectionLayout());

		Label descLabel = form.getToolkit().createLabel(client, StringUtil.getStringSafely(getDescription()));
		descLabel.setLayoutData(new GridData(SWT.BEGINNING,SWT.TOP,false,false,2,1));
		
		createSectionContents(client);

		detail.setClient(client);
		// 边框
		form.getToolkit().paintBordersFor(client);
	}
	
	

	public void initialize(IManagedForm form) {
		this.form = form;
	}

	public boolean isDirty() {
		return false;
	}

	public boolean isStale() {
		// TODO Auto-generated method stub
		return false;
	}

	Layout getSectionLayout(){
		return new GridLayout(2, false);
	}
	
	public void commit(boolean onSave) {
		// TODO Auto-generated method stub
		
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public void setFocus() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * 获取detail的标题
	 * @return
	 */
	protected abstract String getSectionName();
	/**
	 * 获取detail的描述信息
	 * @return
	 */
	protected abstract String getDescription();
	/**
	 * 创建section的内容
	 * @param client
	 */
	protected abstract void createSectionContents(Composite client);
	
	/**
	 * 控件列表
	 */
	final protected List<ControlWithShowControl> contrllist = new ArrayList<ControlWithShowControl>();
	
	public void refresh() {
		for(ControlWithShowControl c : this.contrllist){
			c.removeModifyListener();
			c.syncControl();
			c.addModifyListener();
		}
	}

	public boolean setFormInput(Object input) {
		try {
			this.input = input;
			for(ControlWithShowControl c : this.contrllist){
				if(c.getRefleckHelper() != null){
					c.getRefleckHelper().setModel(input);
				}
			}
			refresh();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void selectionChanged(IFormPart part, ISelection selection) {
		Object sel = ((StructuredSelection)selection).getFirstElement();
		setFormInput(sel);
	}
	
}
