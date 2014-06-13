package com.hundsun.ares.studio.core;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hundsun.ares.studio.core.scripting.AresScriptEngineManager;

/**
 * ≤‚ ‘Ω≈±æ“˝«Ê
 * @author sundl
 *
 */
public class TestScriptEngine {
	
	private IProject testProject1;
	
	@Before
	public void setUp() {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		testProject1 = root.getProject(Helper.TEST_PROJECT_1);
		if (!testProject1.exists()) {
			Helper.prepareProjects();
		}
	}

	@Test
	public void testScriptEngine() {
		IARESProject aresProject = ARESCore.create(testProject1);
		AresScriptEngineManager manager = AresScriptEngineManager.getInstance();
		String script = 
			"importPackage(\"com.hundsun.ares.studio.core.util\"); \n" + 
			"var context = contexts.get('default');\n" +
//			"var project = context.project;\n" +
//			"print(project.getElementName());\n";
			
			"print(1)\n" +
			"print(2)\n" +
			"print(3)\n" +
			"print(StringUtil.isEmpty('xx'))";
		
		try {
//			manager.runJsScript(script, aresProject);
			ScriptEngine engine = manager.getEngineByExtension("js", aresProject);
			ScriptEngine engine2 = manager.getEngineByExtension("js", aresProject);
			Assert.assertTrue(engine != engine2);
			engine.eval(new FileReader("scripts/test1.js"));
		} catch (ScriptException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
//		ScriptEngine engine = manager.getEngineByExtension("js", aresProject);
//		try {
//			engine.eval("print('hello')");
//		} catch (ScriptException e) {
//			e.printStackTrace();
//		}

	}
		
}
