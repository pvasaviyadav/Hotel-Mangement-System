package com.jsp.hotelmanagementsystem.controller;

import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.hotelmanagement.Hotel;
import com.jsp.hotelmanagement.dao.AdminDao;
import com.jsp.hotelmanagement.dao.HotelDao;
import com.mysql.cj.x.protobuf.MysqlxCrud.ModifyView;

@Controller
public class Hotelcontroller {
	
	@Autowired
	HotelDao dao;
   
	@RequestMapping("/addhotel")
	// handler used to create Hotel class object and pass it to HotelForm.jsp
	public ModelAndView addHotel() {
		Hotel hotel=new Hotel();
		ModelAndView mav=new ModelAndView();
		mav.addObject("hotelobj",hotel);
		mav.setViewName("HotelForm");
		return mav;
	}
	
	@RequestMapping("/savehotel")
	//handler used to save hotel details into databse
	public ModelAndView saveHotel(@ModelAttribute("hotelobj") Hotel hotel) {
		hotel.setStatus("Not approved");
		dao.saveHotel(hotel);
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("message","your account is in review,please wait for sometime");
		mav.setViewName("Hotellogin");
		return mav;
	}
	
	@RequestMapping("/hotelloginvalidate")
	//handler used to perform loginvalidations and return
	public ModelAndView hotelLoginValidation(ServletRequest request,HttpSession session) {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		Hotel hotel=dao.login(email, password);
		if(hotel!=null) {
			if(hotel.getStatus().equals("Not approved")) {
				ModelAndView mav=new ModelAndView();
				mav.addObject("message","your account is in review, please wait for sometime");
				mav.setViewName("displaymessage");
				return mav;
			}
			else if(hotel.getStatus().equals("blocked")){
				ModelAndView mav=new ModelAndView();
				mav.addObject("message","your account is blocked");
				mav.setViewName("displaymessage");
				return mav;
		    }
			else {
				   //storing hotel entity object into session
				   //storing the logged in hotel into session
				   session.setAttribute("hotelinfo", hotel.getId());
				   ModelAndView mav=new ModelAndView();
				   mav.addObject("message","logged in successfully");
				   mav.setViewName("hoteloptions");
				   return mav;
			    }
		}
		else {
			   ModelAndView mav=new ModelAndView();
			   mav.addObject("message","invalid credentials");
			   mav.setViewName("Hotellogin");
			   return mav;
		    }
		
	}
	
	@RequestMapping("/fetchunapprovedhotels")
	//handler is used to return unapproved hotels
	public ModelAndView fetchUnapprovedHotels() {
		List<Hotel> hotels=dao.findUnapprovedHotels();
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("unapprovedhotels",hotels);
		mav.setViewName("displayunapprovedhotels");
		return mav;
	}
	
	@RequestMapping("/approvehotel")
	//handler is used to modify the hotel status as approved
	public ModelAndView approvedHotel(@RequestParam("id") int id) {
		Hotel h=dao.findHotelById(id);
		h.setStatus("approved");
		dao.updateHotel(h);
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect://fetchunapprovedhotels");
		return mav;
	}
	
	@RequestMapping("/fetchunblockedhotels")
	//handler is used to return unblocked hotels
	public ModelAndView fetchunblockedhotels() {
		List<Hotel> hotels=dao.findUnblockedHotels();
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("unblockedhotels",hotels);
		mav.setViewName("displayunblockedhotes");
		return mav;
	}
	
	@RequestMapping("/blockedhotel")
	//handler is used to modify the hotel status as blocked
	public ModelAndView blockedHotel(@RequestParam("id") int id) {
		Hotel h=dao.findHotelById(id);
		h.setStatus("blocked");
		dao.updateHotel(h);
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect://fetchunblockedhotels");
		return mav;
	}
	
	@RequestMapping("/fetchblockedhotels")
	//handler is used to return blocked hotels
	public ModelAndView fetchblockedhotels() {
		List<Hotel> hotels=dao.findblockedHotels();
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("blockedhotels",hotels);
		mav.setViewName("displayblockedhotels");
		return mav;
	}
	
	@RequestMapping("/Unblockedhotel")
	//handler is used to modify the hotel status as unblocked
	public ModelAndView UnblockedHotel(@RequestParam("id") int id) {
		Hotel h=dao.findHotelById(id);
		h.setStatus("approved");
		dao.updateHotel(h);
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect://fetchunblockedhotels");
		return mav;
	}
	
	@RequestMapping("/hotellogout")
	//handler is used to delete the hotel data in HttpSession
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mav=new ModelAndView();
		mav.addObject("message","logged out successfully");
		mav.setViewName("HotelHome.jsp");
		
		return mav;
	}	
}
