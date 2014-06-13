/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.preferences.ErrorCheckPreferenceConstant;

/**
 * 
 * @author liaogc
 */
public class ErrorCheckPreferenceInitializer extends AbstractPreferenceInitializer {

	public ErrorCheckPreferenceInitializer() {
	}

	@Override
	public void initializeDefaultPreferences() {/*
		IEclipsePreferences preferences = new DefaultScope().getNode(ARESCore.PLUGIN_ID);
		preferences.putBoolean(ErrorCheckPreferenceConstant.ERROR_CHECK, ErrorCheckPreferenceConstant.ERROR_CHECK_DEFVALUE);
		preferences.putBoolean(ErrorCheckPreferenceConstant.METADATA_CHECK, ErrorCheckPreferenceConstant.METADATA_CHECK_DEFVALUE);
		preferences.putBoolean(ErrorCheckPreferenceConstant.BASIC_DATA_CHECK, ErrorCheckPreferenceConstant.METADATA_CHECK_DEFVALUE);
		preferences.putBoolean(ErrorCheckPreferenceConstant.DATABASE_CHECK, ErrorCheckPreferenceConstant.METADATA_CHECK_DEFVALUE);
		preferences.putBoolean(ErrorCheckPreferenceConstant.SERVICE_CHECK, ErrorCheckPreferenceConstant.METADATA_CHECK_DEFVALUE);
		preferences.putBoolean(ErrorCheckPreferenceConstant.ATOM_CHECK, ErrorCheckPreferenceConstant.METADATA_CHECK_DEFVALUE);
		preferences.putBoolean(ErrorCheckPreferenceConstant.LOGIC_CHECK, ErrorCheckPreferenceConstant.METADATA_CHECK_DEFVALUE);
		preferences.putBoolean(ErrorCheckPreferenceConstant.OBJECT_CHECK, ErrorCheckPreferenceConstant.METADATA_CHECK_DEFVALUE);
		
	*/}
}
