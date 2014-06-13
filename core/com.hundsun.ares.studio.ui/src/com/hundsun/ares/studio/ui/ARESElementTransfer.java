/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.TransferData;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESElement;

/**
 * 默认的ARESElementTransfer的实现。
 * @author sundl
 */
public class ARESElementTransfer extends ByteArrayTransfer {
	
	private static final ARESElementTransfer instance= new ARESElementTransfer();

	// Create a unique ID to make sure that different Eclipse
	// applications use different "types" of <code>ARESElementTransfer</code>
	 private static final String TYPE_NAME= "ares-element-transfer-format:" + System.currentTimeMillis() + ":" + instance.hashCode(); //$NON-NLS-2$//$NON-NLS-1$

	private static final int TYPE_ID = registerType(TYPE_NAME);
	
	public static ARESElementTransfer getInstance() {
		return instance;
	}
	
	private ARESElementTransfer() {} 
	
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

	protected void javaToNative(Object data, TransferData transferData) {
		if (!(data instanceof IARESElement[])) {
			return;
		}
		
		IARESElement[] aresElements = (IARESElement[]) data;
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			DataOutputStream dataOut = new DataOutputStream(out);
			
			// write the number of elements
			dataOut.writeInt(aresElements.length);
			
			// write each element
			for (IARESElement element : aresElements) {
				writeARESElement(dataOut, element);
			}
			
			dataOut.close();
			out.close();
			byte[] bytes = out.toByteArray();
			super.javaToNative(bytes, transferData);			
		} catch (IOException e) {
			/// do nothing
		}
	}
	
	protected Object nativeToJava(TransferData transferData) {
		byte[] bytes = (byte[]) super.nativeToJava(transferData);
		if (bytes == null) {
			return null;
		}
		
		DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes));
		try {
			int count = in.readInt();
			IARESElement[] results = new IARESElement[count];
			for (int i = 0; i < count; i++) {
				results[i] = readARESElement(in);
			}
			return results;
		} catch (IOException e) {
			return null;
		}
	}
	
	private IARESElement readARESElement(DataInputStream dataIn) throws IOException {
		String handleIdentifier = dataIn.readUTF();
		return ARESCore.create(handleIdentifier);
	}
	
	private static void writeARESElement(DataOutputStream dataOut, IARESElement element) throws IOException {
		dataOut.writeUTF(element.getHandleIdentifier());
	}
	
}
