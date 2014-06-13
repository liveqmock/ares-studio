/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.chouse.reference;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.jres.model.chouse.RemovedTableColumn;

/**删除表格列引用关系
 * @author liaogc
 *
 */
public class RemoveTableColumnModificationReference extends ModifyReference{

	private EList<RemovedTableColumn> columns;
    private String stdOldValue;

	/**
	 * @param type
	 */
	public RemoveTableColumnModificationReference(String type,String version,EList<RemovedTableColumn> columns) {
		super(type);
		this.version = version;
		this.columns = columns;
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
		if(this.columns!=null){
			for(RemovedTableColumn column:columns){
				if(StringUtils.equals(column.getName(), stdOldValue)){
					column.setName(value);
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
			for(RemovedTableColumn column:columns){
				vallue.append(column.getName()).append("_");
			}
		}
		if(StringUtils.endsWith(vallue.toString(), "_")){
			StringUtils.removeEnd(vallue.toString(), "_");
		}
		return vallue.toString();
	}
}
