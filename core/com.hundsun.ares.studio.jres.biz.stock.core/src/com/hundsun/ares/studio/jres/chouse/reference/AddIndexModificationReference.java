/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.chouse.reference;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;

/**
 * @author liaogc
 *
 */
public class AddIndexModificationReference extends ModifyReference{

	private EList<TableIndex> indexs;
    private String stdOldValue;

	/**
	 * @param type
	 */
	public AddIndexModificationReference(String type,String version,EList<TableIndex> indexs) {
		super(type);
		this.version = version;
		this.indexs = indexs;
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
		if(this.indexs!=null){
			for(TableIndex index:indexs){
				for(TableIndexColumn tableIndexColumn:index.getColumns()){
					if(StringUtils.equals(tableIndexColumn.getColumnName(), stdOldValue)){
						tableIndexColumn.setColumnName(value);
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
		if(this.indexs!=null){
			for(TableIndex index:indexs){
				for(TableIndexColumn tableIndexColumn:index.getColumns()){
					vallue.append(tableIndexColumn.getColumnName()).append("_");
				}
			}
		}
		if(StringUtils.endsWith(vallue.toString(), "_")){
			StringUtils.removeEnd(vallue.toString(), "_");
		}
		return vallue.toString();
	}
}