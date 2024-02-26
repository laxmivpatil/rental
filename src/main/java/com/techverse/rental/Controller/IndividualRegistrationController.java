package com.techverse.rental.Controller;

import java.util.Optional;

//IndividualController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techverse.rental.DTO.ApiResponse;
import com.techverse.rental.DTO.IndividualDTO;
import com.techverse.rental.DTO.ResponseDTO;
import com.techverse.rental.Model.Individual;
import com.techverse.rental.Repository.IndividualRepository;
import com.techverse.rental.Service.IndividualService;
 
@RestController
@RequestMapping("")
public class IndividualRegistrationController {

 @Autowired
 private IndividualService individualService;
 @Autowired
 private IndividualRepository individualRepository;
 @PostMapping("/individual/register") 
 public ResponseEntity<String> registerIndividual(
		 @RequestPart("fullName") String fullName,
			@RequestPart("phoneNumber") String phoneNumber,
			@RequestPart("aadharNumber") String aadharNumber,
			@RequestPart("email") String email,
			@RequestPart("address") String address,
			@RequestPart("referralCode") String referralCode,
			@RequestPart("aadharCardImg") MultipartFile aadharCardImg) {
	 
     Individual registeredIndividual = individualService.registerIndividual(fullName,phoneNumber,aadharNumber,email,address,referralCode,aadharCardImg);
     return new ResponseEntity<>("Individual registered successfully with ID: " + registeredIndividual.getId(), HttpStatus.CREATED);
 }
 @GetMapping("/individual/checkindividualbymobileoremail")
	public ResponseEntity<?> checkUserByIdentifier(@RequestParam String mobileoremail) {
		 
	    ApiResponse responseBody = new ApiResponse();
	    try {
	     	        
	        if (individualService.getIndividualByMobileOrEmail(mobileoremail).isPresent()) {
	        	responseBody.setStatus(false);
             responseBody.setMessage("User already registered");
               return new ResponseEntity<>(responseBody, HttpStatus.OK);
	             
	        } 
	             else {
	                responseBody.setStatus(true);
	                responseBody.setMessage("User not registered");

	                return ResponseEntity.status(HttpStatus.OK).body(responseBody);
	            }
	         
	    } catch (Exception e) {
	     System.out.println("hi "+e);
	        responseBody.setStatus(false);
	        responseBody.setMessage("Failed to retrieve user." + e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
	    }
	}

	//final
		@GetMapping("/individual/getindividual")
		public ResponseEntity<ResponseDTO<IndividualDTO>> getUserById(@RequestHeader("Authorization") String authorizationHeader) {
			 Optional<Individual> user = individualService.getUserByToken(authorizationHeader.substring(7));
		     
			ResponseDTO<IndividualDTO> responseBody = new ResponseDTO<>();
			  	try {
		         if (user.isPresent()) {
		            responseBody.setStatus(true);
		            responseBody.setMessage("User retrieved successfully.");
		            responseBody.setData(new IndividualDTO(user.get())); // Convert user object to string if needed
		            return ResponseEntity.ok(responseBody);
		        } else {
		        	 responseBody.setStatus(false);
		   		    responseBody.setMessage("User not found.");
		            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
		        }
		    } catch (Exception e) {
		    	 responseBody.setStatus(false);
			        responseBody.setMessage( "Failed to retrive user.");
			        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
			    }
		}

		
		@GetMapping("/user/adddevicetoken")
		public ResponseEntity<ResponseDTO<?>> addDeviceToken(@RequestHeader("Authorization") String authorizationHeader,@RequestParam(value="deviceToken", required=true)String token) {
			 Optional<Individual> user = individualService.getUserByToken(authorizationHeader.substring(7));
		     
			ResponseDTO<String> responseBody = new ResponseDTO<>();
			  	try {
		         if (user.isPresent()) {
		        	 	 System.out.println("token updated");
		        	 user.get().setDeviceToken(token);
		        	 
		        	 individualRepository.save(user.get());
		        	 
		            responseBody.setStatus(true);
		            responseBody.setMessage("device token Saved successfully.");
		            responseBody.setData("");
		            return ResponseEntity.ok(responseBody);
		        } else {
		        	 responseBody.setStatus(false);
		   		    responseBody.setMessage("User not found.");
		            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
		        }
		    } catch (Exception e) {
		    	 responseBody.setStatus(false);
			        responseBody.setMessage( "Failed to retrive user.");
			        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
			    }
		}

}
