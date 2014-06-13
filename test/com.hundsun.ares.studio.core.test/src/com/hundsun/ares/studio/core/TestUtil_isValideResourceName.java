/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.hundsun.ares.studio.core.util.Util;

/**
 * 测试Util工具类
 * @author sundl
 */
@RunWith(Parameterized.class)
public class TestUtil_isValideResourceName {
	
	private Boolean expected;
	private String target;
	
	/**
	 * 测试用例
	 * @return
	 */
	@Parameters
	public static Collection cases() {
		return Arrays.asList(new Object[][] {
				{"test.xxx", true},	// normal
				{"test.", false},
				{".xxx", false},
				{"xxx", false},
				{".", false}
		} );
	}
	
	/**
	 * 参数化测试必须的构造函数
	 * @param expected
	 * @param target
	 */
	public TestUtil_isValideResourceName(String target, Boolean expected) {
		this.expected = expected;
		this.target = target;
	}
	
	@Test
	public void testIsValidResname() {
		assertEquals(expected, Util.isValiedCommonResourceName(target));
	}
	
}
