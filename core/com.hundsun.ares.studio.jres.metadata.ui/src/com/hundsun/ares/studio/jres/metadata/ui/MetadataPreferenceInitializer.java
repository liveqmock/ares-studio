package com.hundsun.ares.studio.jres.metadata.ui;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

import com.hundsun.ares.studio.jres.metadata.MetadataCore;

public class MetadataPreferenceInitializer extends AbstractPreferenceInitializer {

	public MetadataPreferenceInitializer() {
	}

	@Override
	public void initializeDefaultPreferences() {
		IEclipsePreferences preferences = new DefaultScope().getNode(MetadataUI.PLUGIN_ID);
		preferences.put(MetadataCore.PRE_APPLICATION_DEPARTMENT, MetadataCore.PRE_APPLICATION_DEPARTMENT_STOCK);
	}

}
