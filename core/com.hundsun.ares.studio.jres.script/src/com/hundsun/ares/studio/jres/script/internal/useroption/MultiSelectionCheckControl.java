/**
 * 源程序名称：BizPropertyControl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.jres.script
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.script.internal.useroption;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * MultiSelection Check Control
 * @author sundl
 *
 */
public class MultiSelectionCheckControl extends Composite {

	private Object[] input; 
	private ILabelProvider labelProvider;
	
	
	private List<Button> buttons = new ArrayList<Button>();
	private List<Object> selection = new ArrayList<Object>();
	
	/**
	 * @param parent
	 * @param style
	 */
	public MultiSelectionCheckControl(Composite parent, Object[] input, ILabelProvider labelProvider) {
		super(parent, SWT.NONE);
		this.input = input;
		this.labelProvider = labelProvider;
		init();
	}
	
	private void init() {
		GridLayoutFactory.fillDefaults().applyTo(this);
		Composite topComposite = new Composite(this, SWT.NONE);
		GridLayoutFactory.fillDefaults().numColumns(6).applyTo(topComposite);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(topComposite);
		
		for (Object obj : input) {
			Button button = new Button(topComposite, SWT.CHECK);
			buttons.add(button);
			GridDataFactory.fillDefaults().applyTo(button);
			button.setText(labelProvider.getText(obj));
			button.setData("obj", obj);
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					updateSelection();
				}
			});
		}
		
		Composite buttomComposite = new Composite(this, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(buttomComposite);
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(buttomComposite);
		Button checkAll = new Button(buttomComposite, SWT.PUSH);
		checkAll.setText("全选");
		GridDataFactory.fillDefaults().applyTo(checkAll);
		checkAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				for (Button button : buttons) {
					button.setSelection(true);
				}
			}
		});
		
		Button unCheckAll = new Button(buttomComposite, SWT.PUSH);
		unCheckAll.setText("取消全选");
		GridDataFactory.fillDefaults().applyTo(unCheckAll);
		unCheckAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				for (Button button : buttons) {
					button.setSelection(false);
				}
			}
		});
		
	}
	
	public void setSelection(Object[] array) {
		for (Button button : buttons) {
			if (ArrayUtils.contains(array, button.getData("obj"))) {
				button.setSelection(true);
			}
		}
		updateSelection();
	}
	
	private void updateSelection() {
		selection.clear();
		for (Button b : buttons) {
			if (b.getSelection()) {
				selection.add(b.getData("obj"));
			}
		}
	}
	
	public Object[] getSelected() {
		return selection.toArray();
	}
}
