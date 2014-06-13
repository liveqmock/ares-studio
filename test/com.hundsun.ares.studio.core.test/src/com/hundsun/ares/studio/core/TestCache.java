package com.hundsun.ares.studio.core;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertSame;

import org.eclipse.core.runtime.CoreException;
import org.junit.Test;

import com.hundsun.ares.studio.internal.core.ARESProjectProperty;

public class TestCache extends AbstractAresCoreTester{
	
	@Test
	public void testResourceInfo() {
		assertNotNull(resourceA);
		try {
			ActionInfo info = resourceA.getInfo(ActionInfo.class);
			if (info != null) {
				assertEquals("a", info.properties.get("id"));
				assertEquals("A", info.properties.get("name"));
			}
			
			// get info again, should be the same object
			ActionInfo infoAgain = resourceA.getInfo(ActionInfo.class);
			assertSame(info, infoAgain);
						
			// changed the file content, the cache should be refreshed
			ActionInfo wp = resourceA.getWorkingCopy(ActionInfo.class);
			wp.properties.put("id", "a2");
			wp.properties.put("name", "A2");
			resourceA.save(wp, true, null);
			
			ActionInfo info2 = resourceA.getInfo(ActionInfo.class);
			if (info2 != null) {
				assertEquals("a2", info2.properties.get("id"));
				assertEquals("A2", info2.properties.get("name"));
			}
			
		} catch (ARESModelException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testProjectProperty() {
		
		try {
			IProjectProperty property = project.getProperty();
			assertNotNull(property);
			assertEquals(property.exists(), true);
			
			// method 1
			IARESProjectProperty pro1 = property.getInfo();
			assertNotNull(pro1);
			
			// method 2
			IARESProjectProperty pro2 = project.getProjectProperty();
			assertNotNull(pro2);
			
			// should be the same object
			assertSame(pro1, pro2);
			
			// call again, should be the same object with last call
			IARESProjectProperty pro3 = property.getInfo();
			assertSame(pro1, pro3);
			IARESProjectProperty pro4 = project.getProjectProperty();
			assertSame(pro2, pro4);
			assertSame(pro3, pro4);
			
			// check the info
			assertEquals(pro1.getId(), "testprojectid1");
			assertEquals(pro1.getName(), "Test Project1");
			
			// change the info, call again, info should be updated
			ARESProjectProperty newPro = new ARESProjectProperty();
			newPro.setId("testprojectid1new");
			newPro.setName("Test Project New");
			property.save(newPro);
			
			IARESProjectProperty pro5 = project.getProjectProperty();
			assertEquals(pro5.getId(), "testprojectid1new");
			assertEquals(pro5.getName(), "Test Project New");
			
		} catch (ARESModelException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
	}
}
