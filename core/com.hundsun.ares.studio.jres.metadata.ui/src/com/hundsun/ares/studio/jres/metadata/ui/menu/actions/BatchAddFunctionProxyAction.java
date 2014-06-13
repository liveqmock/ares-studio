package com.hundsun.ares.studio.jres.metadata.ui.menu.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.ui.MetadataUI;
import com.hundsun.ares.studio.jres.metadata.ui.actions.IMetadataActionIDConstant;
import com.hundsun.ares.studio.jres.model.metadata.Function;
import com.hundsun.ares.studio.jres.model.metadata.FunctionProxy;
import com.hundsun.ares.studio.jres.model.metadata.MenuItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataFactory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.util.MenuUtils;
import com.hundsun.ares.studio.ui.editor.actions.IUpdateAction;
import com.hundsun.ares.studio.ui.userdialog.CheckedTableSelectionDialog;

public class BatchAddFunctionProxyAction extends Action  implements IUpdateAction{

	TableViewer viewer;
	EditingDomain domain;
	IARESResource resource;
	Map<FunctionProxy,Function> inputMap = new HashMap<FunctionProxy,Function>();
	private static final String[] TITLES = {"功能号","功能名称","备注"};
	public static final EAttribute[] ATTRIBUTES = new EAttribute[] {
		MetadataPackage.Literals.NAMED_ELEMENT__NAME,
		MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME,
		MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION
	};
	{
		if(MenuUtils.isStockDepartment()){
			TITLES[0] = "功能号";
			ATTRIBUTES[0] = MetadataPackage.Literals.NAMED_ELEMENT__NAME;
		}else{
			TITLES[0] = "子交易号";
			ATTRIBUTES[0] = MetadataPackage.Literals.FUNCTION__SUB_TRANS_CODE;
		}
	}

	public BatchAddFunctionProxyAction(TableViewer viewer,EditingDomain domain, IARESResource resource) {
		super();
		this.viewer = viewer;
		this.domain = domain;
		this.resource = resource;
		setId(IMetadataActionIDConstant.CV_BATCH_ADD);
		setText("批量添加");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(MetadataUI.PLUGIN_ID, "icons/full/obj16/batchedAdd.png"));
	}
	
	@Override
	public void run() {
		List<FunctionProxy> inputList = new ArrayList<FunctionProxy>();
		List<FunctionProxy> initialList = new ArrayList<FunctionProxy>();
		List<FunctionProxy> demodedList = new ArrayList<FunctionProxy>();
		calculateFunctionProxyParams(inputList, initialList, demodedList);
		
		FunctionProxySelectionDialog dialog = new FunctionProxySelectionDialog(viewer.getControl().getShell());
		dialog.setInput(inputList);
		dialog.setInitialElementSelections(initialList);
		dialog.setDemodedElements(demodedList);
		if(dialog.open() == Window.OK) {
			List<FunctionProxy> results = dialog.getSelectedResults();
			
			MenuItem item = (MenuItem)viewer.getInput();
			Command command = SetCommand.create(domain, item, MetadataPackage.Literals.MENU_ITEM__FUNCTION_PROXYS, results);
			if(command.canExecute()){
				domain.getCommandStack().execute(command);
			}
			
			viewer.refresh();
		}
	}
	
	@Override
	public void update() {
		Object obj = viewer.getInput();
		setEnabled(obj != null && (obj instanceof MenuItem) && !resource.isReadOnly());
	}
	/**
	 * 计算批量添加初始列表。
	 * 
	 * @param inputList 初始列表
	 * @param initialList 默认选中列表
	 * @param demodedList 已过时数据列表
	 */
	private void calculateFunctionProxyParams(List<FunctionProxy> inputList,
			List<FunctionProxy> initialList, List<FunctionProxy> demodedList) {
		Object obj = viewer.getInput();
		inputMap.clear();
		if(obj != null && obj instanceof MenuItem){
			MenuItem item = (MenuItem)obj;
			EList<FunctionProxy> input = item.getFunctionProxys();
			demodedList.addAll(input);
			inputList.addAll(input);
			for (Function function : MenuUtils.getFunctions(resource)) {
				FunctionProxy fProxy = null;
				if(MenuUtils.isStockDepartment()){
					for (FunctionProxy proxy : input) {
						if(function.getName().equals(proxy.getFunCode())){
							demodedList.remove(proxy);
							initialList.add(proxy);
							fProxy = proxy;
							inputMap.put(fProxy, function);
							break;
						}
					}
				}else{
					for (FunctionProxy proxy : input) {
						if(function.getSubTransCode().equals(proxy.getFunCode())){
							demodedList.remove(proxy);
							initialList.add(proxy);
							fProxy = proxy;
							inputMap.put(fProxy, function);
							break;
						}
					}
				}
				
				if(fProxy == null){
					fProxy = MetadataFactory.eINSTANCE.createFunctionProxy();
					if(MenuUtils.isStockDepartment()){
						fProxy.setFunCode(function.getName());
						fProxy.setDescription(function.getDescription());
					}else{
						fProxy.setFunCode(function.getSubTransCode());
						fProxy.setDescription(function.getDescription());
					}
					inputList.add(fProxy);
					inputMap.put(fProxy, function);
				}
			}
		}
	}
	
	private class FunctionProxySelectionDialog extends CheckedTableSelectionDialog<FunctionProxy> {
		private List<FunctionProxy> demodedElements;
		
		public FunctionProxySelectionDialog(Shell parent) {
			super(parent,TITLES,ATTRIBUTES);
			setTitle("批量添加");
		}
		
		
		/**
		 * 设置过时的数据列表。
		 * 
		 * @param demodedElement
		 */
		public void setDemodedElements(List<FunctionProxy> demodedElements) {
			this.demodedElements = demodedElements;
		}
		

		@Override
		protected String getText(FunctionProxy element, EAttribute attribute) {
			Function func = inputMap.get(element);
			if(func != null){
				return (String) func.eGet(attribute);
			}
			if(attribute.equals(ATTRIBUTES[0])){
				return element.getFunCode();
			}else if(attribute.equals(ATTRIBUTES[2])){
				return element.getDescription();
			}
			return StringUtils.EMPTY;
		}
		
		@Override
		protected Image getUncheckedImage(FunctionProxy element) {
			if (demodedElements.contains(element)) {
				return getDeleteCheckImage();
			}
			return super.getUncheckedImage(element);
		}
		
	}
}
