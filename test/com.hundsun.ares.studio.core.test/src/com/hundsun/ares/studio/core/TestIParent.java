package com.hundsun.ares.studio.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

/**
 * IParrent接口的实现方案
 * @author sundl
 *
 */
public class TestIParent extends AbstractAresCoreTester {

	/**
	 * 项目的getChildren()方法测试
	 */
	@Test
	public void testGetChildren_Project() {
		try {
			IARESElement[] children = project.getChildren();
			assertEquals(4, children.length);
			assertTrue(ArrayUtils.contains(children, root1));
			assertTrue(ArrayUtils.contains(children, lib));
			assertTrue(ArrayUtils.contains(children, externalLib));
			// 还有一个，项目属性
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 模块根的getChilren()方法的测试
	 */
	@Test
	public void testGetChildren_Module_Root() {
		try {
			IARESElement[] children = root1.getChildren();
			assertEquals(3, children.length);
			assertTrue(ArrayUtils.contains(children, moduleA));
			assertTrue(ArrayUtils.contains(children, moduleB));
			// default module?
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 模块的getChildren()方法的测试
	 */
	@Test public void testGetChildren_Module() {
		try {
			IARESElement[] children = moduleB.getChildren();
			assertEquals(2, children.length);
			assertTrue(ArrayUtils.contains(children, resourceB));
			assertTrue(ArrayUtils.contains(children, resourceReadOnly));
			// default module?
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 引用包的getChildren的测试方法
	 */
	@Test public void testGetChildren_Libs() {
		try {
			IARESElement[] children = lib.getChildren();
			assertEquals(1, children.length);
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 项目的hasChildren()方法测试
	 * @throws ARESModelException 
	 */
	@Test
	public void testHasChildren_Project() throws ARESModelException {
		assertTrue(project.hasChildren());
	}

	/**
	 * 模块根的hasChilren()方法的测试
	 * @throws ARESModelException 
	 */
	@Test
	public void testHasChildren_Module_Root() throws ARESModelException {
		assertTrue(root1.hasChildren());
	}
	
	/**
	 * 模块的hasChildren()方法的测试
	 * @throws ARESModelException 
	 */
	@Test public void testHasChildren_Module() throws ARESModelException {
		assertTrue(moduleB.hasChildren());
		//assertFalse(moduleA.hasChildren());
	}
	
	/**
	 * 引用包的hasChildren的测试方法
	 * @throws ARESModelException 
	 */
	@Test public void testHasChildren_Libs() throws ARESModelException {
		assertTrue(root1.hasChildren());
	}
}
