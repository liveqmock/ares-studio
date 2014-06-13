package com.hundsun.ares.studio.jres.metadata.ui.viewer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.registry.ModuleRootType2ResTypeMap;
import com.hundsun.ares.studio.ui.ARESResourceCategory;
import com.hundsun.ares.studio.ui.CommonElementContentProvider;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelEditingSupport;

public class IDRangeContentProvider extends CommonElementContentProvider {

	Set<String> rootTypes = new HashSet<String>();
	Set<String> resTypes = new HashSet<String>();
	
	public IDRangeContentProvider(IARESResource resource,EClass eClass) {
		IExtensibleModelEditingSupport[] editingSupports = ExtensibleModelUtils
		.getEndabledEditingSupports(resource, eClass);
		for(IExtensibleModelEditingSupport editingSupport : editingSupports){
			//默认约束，name为资源类型组成，中间用逗号分隔
			String name = editingSupport.getName();
			for(String resType : StringUtils.split(name, ",")){
				resTypes.add(resType);
				rootTypes.addAll(Arrays.asList(ModuleRootType2ResTypeMap.getInstance().getAllowedRootTypes(resType)));
			}
		}
	}
	
	@Override
	public Object[] getProjectChildren(IARESProject project) {
		List<IARESModule> list = new ArrayList<IARESModule>();
		try {
			for(IARESModuleRoot root : project.getModuleRoots()){
				if(shouldLoad(root)){
					for(Object obj : getModuleRootChildren(root)){
						if(obj instanceof IARESModule && shouldLoad((IARESModule)obj)){
							list.add((IARESModule)obj);
						}
					}
				}
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return list.toArray(new IARESModule[]{});
	}

	@Override
	protected Object[] getModuleChildren(IARESModule module)
			throws ARESModelException {
		List<IARESElement> list = new ArrayList<IARESElement>();
		for(Object obj : super.getModuleChildren(module)){
			if(obj instanceof IARESElement){
				IARESElement element = (IARESElement)obj;
				if(shouldLoad(element)){
					list.add(element);
				}
			}else if(obj instanceof ARESResourceCategory){
				for(IARESResource res : ((ARESResourceCategory)obj).getResources()){
					if(shouldLoad(res)){
						list.add(res);
					}
				}
			}
		}
		return list.toArray(new IARESElement[]{});
	}
	
	
	private boolean shouldLoad(IARESElement element){
		if(element instanceof IARESModuleRoot){
			IARESModuleRoot root = (IARESModuleRoot)element;
			String type = root.getType();
			for(String rootType : rootTypes){
				if(StringUtils.equals(rootType, type)){
					return true;
				}
			}
		}else if(element instanceof IARESModule){
			IARESModule module = (IARESModule)element;
			if(!module.isDefaultModule()){
				return true;
			}
		}else if(element instanceof IARESResource){
			IARESResource res = (IARESResource)element;
			String type = res.getType();
			for(String resType : resTypes){
				if(StringUtils.equals(resType, type)){
					return true;
				}
			}
		}
		return false;
	}

}
