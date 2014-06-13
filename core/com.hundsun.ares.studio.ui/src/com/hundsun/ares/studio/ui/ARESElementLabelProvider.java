/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.model.IWorkbenchAdapter;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IExternalResPathEntry;
import com.hundsun.ares.studio.core.IProjectProperty;
import com.hundsun.ares.studio.core.IReferencedLibrary;
import com.hundsun.ares.studio.core.IResPathEntry;
import com.hundsun.ares.studio.core.IResPathEntryElement;
import com.hundsun.ares.studio.core.model.ResGroup;
import com.hundsun.ares.studio.core.registry.ARESResRegistry;
import com.hundsun.ares.studio.core.registry.IModuleRootDescriptor;
import com.hundsun.ares.studio.core.registry.IResDescriptor;
import com.hundsun.ares.studio.core.registry.IRespathProviderDescriptor;
import com.hundsun.ares.studio.core.registry.ModulesRootTypeRegistry;
import com.hundsun.ares.studio.core.util.StringUtil;

/**
 * 基础的LabelProvider.
 * @author sundl
 */
public class ARESElementLabelProvider implements ILabelProvider, IStyledLabelProvider{
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
	 */
	public Image getImage(Object element) {
		
		if (element instanceof IARESProject) {
			return ARESUI.getImage2(ARESUI.IMG_PROJECT);
		} else if (element instanceof IARESModuleRoot) {
			IARESModuleRoot root = (IARESModuleRoot)element;
			IModuleRootDescriptor desc = ModulesRootTypeRegistry.getInstance().getModuleRootDescriptor(root.getType());
			if (desc != null) {
				ImageDescriptor descriptor = desc.getImageDescriptor();
				return ARESUI.getImageDescriptorRegistry().get(descriptor);
			}
		} else if (element instanceof IARESModule) {
			return ARESUI.getImage2(ARESUI.IMG_PATH_MODULE);
		} else if (element instanceof IARESResource) {
			IARESResource cResource = (IARESResource)element;
			IResDescriptor descriptor = ARESResRegistry.getInstance().getResDescriptor(cResource.getType());
			if (descriptor != null) {
				return ARESUI.getImageDescriptorRegistry().get(descriptor.getImageDescriptor());
			} else {
				return ARESUI.getImage2(ARESUI.IMG_DEFAULT_ARES_RESOURCE);
			}
		} else if (element instanceof ARESResourceCategory || element instanceof ResGroup) {
			return ARESUI.getImage2(ARESUI.IMG_PATH_CATE);
		} else if (element instanceof IReferencedLibrary) {
			return ARESUI.getImage2(ARESUI.IMG_PATH_REFLIB);
		} else if (element instanceof IProjectProperty) {
			return ARESUI.getImage2(ARESUI.IMG_PATH_PROJECT_PRO);
		} else if (element instanceof RefLibContainer) {
			return ARESUI.getImage2(ARESUI.IMG_PATH_LIB_CONTAINER);
		} else if (element instanceof IAdaptable) {
			IWorkbenchAdapter adapter = (IWorkbenchAdapter) ((IAdaptable) element).getAdapter(IWorkbenchAdapter.class);
			if (adapter != null) {
				return ARESUI.getImageDescriptorRegistry().get(adapter.getImageDescriptor(element));
			}
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object element) {
		
		if (element instanceof IARESModuleRoot) {
			String type = ((IARESModuleRoot)element).getType();
			IModuleRootDescriptor rootDesc = ModulesRootTypeRegistry.getInstance().getModuleRootDescriptor(type);
			if (rootDesc != null && !StringUtil.isEmpty(rootDesc.getName()))
				return rootDesc.getName();				
		} else if (element instanceof IARESModule) {
			if (((IARESModule)element).isDefaultModule())
				return "默认模块";
		} else if (element instanceof ARESResourceCategory) {
			return ((ARESResourceCategory)element).getName();
		} else if (element instanceof IARESResource) {
			IARESResource cResource = (IARESResource)element;
			IResDescriptor descriptor = ARESResRegistry.getInstance().getResDescriptor(cResource.getType());
			if (descriptor != null && !StringUtil.isEmpty(descriptor.getFileName())) {
				return descriptor.getName();
			} else {
				return cResource.getName();
			}
		}
		
		if(element instanceof IARESElement) {
			return ((IARESElement)element).getElementName();
		}
		
		if (element instanceof RefLibContainer) {
			return "引用";
		}
		
		if (element instanceof IAdaptable) {
			IWorkbenchAdapter adapter = (IWorkbenchAdapter)((IAdaptable)element).getAdapter(IWorkbenchAdapter.class);
			if (adapter != null) {
				return adapter.getLabel(element);
			}
		}
		
		return null;
	}
	
	public StyledString getStyledText(Object element) {
		StyledString styled = new StyledString();
		styled.append(getText(element));
		if (element instanceof IReferencedLibrary) {
			IReferencedLibrary lib = (IReferencedLibrary) element;
			if (lib.isExternal())
				styled.append(" - " + lib.getPath(), StyledString.QUALIFIER_STYLER);
		} 
		
		if (element instanceof IResPathEntryElement) {
			IResPathEntry entry = ((IResPathEntryElement) element).getResPathEntry();
			if (entry instanceof IExternalResPathEntry) {
				IExternalResPathEntry externalEntry = (IExternalResPathEntry) entry;
				IRespathProviderDescriptor provider = externalEntry.getProvider();
				if (provider != null)
					styled.append("(Provided by " + provider.getName() + ")", StyledString.DECORATIONS_STYLER);
			}
		}

		return styled;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void addListener(ILabelProviderListener listener) {

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
	 */
	public void dispose() {

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
	 */
	public boolean isLabelProperty(Object element, String property) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void removeListener(ILabelProviderListener listener) {

	}
}
