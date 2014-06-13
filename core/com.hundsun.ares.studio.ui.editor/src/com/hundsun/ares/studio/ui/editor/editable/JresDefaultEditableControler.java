/**
 * 源程序名称：JresDefaultEditableControler.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.editable;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.ui.part.FileEditorInput;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.internal.core.ArchiveARESResource;
import com.hundsun.ares.studio.ui.ARESResourceEditorInput;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;

/**
 * @author lvgao
 *
 */
public class JresDefaultEditableControler implements IEditableControl{

	EMFFormEditor editor;
	boolean is_readonly = false;
	//用户只读状态
	Map<String, Object> userStatusMap = new HashMap<String, Object>();
	//只读控制单元
	List<IEditableUnit> unitList = new ArrayList<IEditableUnit>();
	
	public JresDefaultEditableControler(EMFFormEditor editor){
		this.editor = editor;
	}
	
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.editors.editable.IEditableControl#getResourceReadonlyStatus()
	 */
	@Override
	public boolean getResourceReadonlyStatus() {
		return is_readonly;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.editors.editable.IEditableControl#putUserStatus(java.lang.String, java.lang.Object)
	 */
	@Override
	public void putUserStatus(String key, Object status) {
		userStatusMap.put(key, status);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.editors.editable.IEditableControl#notifyUserStatus(java.lang.String)
	 */
	@Override
	public void notifyUserStatus(String key) {
		if(!userStatusMap.containsKey(key)){
			return;
		}
		for(IEditableUnit unit:unitList){
			unit.setReadonlyStatus(key,userStatusMap.get(key));
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.editors.editable.IEditableControl#addEditableUnit(com.hundsun.ares.studio.jres.ui.editors.editable.IEditableUnit)
	 */
	@Override
	public void addEditableUnit(IEditableUnit unit) {
		if(null != unit){
			unitList.add(unit);
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.editors.editable.IEditableControl#refreshAllUnit()
	 */
	@Override
	public void refreshAllUnit(Map<Object, Object> context) {
		if(is_readonly){
			//只读状态下控制
			for(IEditableUnit item:unitList){
				item.setReadonlyStatus(IEditableUnit.KEY_SYSTEM,IEditableUnit.EDITABLE_FALSE);
			}
		}else{
			//非只读状态下控制
			for(IEditableUnit item:unitList){
				item.setReadonlyStatus(IEditableUnit.KEY_SYSTEM,IEditableUnit.EDITABLE_TRUE);
				for(Entry<String, Object> entry:userStatusMap.entrySet()){
					item.setReadonlyStatus(entry.getKey(), entry.getValue());
				}
			}
		}
	}


	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.editors.editable.IEditableControl#refreshResourceReadonlyStatus()
	 */
	@Override
	public void refreshResourceReadonlyStatus() {
		/**TODO#只读控制#吕高#中等#编写人#代码状态#代码行(不包括空白行和注释行)#工时(精确到分钟)#编辑器input的只读判断
		*1、文件只读，判定文只读
		*2、引用资源包资源只读
		*3、未注册只读???
		*/
		Object input = editor.getEditorInput();
		File file = new File(((FileEditorInput)input).getURI());
//		RegisterUtil instance = RegisterUtil.instance;
		if(file.exists()){
//			if(instance ==null || !instance.isRegisted()){
//				is_readonly = true;
//			}
			if( !file.canWrite()){
				is_readonly = true;
			}
			else if(input instanceof ARESResourceEditorInput){
				IARESResource resource = ((ARESResourceEditorInput)input).getARESResource();
				if(resource instanceof ArchiveARESResource){
					is_readonly = true;
				}
			}
			else{
				is_readonly = false;
			}
		}
//		is_readonly = false;//editor.isReadOnly();
	}

}
