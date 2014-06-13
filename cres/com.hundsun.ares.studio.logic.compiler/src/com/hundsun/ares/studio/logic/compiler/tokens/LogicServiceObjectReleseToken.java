/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.logic.compiler.tokens;

import java.util.Map;
import java.util.Set;

import com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.logic.compiler.constant.ILogicEngineContextConstant;

/**
 * @author liaogc
 *
 */
public class LogicServiceObjectReleseToken implements ICodeToken {
	private final static String OBJ_RELEASE = "if (%1$s)\r\n"
		+"{\r\n"
		+"%1$s->Release();\r\n"
		+"}\r\n";
	private final static String OBJ_OUT_RELEASE = "if (%1$s)\r\n"
		+"{\r\n"
		+"free(%1$s->GetPackBuf());\r\n"
		+"%1$s->Release();\r\n"
		+"}\r\n";
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getContent()
	 */
	@Override
	public String getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getType()
	 */
	@Override
	public int getType() {
		return CODE_TEXT;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#genCode(java.util.Map)
	 */
	@Override
	public String genCode(Map<Object, Object> context) throws Exception {
		StringBuffer code = new StringBuffer();
		code.append(freeObject(context));
		return code.toString();
	}
	//处理对象的释放
	private  String   freeObject(Map<Object, Object> context){
		
		StringBuffer obj_release_buffer = new StringBuffer();
	
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(ILogicEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		Set<String> initObjectList = helper.getAttribute(IAtomEngineContextConstant.ATTR_IN_OBJECT_INIT_VARIABLE_LIST);
		Set<String> noInitObjectList = helper.getAttribute(IAtomEngineContextConstant.ATTR_IN_OBJECT_NOINIT_VARIABLE_LIST);
		Set<String> outObjectList = helper.getAttribute(IAtomEngineContextConstant.ATTR_OUT_OBJECT_INIT_VARIABLE_LIST);
		//输入参数释放
		for(String objectSet:noInitObjectList){
			obj_release_buffer.append(String.format(OBJ_RELEASE,"v_"+objectSet+"ResultSet"));
		}
		for(String objectSet:initObjectList){
			if(noInitObjectList.add(objectSet)){
				obj_release_buffer.append(String.format(OBJ_RELEASE,"v_"+objectSet+"ResultSet"));
			}
			
		}
		//输出参数释放
		for(String objectSet:outObjectList){
				obj_release_buffer.append(String.format(OBJ_OUT_RELEASE,"v_"+objectSet+"ResultSet"));
		}
		return obj_release_buffer.toString();
	}

}
