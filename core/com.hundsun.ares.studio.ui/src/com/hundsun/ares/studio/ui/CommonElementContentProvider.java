/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.model.IWorkbenchAdapter;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESElementChangedEvent;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESElementChangeListener;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IReferencedLibrary;
import com.hundsun.ares.studio.core.model.ResGroup;
import com.hundsun.ares.studio.core.registry.ARESResRegistry;
import com.hundsun.ares.studio.core.registry.IModuleRootDescriptor;
import com.hundsun.ares.studio.core.registry.IResCategoryDescriptor;
import com.hundsun.ares.studio.core.registry.ModuleRootType2ResTypeMap;
import com.hundsun.ares.studio.core.registry.ModulesRootTypeRegistry;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.internal.core.ARESProject;

/**
 * 内容提供器
 * @author sundl
 */
public class CommonElementContentProvider extends ARESElementContentProvider {
	protected TreeViewer viewer;
	protected Object input;

	// 模块展示为父子结构还是平级结构
	protected boolean flatLayout = false;
	// 是否显示资源分类
	protected boolean showCategory = true;
	// 是否显示分组
	protected boolean showGroup = true;
	
	private IPropertyChangeListener propertyListener;
	private IARESElementChangeListener elementListener;
	private IResourceChangeListener resListener;
	private Map<IARESModule, ARESResourceCategory[]> catesMap = new HashMap<IARESModule, ARESResourceCategory[]>();
	
	public CommonElementContentProvider() {
		this(true);
	}
	
