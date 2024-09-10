package com.jsp.hotelmanagement.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.hotelmanagement.FoodOrder;

@Repository
public class FoodOrderDao {
  
	    @Autowired
		EntityManagerFactory emf;
	    
	    @Autowired
	    EntityManager em;
	    
	    @Autowired
	    EntityTransaction et;
	    
	    public void saveFoodOrder(FoodOrder FoodOrder) {
	    	et.begin();
	    	em.persist(FoodOrder);
	    	et.commit();
	    }
	    
	    public FoodOrder findById(int id) {
	    	return em.find(FoodOrder.class, id);
	    }
	    
	    public void updateFoodOrder(FoodOrder FoodOrder) {
	    	et.begin();
	    	em.merge(FoodOrder);
	    	et.commit();
	    }
	    
	    public void deleteById(int id) {
	    	et.begin();
	    	em.remove(em.find(FoodOrder.class, id));
	    	et.commit();
	    }
	    
	    public FoodOrder login(String email,String password) {
	    	javax.persistence.Query query= em.createQuery("select c from FoodOrder c where c.email=?1 and c.password=?2");
	    	query.setParameter(1, email);
	    	query.setParameter(2, password);
	    	
	    	try {
	    		return (FoodOrder)query.getSingleResult();
	    	}catch (NoResultException e) {
				return null;
			}
	    }
}
