package com.jsp.hotelmanagementsystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.hotelmanagement.Hotel;
import com.jsp.hotelmanagement.Product;
import com.jsp.hotelmanagement.dao.HotelDao;
import com.jsp.hotelmanagement.dao.ProductDao;

@Controller
@ControllerAdvice
public class Productcontroller {
	
	@Autowired
	ProductDao productDao;
	@Autowired
	HotelDao hotelDao;
    
	@RequestMapping("/addproduct")
	public ModelAndView addProduct() {
		Product p=new Product();
		ModelAndView mav=new ModelAndView();
		mav.addObject("productobj",p);
		mav.setViewName("productform");
		return mav;
	}
	@RequestMapping("/saveproduct")
	public ModelAndView saveProduct(@ModelAttribute("productobj") Product p,HttpSession session) {
		Integer hotel_id=(Integer) session.getAttribute("hotelinfo");
		Hotel hotel=hotelDao.findHotelById(hotel_id);
		List<Product> products=hotel.getProduct();
		if(products.size()>0) {
			products.add(p);
		}
		else {
			List<Product> productList=new ArrayList<Product>();
			productList.add(p);
			hotel.setProduct(productList);
		}
		productDao.saveProduct(p);
		hotelDao.updateHotel(hotel);
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("message","added successfully");
		mav.setViewName("hoteloptions");
		return mav;
	}
	@RequestMapping("/fetchallhotelproducts")
	public ModelAndView fetchallHotelProducts(HttpSession session) {
		Integer hotel_id=(Integer)session.getAttribute("hotelinfo");
		Hotel hotel= hotelDao.findHotelById(hotel_id);
		List<Product> products=hotel.getProduct();
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("products",products);
		mav.setViewName("displayproductstohotel");
		return mav;
	}
	
	@RequestMapping("/updateproduct")
	public ModelAndView updateProductInformation(@RequestParam("id") int id) {
		Product product=productDao.findById(id);
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("existingProductInfo",product);
		mav.setViewName("updateproductform");
		return mav;
	}
	
	@RequestMapping("/updateproductinfo")
	public ModelAndView updateProductInformation(@ModelAttribute("existingProductInfo") Product p) {
		productDao.updateProduct(p);
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("message","updated successfully");
		mav.setViewName("redirect://fetchallhotelproducts");
		return mav;
	}
	
	@RequestMapping("/delete")
	public ModelAndView deleteProduct(@RequestParam("id") int id,HttpSession session) {
		Integer hotel_id=(Integer) session.getAttribute("hotelinfo");
		Hotel hotel=hotelDao.findHotelById(hotel_id);
		List<Product> products= hotel.getProduct();
		
		List<Product> productsList=products.stream().filter(product ->product.getId() != id).collect(Collectors.toList());
		hotel.setProduct(productsList);
		
		hotelDao.updateHotel(hotel);
		productDao.deleteById(id);
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("message","deleted successfully");
		mav.setViewName("redirect://fetchallhotelproducts");
		return mav;
	}
	@RequestMapping("/fetchproductsbyhotel")
	public ModelAndView fetchProductByHotel(ServletRequest request) {
		String hotelName =request.getParameter("hotelname");
		List<Product> products =productDao.fetchProductsByHotel(hotelName);
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("productsList",products);
		mav.setViewName("displayproductstocustomer");
		return mav;
	}
	@RequestMapping("/fetchallproducts")
	public ModelAndView fetchAllProducts() {
		List<Product> products=productDao.findAll();
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("productsList",products);
		mav.setViewName("displayproductstocustomer");
		return mav;
	}
	@RequestMapping("/fetchproductsbetweenpricerange")
	public ModelAndView fetchProductsBetweenPriceRange(ServletRequest request) {
		String minPrice=request.getParameter("minprice");
		String maxPrice=request.getParameter("maxprice");
		
		List<Product> products=productDao.fetchProductsBetweenPriceRange(Double.parseDouble(minPrice),Double.parseDouble(maxPrice));
		ModelAndView mav=new ModelAndView();
		mav.addObject("productsList",products);
		mav.setViewName("displayproductstocustomer");
		return mav;
	}
}
