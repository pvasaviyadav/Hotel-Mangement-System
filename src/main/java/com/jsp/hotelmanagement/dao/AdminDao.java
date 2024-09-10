package com.jsp.hotelmanagement.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.jsp.hotelmanagement.Admin;
import com.mysql.cj.Query;

@Repository
public class AdminDao {
    @Autowired
	EntityManagerFactory emf;
    
    @Autowired
    EntityManager em;
    
    @Autowired
    EntityTransaction et;
    
    public void saveAdmin(Admin admin) {
    	et.begin();
    	em.persist(admin);
    	et.commit();
    }
    
    public Admin findById(int id) {
    	return em.find(Admin.class,id);
    }
    
    public void updateAdmin(Admin admin) {
    	et.begin();
    	em.merge(admin);
    	et.commit();
    }
    
    public void deleteById(int id) {
    	et.begin();
    	em.remove(em.find(Admin.class, id));
    	et.commit();
    }
    
    public Admin login(String name,String password) {
    	javax.persistence.Query query=em.createQuery("select a from Admin a where a.name=?1 and a.password=?2");
    	query.setParameter(1,name);
    	query.setParameter(2, password);
    	
    	try {
    		Admin a=(Admin) query.getSingleResult();
    		return a;
    	}catch (NoResultException e) {
			return null;
		}
    }
    
}
