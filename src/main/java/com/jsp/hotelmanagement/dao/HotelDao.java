package com.jsp.hotelmanagement.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.hotelmanagement.Customer;
import com.jsp.hotelmanagement.Hotel;
import com.mysql.cj.Query;

@Repository
public class HotelDao {
   
	 @Autowired
		EntityManagerFactory emf;
	    
	    @Autowired
	    EntityManager em;
	    
	    @Autowired
	    EntityTransaction et;
	    
	    public void saveHotel(Hotel hotel) {
	    	et.begin();
	    	em.persist(hotel);
	    	et.commit();
	    }
	    
	    public Hotel findHotelById(int id) {
	    	return em.find(Hotel.class, id);
	    }
	    
	    public void updateHotel(Hotel hotel) {
	    	et.begin();
	    	em.merge(hotel);
	    	et.commit();
	    }
	    
	    public void deleteHotelById(int id) {
	    	et.begin();
	    	em.remove(em.find(Hotel.class, id));
	    	et.commit();
	    }
	    
	    public Hotel login(String email,String password) {
	    	javax.persistence.Query query= em.createQuery("select c from Hotel c where c.email=?1 and c.password=?2");
	    	query.setParameter(1, email);
	    	query.setParameter(2, password);
	    	
	    	try {
	    		return (Hotel)query.getSingleResult();
	    	}catch (NoResultException e) {
				return null;
			}
	    }
	    
	    public List<Hotel> findUnapprovedHotels(){
	    	javax.persistence.Query query=em.createQuery("select h from Hotel h where h.status='Not approved'");
	    	return query.getResultList();
	    }
	    
	    public List<Hotel> findUnblockedHotels(){
	    	javax.persistence.Query query=em.createQuery("select h from Hotel h where h.status='approved'");
	    	return query.getResultList();
	    }
	    
	    public List<Hotel> findblockedHotels(){
	    	javax.persistence.Query query=em.createQuery("select h from Hotel h where h.status='blocked'");
	    	return query.getResultList();
	    }
}
