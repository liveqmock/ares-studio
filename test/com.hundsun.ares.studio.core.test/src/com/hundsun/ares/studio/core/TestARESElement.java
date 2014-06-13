package com.hundsun.ares.studio.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


import org.eclipse.core.resources.IProject;
import org.junit.Test;

public class TestARESElement extends AbstractAresCoreTester{
	
	@Test public void testExists() {
		assertTrue(project.exists());
		assertTrue(root1.exists());
		assertTrue(moduleA.exists());
		assertTrue(moduleB.exists());
		assertTrue(resourceA.exists());
		assertTrue(resourceB.exists());
		assertTrue(resInLib.exists());
		assertTrue(lib.exists());
		assertFalse(unRegistedRes.exists());
	}
	
	@Test
	public void testGetElementName() {
		// project
		assertEquals(project.getElementName(), Helper.TEST_PROJECT_1);
		// module root
		assertEquals(root1.getElementName(), Helper.TEST_MODULE_ROOT);
		// module
		assertEquals(moduleA.getElementName(), Helper.TEST_MODULE_NAME);
		assertEquals(moduleB.getElementName(), Helper.TEST_MODULE_NAME_B);
		// resource
		assertEquals(resourceA.getElementName(), "aaa.test");
		assertEquals(resourceB.getElementName(), "bbb.test");
		assertEquals(resInLib.getElementName(), "aaa.test");
	}
	
	@Test
	public void testGetElementType() {
		// project
		assertEquals(project.getElementType(), IARESElement.ARES_PROJECT);
		// module root
		assertEquals(root1.getElementType(), IARESElement.COMMON_MODULE_ROOT);
		// module
		assertEquals(moduleA.getElementType(), IARESElement.COMMON_MODULE);
		// resource
		assertEquals(resourceA.getElementType(), IARESElement.ARES_RESOURCE);
	}
	
	@Test
	public void testGetParent() {
		assertEquals(project.getParent(), ARESCore.getModel());
		// module root
		assertEquals(root1.getParent(), project);
		// module
		assertEquals(moduleA.getParent(), root1);
		assertEquals(moduleB.getParent(), root1);
		// resource
		IARESModule defaultModule = root1.getModule(IARESModule.DEFAULT_MODULE_NAME);
		assertEquals(resourceA.getParent(), defaultModule);
		assertEquals(resourceB.getParent(), moduleB);
		//assertEquals(resInLib, "aaa.test");
	}
	
	@Test
	public void testGetARESProject() {
		// project
		assertEquals(project.getARESProject(), project);
		// module root
		assertEquals(root1.getARESProject(), project);
		// module
		assertEquals(moduleA.getARESProject(), project);
		assertEquals(moduleB.getARESProject(), project);
		// resource
		assertEquals(resourceA.getARESProject(), project);
		assertEquals(resourceB.getARESProject(), project);
		assertEquals(resInLib.getARESProject(), project);
	}
	
	@Test
	public void testGetResource() {
		IProject p = wsRoot.getProject(Helper.TEST_PROJECT_1);
		// project
		assertEquals(project.getResource(), p);
		// module root
		assertEquals(root1.getResource(), p.getFolder(Helper.TEST_MODULE_ROOT));
		// module
		assertEquals(moduleA.getResource(), p.getFolder("testroot1/aa"));
		assertEquals(moduleB.getResource(), p.getFolder("testroot1/aa/bb"));
		// resource
		assertEquals(resourceA.getResource(), p.getFile("testroot1/aaa.test"));
		assertEquals(resourceB.getResource(), p.getFile("testroot1/aa/bb/bbb.test"));
		//assertEquals(resInLib.getResource(), "aaa.test");
	}
	
	@Test
	public void testIsReadOnly() {
		assertFalse(resourceA.isReadOnly());
		assertFalse(resourceB.isReadOnly());
		assertTrue(resourceReadOnly.isReadOnly());
		assertTrue(resInLib.isReadOnly());
	}
	
//	@Test
//	public void testIsStructureKnown() {
//		assertFalse(resourceA.isStructureKnown());
//		try {
//			resourceA.getInfo(Object.class);
//		} catch (ARESModelException e) {
//			e.printStackTrace();
//		}
//		assertTrue(resourceA.isStructureKnown());
//	}
}
