/**
 * 源程序名称：OracleViewBaseEditingSupport.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.orcale.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：王彬
 */
package com.hundsun.ares.studio.jres.database.oracle.internal.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleConstant;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleRefType;
import com.hundsun.ares.studio.jres.database.oracle.internal.ui.providers.OracleEMPropertyDescriptor;
import com.hundsun.ares.studio.jres.database.oracle.ui.OracleExtensibleModelEditingSupport;
import com.hundsun.ares.studio.jres.database.oracle.ui.viewer.OracleSpaceContentProposalHelper;
import com.hundsun.ares.studio.jres.database.oracle.ui.viewer.OracleSpaceContentProposalProvider;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleFactory;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.TextEMPropertyDescriptor;

/**
 * @author wangbin
 *
 */
public class OracleViewBaseEditingSupport extends OracleExtensibleModelEditingSupport  {
	
	/**
	 * 
	 */
	public OracleViewBaseEditingSupport() {
	}


	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.extend.IExtensibleModelEditingSupport#getName()
	 */
	@Override
	public String getName() {
		return "Oracle";
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.extend.IExtensibleModelEditingSupport#getPropertyDescriptors()
	 */
	@Override
	public IExtensibleModelPropertyDescriptor[] getPropertyDescriptors(IARESElement aresElement, EClass eClass) {
		List<IExtensibleModelPropertyDescriptor> descriptors = new ArrayList<IExtensibleModelPropertyDescriptor>();
		// 2012-2-23 sundl 新的实现，删除无用参数
		OracleSpaceContentProposalHelper helper = new OracleSpaceContentProposalHelper(/*aresElement.getARESProject()*/);
		OracleSpaceContentProposalProvider proposalProvider = new OracleSpaceContentProposalProvider(helper, IOracleRefType.Space, aresElement.getARESProject());
		TextEMPropertyDescriptor db = new OracleEMPropertyDescriptor(proposalProvider,OraclePackage.Literals.ORACLE_TABLE_PROPERTY__SPACE);
		db.setDisplayName("所在数据库");
		descriptors.add(db);
		
		return descriptors.toArray(new IExtensibleModelPropertyDescriptor[descriptors.size()]) ;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.extend.IExtensibleModelEditingSupport#getKey()
	 */
	@Override
	public String getKey() {
		return IOracleConstant.VIEW_DATA2_KEY;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.extend.IExtensibleModelEditingSupport#createMapValueObject()
	 */
	@Override
	public EObject createMapValueObject() {
		return OracleFactory.eINSTANCE.createOracleTableProperty();
	}

}
