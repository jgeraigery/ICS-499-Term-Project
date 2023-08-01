package com.abc.termproject.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	
	/**
	 * Work in progress method. Currently uploads invoice items to database from file, each line represents a different invoice item.
	 * File line example looks like: 7,3,2023-12-08,8,5
	 * NOTE: You have to change the directory to one on your personal machine, so follow the database connection pattern where we comment out each others
	 * 
	 * @param upload
	 */
	@PostMapping(path="/admin")
	public void upload(@RequestParam MultipartFile upload) {
		try {
			upload.transferTo(new File("C:\\Users\\Thomas\\OneDrive\\Desktop\\Computer Science & Coding\\Summer 2023\\ICS 499\\Term Project\\Milestone 4\\"+upload.getOriginalFilename()));
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Thomas\\OneDrive\\Desktop\\Computer Science & Coding\\Summer 2023\\ICS 499\\Term Project\\Milestone 4\\"+upload.getOriginalFilename()));
			String temp[]; 
			String currentLine;
			
			while ((currentLine = br.readLine()) != null) {
				temp = currentLine.split(",");
				if (temp.length == 5) {
					dbUtil.insertInvoice(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), temp[2], Integer.parseInt(temp[3]), Integer.parseInt(temp[4]));
				}
			}
			br.close();
		} catch (Exception x) {
			x.printStackTrace();
		}
	}
	
	// Post request method for changing the status of a delivery
    @PostMapping(path="/driver")
    public void driverButtonAction(@RequestParam String command) {
        
        int deliveryID;
        
        //System.out.println(command);
        //System.out.println(command.substring(0, 6));
        
        if (command.substring(0, 6).equals("Verify")) {
            
            try {
                
                deliveryID = Integer.parseInt(command.substring(7));
                
                //System.out.println(deliveryID);
                
                dbUtil.verifyDelivery(deliveryID);
                
                System.out.println("DeliveryID " + deliveryID + " was verified");
                
            } catch (Exception ex) {
                
                System.out.println("error - could not verify delivery\n" + ex.getMessage());
                
            }
            
        } else if (command.substring(0, 6).equals("Cancel")) {
            
            try {
                
                deliveryID = Integer.parseInt(command.substring(7));
                
                //System.out.println(deliveryID);
                
                dbUtil.cancelDelivery(deliveryID);
                
                System.out.println("DeliveryID " + deliveryID + " was cancelled");
                
            } catch (Exception ex) {
                
                System.out.println("error - could not cancel delivery\n" + ex.getMessage());
                
            }
        }
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
