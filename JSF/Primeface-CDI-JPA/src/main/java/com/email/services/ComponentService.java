package com.email.services;

import java.util.List;

import com.email.dao.ComponentDAO;
import com.email.model.Component;
import com.email.model.Data;

public interface ComponentService {
	/* Is called when the user saves the data. */
	public void save (Data data) throws Exception;
	
	/* Is called when the user listing all datas. */
	public List<Data> findAllComponent() throws Exception;
	
	/* Is called when the user delete the data. */
	public void delete(Data data) throws Exception;
	
	/* Is called when the user listing all component. */
	public List<Component> findAllComponentName() throws Exception;
	
	/* Is called when the user add the component. */
	public void addComponent(Component component) throws Exception;
	
	public void setComponentDAO(ComponentDAO componentDAO);

}
