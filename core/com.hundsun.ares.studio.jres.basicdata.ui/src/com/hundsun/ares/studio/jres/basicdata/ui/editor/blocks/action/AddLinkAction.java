/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.basicdata.ui.editor.blocks.action;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.blocks.InfoSelectDialog;
import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;
import com.hundsun.ares.studio.ui.editor.actions.IUpdateAction;

/**
 * @author lvgao
 *
 */
public class AddLinkAction  extends Action implements IUpdateAction{

	EObject resouceInfo;						//当前正在编辑的模型
	EObject owner;
	IARESResource resource;
	EditingDomain domain;
	
	public AddLinkAction(EditingDomain domain,
			IARESResource resource, 
			EObject resouceInfo,
			EObject owner){
		this.owner = owner;
		this.resouceInfo = resouceInfo;
		this.resource = resource;
		this.domain = domain;
		setText("增加");
		setId(IActionIDConstant.CV_ADD);
		setEnabled(false);
		
		setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
	}
	
	public void setOwner(EObject info){
		setEnabled(false);
		
		if(null == info){
			return;
		}
		//分类
		if(info instanceof  MetadataCategory){
			return;
		}
		
		this.owner = info;
		setEnabled(true);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		EPackage ePackage = owner.eClass().getEPackage();
		
		
		InfoSelectDialog dlg = new InfoSelectDialog(new Shell(), 
				resource, 
				resouceInfo);
		dlg.open();
		
//		domain
		if(!dlg.getResult().isEmpty()){
			EClass masterClass = (EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.MasterItem);
			AddCommand command = new AddCommand(domain, owner, 
					masterClass.getEStructuralFeature(IBasicDataEpacakgeConstant.Attr_Slave_Items),
					dlg.getResult());
			domain.getCommandStack().execute(command);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.actions.IUpdateAction#update()
	 */
	@Override
	public void update() {
		
	}

}
