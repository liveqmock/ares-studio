/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ResGroup;
import com.hundsun.ares.studio.core.registry.IOrderable;

/**
 * 比较(排序用)
 * @author sundl
 */
public class AresElementComparater extends ViewerComparator {

	private static Logger logger = Logger.getLogger(AresElementComparater.class);
	
	private static final int PROJECT = 0;
	private static final int MODULE_ROOT = 100;
	private static final int MODULE = 200;
	private static final int CATEGORY = 300;
	private static final int GROUP = 350;
	private static final int RESOURCE = 400;
	
	private static final int FOLDER = 500;
	private static final int FILE = 600;
	
	private static final int OTHER = Integer.MAX_VALUE;
	
	CommonElementContentProvider cep = new CommonElementContentProvider(false);
	CommonElementLabelProvider clp = new CommonElementLabelProvider(cep);

	@Override
	public int category(Object element) {
		if(element instanceof IARESElement) {
			IARESElement ae = (IARESElement)element;
			
			switch(ae.getElementType()) {
				case IARESElement.ARES_PROJECT:
					return PROJECT;
				case IARESElement.COMMON_MODULE_ROOT:
					return MODULE_ROOT;
				case IARESElement.COMMON_MODULE:
					return MODULE;		
				case IARESElement.ARES_RESOURCE:
					return RESOURCE;
				default:
					return OTHER;
			}
		} else if (element instanceof ARESResourceCategory) {
			return CATEGORY;
		} else if (element instanceof ResGroup) {
			return GROUP;
		} else if (element instanceof IFolder) {
			return FOLDER;
		} else if (element instanceof IFile) {
			return FILE;
		}
		
		return OTHER - 1;
	}
	
    public int compare(Viewer viewer, Object e1, Object e2) {
        int cat1 = category(e1);
        int cat2 = category(e2);

        if (cat1 != cat2) {
			return cat1 - cat2;
		}
        
        if (e1 instanceof IARESModuleRoot || e1 instanceof IARESResource || e1 instanceof ARESResourceCategory) {
        	IOrderable o1 = getOrderable(e1);
        	IOrderable o2 = getOrderable(e2);
        	// 2012-03-20 sundl Orderable不同的时候根据getOrder进行排序； 相同的话，说明是同一种类型，应该用下面的逻辑根据Label进行排序。
        	if (o1 != null && o2 != null && o1.getOrder() != o2.getOrder()) {
        		return o1.getOrder() - o2.getOrder();
        	}
        }
        
        // 2012-03-19 sundl 资源变化会导致资源分类文件夹自动collapse的问题：问题与Sorter有关；
        // 默认的实现(super.compare())利用labelprovider的get text进行排序，但是有的情况下，比如有svn插件的时候，
        // 默认的svn插件配置是在改变的资源Text前面添加一个 '>' 代表有变化，如果没有修改过此设置，svn就会影响排序结果，
        // 导致一些奇怪的情况， 比如资源文件夹被自动collapse; 
        // 所以此处不能使用默认实现，利用我们自己的机制进行实现
        // 
        // -- 上面已经确定是同一种类型了
        if (e1 instanceof IARESModule || e1 instanceof IARESResource || e1 instanceof IARESModuleRoot) {
        	ARESElementLabelProvider lbProvider = clp;//new ARESElementLabelProvider();
        	String str1 = lbProvider.getText(e1);
        	String str2 = lbProvider.getText(e2);
        	int result = str1.compareTo(str2);
        	logger.debug(String.format("compare %s and %s, result is: %s", str1, str2, result));
        	return result;
        }
        
        int result = super.compare(viewer, e1, e2);
        logger.debug(String.format("%s and %s compare result: %s", String.valueOf(e1), String.valueOf(e2), result));
        return result;
    }
    
	private IOrderable getOrderable(Object element) {
		if (element instanceof IOrderable) {
			return (IOrderable) element;
		} else if (element instanceof IAdaptable) {
			return (IOrderable) ((IAdaptable) element).getAdapter(IOrderable.class);
		}
		return null;
	}
	
}
