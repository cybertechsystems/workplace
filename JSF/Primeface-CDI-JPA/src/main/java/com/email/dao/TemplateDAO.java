package com.email.dao;

import java.util.List;

import com.email.model.Template;
import com.email.util.ApplicationException;

public interface TemplateDAO {
	
	/* Is called when the user create the template. */
	public void saveOrUpdate(Template template) throws ApplicationException;
	
	/* Is called for fetching all templates. */
	public List<Template> loadAll() throws Exception;
	
	/* Is called for getting template based on Id. */
	public Template loadById(Long id) throws Exception;
	
	/* Is called for finding template by name. */
	public boolean findName(String name) throws Exception;
	
	/* Is called when the user delete the template. */
	public void delete(Template template) throws ApplicationException;
}
