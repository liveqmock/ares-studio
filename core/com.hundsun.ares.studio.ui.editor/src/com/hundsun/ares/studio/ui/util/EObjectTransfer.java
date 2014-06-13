/**
 * 源程序名称：EObjectTransfer.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.TransferData;

import com.hundsun.ares.studio.jres.modelconvert.ModelConverterUtils;

/**
 * 
 * 处理的类型是<code> {@link EObject[]}</code>
 * @author gongyf
 *
 */
public class EObjectTransfer extends ByteArrayTransfer {

	private static final String TYPE_NAME = "com.hundsun.ares.jres.emf"; //$NON-NLS-1$

	private static final int TYPE_ID = registerType(TYPE_NAME);
	
	private static EObjectTransfer instance;
	
	/**
	 * @return the instance
	 */
	public static EObjectTransfer getInstance() {
		if (instance == null) {
			instance = new EObjectTransfer();
		}
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.swt.dnd.Transfer#getTypeIds()
	 */
	@Override
	protected int[] getTypeIds() {
		return new int[] {TYPE_ID};
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.dnd.Transfer#getTypeNames()
	 */
	@Override
	protected String[] getTypeNames() {
		return new String[] {TYPE_NAME};
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.swt.dnd.ByteArrayTransfer#javaToNative(java.lang.Object, org.eclipse.swt.dnd.TransferData)
	 */
	@Override
	protected void javaToNative(Object object, TransferData transferData) {
		if (isSupportedType(transferData)) {
			if (object instanceof EObject[]) {
				Collection<EObject> copied = EcoreUtil.copyAll(Arrays.asList((EObject[])object));
				Resource resource = new XMIResourceImpl();
				resource.getContents().addAll(copied);
				
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				try {
					resource.save(bos, ModelConverterUtils.EMF_SAVE_OPTIONS);
					super.javaToNative(bos.toByteArray(), transferData);
				} catch (IOException e) {
					
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.swt.dnd.ByteArrayTransfer#nativeToJava(org.eclipse.swt.dnd.TransferData)
	 */
	@Override
	protected Object nativeToJava(TransferData transferData) {
		if (isSupportedType(transferData)) {
			byte[] bytes = (byte[]) super.nativeToJava(transferData);
			if (!ArrayUtils.isEmpty(bytes)) {
				ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
				
				Resource resource = new XMIResourceImpl();
				try {
					resource.load(bis, ModelConverterUtils.EMF_LOAD_OPTIONS);
					
					Collection<EObject> copied = EcoreUtil.copyAll(resource.getContents());
					
					return copied.toArray(new EObject[copied.size()]);
				} catch (IOException e) {
				}
			}
		}
		return null;
	}

	
}
