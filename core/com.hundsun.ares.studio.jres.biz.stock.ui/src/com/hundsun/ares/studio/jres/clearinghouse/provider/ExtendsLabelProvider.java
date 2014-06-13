/**
 * 
 */
package com.hundsun.ares.studio.jres.clearinghouse.provider;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.LabelProvider;

import com.hundsun.ares.studio.core.IARESBundle;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.context.JRESContextManager;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.core.model.util.Pair;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleConstant;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleRefType;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleTableProperty;
import com.hundsun.ares.studio.jres.model.database.oracle.TableSpaceRelation;
import com.hundsun.ares.studio.ui.editor.extend.IEMLabelProviderExtension;

/**
 * @author yanwj06282
 *
 */
public class ExtendsLabelProvider extends LabelProvider implements IEMLabelProviderExtension {
	
	private IARESBundle bundle;
	private ExtensibleModel model;
	private String key;
	private EStructuralFeature feature;
	
	public ExtendsLabelProvider(IARESBundle bundle,String key ,EStructuralFeature feature) {
		super();
		this.bundle = bundle;
		this.key = key;
		this.feature = feature;
	}

	@Override
	public String getText(Object element) {
		if (model != null) {
			OracleTableProperty otp = (OracleTableProperty) model.getData2().get(IOracleConstant.TABLE_DATA2_KEY);
			if (otp != null) {
				String space = StringUtils.defaultString(otp.getSpace());
				
				Pair<TableSpaceRelation, IARESResource> result = JRESContextManager.findResource(bundle, space, IOracleRefType.SpaceRelation, false);
				if (result != null) {
					EObject obj = result.first.getData2().get(key);
					if (obj != null && obj.eClass().getEAllStructuralFeatures().contains(feature)) {
						Object o = obj.eGet(feature);
						return o == null ? StringUtils.EMPTY : o.toString();
					}
					
				}
			}
		}

		
		return StringUtils.EMPTY;
	}

	@Override
	public void setExtensibleModel(ExtensibleModel model) {
		this.model = model;
	}

}
