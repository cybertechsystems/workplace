package com.email.dao;

import java.util.List;

import com.email.model.Component;
import com.email.model.Data;
import com.email.util.ApplicationException;

public interface ComponentDAO {
	/* Is called when the user create the data. */
	public void save(Data data) throws ApplicationException;
	
	/* Is called for fetching all data. */
	public List<Data> loadAllComponent() throws Exception;
	
	/* Is called when the user delete the data. */
	public void deleteComponent(Data data) throws Exception;
	
	/* Is called for fetching all data. */
	public List<Component> loadAllComponentName() throws Exception;
	
	/* Is called when the user create the component. */
	public void saveComponent(Component component) throws ApplicationException;
}
