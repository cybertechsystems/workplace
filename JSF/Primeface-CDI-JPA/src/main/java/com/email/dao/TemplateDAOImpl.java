package com.email.dao;

import static com.email.util.Constants.ERROR_TEMPLATE_DELETE;
import static com.email.util.Constants.ERROR_TEMPLATE_SAVE;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.email.model.Template;
import com.email.util.ApplicationException;

@Named("templateDAO")
public class TemplateDAOImpl extends AbstractEntityManager implements
		TemplateDAO {

	private Logger LOGGER = Logger
			.getLogger("com.mailmodule.dao.TemplateDAOImpl");

	public void saveOrUpdate(Template template) throws ApplicationException {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			if (template.getId() != null && template.getId() > 0) {
				em.merge(template);
			} else {
				em.persist(template);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			LOGGER.error("Exception while saving message in TemplateDAOImpl-->saveOrUpdate()"
					+ e.getMessage());
			throw new ApplicationException(e, ERROR_TEMPLATE_SAVE);
		}
	}

	/* Listing all templates from the database */
	@SuppressWarnings("unchecked")
	public List<Template> loadAll() throws Exception {
		List<Template> templateList = null;
		EntityManager em = getEntityManager();
		try {
			templateList = em.createQuery(
					"Select t from Template t").getResultList();
		} catch (Exception e) {
			
			LOGGER.error("Exception while saving message in TemplateDAOImpl-->saveOrUpdate()"
					+ e.getMessage());
			throw e;
		} finally {
			em.close();
		}
		return templateList;
	}

	/* fetching the template based on Id from the database */
	public Template loadById(Long id) throws Exception {
		Template template = null;
		EntityManager em = getEntityManager();
		try {
			Query query = (Query) em.createQuery(
					"Select t from Template t where t.id=:id");
			query.setParameter("id", id.intValue());
			template =  (Template) query.getSingleResult();
		} catch (Exception e) {
			
			LOGGER.error("Exception while checking template by name "
					+ "in TemplateDAOImpl-->findName()" + e.getMessage());
			throw e;
		} finally {
			em.close();
		}
		return template;
	}

	/* fetching the template based on name from the database */
	public boolean findName(String name) throws Exception {
		EntityManager em = getEntityManager();
		try {
			Query query = (Query) em.createQuery(
					"Select t.name from Template t where t.name=:name");
			query.setParameter("name", name);
			@SuppressWarnings("unchecked")
			List<String> check = (List<String>)query.getResultList();
			if (check.isEmpty() == true) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			LOGGER.error("Exception while checking template by name "
					+ "in TemplateDAOImpl-->findName()" + e.getMessage());
			throw e;
		} finally {
			em.close();
		}

	}

	/* Deleting the template based on Id from the database */
	public void delete(Template template) throws ApplicationException {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.createQuery("DELETE FROM Template t where t.id=:id")
					.setParameter("id", template.getId()).executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			
			LOGGER.error("Exception while deleting template in TemplateDAOImpl-->delete()"
					+ e.getMessage());
			throw new ApplicationException(e, ERROR_TEMPLATE_DELETE);
		} finally {
			em.close();
		}
	}
}
