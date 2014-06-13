/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.metadata.ui.menu.actions;

import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.core.model.UserExtensibleProperty;
import com.hundsun.ares.studio.core.model.util.Pair;
import com.hundsun.ares.studio.jres.metadata.ui.MetadataUI;
import com.hundsun.ares.studio.jres.metadata.ui.actions.IMetadataActionIDConstant;
import com.hundsun.ares.studio.jres.model.metadata.FunctionProxy;
import com.hundsun.ares.studio.jres.model.metadata.MenuItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataFactory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.util.MenuUtils;
import com.hundsun.ares.studio.ui.editor.actions.IUpdateAction;

/**
 * @author wangxh
 *
 */
public class RecoverRefMenuFunctionsAction extends Action  implements IUpdateAction{
	
	TableViewer viewer;
	EditingDomain domain;
	IARESResource resource;
	
	public RecoverRefMenuFunctionsAction(TableViewer viewer,EditingDomain domain,IARESResource resource) {
		super();
		this.viewer = viewer;
		this.domain = domain;
		this.resource = resource;
		setId(IMetadataActionIDConstant.CV_RECOVER_FUNCTIONS);
		setText("恢复引用菜单功能");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(MetadataUI.PLUGIN_ID, "icons/full/obj16/refresh.gif"));
	}
	
	@Override
	public void run() {
		Object obj = viewer.getInput();
		if(obj != null && obj instanceof MenuItem){
			MenuItem item = (MenuItem)obj;
			Pair<MenuItem, IARESResource> pair = MenuUtils.resolve(item, resource);
			if(pair.first != null){
				EList<FunctionProxy> list = getFunctionProxys(pair.first);
				Command cmd = SetCommand.create(domain, item, MetadataPackage.Literals.MENU_ITEM__FUNCTION_PROXYS,list);
				domain.getCommandStack().execute(cmd);
			}
		}
	}
	
	private EList<FunctionProxy> getFunctionProxys(MenuItem item){
		EList<FunctionProxy> list = new BasicEList<FunctionProxy>();
		for(FunctionProxy proxy : item.getFunctionProxys()){
			FunctionProxy funcProxy = MetadataFactory.eINSTANCE.createFunctionProxy();
			funcProxy.setFunCode(proxy.getFunCode());
			funcProxy.setDescription(proxy.getDescription());
			funcProxy.getData2().putAll(getItemData2(proxy));
			list.add(funcProxy);
		}
		return list;
	}
	
	private EMap<String, EObject> getItemData2(ExtensibleModel model){
		EMap<String, EObject> map = new BasicEMap<String, EObject>();
		EMap<String, EObject> data2 = model.getData2();
		for(Entry<String, EObject> entry :data2.entrySet()){
			EClass eClass = entry.getValue().eClass();
			EObject obj = eClass.getEPackage().getEFactoryInstance().create(eClass);
			if(obj instanceof UserExtensibleProperty){
				for(Entry<String, String> e : ((UserExtensibleProperty)entry.getValue()).getMap().entrySet()){
					((UserExtensibleProperty)obj).getMap().put(e.getKey(), e.getValue());
				}
			}
			map.put(entry.getKey(), obj);
		}
		return map;
	}
	
	@Override
	public void update() {
		Object obj = viewer.getInput();
		if(obj != null && obj instanceof MenuItem){
			MenuItem item = (MenuItem)obj;
			setEnabled(StringUtils.isNotBlank(item.getRefId()));
		}else{
			setEnabled(false);
		}
	}

}
