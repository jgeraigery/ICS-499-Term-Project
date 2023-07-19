package com.abc.termproject.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abc.termproject.utils.DatabaseUtility;

@Controller
public class NavigationController {
	
	DatabaseUtility dbUtil = new DatabaseUtility();

	// This may not be necessary until if/when we want a custom login page (this includes the login.jsp)
//	@GetMapping("/login")
//	public String login() {
//		return "login";
//	}
	
	// Used to redirect user based on login credentials
	@GetMapping("/")
	public String redirect(HttpServletRequest request) {
		if (request.isUserInRole("DRIVER")) {
			return "driver";
		}
		if (request.isUserInRole("ADMIN")) {
			return "admin";
		}
		return "customer";
	}
	
	// Used to access the admin page manually
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	// attempt to map post method in this class without using servlet only the controller and http request
//	@PostMapping("/upload")
//	public String upload(HttpServletRequest request) {
//		String invoiceId = request.getParameter("invoiceId");
//		String userId = request.getParameter("userId");
//		String date = request.getParameter("date");
//		String prodId = request.getParameter("prodId");
//		String quantity = request.getParameter("quantity");
//		
	// i had this method adding invoiceId as well but had to change it due to merge conflicts
//		dbUtil.insertInvoice(Integer.parseInt(invoiceId), Integer.parseInt(userId), date, Integer.parseInt(prodId), Integer.parseInt(quantity));
//		
//		return "uploadsuccess";
//	}
	
//	@GetMapping("/upload")
//	public String upload() {
//		return "admin";
//	}
	
	// Used to access the admin page manually
	@GetMapping("/driver")
	public String driver() {
		return "driver";
	}
	
	// Used to access the customer page manually
	@GetMapping("/customer")
	public String customer() {
		return "customer";
	}
	
	public String getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    return currentUserName;
		}
		return null;
	}
}
