/**
 * 源程序名称：DatabindingHelper.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.blocks;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateListStrategy;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;

/**
 * @deprecated 这个帮助类写法不正确，被废弃
 *
 */
public class DatabindingHelper {

	/**
	 * @deprecated
	 * @param context
	 * @param control
	 * @param obj
	 * @param attr
	 * @param targetToModel
	 * @param modelToTarget
	 */
	public static void emfBind(DataBindingContext context, 
								Control control, 
								EObject obj, 
								EAttribute attr, 
								UpdateValueStrategy targetToModel,
								UpdateValueStrategy modelToTarget) {
		IObservableValue txtAuthorObserveWidget = SWTObservables.observeText(control, SWT.FocusOut);
		IObservableValue txtAuthorObserveValue = EMFObservables.observeValue(obj, attr);
		context.bindValue(txtAuthorObserveWidget, txtAuthorObserveValue, targetToModel, modelToTarget);
	}
	
	
	/**
	 * 将枚举的值绑定到combo的下拉列表中
	 * @param context
	 * @param control
	 * @param eenum
	 * @deprecated
	 */
	public static void emfComboBind(DataBindingContext context
			,Control control
			,EEnum eenum){
		context.bindList(SWTObservables.observeItems(control)
				, new WritableList(eenum.getELiterals(), null)
		        , new UpdateListStrategy(), 
				new UpdateListStrategy().setConverter(new EnumToStringConventor()));
	}
	
	/**
	 * 将EMF属性绑定到combo控件
	 * @param context
	 * @param control  控件
	 * @param obj      模型
	 * @param attr     属性 
	 * @param targetToModel 界面到模型策略
	 * @param modelToTarget 模型到界面策略
	 * @deprecated
	 */
	public static void emfComboBind(DataBindingContext context, 
			Control control, 
			EObject obj, 
			EAttribute attr,
			UpdateValueStrategy targetToModel,
			UpdateValueStrategy modelToTarget) {
		
		IObservableValue comboObserveWidget = SWTObservables.observeText(control);
		IObservableValue comboObserveValue = EMFObservables.observeValue(obj, attr);
		context.bindValue(comboObserveWidget,comboObserveValue,targetToModel,modelToTarget);
	}
	
	/**
	 * @deprecated
	 * @param context
	 * @param control
	 * @param obj
	 * @param attr
	 * @param targetToModel
	 * @param modelToTarget
	 */
	public static void emfSpinnerBind(DataBindingContext context, 
			Control control, 
			EObject obj, 
			EAttribute attr,
			UpdateValueStrategy targetToModel,
			UpdateValueStrategy modelToTarget) {
		
		IObservableValue spinnerIPrecisionObserveWidget = SWTObservables.observeSelection(control);
		IObservableValue spinnerIPrecisionObserveValue = EMFObservables.observeValue(obj, attr);
		context.bindValue(spinnerIPrecisionObserveWidget,spinnerIPrecisionObserveValue, targetToModel,modelToTarget);
		
	}
	

	
}
