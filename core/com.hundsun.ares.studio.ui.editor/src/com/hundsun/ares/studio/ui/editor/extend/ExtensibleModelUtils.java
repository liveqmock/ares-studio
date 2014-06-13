/**
 * 源程序名称：ExtensibleModelUtils.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.ui.editor.extend;

import java.security.Provider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.ui.editor.IDiagnosticProvider;
import com.hundsun.ares.studio.ui.editor.extend.user.IUserExtendedPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.user.UserExtendedPropertyColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.extend.user.UserExtendedPropertyEditingSupport;

/**
 * @author gongyf
 * 
 */
public class ExtensibleModelUtils {

	/**
	 * 获取当前需要提供的编辑支持
	 * 
	 * @param resource
	 * @param eClass
	 * @return
	 */
	public static IExtensibleModelEditingSupport[] getEndabledEditingSupports(
			IARESElement resource, EClass eClass) {
		IExtensibleModelEditingSupport[] editingSupports = ExtensibleModelManager
				.getInstance().getExtensibleModelEditingSupports(eClass);
		List<IExtensibleModelEditingSupport> elementList = new ArrayList<IExtensibleModelEditingSupport>();
		for (IExtensibleModelEditingSupport es : editingSupports) {
			if (es.isEnable(resource, eClass)) {
				elementList.add(es);
			}
		}
		return elementList
				.toArray(new IExtensibleModelEditingSupport[elementList.size()]);
	}

	/**
	 * 对于指定可扩展模型进行初始化操作，装载被定义的扩展对象
	 * 
	 * @param resource
	 * @param model
	 * @param force
	 *            强制覆盖存在的扩展属性
	 */
	public static void extend(IARESElement element, ExtensibleModel model, boolean force) {
		IExtensibleModelEditingSupport[] supports = getEndabledEditingSupports(
				element, model.eClass());
		for (IExtensibleModelEditingSupport support : supports) {
			if (force || !model.getData2().containsKey(support.getKey())) {
				model.getData2().put(support.getKey(),
						support.createMapValueObject());
			}
		}
	}

	/**
	 * 将模型中的扩展模型进行初始化操作，装载被定义的扩展对象
	 * 
	 * @param resource
	 * @param res
	 * @param force
	 *            强制覆盖存在的扩展属性
	 */
	public static void extendResource(IARESElement element, Resource res,
			boolean force) {
		List<ExtensibleModel> models = new ArrayList<ExtensibleModel>();
		for (Iterator<Object> iterator = EcoreUtil.getAllContents(res, true); iterator
				.hasNext();) {
			Object obj = iterator.next();
			// 这里不能直接操作模型，需要先遍历完
			if (obj instanceof ExtensibleModel) {
				models.add((ExtensibleModel) obj);
			}
		}

		for (ExtensibleModel model : models) {
			extend(element, model, force);
		}
	}

	/**
	 * 创建扩展列
	 * 
	 * @param viewer
	 * @param resource
	 * @param eClass
	 * @param diagnosticProvider
	 */
	public static void createExtensibleModelTableViewerColumns(
			TableViewer viewer, IARESResource resource, EClass eClass,
			IDiagnosticProvider diagnosticProvider) {
		createExtensibleModelTableViewerColumns(viewer, resource, eClass, diagnosticProvider, true);
	}

	/**
	 * 创建扩展列
	 * 
	 * @param viewer
	 * @param resource
	 * @param eClass
	 * @param diagnosticProvider
	 */
	public static void createExtensibleModelTableViewerColumns(
			TableViewer viewer, IARESResource resource, EClass eClass,
			IDiagnosticProvider diagnosticProvider, boolean isEditingSupport) {
		IExtensibleModelEditingSupport[] editingSupports = ExtensibleModelUtils
				.getEndabledEditingSupports(resource, eClass);
		GC gc = new GC(viewer.getControl());

		try {
			for (IExtensibleModelEditingSupport support : editingSupports) {
				for (IExtensibleModelPropertyDescriptor descriptor : support
						.getPropertyDescriptors(resource, eClass)) {
					TableViewerColumn tvColumn = new TableViewerColumn(viewer,	SWT.LEFT);

					// 长度根据现实的名称类决定
					String displayName = descriptor.getDisplayName();

					Point p = gc.stringExtent(displayName);

					tvColumn.getColumn().setWidth(p.x + 20);
					tvColumn.getColumn().setText(displayName);
					
					// 新接口标志，用新的处理方式
					if (descriptor instanceof IUserExtendedPropertyDescriptor) {
						tvColumn.setLabelProvider(new UserExtendedPropertyColumnLabelProvider(descriptor));
						tvColumn.setEditingSupport(new UserExtendedPropertyEditingSupport(viewer, descriptor));
					} else {
						// 保留兼容老的处理方式
						ExtensibleModelColumnLabelProvider provider = new ExtensibleModelColumnLabelProvider(
								support, descriptor , resource);
						provider.setDiagnosticProvider(diagnosticProvider);
						tvColumn.setLabelProvider(provider);
						if (isEditingSupport) {
							tvColumn.setEditingSupport(new ExtensibleModelEditingSupport(
									viewer, support, descriptor));
						}
					}
					tvColumn.getColumn().setMoveable(true);
				}

			}
		} finally {
			gc.dispose();
		}
	}

	/**
	 * 创建扩展列
	 * 
	 * @param viewer
	 * @param resource
	 * @param eClass
	 * @param diagnosticProvider
	 */
	public static void createExtensibleModelTreeViewerColumns(
			TreeViewer viewer, IARESResource resource, EClass eClass,
			IDiagnosticProvider diagnosticProvider) {
		// 2012-10-29 sundl 如果取不到resource，不能执行 
		if (resource == null) {
			return;
		}
		IExtensibleModelEditingSupport[] editingSupports = ExtensibleModelUtils
				.getEndabledEditingSupports(resource, eClass);
		GC gc = new GC(viewer.getControl());

		try {
			for (IExtensibleModelEditingSupport support : editingSupports) {
				for (IExtensibleModelPropertyDescriptor descriptor : support.getPropertyDescriptors(resource, eClass)) {
					TreeViewerColumn tvColumn = new TreeViewerColumn(viewer, SWT.LEFT);

					// 长度根据现实的名称类决定
					String displayName = descriptor.getDisplayName();

					Point p = gc.stringExtent(displayName);

					tvColumn.getColumn().setWidth(p.x + 20);
					tvColumn.getColumn().setText(displayName);

					if (descriptor instanceof IUserExtendedPropertyDescriptor) {
						tvColumn.setLabelProvider(new UserExtendedPropertyColumnLabelProvider(descriptor));
						tvColumn.setEditingSupport(new UserExtendedPropertyEditingSupport(viewer, descriptor));
					} else {
						ExtensibleModelColumnLabelProvider provider = new ExtensibleModelColumnLabelProvider(support, descriptor, resource);
						provider.setDiagnosticProvider(diagnosticProvider);
						tvColumn.setLabelProvider(provider);

						tvColumn.setEditingSupport(new ExtensibleModelEditingSupport(viewer, support, descriptor));
					}

					tvColumn.getColumn().setMoveable(true);
				}

			}
		} finally {
			gc.dispose();
		}
	}

	public static ExtensibleModel getHostExtensibleModel(EObject obj) {
		EObject parent = obj;
		while (parent != null && !(parent instanceof ExtensibleModel)) {
			parent = parent.eContainer();
		}
		return (ExtensibleModel) parent;
	}
	
}
