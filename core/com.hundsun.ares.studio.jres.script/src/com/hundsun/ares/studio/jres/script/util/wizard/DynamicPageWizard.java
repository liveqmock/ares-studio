/**
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.script.util.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;

/**
 * 动态向导基类(完善后可放入框架层)
 * @author liaogc
 * 
 */
public class DynamicPageWizard extends Wizard {

	protected List<IWizardPage> pages = new ArrayList<IWizardPage>();
	protected ImageDescriptor defaultImageDescriptor = JFaceResources
			.getImageRegistry().getDescriptor(DEFAULT_IMAGE);
	protected boolean forcePreviousAndNextButtons;

	/**
	 * 构造函数，创建一个空的向导
	 */
	protected DynamicPageWizard() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.wizard.Wizard#addPage(org.eclipse.jface.wizard.IWizardPage
	 * )
	 */
	public void addPage(IWizardPage page) {
		pages.add(page);
		page.setWizard(this);
	}

	/**
	 * 在指定的向导页前插入向导页
	 * 
	 * @param page
	 * @param nextPage
	 * @return
	 */
	public boolean addPage(IWizardPage page, IWizardPage nextPage) {
		for (int i = 0; i < pages.size(); i++) {
			if (pages.get(i) == nextPage) {
				return addPage(page, i);
			}
		}
		return false;
	}

	/**
	 * 在指定的位置插入向导页
	 * 
	 * @param page
	 * @param location
	 */
	public boolean addPage(IWizardPage page, int location) {
		// Invalid location
		if (location < 0 || location > pages.size())
			return false;

		// Create the new page list
		List<IWizardPage> newPages = new ArrayList<IWizardPage>();
		for (int i = 0; i < location; i++) {
			newPages.add(pages.get(i));
		}

		page.setWizard(this);
		newPages.add(page);

		for (int i = location; i < pages.size(); i++) {
			newPages.add(pages.get(i));
		}

		// Set the relationship
		if (location != pages.size())
			((IWizardPage) newPages.get(location + 1)).setPreviousPage(page);

		((IWizardPage) page).setPreviousPage((IWizardPage) newPages
				.get(location - 1));
		pages = newPages;
		return true;
	}

	/**
	 * 删除指定位置的向导页
	 * 
	 * @param number
	 */
	public void removePage(int number) {
		if (number < 0)
			return;
		if (number > pages.size() - 1)
			return;

		if (number == 0)
			pages.remove(0);
		else if (number == pages.size() - 1)
			pages.remove(number);
		else {
			IWizardPage wizarPage = (IWizardPage) pages.get(number + 1);
			wizarPage.setPreviousPage((IWizardPage) pages.get(number - 1));
			pages.remove(number);
		}
	}

	/**
	 * 删除指定的向导页
	 * 
	 * @param page
	 */
	public void removePage(IWizardPage page) {
		int number = -1;
		for (int i = 0; i < pages.size(); i++) {
			if (pages.get(i) == page)
				number = i;
		}

		removePage(number);
	}

	/**
	 * 删除向导中某种类名的所有向导页
	 * 
	 * @param number
	 */
	public void removePage(String className) {
		for (int i = 0; i < pages.size(); i++) {
			if (pages.get(i).getClass().getCanonicalName()
					.equalsIgnoreCase(className))
				removePage(i);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#createPageControls
	 * (org.eclipse.swt.widgets.Composite)
	 */
	public void createPageControls(Composite pageContainer) {

		// the default behavior is to create all the pages controls
		for (int i = 0; i < pages.size(); i++) {
			IWizardPage page = pages.get(i);
			page.createControl(pageContainer);
			Assert.isNotNull(page.getControl());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#dispose()
	 */
	@Override
	public void dispose() {
		super.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#getPageCount()
	 */
	@Override
	public int getPageCount() {
		return pages.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#getPages()
	 */
	@Override
	public IWizardPage[] getPages() {
		return pages.toArray(new IWizardPage[pages.size()]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.wizard.Wizard#getPreviousPage(org.eclipse.jface.wizard
	 * .IWizardPage)
	 */
	@Override
	public IWizardPage getPreviousPage(IWizardPage page) {
		int index = pages.indexOf(page);
		if (index == 0 || index == -1) {
			// first page or page not found
			return null;
		}
		return pages.get(index - 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.wizard.Wizard#getNextPage(org.eclipse.jface.wizard.
	 * IWizardPage)
	 */
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		int index = pages.indexOf(page);
		if (index == pages.size() - 1 || index == -1) {
			// last page or page not found
			return null;
		}
		return pages.get(index + 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#getStartingPage()
	 */
	@Override
	public IWizardPage getStartingPage() {
		if (pages.size() == 0) {
			return null;
		}
		return pages.get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#needsPreviousAndNextButtons()
	 */
	@Override
	public boolean needsPreviousAndNextButtons() {
		return forcePreviousAndNextButtons || pages.size() > 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.wizard.Wizard#setForcePreviousAndNextButtons(boolean)
	 */
	@Override
	public void setForcePreviousAndNextButtons(boolean b) {
		forcePreviousAndNextButtons = b;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.wizard.Wizard#setDefaultPageImageDescriptor(org.eclipse
	 * .jface.resource.ImageDescriptor)
	 */
	@Override
	public void setDefaultPageImageDescriptor(ImageDescriptor imageDescriptor) {
		defaultImageDescriptor = imageDescriptor;
	}

}
