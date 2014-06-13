/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.token;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.ICodeToken;

/**
 * @author zhuyf
 *
 */
public class ResultSetDefineToken implements ICodeToken {

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
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#genCode(java.util.Map)
	 */
	@Override
	public String genCode(Map<Object, Object> context) throws Exception {
		// 每一个结果集，生成一个IF2ResultSet * lpResultSet{0} = NULL;
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		Set<String> rsList = helper.getAttribute(IAtomEngineContextConstant.ATTR_RESULTSET_LIST);
		StringBuffer codeBuffer = new StringBuffer();
		if(rsList.size() > 0){
			codeBuffer.append("//IF2ResultSet声明及初始化" + ITokenConstant.NL);
		}
		for(Iterator<String> ite = rsList.iterator();ite.hasNext();){
			String rsId = ite.next();
			codeBuffer.append(String.format(RESULT_SET_DEFINE_STR, rsId));
		}
		return codeBuffer.toString();
	}
	
	private final static String RESULT_SET_DEFINE_STR = "IF2ResultSet * lpResultSet%1$s = NULL;" +ITokenConstant.NL;

}
