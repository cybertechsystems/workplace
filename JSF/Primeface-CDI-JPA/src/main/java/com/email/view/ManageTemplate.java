package com.email.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import com.email.model.Template;
import com.email.services.TemplateService;

@RequestScoped
@javax.faces.bean.ManagedBean(name="manageTemplate")
public class ManageTemplate extends BaseAction implements Serializable {
	private Logger LOGGER = Logger.getLogger("com.mailmodule.view.ManageTemplate");
	
	private static final long serialVersionUID = 1L;
	private Template template;
	
	@Inject
	private TemplateService templateService;

	private List<Template> templates;
	private static Map<String, String> dataFields;
	private String dataField;
	private String templateId;
	
	static {
		dataFields = new HashMap<String, String>();
		dataFields.put("user_id", "@USER_ID");
		dataFields.put("full_name", "@FULL_NAME");
		dataFields.put("email", "@EMAIL");
		dataFields.put("transaction_id", "@TRANSACTION_ID");
	}

	public ManageTemplate() {
		
	}

	public Map<String, String> getDataFields() {
		return dataFields;
	}

	public void setDataFields(Map<String, String> dataFields) {
		ManageTemplate.dataFields = dataFields;
	}

	public String getDataField() {
		return dataField;
	}

	public void setDataField(String dataField) {
		this.dataField = dataField;
	}

	public Template getTemplate() {
		if (template == null) {
			template = new Template();
		}
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public List<Template> getTemplates() {
		try{
			LOGGER.info("============================================"+
					"Start of ManageTemplate.getTemplates()");
			templates = templateService.findAll();
			
			LOGGER.info("============================================"+
					"End of ManageTemplate.getTemplates()");
		}catch (Exception e) {
			LOGGER.warning("Exception in ManageTemplate-->getTemplates()"	+ e.getMessage());
		}
		return templates;
	}

	public void setTemplates(List<Template> templates) {
		this.templates = templates;
	}

	/**
	 * This method is used for validating,creating and updating template.
	 * 
	 * validates if any existing template having same name is already present in
	 * the database. If record is already present then it is displaying
	 * confirmation message to user.
	 * 
	 * @param actionEvent
	 */
	public String save() throws Exception {
		try {
			LOGGER.info("============================================"+
					"Start of ManageTemplate.save()");
			
			if (validate(template.getName())) {
				FacesMessage facesMessage = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"Template having same name already exists",
						"Template having same name already exists");
				FacesContext.getCurrentInstance()
						.addMessage(null, facesMessage);
			} else {
				Template temp = new Template();
				temp.setContent(template.getContent());
				temp.setName(template.getName());
				templateService.saveOrUpdate(temp);
				
				FacesMessage facesMessage = new FacesMessage(
						FacesMessage.SEVERITY_INFO, "Template is saved",
						"Template is saved");
				FacesContext.getCurrentInstance()
						.addMessage(null, facesMessage);
				
				template = new Template();
			}
			LOGGER.info("============================================"+
					"End of ManageTemplate.save()");
			
			return "manageTemplate";
		} catch (Exception e) {
			return manageError(e, "Error while saving Template. ");
		}
	}

	/**
	 * This method is used for update existing template
	 * get the id from the template
	 */
	public String edit() {
		try {
			LOGGER.info("============================================"+
					"Start of ManageTemplate.edit()");
			templateService.saveOrUpdate(template);
			
			FacesMessage facesMessage = new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Template is Updated",
					"Template is Updated");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			template = new Template();

			LOGGER.info("============================================"+
					"End of ManageTemplate.edit()");
			
			return "manageTemplate";
		} catch (Exception e) {
			return manageError(e, "Error while editing Template. ");
		}

	}

	/**
	 * This method is used for delete existing template
	 * get the template
	 * get the id from the template and delete template based on id
	 */
	public String delete() {
		try {
			LOGGER.info("============================================"+
					"Start of ManageTemplate.delete()");
			templateService.remove(template);
			templates = templateService.findAll();
			
			template = new Template();
			
			FacesMessage facesMessage = new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Template is Deleted",
					"Template is Deleted");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			
			LOGGER.info("============================================"+
					"Start of ManageTemplate.delete()");
			
			return "manageTemplate";
			
		} catch (Exception e) {
			return manageError(e, "Error while deleting Template. ");
		}
	}

	/**
	 * This method validates for existing template in the database by name it
	 * returns true if record is already present otherwise it returns false.
	 * 
	 * @param name
	 * @return boolean
	 */
	public boolean validate(String name) {
		LOGGER.info("============================================"+
				"Start of ManageTemplate.validate():name is:"+name);
		
		boolean value = false;
		try{
			value =  templateService.findByName(name);
		
		}catch (Exception e) {
			LOGGER.warning("Exception in ManageTemplate-->validate()"
					+ e.getMessage());
		}
		LOGGER.info("============================================"+
				"End of ManageTemplate.validate()");
		return value;
	}

	/**
	 * This method is getting called on change of dropDown values in the
	 * manageTemplate. it fetches record based on the selected dropDown value
	 * and updates in the rest of the text fields.
	 * 
	 * @param evt
	 */
	public String valueChange(ValueChangeEvent evt) {
		try{
			LOGGER.info("============================================"+
					"Start of ManageTemplate.valueChange()");
			String page = (String) evt.getNewValue();
			Long id = Long.parseLong(page);
			template = templateService.findById(id);
			
			LOGGER.info("============================================"+
					"End of ManageTemplate.valueChange()");
			return "manageTemplate";
		} catch (Exception e) {
			return manageError(e, "Error while getting Template values. ");
		}
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	
}
