/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.basicdata.ui.scriptwrap;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.core.service.DataServiceManager;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveLinkTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.SingleTable;
import com.hundsun.ares.studio.jres.basicdata.logic.epackage.BasicDataEpackageFactory;
import com.hundsun.ares.studio.jres.basicdata.logic.util.BasicDataEpackageUtil;
import com.hundsun.ares.studio.jres.metadata.service.IMetadataService;
import com.hundsun.ares.studio.jres.script.api.metadata.IStandardFieldScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.ISingleTableScriptWrap;
import com.hundsun.ares.studio.jres.script.base.ResourceWrapBase;
import com.hundsun.ares.studio.jres.script.tool.HistoryCommentHelper;


/**
 * @author lvgao
 *
 */
public class SingleTableScriptWrapImpl  extends ResourceWrapBase<BasicDataBaseModel> implements ISingleTableScriptWrap{

	public SingleTableScriptWrapImpl(IARESResource resource){
		super(resource);
	}
	
	@Override
	public IStandardFieldScriptWrap[] getMasterStandardFields() {
		List<IStandardFieldScriptWrap> tlist = new ArrayList<IStandardFieldScriptWrap>();
		try {
			IMetadataService service = DataServiceManager.getInstance().getService(resource.getARESProject(), IMetadataService.class);
			
			EClass eClass = (EClass) getOriginalInfo().eClass().getEPackage().getEClassifier(IBasicDataEpacakgeConstant.MasterItem);
			for(EAttribute attr:BasicDataEpackageUtil.filterAttr(eClass)){
				tlist.add(new BasicDataFieldScriptWrapImpl(service,attr ,resource ));
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tlist.toArray(new IStandardFieldScriptWrap[0]);
	}

	@Override
	public String getMasterTableName() {
		try {
			Object define = (Object) BasicDataEpackageFactory.eINSTANCE.getDefine(resource);
			if (define instanceof SingleTable) {
				return ((SingleTable) define).getMaster();
			}else if (define instanceof MasterSlaveTable) {
				return ((MasterSlaveTable) define).getMaster();
			}else if (define instanceof MasterSlaveLinkTable) {
				return ((MasterSlaveLinkTable) define).getMaster();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.basicdata.ui.scriptwrap.ISingleTableScriptWrap#getMasterAttrs()
	 */
	@Override
	public String[] getMasterAttrs() {
		try {
			EClass eClass = (EClass) getOriginalInfo().eClass().getEPackage().getEClassifier(IBasicDataEpacakgeConstant.MasterItem);
			List<String> tlist = new ArrayList<String>();
			for(EAttribute attr:BasicDataEpackageUtil.filterAttr(eClass)){
				tlist.add(attr.getName());
			}
			return tlist.toArray(new String[0]);
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public String getModifyHistory(String commentMark) {
		try {
			return HistoryCommentHelper.getHistoryCommentForMetadata(resource.getInfo(JRESResourceInfo.class),commentMark);
		} catch (Exception e) {
			console.warn(String.format("生成资源[%s]修改记录注释失败,详细信息:%s", resource.getPath().toOSString(),e.getMessage()));
		}
		return "";
	}

	@Override
	public Class getInfoClass() {
		return BasicDataBaseModel.class;
	}

	public String getFileName(){
		return getOriginalInfo().getFile();
	}
	
}
