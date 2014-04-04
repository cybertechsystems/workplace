package com.email.service;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;

import com.email.common.BaseTestCase;
import com.email.dao.ComponentDAO;
import com.email.dao.ComponentDAOImpl;
import com.email.model.Component;
import com.email.model.Data;
import com.email.services.ComponentService;
import com.email.services.ComponentServiceImpl;

public class TestComponentService extends BaseTestCase{
	
	private Component components = new Component();
	private Data component = new Data();
	@Inject
	private ComponentService componentService = new ComponentServiceImpl();
	@Inject
	private ComponentDAO componentDAO = new ComponentDAOImpl();
	
	@Before
	public void setUp() {
		try {
			componentService.setComponentDAO(componentDAO);
		} catch (Exception e) {
			
		}
	}
	/**
	 * This method is used for testing add components. 
	 * saving the new values .
	 * Checking the size of component in database before and after saving.
	 * when comparing, if it is greater than one,success the test
	 */
	@Test
	public void testAddComponent(){
		try{
			List<Component> componentList = componentService.findAllComponentName();
			int countBefore = componentList.size();
			addComponent();
			componentList = componentService.findAllComponentName();
			int countAfter = componentList.size();
			assertEquals(countAfter, countBefore + 1);
		} catch (Exception e) {
			
		}
	}
	/**
	 * This method is used for testing save data. 
	 * saving the new values .
	 * Checking the size of data in database before and after saving.
	 * when comparing, if it is greater than one,success the test
	 */
	@Test
	public void testSave() {
		try {
			List<Data> components = this.componentService.findAllComponent();
			int countBefore = components.size();
			saveComponent();
			components = this.componentService.findAllComponent();
			int countAfter = components.size();
			assertEquals(countAfter, countBefore + 1);
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * This method is used for testing delete method.
	 * delete the saved data and 
	 * comparing size of the data in database before and after deleting the data
	 * when comparing, if it is less than one,success the test
	 */
	@Test
	public void testDelete() {
		try {
			saveComponent();
			List<Data> components = componentService.findAllComponent();
			int countBefore = components.size();
			componentService.delete(component);
			components = componentService.findAllComponent();
			int countAfter = components.size();
			assertEquals(countAfter , countBefore - 1);
		} catch (Exception e) {
			
		}
	}
	
	public void saveComponent() throws Exception {
		component = DummyValues.createDummyComponent();
		this.componentService.save(component);
	}
	
	public void addComponent() throws Exception {
		components = DummyValues.createDummyComponents();
		this.componentService.addComponent(components);
	}
}
