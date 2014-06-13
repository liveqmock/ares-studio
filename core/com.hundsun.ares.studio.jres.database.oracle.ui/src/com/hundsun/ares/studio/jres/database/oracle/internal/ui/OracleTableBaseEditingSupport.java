/**
 * 源程序名称：OracleTableBaseEditingSupport.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.orcale.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.database.oracle.internal.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.LabelProvider;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleConstant;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleRefType;
import com.hundsun.ares.studio.jres.database.oracle.internal.ui.providers.IndexSpaceLabelProvider;
import com.hundsun.ares.studio.jres.database.oracle.internal.ui.providers.OracleEMPropertyDescriptor;
import com.hundsun.ares.studio.jres.database.oracle.ui.OracleExtensibleModelEditingSupport;
import com.hundsun.ares.studio.jres.database.oracle.ui.viewer.OracleSpaceContentProposalHelper;
import com.hundsun.ares.studio.jres.database.oracle.ui.viewer.OracleSpaceContentProposalProvider;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleFactory;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.jres.model.database.oracle.table_type;
import com.hundsun.ares.studio.ui.editor.extend.ComboBoxEMPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.DerivedEMPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.TextEMPropertyDescriptor;

/**
 * @author gongyf
 *
 */
public class OracleTableBaseEditingSupport extends OracleExtensibleModelEditingSupport {

	
	/**
	 * 
	 */
	public OracleTableBaseEditingSupport() {
	}
	

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.extend.IExtensibleModelEditingSupport#getName()
	 */
	@Override
	public String getName() {
		return "数据库(表空间)";
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.extend.IExtensibleModelEditingSupport#getPropertyDescriptors()
	 */
	@Override
	public IExtensibleModelPropertyDescriptor[] getPropertyDescriptors(IARESElement aresElement, EClass eClass) {
		List<IExtensibleModelPropertyDescriptor> descriptors = new ArrayList<IExtensibleModelPropertyDescriptor>();
		
		//表类型
		
		ComboBoxEMPropertyDescriptor tableType = new ComboBoxEMPropertyDescriptor(OraclePackage.Literals.ORACLE_TABLE_PROPERTY__TABLETYPE
				, OraclePackage.eINSTANCE.gettable_type(),new TableTypeLableProvider());
		tableType.setDisplayName("表类型");
		descriptors.add(tableType);
		
		
		// 2012-2-23 sundl 新的实现，删除无用的参数
		OracleSpaceContentProposalHelper helper = new OracleSpaceContentProposalHelper(/*aresElement.getARESProject()*/);
		
		OracleSpaceContentProposalProvider proposalProvider = new OracleSpaceContentProposalProvider(helper, IOracleRefType.Space, aresElement.getARESProject());
		// 表空间
		TextEMPropertyDescriptor space = new OracleEMPropertyDescriptor(proposalProvider,OraclePackage.Literals.ORACLE_TABLE_PROPERTY__SPACE);
		space.setDisplayName("所在数据库");
		descriptors.add(space);
		
		DerivedEMPropertyDescriptor indexSpace = 
			new DerivedEMPropertyDescriptor(OraclePackage.Literals.ORACLE_TABLE_PROPERTY__SPACE, new IndexSpaceLabelProvider(ARESElementUtil.getARESBundle(aresElement)));
		indexSpace.setDisplayName("索引表空间");
		descriptors.add(indexSpace);
		
		return descriptors.toArray(new IExtensibleModelPropertyDescriptor[descriptors.size()]) ;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.extend.IExtensibleModelEditingSupport#getKey()
	 */
	@Override
	public String getKey() {
		return IOracleConstant.TABLE_DATA2_KEY;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.extend.IExtensibleModelEditingSupport#createMapValueObject()
	 */
	@Override
	public EObject createMapValueObject() {
		return OracleFactory.eINSTANCE.createOracleTableProperty();
	}

	class TableTypeLableProvider extends LabelProvider {
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
		 */
		@Override
		public String getText(Object element) {
			table_type type = (table_type)element;
			if(type.getValue() == table_type.COMMON_VALUE) {
				return "一般表";
			}else if(type.getValue() == table_type.TEMP_NO_VALUE_VALUE){
				return "临时表(不保留数据)";
			}else if(type.getValue() == table_type.TEMP_WITH_VALUE_VALUE) {
				return "临时表(保留数据)";
			}
			
			return super.getText(element);
		}
	}
}
