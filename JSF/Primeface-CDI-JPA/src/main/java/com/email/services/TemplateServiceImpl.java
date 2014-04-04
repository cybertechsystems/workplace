package com.email.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.email.dao.TemplateDAO;
import com.email.model.Template;

@Named("templateService")
public class TemplateServiceImpl implements TemplateService {
	
	@Inject
	private TemplateDAO templateDAO;
	

	public void setTemplateDAO(TemplateDAO templateDAO) {
		this.templateDAO = templateDAO;
	}

	public void saveOrUpdate(Template template) throws Exception {
		templateDAO.saveOrUpdate(template);
	}

	public List<Template> findAll()  throws Exception{
		return templateDAO.loadAll();
	}

	public Template findById(Long id)  throws Exception{
		return (Template) templateDAO.loadById(id);
	}

	public boolean findByName(String name)  throws Exception{
		return templateDAO.findName(name);
	}
	
	public void remove(Template template)  throws Exception{
		templateDAO.delete(template);
	}
	
}
