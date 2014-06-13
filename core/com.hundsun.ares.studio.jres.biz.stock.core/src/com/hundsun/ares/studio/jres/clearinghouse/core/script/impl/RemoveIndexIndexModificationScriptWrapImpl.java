/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.chouse.RemoveIndexField;
import com.hundsun.ares.studio.jres.model.chouse.RemoveIndexFieldModification;
import com.hundsun.ares.studio.jres.model.database.DatabaseFactory;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.script.api.database.ITableIndexScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IRemoveIndexFieldModificationScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

/**
 * @author liaogc
 *
 */
public class RemoveIndexIndexModificationScriptWrapImpl extends CommonScriptWrap<RemoveIndexFieldModification>
		implements IRemoveIndexFieldModificationScriptWrap {

	public RemoveIndexIndexModificationScriptWrapImpl(RemoveIndexFieldModification removeIndexField ,IARESResource resource) {
		super(removeIndexField ,resource);
	}

	public ITableIndexScriptWrap[] getDetails(){
		List<ITableIndexScriptWrap> removeIndexes = new ArrayList<ITableIndexScriptWrap>();
		for(RemoveIndexField removeIndexField : getOriginalInfo().getIndexs()){
			TableIndex tableIndex = DatabaseFactory.eINSTANCE.createTableIndex();
			TableIndex oldTableIndex = getTableIndex(removeIndexField.getName(),removeIndexField.getMark());
			if(oldTableIndex!=null){
				tableIndex = EcoreUtil.copy(oldTableIndex);
				//把要删除的字段从原索引中去除
				for (TableIndexColumn field : removeIndexField.getIndexFields()) {
					Iterator<TableIndexColumn> iter = tableIndex.getColumns().iterator();
					while(iter.hasNext()){
						TableIndexColumn tableIndexColumn = iter.next();
						if(StringUtils.equals(field.getColumnName(), tableIndexColumn.getColumnName())){
							iter.remove();
						}
					}
				}
			}
			tableIndex.setMark(removeIndexField.getMark());
			removeIndexes.add(new TableIndexScriptWrapImpl(null ,tableIndex, resource));
		}
		return removeIndexes.toArray(new ITableIndexScriptWrap[0]);
	} 
	
	/**
	 * 根据索引名称获得对应的索引
	 * @param name
	 * @return
	 */
	private TableIndex getTableIndex(String name,String mark){
		try {
			TableResourceData tableResourceData = resource.getInfo(TableResourceData.class);
			for(TableIndex tableIndex :tableResourceData.getIndexes()){
				if(StringUtils.equals(name, tableIndex.getName())){
					if(StringUtils.isNotBlank(mark)){
						if(StringUtils.equals(mark, tableIndex.getMark())){
							return tableIndex;
						}
					}else{
						return tableIndex;
					}
					
				}
			}
			//如果没有找到带标记的索引
			for(TableIndex tableIndex :tableResourceData.getIndexes()){
				if(StringUtils.equals(name, tableIndex.getName())){
					if(StringUtils.isBlank(tableIndex.getMark())||StringUtils.equalsIgnoreCase(tableIndex.getMark(), "c")){
						return tableIndex;
					}
					
				}
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}