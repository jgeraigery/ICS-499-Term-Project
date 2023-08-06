package com.abc.termproject.controller;

import java.io.File;
import java.io.FileReader;
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
	
	/**
	 * Method to redirect user based on login credentials
	 * @param (HttpServletRequest) - request
	 * @return (String) - landing page
	 */
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
	 * Method for uploading invoice items to database from file, each line represents a different invoice item.
	 * If a delivery needs to be added then it can as well by following the syntax displayed below.
	 * File line example of invoice item looks like: 7,1,2023-12-08,8,5
	 * File line example of delivery looks like: in progress,4,1,1,7,2023-12-08
	 * NOTE: You have to change the directory to one on your personal machine (lines 58 and 59)
	 * 
	 * @param (MultipartFile) - upload; uploaded file from user, must be a csv following format specified above
	 */
	@PostMapping(path="/admin")
	public void upload(@RequestParam MultipartFile upload) {
		if (upload.getOriginalFilename().substring(upload.getOriginalFilename().length()-4).equalsIgnoreCase(".csv")) {
			// do nothing, this is good
		} else {
			return;
		}
		try {
			upload.transferTo(new File("C:\\Users\\Thomas\\OneDrive\\Desktop\\Computer Science & Coding\\Summer 2023\\ICS 499\\Term Project\\Milestone 4\\"+upload.getOriginalFilename()));
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Thomas\\OneDrive\\Desktop\\Computer Science & Coding\\Summer 2023\\ICS 499\\Term Project\\Milestone 4\\"+upload.getOriginalFilename()));
			String temp[]; 
			String currentLine;
			
			while ((currentLine = br.readLine()) != null) {
				temp = currentLine.split(",");
				if (temp.length == 5) {
					dbUtil.insertInvoice(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), temp[2], Integer.parseInt(temp[3]), Integer.parseInt(temp[4]));
				} else if (temp.length == 6) {
					dbUtil.insertDelivery(temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), Integer.parseInt(temp[3]), Integer.parseInt(temp[4]), temp[5]);
				} else {
					// do nothing bad line
				}
			}
			br.close();
		} catch (Exception x) {
			x.printStackTrace();
		}
	}
	
	/**
	 * Post request method for changing the status of a delivery
	 * @param (String) - command
	 */
    @PostMapping(path="/driver")
    public void driverButtonAction(@RequestParam String command) {
        
        int deliveryID;
        
        if (command.substring(0, 6).equals("Verify")) {
            
            try {
                
                deliveryID = Integer.parseInt(command.substring(7));
                
                dbUtil.verifyDelivery(deliveryID);
                
                System.out.println("DeliveryID " + deliveryID + " was verified");
                
            } catch (Exception ex) {
                
                System.out.println("error - could not verify delivery\n" + ex.getMessage());
                
            }
            
        } else if (command.substring(0, 6).equals("Cancel")) {
            
            try {
                
                deliveryID = Integer.parseInt(command.substring(7));
                
                dbUtil.cancelDelivery(deliveryID);
                
                System.out.println("DeliveryID " + deliveryID + " was cancelled");
                
            } catch (Exception ex) {
                
                System.out.println("error - could not cancel delivery\n" + ex.getMessage());
                
            }
            
        } else if (command.substring(0, 6).equals("InProg")) {
            
            try {
                
                deliveryID = Integer.parseInt(command.substring(7));
                
                dbUtil.inProgDelivery(deliveryID);
                
                System.out.println("DeliveryID " + deliveryID + " was set to in progress");
                
            } catch (Exception ex) {
                
                System.out.println("error - could not set to In Progress\n" + ex.getMessage());
                
            }
        }
    }
	
    /**
     * Method used for accessing current user logged into app
     * @return (String) - current user name
     */
	public String getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    return currentUserName;
		}
		return null;
	}
}
