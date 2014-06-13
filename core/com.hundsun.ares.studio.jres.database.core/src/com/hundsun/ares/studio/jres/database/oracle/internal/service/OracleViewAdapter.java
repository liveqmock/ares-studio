package com.hundsun.ares.studio.jres.database.oracle.internal.service;

import com.hundsun.ares.studio.jres.database.internal.service.ViewAdapter;
import com.hundsun.ares.studio.jres.database.oracle.service.IOracleView;
import com.hundsun.ares.studio.jres.model.database.ViewResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleViewProperty;

public class OracleViewAdapter extends ViewAdapter implements IOracleView {

	public OracleViewAdapter(ViewResourceData view) {
		super(view);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleView#getSpace()
	 */
	@Override
	public String getSpace() {
		return ((OracleViewProperty) view).getSpace();
	}

	

}
