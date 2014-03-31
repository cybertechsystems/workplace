package com.email.dao;

import static com.email.util.Constants.ERROR_COMPOSE_MAIL_DELETE;
import static com.email.util.Constants.ERROR_COMPOSE_MAIL_SAVE;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.email.model.ComposeMail;
import com.email.util.ApplicationException;

@Named("messageDAO")
public class MessageDAOImpl extends AbstractEntityManager implements
		MessageDAO {
	
	private Logger LOGGER = Logger.getLogger("com.mailmodule.dao.MessageDAOImpl");

	/* save the mail to the database */
	public void save(ComposeMail mail) throws ApplicationException {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(mail);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			LOGGER.error("Exception while saving message in MessageDAOImpl-->save()"
					+ e.getMessage());
			throw new ApplicationException(e, ERROR_COMPOSE_MAIL_SAVE);
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
	public void deleteMail(ComposeMail mail) throws ApplicationException {
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
			throw new ApplicationException(e, ERROR_COMPOSE_MAIL_DELETE);
		} finally {
			em.close();
		}
	}
}
