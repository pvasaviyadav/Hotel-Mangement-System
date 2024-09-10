package com.jsp.hotelmanagementsystem.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.hotelmanagement.Admin;
import com.jsp.hotelmanagement.dao.AdminDao;
import com.mysql.cj.Query;

@Controller
@ControllerAdvice
public class Admincontroller {
	
	@Autowired
	AdminDao dao;
   
	@RequestMapping("/addadmin")
	//handler use to create admin object and forword to Adminform.jsp
	public ModelAndView addAdmin() {
		Admin admin=new Admin();
		ModelAndView mav=new ModelAndView();
		mav.addObject("adminobj",admin);
		mav.setViewName("AdminForm");
		return mav;
	}
	
	@RequestMapping("/saveadmin")
	//handler used to save admin details into databse
	public ModelAndView saveAdmin(@ModelAttribute("adminobj") Admin admin) {
		dao.saveAdmin(admin);
		
		ModelAndView mav= new ModelAndView();
		mav.addObject("message","account created successfully");
		mav.setViewName("AdminLogin");
		
		return mav;
	}
	@RequestMapping("/adminloginvalidate")
	//handler used to perform login validation for admin
	public ModelAndView loginValidation(ServletRequest request,HttpSession session) {
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		
		Admin admin=dao.login(name, password);
		
		if(admin != null) {
			session.setAttribute("admininfo",admin);
			ModelAndView mav= new ModelAndView();
			mav.addObject("message","loggedin succesfully");
			mav.setViewName("Adminoptions");
			return mav;
		}
		else {
			ModelAndView mav=new ModelAndView();
			mav.addObject("message","invalid credentials");
			mav.setViewName("AdminLogin");
			return mav;
		}
	}
	@RequestMapping("/adminlogout")
	//handler is used to delete the admin data in HttpSession
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mav=new ModelAndView();
		mav.addObject("message","logged out successfully");
		mav.setViewName("Adminhome");
		
		return mav;
	}
	
}
