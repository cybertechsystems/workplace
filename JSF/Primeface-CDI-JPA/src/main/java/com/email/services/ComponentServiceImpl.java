package com.email.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.email.dao.ComponentDAO;
import com.email.model.Component;
import com.email.model.Data;

@Named("componentService")
public class ComponentServiceImpl implements ComponentService {

	@Inject
	private ComponentDAO componentDAO;
	
	public void setComponentDAO(ComponentDAO componentDAO) {
		this.componentDAO = componentDAO;
	}

	public void save(Data data) throws Exception {
		componentDAO.save(data);
	}
	
	public List<Data> findAllComponent()  throws Exception{
		return componentDAO.loadAllComponent();
	}
	
	public void delete(Data data)  throws Exception{
		componentDAO.deleteComponent(data);
	}
	
	public List<Component> findAllComponentName()  throws Exception{
		return componentDAO.loadAllComponentName();
	}
	
	public void addComponent(Component component) throws Exception {
		componentDAO.saveComponent(component);
	}
}
