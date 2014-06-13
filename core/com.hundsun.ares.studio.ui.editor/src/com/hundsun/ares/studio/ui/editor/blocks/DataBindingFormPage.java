/**
 * 源程序名称：DataBindingFormPage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.ui.editor.blocks;

import java.util.Arrays;

import org.apache.commons.lang.ObjectUtils;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateListStrategy;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.Observables;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.hundsun.ares.studio.ui.databinding.IndexToItemsConverter;
import com.hundsun.ares.studio.ui.databinding.ItemsToIndexConverter;
import com.hundsun.ares.studio.ui.databinding.LabelProviderConverter;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.EMFFormPage;
import com.hundsun.ares.studio.ui.editor.FormControlProblemView;
import com.hundsun.ares.studio.ui.validate.KeyParameter;

/**
 * @author gongyf
 *
 */
public abstract class DataBindingFormPage extends EMFFormPage {

	private DataBindingContext dataBindingContext;
	
	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public DataBindingFormPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	/**
	 * @return the dataBindingContext
	 */
	protected DataBindingContext getDataBindingContext() {
		if (dataBindingContext == null) {
			dataBindingContext = new EMFDataBindingContext();
		}
		return dataBindingContext;
	}
	
	protected void destroyDataBindingContext() {
		if (dataBindingContext != null) {
			dataBindingContext.dispose();
			dataBindingContext = null;
		}
	}
	
	/**
	 * 配置数据绑定
	 */
	protected abstract void doDataBingingOnControls();
	
	protected void bingSelection(Control control, EObject container, EStructuralFeature feature) {
		// 数据绑定
		getDataBindingContext().bindValue(
				SWTObservables.observeSelection(control), 
				EMFEditObservables.observeValue(getEditingDomain(), container, feature));
		
		// 错误检查绑定
		getProblemPool().addView(new FormControlProblemView(
				new KeyParameter(container, feature), control));
		
		// 只读控制
		//getEditableControl().addEditableUnit(new JresDefaultEditableUnit(control));
	}
	
	protected void bingText(Text text, EObject container, EStructuralFeature feature) {
		// 数据绑定
		getDataBindingContext().bindValue(
				SWTObservables.observeText(text, FormWidgetUtils.getDefaultTextDataBingingEvents()), 
				EMFEditObservables.observeValue(getEditingDomain(), container, feature));
		
		// 错误检查绑定
		getProblemPool().addView(new FormControlProblemView(
				new KeyParameter(container, feature), text));
		
		// 只读控制
		//getEditableControl().addEditableUnit(new JresDefaultEditableUnit(text));
	}
	
	/**
	 * 绑定一个下拉菜单
	 * @param cb
	 * @param items
	 * @param labelProvider 如果是String数组，可以设置为null
	 * @param container
	 * @param feature
	 */
	protected void bingCombo(Combo cb, Object[] items, ILabelProvider labelProvider, EObject container, EStructuralFeature feature) {
		
		labelProvider = (ILabelProvider) ObjectUtils.defaultIfNull(labelProvider, new LabelProvider());
		
		// 绑定下拉菜单
		getDataBindingContext().bindList(
				SWTObservables.observeItems(cb), 
				Observables.staticObservableList(Arrays.asList(items)), null, 
				new UpdateListStrategy().setConverter(new LabelProviderConverter(labelProvider)));
		
		// 数据绑定
		getDataBindingContext().bindValue(
				SWTObservables.observeSingleSelectionIndex(cb), 
				EMFEditObservables.observeValue(getEditingDomain(), container, feature), new UpdateValueStrategy().setConverter(
						new IndexToItemsConverter(items)
				), new UpdateValueStrategy().setConverter(new ItemsToIndexConverter(items)));
		
		
		
		// 错误检查绑定
		getProblemPool().addView(new FormControlProblemView(
				new KeyParameter(container, feature), cb));
		
		// 只读控制
		//getEditableControl().addEditableUnit(new JresDefaultEditableUnit(cb));
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.IEMFFormPage#infoChange()
	 */
	@Override
	public void infoChange() {
		destroyDataBindingContext();
		doDataBingingOnControls();
		super.infoChange();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#dispose()
	 */
	@Override
	public void dispose() {
		destroyDataBindingContext();
		super.dispose();
	}
}
