/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.scripting;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 
 * @author sundl
 */
public class TestScripting {

	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		ScriptEngineManager mgr = new ScriptEngineManager();
		long t2 = System.currentTimeMillis();
		System.out.println(t2 - t1);
		ScriptEngine jsEngine = mgr.getEngineByName("JavaScript");
		jsEngine.put("x", 1);
		long t3 = System.currentTimeMillis();
		System.out.println(t3 - t2);
//		mgr = new ScriptEngineManager();
		ScriptEngine jsEngine2 = mgr.getEngineByName("JavaScript");
		long t4 = System.currentTimeMillis();
		System.out.println(t4-t3);
		
		System.out.println(jsEngine);
		System.out.println(jsEngine2);
		String script = "importClass(java.awt.Rectangle);" +
				"r = new Rectangle( );" +
				"print(r)";
		  try {
//		    jsEngine.eval("print(x); x++ ;print('Hello, world!')");
//		    jsEngine.eval("print(x); print('Hello, world!')");
//		    jsEngine2.eval("print(x); print('Hello, world!')");
			jsEngine.eval(script);
		  } catch (ScriptException ex) {
		      ex.printStackTrace();
		  }  
	}
	
}
