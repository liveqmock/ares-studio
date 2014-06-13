/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.ui.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.core.util.ResourcesUtil;

/**
 * 
 * 用于系统复制粘贴的工具类。
 * <li>只支持EMF对象 </li>
 * <li>对象可以进行适配 </li>
 * @author gongyf
 *
 */
public class ARESEMFClipboard {
	
	private static ARESEMFClipboard instance;
	private ARESEMFClipboard () {
		
	}
	
	/**
	 * @return the instance
	 */
	public static ARESEMFClipboard getInstance() {
		if (instance == null) {
			instance = new ARESEMFClipboard();
		}
		return instance;
	}
	
	private List<IJRESClipboardListener> listeners = new ArrayList<IJRESClipboardListener>();
	
	/**
	 * 添加剪贴板监听器
	 * @param listener
	 */
	public synchronized void addClipboardListener(IJRESClipboardListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * 删除剪贴板监听器
	 * @param listener
	 */
	public synchronized void removeClipboardListener(IJRESClipboardListener listener) {
		listeners.remove(listener);
	}
	
	
	protected void fireAboutToBeChanged() {
		for (Iterator<IJRESClipboardListener> iterator = listeners.iterator(); iterator.hasNext();) {
			try {
				iterator.next().clipboardAboutToBeChanged(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void fireChanged() {
		for (Iterator<IJRESClipboardListener> iterator = listeners.iterator(); iterator.hasNext();) {
			try {
				iterator.next().clipboardChanged(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void copyToClipboard(EObject... eObjects) {
		copyToClipboard(eObjects, null, null);
	}
	
	/**
	 * 把数据保存到系统剪贴板中去，可以同时保存EMF模型、文本和图片<BR>
	 * 文本和图片可以为<code>null</code>
	 * 
	 * @param eObjects
	 * @param text
	 * @param imageData
	 */
	public void copyToClipboard(EObject[] eObjects, String text, ImageData imageData) {
		List<Object> contents = new ArrayList<Object>();
		List<Transfer> transfers = new ArrayList<Transfer>();
		if (eObjects != null) {
			contents.add(eObjects);
			transfers.add(EObjectTransfer.getInstance());
		}
		
		if (text != null) {
			contents.add(text);
			transfers.add(TextTransfer.getInstance());
		}
		
		if (imageData != null) {
			contents.add(imageData);
			transfers.add(ImageTransfer.getInstance());
		}
		
		if (contents.size() > 0) {
			Clipboard clipboard = new Clipboard(Display.getDefault());
			fireAboutToBeChanged();
			clipboard.setContents(contents.toArray(), transfers.toArray(new Transfer[transfers.size()]));
			fireChanged();
			clipboard.dispose();
		}
	}
	
	/**
	 * 从系统剪贴板中获取指定类型的模型，如果存入的模型可以适配则会进行自动适配
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public <T> List<T> pasteFromClipboard(Class<T> clazz) {
		Clipboard clipboard = new Clipboard(Display.getDefault());
		try {
			EObject[] objects = (EObject[]) clipboard.getContents(EObjectTransfer.getInstance());
			if (!ArrayUtils.isEmpty(objects)) {
				List<T> result = new ArrayList<T>();
				for (int i = 0; i < objects.length; i++) {
					T o = ResourcesUtil.getAdapter(objects[i], clazz, true);
					if (o != null) {
						result.add(o);
					}
				}
				return result;
			}
			return Collections.emptyList();
		} finally {
			clipboard.dispose();
		}
	}
	
	/**
	 * 从剪贴板中获取文本信息
	 * @return
	 */
	public String pasteFromClipboardByString() {
		Clipboard clipboard = new Clipboard(Display.getDefault());
		try {
			String textData = (String) clipboard.getContents(TextTransfer.getInstance());
			return textData;
		} finally {
			clipboard.dispose();
		}
	}
	
	/**
	 * 从系统剪贴板中获取图片信息
	 * @return
	 */
	public ImageData pasteFromClipboardByImage() {
		Clipboard clipboard = new Clipboard(Display.getDefault());
		try {
			ImageData data = (ImageData) clipboard.getContents(ImageTransfer.getInstance());
			return data;
		} finally {
			clipboard.dispose();
		}
	}
}
