/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.basicdata.core.basicdata.validate.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * @author lvgao
 *
 */
public class InfoTableItemLocator  implements IKeyTableLocator{

	String[] keyAttrs;
	List<EObject> items;
	
	Map<String, List<EObject>> cacheMap;
	
	public InfoTableItemLocator(String[] keyAttrs){
		this.keyAttrs = keyAttrs;
		
	}
	
	/**
	 * 初始化
	 * @throws Exception
	 */
	public  void setInput(List<EObject> items) {
		this.items = items;
	}
	
	public  void init() throws Exception{
		if(null == cacheMap){
			 cacheMap = new HashMap<String, List<EObject>>();
		}
		cacheMap.clear();  //清空缓存
		
		if(items.size() == 0 || keyAttrs.length == 0){
			return ;
		}
		
		for(EObject item:items){
			String key = getKeyStr(item);
			if(!cacheMap.containsKey(key)){
				cacheMap.put(key, new ArrayList<EObject>());
			}
			cacheMap.get(key).add(item);
		}
	}
	
	private String getKeyStr(EObject obj) throws Exception{
		StringBuffer buffer = new StringBuffer();
		for(String attr:keyAttrs){
			String str = "";
			EStructuralFeature feature = obj.eClass().getEStructuralFeature(attr);
			if(null == feature){
				throw new Exception(String.format("对象不包含[%s]属性", attr));
			}
			Object tobj = obj.eGet(feature);
			if(null == str){
				str = "";
			}else if(tobj!=null){
				str = tobj.toString().trim();
			}
			buffer.append(str + "\r\n");
		}
		return buffer.toString();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.validate.util.IKeyTableLocator#getLinkObject(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public EObject getLinkObject(EObject obj) throws Exception{
		if(null ==cacheMap ){
			init();  //初始化
		}
		String key = getKeyStr(obj);
		if(cacheMap.containsKey(key)){
			return cacheMap.get(key).get(0);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.validate.util.IKeyTableLocator#getLinkObjectCount(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public int getLinkObjectCount(EObject obj)throws Exception{
		if(null ==cacheMap ){
			init();  //初始化
		}
		String key = getKeyStr(obj);
		if(cacheMap.containsKey(key)){
			return cacheMap.get(key).size();
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.validate.util.IKeyTableLocator#clear()
	 */
	@Override
	public void reset() {
		if(null != cacheMap){
			cacheMap.clear();
		}
		cacheMap = null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.validate.util.IKeyTableLocator#isReady()
	 */
	@Override
	public boolean isReady() {
		return null != cacheMap;
	}

}
