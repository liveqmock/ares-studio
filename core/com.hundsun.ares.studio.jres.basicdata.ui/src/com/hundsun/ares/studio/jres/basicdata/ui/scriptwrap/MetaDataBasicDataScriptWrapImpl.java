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
import com.hundsun.ares.studio.core.service.DataServiceManager;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData;
import com.hundsun.ares.studio.jres.basicdata.logic.util.BasicDataEpackageUtil;
import com.hundsun.ares.studio.jres.metadata.service.IMetadataService;
import com.hundsun.ares.studio.jres.script.api.metadata.IStandardFieldScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.ISingleTableScriptWrap;
import com.hundsun.ares.studio.jres.script.base.ResourceWrapBase;


/**
 * @author lvgao
 *
 */
public class MetaDataBasicDataScriptWrapImpl  extends ResourceWrapBase<StandardFieldModelAndData> implements ISingleTableScriptWrap{

	public MetaDataBasicDataScriptWrapImpl(IARESResource resource){
		super(resource);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.basicdata.ui.scriptwrap.ISingleTableScriptWrap#getMasterAttrs()
	 */
	@Override
	public IStandardFieldScriptWrap[] getMasterStandardFields() {
		try {
			IMetadataService service = DataServiceManager.getInstance().getService(resource.getARESProject(), IMetadataService.class);
			
			EClass eClass = (EClass) getOriginalInfo().getData().eClass().getEPackage().getEClassifier(IBasicDataEpacakgeConstant.MasterItem);
			List<IStandardFieldScriptWrap> tlist = new ArrayList<IStandardFieldScriptWrap>();

			for(EAttribute attr:BasicDataEpackageUtil.filterAttr(eClass)){
				tlist.add(new BasicDataFieldScriptWrapImpl(service,attr ,resource ));
			}
			return tlist.toArray(new IStandardFieldScriptWrap[0]);
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public String getMasterTableName() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.basicdata.ui.scriptwrap.ISingleTableScriptWrap#getMasterAttrs()
	 */
	@Override
	public String[] getMasterAttrs() {
		try {
			EClass eClass = (EClass) getOriginalInfo().getData().eClass().getEPackage().getEClassifier(IBasicDataEpacakgeConstant.MasterItem);
			List<String> tlist = new ArrayList<String>();
			for(EAttribute attr:BasicDataEpackageUtil.filterAttr(eClass)){
				tlist.add(attr.getName());
			}
			return tlist.toArray(new String[0]);
		} catch (Exception e) {
		}
		return null;
		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.wrap.IResourceModifyHistory#getModifyHistory(java.lang.String)
	 */
	@Override
	public String getModifyHistory(String commentMark) {
		return null;
	}

	@Override
	public Class getInfoClass() {
		return StandardFieldModelAndData.class;
	}

}
