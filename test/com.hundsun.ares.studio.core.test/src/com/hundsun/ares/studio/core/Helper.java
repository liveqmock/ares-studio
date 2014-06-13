package com.hundsun.ares.studio.core;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.junit.Test;
import org.osgi.framework.Bundle;

import com.hundsun.ares.studio.core.model.converter.ProjectPropertyConverter;
import com.hundsun.ares.studio.internal.core.ARESProjectProperty;

public class Helper {

	public static final String TEST_PROJECT_1 = "testproject1";
	public static final String TEST_RESOURCE_NAME = "aaa.test";
	public static final String TEST_RESOURCE_NAME_A = "aaa";
	public static final String TEST_RESOURCE_NAME_B = "aa.bb.bbb";
	public static final String TEST_RESOURCE_TYPE = "test";
	public static final String TEST_MODULE_NAME = "aa";
	public static final String TEST_MODULE_NAME_B = "aa.bb";
	public static final String TEST_MODULE_ROOT = "testroot1";
	
	/**
	 * 准备测试环境
	 */
	public static void prepareProjects() {
		System.out.println("preparing test projects...");
		
		IWorkspace ws = ResourcesPlugin.getWorkspace();
		try {
			ws.run(new IWorkspaceRunnable() {				
				public void run(IProgressMonitor monitor) throws CoreException {
					// 1. 创建项目，模块根，模块，资源
					createResources();
					// 2. 创建项目属性
					craeteProjectProperty();
				}
			}, null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
	
	private static void createResources() {
		IWorkspaceRoot ws = ResourcesPlugin.getWorkspace().getRoot();
		IProject project1 = ws.getProject(TEST_PROJECT_1);
		if (!project1.exists()) {
			IProjectDescription desc = ws.getWorkspace().newProjectDescription(TEST_PROJECT_1);	
			try {
				desc.setNatureIds(new String[] {ARESCore.NATURE_ID, "com.hundsun.ares.studio.core.testnature"});
				project1.create(desc, null);
				project1.open(null);
			} catch (CoreException e) {
				e.printStackTrace();
			} 
			
			Bundle bundle = Platform.getBundle(ARESCore.PLUGIN_ID);
			Enumeration entries = bundle.findEntries(TEST_PROJECT_1, "*", true);
			for (;entries.hasMoreElements();) {
				URL entry = (URL)entries.nextElement();
				try {
					String file = entry.getFile();

					if (!file.contains(".svn")) {
						String[] segments = file.split("/");
						if (segments.length > 0) {
							String last = segments[segments.length - 1];
							// this is a file...
							if (last.contains(".")) {
								System.out.print("Copying file: " + file + " ...");
								IPath path = new Path(file);
								path = path.removeFirstSegments(1);
								path = path.makeRelative();
								InputStream is = FileLocator.toFileURL(entry).openStream();
								saflyCreateFile(project1, path, is);
								System.out.println("  Done.");
							}
						}
					}				
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static void craeteProjectProperty() {
		IWorkspaceRoot ws = ResourcesPlugin.getWorkspace().getRoot();
		IProject project1 = ws.getProject(TEST_PROJECT_1);
		ARESProjectProperty property = new ARESProjectProperty();
		
		property.setId("testprojectid1");
		property.setName("Test Project1");
		property.setVersion("1.0.0");
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		try {
			ProjectPropertyConverter.getInstance().write(bos, property);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		saflyCreateFile(project1, new Path(IARESProjectProperty.PRO_FILE), new ByteArrayInputStream(bos.toByteArray()));
	}
	
	@Test
	public void test() {
		System.out.println(System.getProperty("user.dir"));
		prepareProjects();
		IWorkspaceRoot ws = ResourcesPlugin.getWorkspace().getRoot();
		IProject project1 = ws.getProject(TEST_PROJECT_1);
		assertTrue(project1.exists());
	}
	
	private static void saflyCreateFile(IProject project, IPath path, InputStream is) {
		IFile file = project.getFile(path);
		if (file.exists()) {
			try {
				file.setContents(is, IFile.FORCE, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		} else {
			String[] segments = path.segments();
			// create the dirs...
			IPath tmp = new Path("");
			for (int i = 0; i < segments.length - 1; i++) {
				tmp = tmp.append(segments[i]);
				IFolder folder = project.getFolder(tmp);
				if (!folder.exists()) {
					try {
						folder.create(true, true, null);
					} catch (CoreException e) {
						e.printStackTrace();
					}
				}
			}
			
			// create the file
			try {
				file.create(is, true, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
