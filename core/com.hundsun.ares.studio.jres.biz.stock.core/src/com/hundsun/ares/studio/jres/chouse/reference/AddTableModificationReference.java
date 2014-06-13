/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.chouse.reference;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;

/**
 * @author liaogc
 *
 */
public class AddTableModificationReference extends ModifyReference{

	private EList<TableColumn> columns;
	private  EList<TableIndex> indexes;
    private String stdOldValue;

	/**
	 * @param type
	 */
	public AddTableModificationReference(String type,String version,EList<TableColumn> columns,EList<TableIndex> indexes) {
		super(type);
		this.version = version;
		this.columns = columns;
		this.indexes = indexes;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.chouse.reference.ModifyReference#canDo(java.util.Map)
	 */
	@Override
	public boolean canDo(Map<Object, Object> parameters) {

		if(parameters==null || parameters.size()==0 ) {
			return false;
		}
		if(parameters.get("newValue")!=null && parameters.get("oldValue")!=null &&parameters.get("version")!=null){
			 stdOldValue = (String) parameters.get("oldValue");
			 String stdNewValue = (String) parameters.get("newValue");
			StringUtils.equals(stdOldValue, stdNewValue);
			this.projectVersion = (String) parameters.get("version");
			//版本的判断super.canDo(parameters);
			return !StringUtils.equals(stdOldValue, stdNewValue)&& super.canDo(parameters);
		}
		
		return false;
	
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.impl.ReferenceImpl#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String value) {
		//修改表字段
		if(this.columns!=null){
			for(TableColumn column:columns){
				if(StringUtils.equals(column.getFieldName(), stdOldValue)){
					column.setFieldName(value);
				}
			}
		}
		//修改表索引
		if(this.indexes!=null){
			for(TableIndex indexe:indexes){
				for (TableIndexColumn indexColumn:indexe.getColumns()){
					if(StringUtils.equals(indexColumn.getColumnName(), stdOldValue)){
						indexColumn.setColumnName(value);
					}
				}
				
			}
		}
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.impl.ReferenceImpl#getValue()
	 */
	@Override
	public String getValue() {
		StringBuffer vallue = new StringBuffer();
		if(this.columns!=null){
			for(TableColumn column:columns){
				vallue.append(column.getFieldName()).append("_");
			}
		}
		if(this.indexes!=null){
			for(TableIndex indexe:indexes){
				vallue.append(indexe.getName()).append("_");
			}
		}
		if(StringUtils.endsWith(vallue.toString(), "_")){
			StringUtils.removeEnd(vallue.toString(), "_");
		}
		return vallue.toString();
	}
}

