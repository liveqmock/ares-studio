package com.hundsun.ares.studio.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.core.resources.IFolder;
import org.junit.Test;


public class TestARESProject extends AbstractAresCoreTester{

	@Test
	public void test_getAllModuleRoots() {
		// 这个方法 
//		IARESModuleRoot[] roots = project.getAllModuleRoots();
//		assertEquals(10, roots.length);
	}
	
	@Test public void testGetModuleRoot() {
		IFolder folder = project.getProject().getFolder("testroot1");
		IARESModuleRoot root = project.getModuleRoot(folder);
		assertNotNull(root);
		assertEquals(root1, root);
	}
	
	@Test public void getGetModules() {
		try {
			IARESModule[] modules = project.getModules();
			assertTrue(ArrayUtils.contains(modules, moduleA));
			assertTrue(ArrayUtils.contains(modules, moduleB));
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
	}
	
	@Test public void testGetProperty() {
		IProjectProperty property = project.getProperty();
		assertTrue(property.exists());
		try {
			property.getInfo();
		} catch (ARESModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
