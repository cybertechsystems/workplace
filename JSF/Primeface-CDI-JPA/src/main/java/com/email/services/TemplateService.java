package com.email.services;

import java.util.List;

import com.email.dao.TemplateDAO;
import com.email.model.Template;

public interface TemplateService {

	/* Is called when the user saves the template. */
	public void saveOrUpdate(Template template) throws Exception;
	
	/* Is called when the user listing all templates. */
	public List<Template> findAll() throws Exception;
	
	/* Is called when the user fetching the template based on Id. */
	public Template findById(Long id) throws Exception;
	
	/* Is called when the user saves the template. */
	public boolean findByName(String name) throws Exception;
	
	/* Is called when the user delete the template. */
	public void remove(Template template) throws Exception;
	public void setTemplateDAO(TemplateDAO templateDAO);
	
}
