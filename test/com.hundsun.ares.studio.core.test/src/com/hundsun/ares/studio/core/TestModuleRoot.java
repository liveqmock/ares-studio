package com.hundsun.ares.studio.core;

import org.junit.Test;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.internal.core.ARESModel;

public class TestModuleRoot {

	@Test
	public void test() {
		
		ARESModel model = (ARESModel)ARESCore.getModel();
		try {
			model.open(null);
			for (IARESProject project : model.getARESProjects()) {
				project.open(null);
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		} 	
	}
	
}
