package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.chouse.ConstraintModifyDetail;
import com.hundsun.ares.studio.jres.model.chouse.RemoveConstraintModification;
import com.hundsun.ares.studio.jres.model.chouse.util.StockUtils;
import com.hundsun.ares.studio.jres.model.database.TableKey;
import com.hundsun.ares.studio.jres.script.api.database.ITableKeyScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IAddConstraintModificationScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

public class RemoveConstraintModificationScriptWrap extends CommonScriptWrap<RemoveConstraintModification> implements IAddConstraintModificationScriptWrap{

	public RemoveConstraintModificationScriptWrap(RemoveConstraintModification t,	IARESResource resource) {
		super(t, resource);
	}

	@Override
	public ITableKeyScriptWrap[] getDetails() {
		List<ITableKeyScriptWrap> details = new ArrayList<ITableKeyScriptWrap>();
		for(ConstraintModifyDetail detail : getOriginalInfo().getDetails()){
			TableKey key = StockUtils.toTableKey(detail);
			details.add(new TableKeyScriptWrapImpl(key, resource));
		}
		return details.toArray(new ITableKeyScriptWrap[0]);
	}

}
