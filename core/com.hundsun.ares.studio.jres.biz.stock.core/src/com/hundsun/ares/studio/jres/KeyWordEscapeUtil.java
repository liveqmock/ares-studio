/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuyf
 *
 */
public class KeyWordEscapeUtil {
	
	private static List<KeyWordEntity> keyList = new ArrayList<KeyWordEntity>();
	static{
		keyList.add(new KeyWordEntity("condition","oracle","'condition'"));
		keyList.add(new KeyWordEntity("condition","mysql","`condition`"));
		keyList.add(new KeyWordEntity("precision","oracle","'precision'"));
		keyList.add(new KeyWordEntity("precision","mysql","`precision`"));
		keyList.add(new KeyWordEntity("int","oracle","'int'"));
		keyList.add(new KeyWordEntity("int","mysql","`int`"));
		keyList.add(new KeyWordEntity("int1","oracle","'int1'"));
		keyList.add(new KeyWordEntity("int1","mysql","`int1`"));
		keyList.add(new KeyWordEntity("int2","oracle","'int2'"));
		keyList.add(new KeyWordEntity("int2","mysql","`int2`"));
		keyList.add(new KeyWordEntity("int3","oracle","'int3'"));
		keyList.add(new KeyWordEntity("int3","mysql","`int3`"));
		keyList.add(new KeyWordEntity("int4","oracle","'int4'"));
		keyList.add(new KeyWordEntity("int4","mysql","`int4`"));
		keyList.add(new KeyWordEntity("int5","oracle","'int5'"));
		keyList.add(new KeyWordEntity("int5","mysql","`int5`"));
		keyList.add(new KeyWordEntity("int6","oracle","'int6'"));
		keyList.add(new KeyWordEntity("int6","mysql","`int6`"));
		keyList.add(new KeyWordEntity("int7","oracle","'int7'"));
		keyList.add(new KeyWordEntity("int7","mysql","`int7`"));
		keyList.add(new KeyWordEntity("int8","oracle","'int8'"));
		keyList.add(new KeyWordEntity("int8","mysql","`int8`"));
		keyList.add(new KeyWordEntity("div","oracle","'div'"));
		keyList.add(new KeyWordEntity("div","mysql","`div`"));
	}
	
	public static String getEscapeByNameAndType(String name,String type){
		for(int i = 0;i < keyList.size();i++){
			KeyWordEntity entity = keyList.get(i);
			if(entity.getName().equals(name) && entity.getType().equals(type)){
				return entity.getEscape();
			}
		}
		return name;
	}

}
