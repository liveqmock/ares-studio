/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.chouse.reference;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.hundsun.ares.studio.core.model.ReferenceWithNamespace;
import com.hundsun.ares.studio.core.model.impl.ConditionReference;
import com.hundsun.ares.studio.core.model.impl.ReferenceImpl;

/**
 * @author liaogc
 *
 */
public class ModifyReference   extends ReferenceImpl implements ConditionReference{
	private static final String SEPARATOR		= ".";
	protected String version;
	protected String projectVersion;
	
	public ModifyReference(String type){
		super();
		this.type = type;
	}


	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.impl.ConditionReference#canDo(java.util.Map)
	 */
	@Override
	public boolean canDo(Map<Object, Object> parameters) {
		//版本的判断
		if(StringUtils.isBlank(version)|| StringUtils.isBlank(projectVersion)){
			return false;
		}
		return compareVersion(projectVersion,version) ;
	}
	
	/**
	 * 字符串比较大小，v1>=v2 返回true ,反之返回false
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	private  boolean compareVersion(String v1, String v2) {
		if (StringUtils.equals(v1, v2)) {
			return true;
		}
		String[] v1s = StringUtils.split(v1, ".");
		String[] v2s = StringUtils.split(v2, ".");
		if (v1 == null || v2 == null) {
			return false;
		}
		try {
			String[] tempVs = null;
			if (v1s.length > v2s.length) {
				tempVs = new String[v1s.length];
				System.arraycopy(v2s, 0, tempVs, 0, v2s.length);
				for (int i = 0; i < tempVs.length - v2s.length; i++) {
					tempVs[v2s.length + i] = "0";
				}
				return compareInt(v1s, tempVs);
			} else {
				tempVs = new String[v2s.length];
				System.arraycopy(v1s, 0, tempVs, 0, v1s.length);
				for (int i = 0; i < tempVs.length - v1s.length; i++) {
					tempVs[v1s.length + i] = "0";
				}
				return compareInt(tempVs, v2s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private  boolean compareInt(String[] v1, String[] v2) {
		for (int i = 0; i < v1.length; i++) {
			int v1i = Integer.parseInt(v1[i]);
			int v2i = Integer.parseInt(v2[i]);
			if (v1i > v2i) {
				return true;
			} else if (v1i < v2i) {
				return false;
			}
		}
		return false;
	}

	

	

}