	public CommonElementContentProvider(boolean refresh) {
		IPreferenceStore store = ARESUI.INSTANCE.getPreferenceStore();
		flatLayout = store.getBoolean(ARESPreferences.FLATLAYOUT);
		showCategory = store.getBoolean(ARESPreferences.SHOW_CATEGORY);
		
		propertyListener = new IPropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent event) {
				if (event.getProperty().equals(ARESPreferences.FLATLAYOUT)) {
					boolean newValue = ((Boolean) event.getNewValue()).booleanValue() ? true : false;
					flatLayout = newValue;
					safeRefresh();
				} else if (event.getProperty().equals(ARESPreferences.SHOW_CATEGORY)) {
					boolean newValue = ((Boolean) event.getNewValue()).booleanValue() ? true : false;
					showCategory = newValue;
					safeRefresh();
				} 
			}
			
		};
		ARESUI.INSTANCE.getPreferenceStore().addPropertyChangeListener(propertyListener);
		
		elementListener = new IARESElementChangeListener() {
			
			// 2012-03-08 sundl 增加参数
			public void elementChanged(ARESElementChangedEvent event) {
				safeRefresh();
			}
			
		};
		
		if (refresh)
			ARESCore.addElementListener(elementListener);	
	}
	
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) { 
		this.viewer = (TreeViewer)viewer;
		this.input = newInput;
		super.inputChanged(viewer, oldInput, newInput);
	}

	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof ARESResourceCategory) {
			ARESResourceCategory category = (ARESResourceCategory)parentElement;
			return getCategoryChildren(category);
		} else if (parentElement instanceof ResGroup) {
			return getGroupChildren((ResGroup) parentElement);
		} else if (parentElement instanceof RefLibContainer) {
			return ((RefLibContainer) parentElement).getChildren();
		} else if (parentElement instanceof IAdaptable) {
			IWorkbenchAdapter adapter = (IWorkbenchAdapter) ((IAdaptable) parentElement).getAdapter(IWorkbenchAdapter.class);
			if (adapter != null)
				return adapter.getChildren(parentElement);
		}
		
		return super.getChildren(parentElement);
	}
	
	@Override
	protected Object[] getProjectChildren(IARESProject project) {
		if (project instanceof IARESProject) {
			//return getModuleRoots((ICommonProject)project);
			try {
				List<Object> children = new ArrayList<Object>();
				for ( IARESElement child : ((IARESProject)project).getChildren()) {
					if (!(child instanceof IReferencedLibrary) ){
						children.add(child);
					}
				}
				// 2013-05-15 sundl 只有无ref nature才添加引用节点
				// 注意这里的逻辑，为保持老工程的兼容性，有ref nature的不显示引用节点；无ref nature的才显示引用节点
				if (!ARESProject.hasRefNature(project.getProject()))
					children.add(new RefLibContainer((IARESProject) project));
				return children.toArray();
			} catch (ARESModelException e) {
				//e.printStackTrace();
			}
		}
		return NO_CHILDREN;
	}

	protected IARESModuleRoot[] getModuleRoots(IARESProject project) {
		List<IARESModuleRoot> roots = new ArrayList<IARESModuleRoot>();
		try {
			roots.addAll(Arrays.asList(project.getModuleRoots()));
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return roots.toArray(new IARESModuleRoot[roots.size()]);
	}
	
	protected Object[] getModuleRootChildren(IARESModuleRoot root) throws ARESModelException {
		ModulesRootTypeRegistry reg = ModulesRootTypeRegistry.getInstance();
		IModuleRootDescriptor desc = reg.getModuleRootDescriptor(root.getType());
		
		List<IARESElement> results = new ArrayList<IARESElement>();
		
		// 如果配置了不显示默认模块
		if (desc != null && !desc.useDefaultModule()) {
			IARESResource[] resUnderDefaultModule = root.getModule("").getARESResources();
			List<IARESModule> allModules = new ArrayList<IARESModule>(Arrays.asList(getModules(root)));
			allModules.remove(root.getModule(""));
			results.addAll(Arrays.asList(resUnderDefaultModule));
			results.addAll(allModules);
			// 不显示默认模块，模块根属性会作为一个默认模块下的资源，包含在上面的resUnderDefaultModule里，所以不需特殊处理
		} else {
			results.addAll(Arrays.asList(getModules(root)));
			// 模块根的属性是依据模块根下面的root.xml来的, 这个文件在解析的时候，被解析成为默认模块下的一个普通资源
			if ((desc != null) && desc.useProperty()) {
				IARESModule defModule = root.getModule(StringUtils.EMPTY);
				IARESResource profile = defModule.getARESResource(IARESModuleRoot.PROPERTY_FILE);
				if (profile != null && profile.exists()) {
					results.add(profile);
				}
			}
		}
		
		return (IARESElement[]) results.toArray(new IARESElement[results.size()]);
	}
	
	private IARESModule[] getModules(IARESModuleRoot root) throws ARESModelException {
		if (flatLayout) {
			return root.getModules();
		} else {
			List<IARESModule> children = new ArrayList<IARESModule>();
			getHierarchicalModuleChildren(root, null, children);
			return children.toArray(new IARESModule[0]);
		}
	}
	
	// 计算层级模式下模块的子模块
	@SuppressWarnings("unchecked")
	private void getHierarchicalModuleChildren(IARESModuleRoot root, IARESModule fragment, Collection result) throws ARESModelException {
		IARESElement[] children = root.getChildren();
		String prefix= fragment != null ? fragment.getElementName() + '.' : ""; //$NON-NLS-1$
		int prefixLen= prefix.length();
		for (int i= 0; i < children.length; i++) {
			IARESModule curr= (IARESModule) children[i];
			String name= curr.getElementName();
			if (name.startsWith(prefix) && name.length() > prefixLen && name.indexOf('.', prefixLen) == -1) {
				result.add(curr);
			} else if (fragment == null && curr.isDefaultModule()) {
				result.add(curr);
			}
		}
	}
	
	// 模块的getChildren
	protected Object[] getModuleChildren(IARESModule module) throws ARESModelException {
		if (flatLayout) {
			return getNonModuleChildrenOfModule(module);
		} else {
			List<Object> children = new ArrayList<Object>();
			getHierarchicalModuleChildren((IARESModuleRoot)module.getParent(), module, children);
			children.addAll(Arrays.asList(getNonModuleChildrenOfModule(module)));
			return children.toArray();
		}
	}
	
	private Object[] getNonModuleChildrenOfModule(IARESModule module) {
		if (showCategory) {
			// 先计算应该显示的分类
			ARESResourceCategory[] cates = getCategories(module);
			// 对于那些没有注册分类的资源类型，直接显示在模块下
			List<String> nonCateTypes = ARESResRegistry.getInstance().getNonCategoryResTypes();
			IARESResource[] nonCateResources = module.getARESResources(nonCateTypes.toArray(new String[0]));
			if (nonCateResources.length > 0) {
				Object[] result = new Object[cates.length + nonCateResources.length];
				System.arraycopy(cates, 0, result, 0, cates.length);
				System.arraycopy(nonCateResources, 0, result, cates.length, nonCateResources.length);
				return result;
			} else if (cates.length > 0) {
				return cates;
			} else {
				return new Object[0];
			}			
		} else {
			if (showGroup) {
				List<Object> children = new ArrayList<Object>();
				children.addAll(ResGroup.getGroups(module));
				children.addAll(ResGroup.getNoGroupResources(module));
				return children.toArray();
			} else {
				return module.getARESResources();
			}
		} 
	}
	
	protected ARESResourceCategory[] getCategories(IARESModule module) {
		ARESResourceCategory[] cates = catesMap.get(module);
		if (cates == null) {
			cates = createCategories(module);
			catesMap.put(module, cates);
		}
		return cates;
	}
	
	/**
	 * 计算并创建模块下的分类
	 * @param module 模块
	 * @return 模块下的分类
	 */
	private ARESResourceCategory[] createCategories(IARESModule module) {
		String rootType = module.getRoot().getType();
		ARESResRegistry reg = ARESResRegistry.getInstance();
		ModuleRootType2ResTypeMap map = ModuleRootType2ResTypeMap.getInstance();
		Set<ARESResourceCategory> cates = new HashSet<ARESResourceCategory>();
		IResCategoryDescriptor[] allRegistedCates = reg.getCategories();
		for (IResCategoryDescriptor desc : allRegistedCates) {
			// 对于所有注册的分类，只要其下注册的资源类型有一个是在该模块下允许存放的，就显示分类
			for (String type : reg.getRestypes(desc.getId())) {
				if (map.isAllowed(rootType, type)) {
					cates.add(new ARESResourceCategory(module, desc.getId()));
					break;
				}
			}
		}
		return cates.toArray(new ARESResourceCategory[0]);
	}
	
	protected Object[] getCategoryChildren(ARESResourceCategory category) {
		IARESResource[] resources = category.getResources();
		if (showGroup) {
			List<Object> children = new ArrayList<Object>();
			IARESResource[] resource = category.getResources();
			Set<ResGroup> groups = ResGroup.getGroups(resource);
			for (ResGroup g : groups) {
				g.setCateId(category.getId());
			}
			children.addAll(groups);
			children.addAll(ResGroup.getNoGroupResources(resources));
			return children.toArray();
		}
		return resources;
	}
	
	protected Object[] getGroupChildren(ResGroup group) {
		return group.getResources();
	}
	
	public boolean hasChildren(Object element) {
		if (element instanceof IARESModule || element instanceof ARESResourceCategory) {
			return getChildren(element).length > 0;
		} else if (element instanceof RefLibContainer) {
			return ((RefLibContainer) element).getChildren().length > 0;
		}
		return super.hasChildren(element);
	}
	
	@Override
	protected Object internalGetParent(Object child) {
		if (!flatLayout && child instanceof IARESModule) {
			return getHierarchicalModuleParent((IARESModule)child);
		}
		
		if (child instanceof ARESResourceCategory) {
			return ((ARESResourceCategory)child).getModule();
		}
		
		if (child instanceof ResGroup) {
			ResGroup group = (ResGroup) child;
			if (group.getCateId() != null) {
				
			}
		}
		
		if (child instanceof IARESResource) {
			IARESResource res = (IARESResource) child;
			Object parent = null;
			if (showCategory) {
				// 如果资源有分类，则返回分类
				parent = getCategoryOfResoruce(res);
			} 
			
			if (parent != null) {
				ARESResourceCategory cate = (ARESResourceCategory) parent;
				if (showGroup) {
					String group = ARESElementUtil.getGroup(res);
					if (StringUtils.isNotEmpty(group)) {
						parent = new ResGroup(group, res.getModule(), cate.getId());
					}
				}
				return parent;
			} else { 
				if (showGroup) {
					String group = ARESElementUtil.getGroup(res);
					return new ResGroup(group, res.getModule());
				}
				return res.getModule();
			}
		} else if (child instanceof RefLibContainer) {
			return ((RefLibContainer) child).getProject();
		} else if (child instanceof IReferencedLibrary) {
			return new RefLibContainer(((IReferencedLibrary) child).getARESProject());
		}
		
		return super.internalGetParent(child);
	}
	
	public ARESResourceCategory getCategoryOfResoruce(IARESResource res) {
		IARESModule module = res.getModule();
		ARESResourceCategory[] cates = getCategories(module);
		for (ARESResourceCategory cate : cates) {
			if (cate.isResTypeAllowed(res.getType()))
				return cate;
		}
		return null;
	}
	
	public static Object getHierarchicalModuleParent(IARESModule module) {
		String name= module.getElementName();
		IARESModuleRoot parent= (IARESModuleRoot) module.getParent();
		int index= name.lastIndexOf('.');
		if (index != -1) {
			String realParentName= name.substring(0, index);
			IARESModule element= parent.getModule(realParentName);
			if (element != null && element.exists()) {
				return element;
			} 
		}
		return parent;
	}
	protected void refresh() {
		if (viewer.getControl().isDisposed()) 
			return;
		
		viewer.getTree().setRedraw(false);
		viewer.refresh();
		viewer.getTree().setRedraw(true);
	}
	
	public void safeRefresh() {
		if (viewer == null)
			return;
		
		Control ctrl = viewer.getTree();
		if (!ctrl.isDisposed()) {
			if (ctrl.getDisplay().getThread() == Thread.currentThread()) {
				refresh();
			} else {
				viewer.getTree().getDisplay().asyncExec(new Runnable() {
					public void run() {
						refresh();
					}
				});
			}
		}
	}
	
	@Override
	public void dispose() {
		super.dispose();
		ARESCore.removeElementListener(elementListener);
		ARESUI.getDefault().getPreferenceStore().removePropertyChangeListener(propertyListener);
	}
}
