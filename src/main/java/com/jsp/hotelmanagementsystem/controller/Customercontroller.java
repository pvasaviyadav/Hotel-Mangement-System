package com.jsp.hotelmanagementsystem.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.hotelmanagement.Admin;
import com.jsp.hotelmanagement.Customer;
import com.jsp.hotelmanagement.dao.CustomerDao;

@Controller
@ControllerAdvice
public class Customercontroller {
   
	@Autowired
	CustomerDao dao;
	
	@RequestMapping("/addcustomer")
	//handler use to create customer object and forword to CustomerForm.jsp
	public ModelAndView addCustomer() {
		Customer customer=new Customer();
		ModelAndView mav=new ModelAndView();
		mav.addObject("customerobj",customer);
		mav.setViewName("CustomerForm");
		return mav;
	}
	
	@RequestMapping("/savecustomer")
	//handler used to save customer details into databse
		public ModelAndView saveAdmin(@ModelAttribute("customerobj") Customer customer) {
			dao.saveCustomer(customer);
			
			ModelAndView mav= new ModelAndView();
			mav.addObject("message","Registered successfully");
			mav.setViewName("customerlogin");
			
			return mav;
		}
	@RequestMapping("/customerloginvalidate")
	//handler used to perform login validation for customer
		public ModelAndView loginValidation(ServletRequest request,HttpSession session) {
			String name=request.getParameter("name");
			String password=request.getParameter("password");
			
			Customer customer=dao.login(name, password);
			
			if(customer != null) {
				session.setAttribute("customerinfo",customer.getId());
				ModelAndView mav= new ModelAndView();
				mav.addObject("message","loggedin succesfully");
				mav.setViewName("Customeroptions");
				return mav;
			}
			else {
				ModelAndView mav=new ModelAndView();
				mav.addObject("message","invalid credentials");
				mav.setViewName("customerlogin");
				return mav;
			}
}
}
