package com.hundsun.ares.studio.core.test;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

import com.hundsun.ares.studio.core.ConsoleHelper;

public class TestApplication implements IApplication {

	public Object start(IApplicationContext context) throws Exception {
		ConsoleHelper.getLogger().warn("warn");
		return IApplication.EXIT_OK;
	}

	public void stop() {

	}

}
