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
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveLinkTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.PackageDefine;
import com.hundsun.ares.studio.jres.basicdata.logic.epackage.BasicDataEpackageFactory;
import com.hundsun.ares.studio.jres.basicdata.logic.util.BasicDataEpackageUtil;
import com.hundsun.ares.studio.jres.metadata.core.script.impl.StandardFieldScriptWrapImpl;
import com.hundsun.ares.studio.jres.metadata.service.IMetadataService;
import com.hundsun.ares.studio.jres.metadata.service.IStandardField;
import com.hundsun.ares.studio.jres.script.api.metadata.IStandardFieldScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IMasterSlaveLinkTableScriptWrap;


/**
 * @author lvgao
 *
 */
public class MasterSlaveLinkTableScriptWrapImpl  extends MasterSlaveTableScriptWrapImpl implements IMasterSlaveLinkTableScriptWrap{

	public MasterSlaveLinkTableScriptWrapImpl(IARESResource resource) {
		super(resource);
	}

	@Override
	public IStandardFieldScriptWrap[] getLinkStandardFields() {
		List<IStandardFieldScriptWrap> tlist = new ArrayList<IStandardFieldScriptWrap>();
		try {
			IMetadataService service = DataServiceManager.getInstance().getService(resource.getARESProject(), IMetadataService.class);
			
			EClass eClass = (EClass) getOriginalInfo().eClass().getEPackage().getEClassifier(IBasicDataEpacakgeConstant.InfoItem);
			for(EAttribute attr:BasicDataEpackageUtil.filterAttr(eClass)){
				IStandardField item = service.getStandardField(attr.getName());
				if(null == item){
					console.info(String.format("资源%s关联的标准字段%s没找到。", resource.getName(),attr.getName()));
				}else{
					tlist.add(new StandardFieldScriptWrapImpl(item ,resource));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tlist.toArray(new IStandardFieldScriptWrap[0]);
	}

	@Override
	public String[] getLinkAttrs() {
		try {
			EClass eClass = (EClass) getOriginalInfo().eClass().getEPackage().getEClassifier(IBasicDataEpacakgeConstant.InfoItem);
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
	public String getLinkTableName() {
		try {
			PackageDefine define = BasicDataEpackageFactory.eINSTANCE.getDefine(resource);
			if (define instanceof MasterSlaveLinkTable) {
				return ((MasterSlaveLinkTable) define).getLink();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
