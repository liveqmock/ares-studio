/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor.text.annotation;

import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationPresentation;
import org.eclipse.jface.text.source.ImageUtilities;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.hundsun.ares.studio.core.IARESProblem;

/**
 * 对应于一个ARESProblem的Annotation
 * @author sundl
 */
public class ARESProblemAnnotation extends Annotation implements IAnnotationPresentation{

	private IARESProblem problem;
	
	private static Image errorImage = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_DEC_FIELD_ERROR);
	private static Image warnImage = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_DEC_FIELD_WARNING);
	
	private Image image;
	
	public ARESProblemAnnotation(IARESProblem problem) {
		this.problem = problem;
		if (problem.isError()) {
			image = errorImage;
		} else if (problem.isWarning()) {
			image = warnImage;
		}
	}
	
	public String getText() {
		return problem.getMessage();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.source.IAnnotationPresentation#getLayer()
	 */
	@Override
	public int getLayer() {
		return 1;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.source.IAnnotationPresentation#paint(org.eclipse.swt.graphics.GC, org.eclipse.swt.widgets.Canvas, org.eclipse.swt.graphics.Rectangle)
	 */
	@Override
	public void paint(GC gc, Canvas canvas, Rectangle bounds) {
		if (image != null)
			ImageUtilities.drawImage(image, gc, canvas, bounds, SWT.CENTER, SWT.TOP);
	}
}
