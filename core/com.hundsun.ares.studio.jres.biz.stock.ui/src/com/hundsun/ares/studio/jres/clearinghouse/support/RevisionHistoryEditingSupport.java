/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse.support;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.clearinghouse.TableModifyEMPropertyDescriptor;
import com.hundsun.ares.studio.jres.clearinghouse.provider.ModifyHistoryContentLabelProvider;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseResType;
import com.hundsun.ares.studio.jres.database.oracle.ui.OracleExtensibleModelEditingSupport;
import com.hundsun.ares.studio.jres.model.chouse.ChouseFactory;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.chouse.IStock3Constant;
import com.hundsun.ares.studio.jres.model.chouse.RevisionHistoryProperty;
import com.hundsun.ares.studio.ui.editor.extend.DerivedEMPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;
import com.hundsun.ares.studio.ui.editor.extend.TextEMPropertyDescriptor;

/**
 * @author gongyf
 *
 */
public class RevisionHistoryEditingSupport extends OracleExtensibleModelEditingSupport {

	/**
	 * 
	 */
	public RevisionHistoryEditingSupport() {
	}
	
	@Override
	public boolean isEnable(IARESElement aresElement, EClass eClass) {
		if (aresElement instanceof IARESResource && IDatabaseResType.Table.equalsIgnoreCase(((IARESResource)aresElement).getType())  ) {
			return super.isEnable(aresElement, eClass);
		}
		return false;
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.extend.IExtensibleModelEditingSupport#getName()
	 */
	@Override
	public String getName() {
		return "证券三部";
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.extend.IExtensibleModelEditingSupport#getKey()
	 */
	@Override
	public String getKey() {
		return IStock3Constant.HISTORY_DATA2_KEY;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.extend.IExtensibleModelEditingSupport#createMapValueObject()
	 */
	@Override
	public EObject createMapValueObject() {
		RevisionHistoryProperty p = ChouseFactory.eINSTANCE.createRevisionHistoryProperty();
		// 默认值，但是需要被序列化
		p.setInternalVersion("$Rev$");
		return p;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.extend.IExtensibleModelEditingSupport#getPropertyDescriptors()
	 */
	@Override
	public IExtensibleModelPropertyDescriptor[] getPropertyDescriptors(IARESElement aresElement, EClass eClass) {
		List<IExtensibleModelPropertyDescriptor> descriptors = new ArrayList<IExtensibleModelPropertyDescriptor>();
		
		TextEMPropertyDescriptor version = new TextEMPropertyDescriptor(ChousePackage.Literals.HISTORY_PROPERTY__INTERNAL_VERSION);
		version.setDisplayName("版本提交信息");
		descriptors.add(version);
		
		TableModifyEMPropertyDescriptor modify = new TableModifyEMPropertyDescriptor(ChousePackage.Literals.REVISION_HISTORY_PROPERTY__ACTION, (IARESResource)aresElement);
		modify.setDisplayName("修改类型");
		descriptors.add(modify);
		
		//2013年5月24日10:28:25 去除修改内容
//		DerivedEMPropertyDescriptor hisSpace = 
//			new DerivedEMPropertyDescriptor(ChousePackage.Literals.REVISION_HISTORY_PROPERTY__ACTION, new ModifyHistoryContentLabelProvider());
//		hisSpace.setDisplayName("修改内容");
//		descriptors.add(hisSpace);
		
		return descriptors.toArray(new IExtensibleModelPropertyDescriptor[descriptors.size()]);
	}

}
