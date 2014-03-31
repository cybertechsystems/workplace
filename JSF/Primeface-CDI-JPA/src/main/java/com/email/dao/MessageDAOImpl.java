package com.email.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.email.model.ComposeMail;

@Named("messageDAO")
public class MessageDAOImpl extends AbstractEntityManager implements
		MessageDAO {
	
	private Logger LOGGER = Logger.getLogger("com.mailmodule.dao.MessageDAOImpl");

	/* save the mail to the database */
	public void save(ComposeMail mail) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(mail);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			LOGGER.error("Exception while saving message in MessageDAOImpl-->save()"
					+ e.getMessage());
			
		} finally {
			em.close();
		}
	}
	
	/* Listing all mails from the database */
	@SuppressWarnings("unchecked")
	public List<ComposeMail> loadAllMail() throws Exception {
		List<ComposeMail> composeMailList = null;
		EntityManager em = getEntityManager();
		try {
			composeMailList = em.createQuery(
					"Select c from ComposeMail c").getResultList();
		} catch (Exception e) {
			LOGGER.error("Exception while saving message in MessageDAOImpl-->loadAllMail()"
					+ e.getMessage());
			throw e;
		} finally {
			em.close();
		}
		return composeMailList;
	}
	
	/* Deleting the mail based on Id from the database */
	public void deleteMail(ComposeMail mail) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.createQuery("DELETE FROM ComposeMail c where c.id=:id")
					.setParameter("id", mail.getId()).executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			LOGGER.error("Exception while deleting template in MessageDAOImpl-->delete()"
					+ e.getMessage());
			throw e;
		} finally {
			em.close();
		}
	}
}
