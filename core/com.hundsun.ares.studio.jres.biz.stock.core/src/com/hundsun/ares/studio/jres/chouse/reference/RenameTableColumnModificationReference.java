/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.chouse.reference;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.jres.model.chouse.RTCMDetail;

/**
 * 重命名表格列
 * @author liaogc
 *
 */
public class RenameTableColumnModificationReference extends ModifyReference{

	private EList<RTCMDetail> details;
    private String stdOldValue;

	/**
	 * @param type
	 */
	public RenameTableColumnModificationReference(String type,String version,EList<RTCMDetail> details) {
		super(type);
		this.version = version;
		this.details = details;
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
		if(this.details!=null){
			for(RTCMDetail detail:details){
				if(StringUtils.equals(detail.getOldName(), stdOldValue)){
					detail.setOldName(value);
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
		if(this.details!=null){
			for(RTCMDetail detail:details){
				vallue.append(detail.getOldName()).append("_");
			}
		}
		if(StringUtils.endsWith(vallue.toString(), "_")){
			StringUtils.removeEnd(vallue.toString(), "_");
		}
		return vallue.toString();
	}
}
