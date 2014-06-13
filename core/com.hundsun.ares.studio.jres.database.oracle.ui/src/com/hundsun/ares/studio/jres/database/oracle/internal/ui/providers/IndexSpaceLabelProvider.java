/**
 * 
 */
package com.hundsun.ares.studio.jres.database.oracle.internal.ui.providers;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.LabelProvider;

import com.hundsun.ares.studio.core.IARESBundle;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.context.JRESContextManager;
import com.hundsun.ares.studio.core.model.util.Pair;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleRefType;
import com.hundsun.ares.studio.jres.model.database.oracle.TableSpaceRelation;

/**
 * @author gongyf
 *
 */
public class IndexSpaceLabelProvider extends LabelProvider {
	
	private IARESBundle bundle;
	
	public IndexSpaceLabelProvider(IARESBundle bundle) {
		super();
		this.bundle = bundle;
	}

	@Override
	public String getText(Object element) {
		
		//String space = StringUtils.defaultString((String)element) ;
		String space ="";
		if(element instanceof String) space=StringUtils.defaultString((String)element) ;
		
		Pair<TableSpaceRelation, IARESResource> result = JRESContextManager.findResource(bundle, space, IOracleRefType.SpaceRelation, false);
		if (result != null) {
			return StringUtils.defaultString(result.first.getIndexSpace());
		}
		return StringUtils.EMPTY;
	}
}
