/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IResPathEntry;
import com.hundsun.ares.studio.internal.core.ReferencedLibrayUtil;


/**
 * 添加外部引用资源包
 * @author sundl
 */
public class AddExternalFileToResPathAction extends AddToResPathAction {
	
	public AddExternalFileToResPathAction(Shell shell) {
		super(shell, "添加外部引用包");
	}
	
    public void run() {
    	FileDialog dialog = new FileDialog(shell, SWT.MULTI);
    	dialog.open();
    	String[] files = dialog.getFileNames();
    	if (files.length == 0)
    		return;
    	
    	String folder = dialog.getFilterPath();
    	for (String file : files) {
    		String path = folder + File.separator + file;
    		if (path.length() >= 200) {
    			MessageDialog.openError(shell, "错误", String.format("所选文件:%s 的路径长度超过200，请移动文件位置或缩短文件(文件夹)名字长度后重新添加。", file));
    			return;
    		}
    		else {
    			File aresFile = new File(path);
    			if (!ReferencedLibrayUtil.testFile(aresFile).isOK()) {
        			MessageDialog.openError(shell, "错误", String.format("所选文件:%s 的不是有效的引用包文件.", file));
        			return;
    			}
    		}
    	}
    	
    	List<IResPathEntry> entries = new ArrayList<IResPathEntry>();
    	for (String file : files) {
    		entries.add(ARESCore.newLibEntry(new Path(folder + File.separator + file)));
    	}
    	addResEntryToResPath(entries.toArray(new IResPathEntry[0]), ARESCore.create(project));
    }
    
}
