/**
 * 源程序名称：EObjectColumnLabelProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.viewers;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.hundsun.ares.studio.ui.editor.IDiagnosticProvider;

public class EObjectColumnLabelProvider extends BaseEObjectColumnLabelProvider {
	
	private static ImageDescriptor IMG_DEC_ERROR = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_ERROR);
	private static ImageDescriptor IMG_DEC_WARNING = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_WARNING);

	private static Image IMG_ERROR = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_ERROR_TSK);
	private static Image IMG_WARNING = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_WARN_TSK);
	
	private IDiagnosticProvider diagnosticProvider;
	
	/**
	 * @param attribute
	 */
	public EObjectColumnLabelProvider(EStructuralFeature attribute) {
		super(attribute);
	}

	/**
	 * @param attributeProvider
	 */
	public EObjectColumnLabelProvider(
			IEStructuralFeatureProvider attributeProvider) {
		super(attributeProvider);
	}

	/**
	 * @param diagnosticProvider the diagnosticProvider to set
	 */
	public void setDiagnosticProvider(IDiagnosticProvider diagnosticProvider) {
		this.diagnosticProvider = diagnosticProvider;
	}
	
	protected Diagnostic getDiagnostic(Object element) {
		if (diagnosticProvider != null) {
			return diagnosticProvider.getDiagnostic(getOwner(element), getEStructuralFeature(element));
		}
		return null;
	}
	
	/**
	 * @return the diagnosticProvider
	 */
	protected IDiagnosticProvider getDiagnosticProvider() {
		return diagnosticProvider;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getImage(java.lang.Object)
	 */
	@Override
	final public Image getImage(Object element) {
		Image image = doGetImage(element);
		// 如果返回是有图标的，则需要加上标记
		
		Diagnostic diagnostic = getDiagnostic(element);
		if (diagnostic != null) {
			if (image == null) {
				switch (diagnostic.getSeverity()) {
				case Diagnostic.ERROR:
					return IMG_ERROR;
				case Diagnostic.WARNING:
					return IMG_WARNING;
				}
			} else {
				DecorationOverlayIcon icon = null;
				switch (diagnostic.getSeverity()) {
				case Diagnostic.ERROR:
					icon = new DecorationOverlayIcon(image, IMG_DEC_ERROR, IDecoration.BOTTOM_LEFT);
					break;
				case Diagnostic.WARNING:
					icon = new DecorationOverlayIcon(image, IMG_DEC_WARNING, IDecoration.BOTTOM_LEFT);
					break;
				}
				if (icon != null) {
					return ExtendedImageRegistry.INSTANCE.getImage(icon);
				} else {
					return image;
				}
				
			}
		}

		return image;
	}
	
	protected Image doGetImage(Object element) {
		return null;
	}
	
	@Override
	public Color getBackground(Object element) {
		return super.getBackground(element);
	}
	
	@Override
	public String getToolTipText(Object element) {
		Diagnostic diagnostic = getDiagnostic(element);
		if (diagnostic != null) {
			return diagnostic.getMessage();
		}
		return super.getToolTipText(element);
	}
	
	@Override
	public int getToolTipTimeDisplayed(Object object) {
		return 0;
	}
}
