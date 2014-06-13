/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.ui.module;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.cres.extend.cresextend.MoudleDepend;
import com.hundsun.ares.studio.ui.CommonElementContentProvider;
import com.hundsun.ares.studio.ui.CommonElementLabelProvider;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerAddAction;

/**
 * @author qinyuan
 *
 */
public class ColumnViewerAddMoudleDependAction extends ColumnViewerAddAction {
	private Composite composite;
	private IARESResource resource;
	private static String ATOM_MODULE_ROOT = "com.hundsun.ares.studio.atom.resources.atomroot";
	private static String LOGIC_MODULE_ROOT = "com.hundsun.ares.studio.logic.resources.logicroot";
	private boolean isAtomMoudle = false;

	/**
	 * @param viewer
	 * @param editingDomain
	 */
	public ColumnViewerAddMoudleDependAction(IARESResource resource, Composite composite,
			ColumnViewer viewer,
			EditingDomain editingDomain,EObject owner, EReference reference,EClass itemClass) {
		super(viewer, editingDomain,owner,reference,itemClass);
		
		this.composite = composite;
		this.resource = resource;
		this.isAtomMoudle = this.isAtomMoudle();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		ModuleContentProvider cp = new ModuleContentProvider();
		ElementTreeSelectionDialog moduleSelectDialog = new ElementTreeSelectionDialog(
				composite.getShell(),
				new CresModuelLabelProvider(cp),
				cp
				);
		moduleSelectDialog.setInput(resource.getARESProject());
		moduleSelectDialog.addFilter(new CRESModuelViewerFilter());

		moduleSelectDialog.setTitle("浏览");

		if (moduleSelectDialog.open() == Window.OK) {
			Object moudle = moduleSelectDialog.getFirstResult();
			if (moudle instanceof IARESModule) {
				
				if (getViewer().isCellEditorActive()) {
					getViewer().cancelEditing();
				}
				
				//
				excuteCommand((IARESModule)moudle);
				clearCommand();
				// 让表格选择影响操作的对象
				Command mostRecentCommand = getEditingDomain().getCommandStack()
				.getMostRecentCommand();
				if (mostRecentCommand != null) {
					setSelectionToViewer(mostRecentCommand.getAffectedObjects());
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.actions.ColumnViewerAction#calculateEnabled()
	 */
	@Override
	protected boolean calculateEnabled() {
		return true;
	}
	
	/**
	 * @param moudle
	 */
	private void excuteCommand(IARESModule moudle) {
		if (getOwner() != null && getReference() != null) {
			EObject newObj = getEMFClass().getEPackage().getEFactoryInstance().create(getEMFClass());
			if(newObj instanceof MoudleDepend) {
				MoudleDepend md = (MoudleDepend)newObj;
				md.setModulePath(moudle.getElementName());
				md.setName(moudle.getShortName());
				Command command = AddCommand.create(getEditingDomain(), getOwner(), getReference(), md);
				getEditingDomain().getCommandStack().execute(command);
			}
		}
	}
	/**
	 * 是否是原子模块
	 * @return
	 */
	private boolean isAtomMoudle(){
		return StringUtils.equals(ATOM_MODULE_ROOT, this.resource.getModule().getRoot().getType());
	}
	
	/**
	 * 过滤不需要的模块:
	 * 1,如果是原子,那么只能选择除本模块以外的原子模块
	 * 2,如果是逻辑,那么只能选择除本模块以外的逻辑模块以及原子模块
	 * @author liaogc
	 *
	 */
	class CRESModuelViewerFilter extends ViewerFilter{

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
		 */
		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if(element instanceof IARESModuleRoot){

				IARESModuleRoot moduleRoot = (IARESModuleRoot) element;
				if(isAtomMoudle){
					return StringUtils.equals(ATOM_MODULE_ROOT,moduleRoot.getType());
				}else{
					return (StringUtils.equals(LOGIC_MODULE_ROOT, moduleRoot.getType()))||(StringUtils.equals(ATOM_MODULE_ROOT, moduleRoot.getType()));
				}
			
				
			}else if(element instanceof IARESModule){
				IARESModule module = (IARESModule) element;
				if(isAtomMoudle){
					return StringUtils.equals(ATOM_MODULE_ROOT, module.getRoot().getType()) && !module.equals(resource.getModule());
				}else{
					return (StringUtils.equals(LOGIC_MODULE_ROOT, module.getRoot().getType())) && !module.equals(resource.getModule())||(StringUtils.equals(ATOM_MODULE_ROOT, module.getRoot().getType()) && !module.equals(resource.getModule()));
				}
			}
			return false;
		}
		
	}

}



class ModuleContentProvider  extends CommonElementContentProvider{

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.CommonElementContentProvider#getModuleChildren(com.hundsun.ares.studio.core.IARESModule)
	 */
	@Override
	protected Object[] getModuleChildren(IARESModule module)
			throws ARESModelException {
		if (flatLayout) {
			return module.getARESResources("xml");
		}else {
			List<Object> result = new ArrayList<Object>();
			IARESElement[] children = ((IARESModuleRoot)module.getParent()).getChildren();
			String prefix= module != null ? module.getElementName() + '.' : ""; //$NON-NLS-1$
			int prefixLen= prefix.length();
			for (int i= 0; i < children.length; i++) {
				IARESModule curr= (IARESModule) children[i];
				String name= curr.getElementName();
				if (name.startsWith(prefix) && name.length() > prefixLen && name.indexOf('.', prefixLen) == -1) {
					result.add(curr);
				} else if (module == null && curr.isDefaultModule()) {
					result.add(curr);
				}
			}
			return result.toArray();
		}
	}
}

class CresModuelLabelProvider extends CommonElementLabelProvider {

	/**
	 * @param cp
	 */
	public CresModuelLabelProvider(CommonElementContentProvider cp) {
		super(cp);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.CommonElementLabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		return super.getText(element);
	}
}
