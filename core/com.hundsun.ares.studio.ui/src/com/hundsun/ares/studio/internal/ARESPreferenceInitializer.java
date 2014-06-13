package com.hundsun.ares.studio.internal;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

import com.hundsun.ares.studio.ui.ARESPreferences;
import com.hundsun.ares.studio.ui.ARESUI;

public class ARESPreferenceInitializer extends AbstractPreferenceInitializer {

	public ARESPreferenceInitializer() {
	}

	@Override
	public void initializeDefaultPreferences() {
		IEclipsePreferences preferences = new DefaultScope().getNode(ARESUI.PLUGIN_ID);
		preferences.put(ARESPreferences.LABEL_RESOURCE, "{ename}({cname})");
		preferences.put(ARESPreferences.LABEL_MODULE, "{ename}({cname})");
		preferences.putBoolean(ARESPreferences.FLATLAYOUT, false);
		preferences.putBoolean(ARESPreferences.SHOW_CATEGORY, true);
		preferences.put(ARESUI.PRE_CELLEDITOR_ACTIVE_MODE, ARESUI.PRE_CELLEDITOR_ACTIVE_MODE_DOUBLECLICK);
		preferences.put(ARESUI.PRE_GENERATE_CHARSET, "GB2312");
		
	}

}
