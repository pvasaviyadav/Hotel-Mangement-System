package com.jsp.hotelmanagement.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.hotelmanagement.Customer;
import com.jsp.hotelmanagement.Item;
import com.jsp.hotelmanagement.Product;

@Repository
public class ItemDao {
  
	 @Autowired
		EntityManagerFactory emf;
	    
	    @Autowired
	    EntityManager em;
	    
	    @Autowired
	    EntityTransaction et;
	    
	    public void saveItem(Item Item) {
	    	et.begin();
	    	em.persist(Item);
	    	et.commit();
	    }
	    
	    public Item findById(int id) {
	    	return em.find(Item.class, id);
	    }
	    
	    public void updateItem(Item Item) {
	    	et.begin();
	    	em.merge(Item);
	    	et.commit();
	    }
	    
	    public void deleteById(int id) {
	    	et.begin();
	    	em.remove(em.find(Item.class, id));
	    	et.commit();
	    }
	    
	    public Item login(String email,String password) {
	    	javax.persistence.Query query= em.createQuery("select c from Item c where c.email=?1 and c.password=?2");
	    	query.setParameter(1, email);
	    	query.setParameter(2, password);
	    	
	    	try {
	    		return (Item)query.getSingleResult();
	    	}catch (NoResultException e) {
				return null;
			}
	    }
}
