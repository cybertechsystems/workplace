package com.email.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.email.model.Component;
import com.email.model.Data;
import com.email.services.ComponentService;

@RequestScoped
@javax.faces.bean.ManagedBean(name="tableBean")
public class ComponentsTableBean  extends BaseAction implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Logger LOGGER = Logger.getLogger("com.email.view.ComponentsTableBean");
	private Data data;
	private Component component;
	
	@Inject
	private ComponentService componentService;
	private List<Data> dataList;
	private List<Component> componentNameList;
	
	
	public Component getComponent() {
		if(component == null){
			component = new Component();
		}
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	public Data getData() {
		if(data == null){
			data = new Data();
		}
		return data;
	}
	
	public void setData(Data data) {
		this.data = data;
	}
	
	public List<Data> getDataList() throws Exception {
		LOGGER.info(
				"Start of DataListTableBean.getComponentList()");
		try{
			dataList = componentService.findAllComponent();
		} catch (Exception e) {
			manageError(e, "Error while getting Mail List. ");
		}
		LOGGER.info(
				"End of DataListTableBean.getComponentList()");
		return dataList;
	}
	
	/**
	 * This method helps to list all component name 
	 * @return componentNameList
	 * @throws Exception
	 */
	public List<Component> getComponentNameList() throws Exception {
		LOGGER.info(
				"Start of DataListTableBean.getComponentNameList()");
		try{
			componentNameList = componentService.findAllComponentName();
		} catch (Exception e) {
			manageError(e, "Error while getting Component List. ");
		}
		LOGGER.info(
				"End of DataListTableBean.getComponentNameList()");
		return componentNameList;
	}
	/**
	 * This method is used to save the data to databases
	 * fetch the list for displaying data list
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		try {
			LOGGER.info(
					"Start of DataListTableBean.save()");
			componentService.save(data);
			dataList = componentService.findAllComponent();
			
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Data saved", "Data saved");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			data = new Data();
			
			LOGGER.info(
					"End of DataListTableBean.save()");
			
		} catch (Exception e) {
			return manageError(e, "Error while saving Data. ");
		}
		return "dataTable";
	}
	
	/**
	 * when clicking on delete button , this method will get called.
	 * get the selected data and delete selected data based on the id
	 * After deleting Lists all data
	 * @throws Exception
	 */
	public void delete() throws Exception {
		LOGGER.info(
				"Start of DataListTableBean:delete()");
		try {
			componentService.delete(data);
			dataList = componentService.findAllComponent();
			
			FacesMessage facesMessage = new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Data is Deleted",
					"Data is Deleted");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			
			LOGGER.info(
					"End of DataListTableBean:delete()");	
		} catch (Exception e) {
			manageError(e, "Error while Deleting data. ");
		}
	}
	/**
	 * OnClicking of components this method get called.
	 * Get the page from the component
	 * @return page
	 */
	public String getComponentPage() {
		LOGGER.info(
				"Start of DataListTableBean:getComponentPage()");
		String value = FacesContext.getCurrentInstance().
				getExternalContext().getRequestParameterMap().get("datatable_selection");
		if(value == ""){
			return "componentTable";
		}
		String page = component.getPage();
		LOGGER.info(
				"End of DataListTableBean:getComponentPage()");	
		return page;
	}
	
	/**
	 * This method is used to save the data to databases
	 * fetch the list and displaying data list
	 * @return
	 * @throws Exception
	 */
	public String addComponent() throws Exception {
		try {
			LOGGER.info(
					"Start of DataListTableBean.addComponent()");
			componentService.addComponent(component);
			dataList = componentService.findAllComponent();
			
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Data saved", "Data saved");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			data = new Data();
			
			LOGGER.info(
					"End of DataListTableBean.addComponent()");
			
		} catch (Exception e) {
			return manageError(e, "Error while saving Data. ");
		}
		return "componentTable";
	}
}
