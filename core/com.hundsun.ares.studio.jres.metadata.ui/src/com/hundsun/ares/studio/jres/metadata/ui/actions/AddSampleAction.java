/**
 * 源程序名称：AddSampleAction.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ColumnViewer;

import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.Operation;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerAction;

/**
 * 根据需求添加示例操作
 * @author wangxh
 *
 */
public class AddSampleAction extends ColumnViewerAction {

	private EObject owner;
	private EClass eClass;
	private String text;
	private ColumnViewer viewer;
	private URL url;
	private String scriptLocation;
	/**
	 * @param viewer
	 * @param editingDomain
	 * @param text  标题
	 * @param owner 
	 * @param path   路径
	 * @param project
	 */
	public AddSampleAction(ColumnViewer viewer, EditingDomain editingDomain,String text,EObject owner, URL url,String scriptLocation) {
		super(viewer, editingDomain);
		setText(text);
		setEnabled(true);
		this.owner = owner;
		this.eClass=MetadataPackage.Literals.OPERATION;
		this.text=text;
		this.viewer=viewer;
		this.url=url;
		this.scriptLocation =  scriptLocation;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.actions.ColumnViewerAction#createCommand()
	 */
	@Override
	protected Command createCommand() {
		//根据提供的脚本文件实现操作
		EObject newObj = eClass.getEPackage().getEFactoryInstance().create(eClass);
		Command command = AddCommand.create(getEditingDomain(), owner, 
				MetadataPackage.Literals.METADATA_RESOURCE_DATA__OPERATIONS, newObj);
		if(newObj instanceof Operation){
			((Operation)newObj).setTitle(text);
			((Operation)newObj).setFile(this.scriptLocation);
			((Operation)newObj).setFunctionName("main");
			InputStream is;
			try {
				is = FileLocator.toFileURL(url).openStream();
				((Operation)newObj).setCode(convertStreamToString(is));
			} catch (Exception e) {
				MessageDialog.openError(viewer.getControl().getShell(),"文件不存在", "该示例的脚本文件不存在！");
				e.printStackTrace();
				return null;
			}
		}
		return command;
	}

	/**
	 * @param is
	 * @return
	 */
	private String convertStreamToString(InputStream is) {
		 BufferedReader reader;
			try {
				reader = new BufferedReader(new InputStreamReader(is));
		        StringBuilder sb = new StringBuilder();    
		     
		        String line = null;    
		        try {    
		            while ((line = reader.readLine()) != null) {    
		                sb.append(line + "\n");    
		            }    
		        } catch (IOException e) {    
		            e.printStackTrace();    
		        } finally {    
		            try {    
		            	is.close();    
		            } catch (IOException e) {    
		                e.printStackTrace();    
		            }    
		        }    
		     
		        return sb.toString();    
			} catch (Exception e1) {
				MessageDialog.openError(viewer.getControl().getShell(),"文件打开异常", "该示例的脚本文件打开异常！");
				e1.printStackTrace();
			}
			return null;
	}

}
