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
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.MasterSlaveTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.PackageDefine;
import com.hundsun.ares.studio.jres.basicdata.logic.epackage.BasicDataEpackageFactory;
import com.hundsun.ares.studio.jres.basicdata.logic.util.BasicDataEpackageUtil;
import com.hundsun.ares.studio.jres.metadata.service.IMetadataService;
import com.hundsun.ares.studio.jres.script.api.metadata.IStandardFieldScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IMasterSlaveTableScriptWrap;


/**
 * @author lvgao
 *
 */
public class MasterSlaveTableScriptWrapImpl  extends SingleTableScriptWrapImpl implements IMasterSlaveTableScriptWrap{

	public MasterSlaveTableScriptWrapImpl(IARESResource resource) {
		super(resource);
	}

	@Override
	public IStandardFieldScriptWrap[] getSlaveStandardFields() {
		List<IStandardFieldScriptWrap> tlist = new ArrayList<IStandardFieldScriptWrap>();
		try {
			IMetadataService service = DataServiceManager.getInstance().getService(resource.getARESProject(), IMetadataService.class);
			
			EClass eClass = (EClass) getOriginalInfo().eClass().getEPackage().getEClassifier(IBasicDataEpacakgeConstant.SlaveItem);
			for(EAttribute attr:BasicDataEpackageUtil.filterAttr(eClass)){
				tlist.add(new BasicDataFieldScriptWrapImpl(service,attr ,resource));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tlist.toArray(new IStandardFieldScriptWrap[0]);
	}

	@Override
	public String[] getSlaveAttrs() {
		try {
			EClass eClass = (EClass) getOriginalInfo().eClass().getEPackage().getEClassifier(IBasicDataEpacakgeConstant.SlaveItem);
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
	public String getSlaveTableName() {
		try {
			PackageDefine define = BasicDataEpackageFactory.eINSTANCE.getDefine(resource);
			if (define instanceof MasterSlaveTable) {
				return ((MasterSlaveTable) define).getSlave();
			}else if (define instanceof MasterSlaveLinkTable) {
				return ((MasterSlaveLinkTable) define).getSlave();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
