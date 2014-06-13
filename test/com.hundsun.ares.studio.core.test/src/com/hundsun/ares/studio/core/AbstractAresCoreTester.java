package com.hundsun.ares.studio.core;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.junit.Before;

public class AbstractAresCoreTester {
	
	protected IARESProject project;
	protected IARESModuleRoot root1;
	protected IARESModule moduleA;
	protected IARESModule moduleB;
	protected IARESResource resourceA;
	protected IARESResource resourceB;
	protected IARESResource unRegistedRes;
	protected IARESResource resourceReadOnly;
	protected IReferencedLibrary lib;
	protected IReferencedLibrary externalLib;
	protected IARESResource resInLib;
	
	protected IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
	
	@Before
	public void setUp() {
		Helper.prepareProjects();
		IProject p = wsRoot.getProject(Helper.TEST_PROJECT_1);
		project = ARESCore.create(p);
		root1 = project.getModuleRoot(p.getFolder(Helper.TEST_MODULE_ROOT));
		moduleA = root1.getModule(Helper.TEST_MODULE_NAME);	
		moduleB = root1.getModule(Helper.TEST_MODULE_NAME_B);
		try {
			resourceA = project.findResource("aaa", "test");
			resourceB = project.findResource(Helper.TEST_RESOURCE_NAME_B, Helper.TEST_RESOURCE_TYPE);
			resourceReadOnly = project.findResource("aa.bb.readonly", "test");
			unRegistedRes = (IARESResource) ARESCore.create(p.getFile("testroot1/aa/bb/dddd.test"));
			IFile readOnlyFile = (IFile) resourceReadOnly.getResource();
			ResourceAttributes attr = readOnlyFile.getResourceAttributes();
			attr.setReadOnly(true);
			try {
				readOnlyFile.setResourceAttributes(attr);
			} catch (CoreException e) {
				e.printStackTrace();
			}
			lib = project.getReferencedLibrary(ARESCore.newLibEntry(new Path("testproject1/test.ares")));
			resInLib = lib.findResource("aaa", "test");
			externalLib = project.getReferencedLibrary(ARESCore.newLibEntry(new Path("D:\\workspace\\hs\\framework\\Sources\\com.hundsun.ares.studio.core.test\\testproject1\\test.ares")));
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
	}
	
}
