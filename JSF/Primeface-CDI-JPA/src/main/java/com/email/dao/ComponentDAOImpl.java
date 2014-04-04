package com.email.dao;

import static com.email.util.Constants.ERROR_COMPONENT_DELETE;
import static com.email.util.Constants.ERROR_COMPONENT_SAVE;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.email.model.Component;
import com.email.model.Data;
import com.email.util.ApplicationException;

@Named("componentDAO")
public class ComponentDAOImpl extends AbstractEntityManager implements
	ComponentDAO {
		
	private Logger LOGGER = Logger.getLogger("com.mailmodule.dao.ComponentDAOImpl");
	
	/* save the data to the database */
	public void save(Data data) throws ApplicationException {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(data);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			LOGGER.error("Exception while saving data in ComponentDAOImpl-->save()"
					+ e.getMessage());
			throw new ApplicationException(e, ERROR_COMPONENT_SAVE);
		} finally {
			em.close();
		}
	}
	
	/* Listing all datas from the database */
	@SuppressWarnings("unchecked")
	public List<Data> loadAllComponent() throws Exception {
		List<Data> dataList = null;
		EntityManager em = getEntityManager();
		try {
			dataList = em.createQuery(
					"Select d from Data d").getResultList();
		} catch (Exception e) {
			LOGGER.error("Exception while listing data in ComponentDAOImpl-->loadAllComponent()"
					+ e.getMessage());
			throw e;
		} finally {
			em.close();
		}
		return dataList;
	}
	
	/* Listing all components from the database */
	@SuppressWarnings("unchecked")
	public List<Component> loadAllComponentName() throws Exception {
		List<Component> componentNameList = null;
		EntityManager em = getEntityManager();
		try {
			componentNameList = em.createQuery(
					"Select c from Component c").getResultList();
		} catch (Exception e) {
			LOGGER.error("Exception while listing Component in ComponentDAOImpl-->loadAllComponentName()"
					+ e.getMessage());
			throw e;
		} finally {
			em.close();
		}
		return componentNameList;
	}
	
	/* Deleting the data based on Id from the database */
	public void deleteComponent(Data data) throws ApplicationException {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.createQuery("DELETE FROM Data d where d.id=:id")
					.setParameter("id", data.getId()).executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			LOGGER.error("Exception while deleting data in ComponentDAOImpl-->delete()"
					+ e.getMessage());
			throw new ApplicationException(e, ERROR_COMPONENT_DELETE);
		} finally {
			em.close();
		}
	}
	
	/* save the data to the database */
	public void saveComponent(Component component) throws ApplicationException {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(component);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			LOGGER.error("Exception while saving component in ComponentDAOImpl-->save()"
					+ e.getMessage());
			throw new ApplicationException(e, ERROR_COMPONENT_SAVE);
		} finally {
			em.close();
		}
	}
}
