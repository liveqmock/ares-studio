/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.util;

public class KeyValue {
	public KeyValue(Object key,Object value) {
		this.key = key;
		this.value = value;
	}
	Object key;
	Object value;
	public Object getKey() {
		return key;
	}
	public Object getValue() {
		return value;
	}
}
