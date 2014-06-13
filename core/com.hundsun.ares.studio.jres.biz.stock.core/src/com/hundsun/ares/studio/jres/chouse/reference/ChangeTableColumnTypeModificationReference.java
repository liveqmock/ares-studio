/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.chouse.reference;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.jres.model.chouse.CTCTMDetail;

/**
 * @author liaogc
 *
 */
public class ChangeTableColumnTypeModificationReference extends ModifyReference{

	private EList<CTCTMDetail> modifyDetails;
    private String stdOldValue;

	/**
	 * @param type
	 */
	public ChangeTableColumnTypeModificationReference(String type,String version,EList<CTCTMDetail> modifyDetails) {
		super(type);
		this.version = version;
		this.modifyDetails = modifyDetails;
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
		if(this.modifyDetails!=null){
			for(CTCTMDetail modifyDetail:modifyDetails){
				if(StringUtils.equals(modifyDetail.getName(), stdOldValue)){
					modifyDetail.setName(value);
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
		if(this.modifyDetails!=null){
			for(CTCTMDetail modifyDetail:modifyDetails){
				vallue.append(modifyDetail.getName()).append("_");
			}
		}
		if(StringUtils.endsWith(vallue.toString(), "_")){
			StringUtils.removeEnd(vallue.toString(), "_");
		}
		return vallue.toString();
	}
}