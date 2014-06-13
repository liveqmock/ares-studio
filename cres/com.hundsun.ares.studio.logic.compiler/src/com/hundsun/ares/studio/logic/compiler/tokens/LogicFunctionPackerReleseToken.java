/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.logic.compiler.tokens;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.constants.IAtomRefType;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.model.BasicResourceInfo;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.logic.compiler.constant.ILogicEngineContextConstant;
import com.hundsun.ares.studio.logic.constants.ILogicRefType;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author qinyuan
 *
 */
public class LogicFunctionPackerReleseToken implements ICodeToken {

	public String OUT_PACKER_PREFIX = "lpOut";
	public String TYPE_PACKER_POINTER = "IPacker *";
	public String NAME_SERVICE_PACK_SERVICE = "lpPackService";
	protected String FORMAT_RELEASE_CODE = "if (%1$s) \n" +
		"{ \n" +
		"  free(%1$s->GetPackBuf()); \n" +
		"  %1$s->Release(); \n" +
		"}\n";
	public String NAME_SERVICE_FUNC_INPACKER = "lpFuncInPacker";
	
	@Override
	public String getContent() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getType()
	 */
	@Override
	public int getType() {
		return CODE_TEXT;
	}

	@Override
	public String genCode(Map<Object, Object> context) throws Exception {
		IARESProject project = (IARESProject) context.get(ILogicEngineContextConstant.Aresproject);
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(ILogicEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		Set<String> funcCallList = helper.getAttribute(ILogicEngineContextConstant.ATTR_LOGIC_FUNC_CALL);
		Set<String> serCallList = helper.getAttribute(ILogicEngineContextConstant.ATTR_ATOM_SERVICE_CALL);
		StringBuffer releaseCode = new StringBuffer();
		releaseCode.append( String.format(FORMAT_RELEASE_CODE, NAME_SERVICE_FUNC_INPACKER) );
		for(String call : funcCallList){
			String objId = getObjectId(project, call,ILogicRefType.LOGIC_FUNCTION_CNAME);
			releaseCode.append( String.format(FORMAT_RELEASE_CODE, OUT_PACKER_PREFIX + objId) );
		}
		for(String call : serCallList){
			String objId = getObjectId(project, call,IAtomRefType.ATOM_SERVICE_CNAME);
			releaseCode.append( String.format(FORMAT_RELEASE_CODE, OUT_PACKER_PREFIX + objId) );
		}
			
		return releaseCode.toString();
	}

	/**
	 * 寻找调用资源的对象号
	 * 
	 * @param project
	 * @param refType（因为传入的是中文名，所以这个方法只支持原子、逻辑函数服务）
	 * @return
	 */
	private String getObjectId(IARESProject project , String chName , String refType){
		ReferenceInfo ref = ReferenceManager.getInstance().getFirstReferenceInfo(project, refType, chName, true);
		if (ref != null) {
			Object obj = ref.getObject();
			if (obj instanceof BasicResourceInfo) {
				String objectid = ((BasicResourceInfo) obj).getObjectId();
				if(StringUtils.isBlank(objectid)){
					return ((BasicResourceInfo) obj).getName();
				}
				return objectid;
			}
		}
		return StringUtils.EMPTY;
	}
	
}
