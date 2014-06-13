package com.hundsun.ares.studio.jres.basicdata.ui.editor.blocks;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.logic.util.BasicDataEpackageUtil;
import com.hundsun.ares.studio.ui.FilteredTable;
import com.hundsun.ares.studio.ui.TableViewerPatternFilter;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnViewerProblemView;

public class InfoSelectDialog extends Dialog {

	IARESResource resource;
	EPackage ePackage;

	TableViewer treeViewer;
	EObject resourceInfo;								//正在编辑的项
	
	List<Object> result = new ArrayList<Object>();

	public InfoSelectDialog(Shell parentShell, 
			IARESResource resource,
			EObject resourceInfo) {
		super(parentShell);
		this.resource = resource;
		this.ePackage = resourceInfo.eClass().getEPackage();
		this.resourceInfo = resourceInfo;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("选择");
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite contents = (Composite) super.createDialogArea(parent);

		treeViewer = doCreateColumnViewer(contents);

		EObjectColumnViewerProblemView problemView = new EObjectColumnViewerProblemView(
				treeViewer);
		EClass slaveEclass = (EClass) ePackage
				.getEClassifier(IBasicDataEpacakgeConstant.InfoItem);
		EAttribute[] attrArray = BasicDataEpackageUtil.filterAttr(slaveEclass);

		for (int i = 0; i < attrArray.length; i++) {
			// 定义主属性
			EAttribute attribute = attrArray[i];

			// 创建表格列
			TableViewerColumn column = new TableViewerColumn(treeViewer,
					SWT.LEFT);
			column.getColumn().setText(
					BasicDataEpackageUtil.getAttrColumnName(resource,
							attrArray[i]));
			column.getColumn().setWidth(120);
			column.getColumn().setMoveable(true);

			// 设置标签提供器
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(
					attribute);
			// provider.setDiagnosticProvider(problemView);
			column.setLabelProvider(provider);

			// // 设置编辑支持
			// BasicDataTextEditingSupport es = new
			// BasicDataTextEditingSupport(treeViewer, new
			// IEStructuralFeatureProvider.Impl(attribute));
			// es.setDecorator(new
			// BaiscDataEditingSupportDecorator(attribute,resource));
			// column.setEditingSupport(es);
		}

		GridDataFactory.swtDefaults().hint(400, 300).grab(true, true).align(SWT.FILL, SWT.FILL)
				.applyTo(treeViewer.getControl());

		treeViewer.setContentProvider(new InfoListContentProvider());

		try {
			EStructuralFeature feature = resourceInfo.eClass().getEStructuralFeature(
					IBasicDataEpacakgeConstant.Attr_Info_Items);
			treeViewer.setInput(resourceInfo.eGet(feature));
		} catch (Exception e) {
		}

		return contents;
	}

	/**
	 * 创建table
	 * 
	 * @param parent
	 * @return
	 */
	protected TableViewer doCreateColumnViewer(Composite parent) {
		final FilteredTable table = new FilteredTable(parent, SWT.BORDER
				| SWT.FULL_SELECTION | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL,
				new TableViewerPatternFilter(), true) {
			/*
			 * (non-Javadoc)
			 * 
			 * @see com.hundsun.ares.studio.jres.ui.common.FilteredTable#
			 * getRefreshJobDelay()
			 */
			@Override
			protected long getRefreshJobDelay() {
				return getViewer().getTable().getItemCount() / 40 + 100;
			}
		};

		table.getViewer().getTable().setHeaderVisible(true);
		table.getViewer().getTable().setLinesVisible(true);

		return table.getViewer();
	}

	public TableViewer getViewer() {
		return this.treeViewer;
	}

	@Override
	protected void okPressed() {
		result.clear();
		IStructuredSelection selection = (IStructuredSelection) this.treeViewer
				.getSelection();
		if (selection.isEmpty()) {
			return;
		}
		
		EClass infoClass = (EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.InfoItem);
		EClass linkClass = (EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.SlaveItem);
		EStructuralFeature refer = linkClass.getEStructuralFeature(IBasicDataEpacakgeConstant.Attr_Info_Content_Reference);
		
		String[] masterKeys = BasicDataEpackageUtil.getMasterKeyAnnotation(infoClass) ;
		
		for(Object item:selection.toArray()){
			EObject obj = (EObject)item;
			//创建关联表项
			EObject slaveItem =  ePackage.getEFactoryInstance().create(linkClass);
			//关联表项赋值
			for(String key:masterKeys){
				EStructuralFeature source = infoClass.getEStructuralFeature(key);
				EStructuralFeature target = linkClass.getEStructuralFeature(key);
				slaveItem.eSet(target, obj.eGet(source));
			}
			//添加引用
			slaveItem.eSet(refer, item);
			
			result.add(slaveItem);
		}
		
		// result = new BasicEList<Object>(values.getChildren());
		super.okPressed();
	}

	@Override
	public boolean close() {

		return super.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#isResizable()
	 */
	@Override
	protected boolean isResizable() {
		return true;
	}
	
	/**
	 * 获取结果
	 * @return
	 */
	public List<Object> getResult(){
		return result;
	}

}
