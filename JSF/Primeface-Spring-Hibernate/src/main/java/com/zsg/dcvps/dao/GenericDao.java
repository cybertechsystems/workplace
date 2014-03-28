package com.zsg.dcvps.dao;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.core.GenericTypeResolver;

import com.zsg.dcvps.entity.Details;

public abstract class GenericDao<T> {
    protected Session                     session;
    private Class<T>                    genericType;


    @SuppressWarnings("unchecked")
    public GenericDao(SessionFactory sessionFactory) {
        this.genericType = (Class<T>) GenericTypeResolver.resolveTypeArgument(
                getClass(), GenericDao.class);
        if (sessionFactory != null) {
            session = sessionFactory.openSession();
        } 
    }

    public T find(String query, Object[] params) {
        try {
            
            Query st = session.getNamedQuery(query);
            
            for(int i = 0; i < params.length ; i ++) {
            	st.setParameter(i, params[i]);
            }
            
            return (T) st.uniqueResult();
        } catch (final HibernateException e) {
            throw e;
        } 
    }
    
    public List<T> findAll(String query, Object[] params) {
        try {
            
            Query st = session.getNamedQuery(query);
            
            for(int i = 0; i < params.length ; i ++) {
            	st.setParameter(i, params[i]);
            }
            
            return (List<T>) st.list();
        } catch (final HibernateException e) {
            throw e;
        } 
    }
    
    public void delete(T item) {
        try {
            session.delete(item);
            session.flush();
        } catch (final HibernateException e) {
            throw e;
        } 
    }

    public void save(T item) {
        try {
            session.save(item);
        } catch (final HibernateException e) {
            throw e;
        } 
    }

    @SuppressWarnings("unchecked")
    public T find(Long id) {
        T result = null;
        try {
            result = (T) session.load(genericType, id);
        } catch (final HibernateException e) {
            throw e;
        } 
        return result;
    }
    
    @SuppressWarnings("unchecked")
    public void update(T item) {
    	 try {
             session.merge(item);
             session.flush();
         } catch (final HibernateException e) {
             throw e;
         } 
    }
    
    public List<T> findWithNamedQuery(String query, int start, int end) {
    	try {
	    	Query st = session.getNamedQuery(query);
	    	st.setMaxResults(end - start);
	    	st.setFirstResult(start);
	        return (List<T>) st.list();
	    } catch (final HibernateException e) {
	        throw e;
	    } 
    }
	
	public int countTotalRecord(String query) {
        try {
			Query st = session.getNamedQuery(query);
			Number result = (Number) st.uniqueResult();
			return result.intValue();
		} catch (final HibernateException e) {
			throw e;
		}
    }
	
	public List<T> findWithCheckId(String query, int start, int end, String checkId) {
		try {
	    	Query st = session.getNamedQuery(query);
	    	st.setMaxResults(end - start);
	    	st.setFirstResult(start);
	    	st.setParameter("checkId", checkId);
	        return (List<T>) st.list();
	    } catch (final HibernateException e) {
	        throw e;
	    } 
	}
	
	public int countTotalDetails(String query, String checkId) {
		try {
	    	Query st = session.getNamedQuery(query);
	    	st.setParameter("checkId", checkId);
	    	Number result = (Number) st.uniqueResult();
	        return result.intValue();
	    } catch (final HibernateException e) {
	        throw e;
	    } 
	}
}