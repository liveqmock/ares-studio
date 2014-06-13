package com.hundsun.ares.studio.ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.TransferData;

public class ARESClipboardDataTransfer extends ByteArrayTransfer {

	private static ARESClipboardDataTransfer instance = new ARESClipboardDataTransfer();
	private static final String TYPE_NAME = "ares-clipbord-data"; //$NON-NLS-1$
	private static final int TYPE_ID = registerType(TYPE_NAME);
	
	private ARESClipboardDataTransfer() {
		
	}

	public static ARESClipboardDataTransfer getInstance() {
		return instance;
	}
	
    public void javaToNative(Object object, TransferData transferData) {
        if (object == null || !(object instanceof IARESClipboardData[]))
            return;

        if (isSupportedType(transferData)) {
        	IARESClipboardData[] myTypes = (IARESClipboardData[]) object;
            try {
                // write data to a byte array and then ask super to convert to pMedium
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                DataOutputStream writeOut = new DataOutputStream(out);

                /* The serialization format is:
                 * (int) number of elements
                 * Then, the following for each element:
                 * (String) format type
                 * (byte[]) data
                 */

                writeOut.writeInt(myTypes.length);

                byte[] array = null;

                for (int i = 0; i < myTypes.length; i++) {
                    writeOut.writeUTF(myTypes[i].getFormatType());
                    array = myTypes[i].getData();

                    writeOut.writeInt(array.length);
                    writeOut.write(array);
                }

                byte[] buffer = out.toByteArray();
                writeOut.close();

                super.javaToNative(buffer, transferData);

            } catch (IOException e) {
				// TODO
			}
        }
    }
    
    public Object nativeToJava(TransferData transferData) {

        if (isSupportedType(transferData)) {

            byte[] buffer = (byte[]) super.nativeToJava(transferData);

            if (buffer == null) {
                return null;
            }

            try {
                ByteArrayInputStream in = new ByteArrayInputStream(buffer);
                DataInputStream readIn = new DataInputStream(in);

                int count = readIn.readInt();
                IARESClipboardData[] myData = new IARESClipboardData[count];

                String type = null;
                byte[] array = null;

                for (int i = 0; i < count; i++) {
                    type = readIn.readUTF();
                    array = new byte[readIn.readInt()];
                    readIn.read(array);

                    myData[i] = new ARESClipboardData(type, array);
                }

                readIn.close();

                return myData;

            } catch (IOException ex) {
                return null;
            }
        }

        return null;
    }
    
	@Override
	protected int[] getTypeIds() {
		return new int[]{TYPE_ID};
	}

	@Override
	protected String[] getTypeNames() {
		return new String[]{TYPE_NAME};
	}
	

}
