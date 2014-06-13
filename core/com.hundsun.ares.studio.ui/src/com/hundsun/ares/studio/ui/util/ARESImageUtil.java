/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.util;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * 
 * @author Administrator
 */
public class ARESImageUtil {

	private static ImageDescriptor IMG_DEC_ERROR = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_ERROR);
	private static ImageDescriptor IMG_DEC_WARNING = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_WARNING);

	private static Image IMG_ERROR = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_ERROR_TSK);
	private static Image IMG_WARNING = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_WARN_TSK);
	
	
	public static Image getERRORImage(){
		return IMG_ERROR;
	}
	
	public static Image getWARNINGImage(){
		return IMG_WARNING;
	}
	
	public static ImageDescriptor getERRORImageDescriptor(){
		return IMG_DEC_ERROR;
	}
	
	public static ImageDescriptor getWARNINGImageDescriptor(){
		return IMG_DEC_WARNING;
	}
}
