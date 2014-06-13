package com.hundsun.ares.studio.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
/**
 * 测试套件
 * @author sundl
 *
 */
@RunWith(Suite.class)	// 说明这是个测试套件
@Suite.SuiteClasses(	// 测试套件包含哪些类，可以包含其他测试套件
		{
		TestElementFactory.class,
		TestUtil_isValideResourceName.class,
		TestUtil_isValideModuleName.class,
		TestCache.class,
		TestARESElement.class,
		TestIParent.class,
		TestARESProject.class,
		TestARESBundle.class}
		)	
public class AllTests {
	
}
