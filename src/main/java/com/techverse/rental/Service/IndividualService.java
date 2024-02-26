package com.techverse.rental.Service;

//IndividualService.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
 
import com.techverse.rental.Model.Individual;
import com.techverse.rental.Repository.IndividualRepository;
 

import java.util.Date;
import java.util.Optional;

@Service
public class IndividualService {

 @Autowired
 private IndividualRepository individualRepository;
 @Autowired
 private StorageService storageService;

 public Individual registerIndividual( String fullName, String phoneNumber, String aadharNumber, String email,
			String address, String referralCode, MultipartFile aadharCardImg) {
	 String path="";
     // Add validation logic, password hashing, etc.
	
	 if (aadharCardImg != null && !aadharCardImg.isEmpty()) {
         // Implement logic to handle profile photo upload, save to storage, etc.
         // For example, you can save the file to a specific directory or cloud storage.
         // Update admin's profile photo URL in the database accordingly.
         // admin.setProfilePhotoUrl(savedProfilePhotoUrl);
       path=storageService.uploadFileOnAzure(aadharCardImg);
     	 
     	            }
	 Individual individual=new Individual(fullName,phoneNumber,aadharNumber,email,address,referralCode,path);
     return individualRepository.save(individual);
 }
 
 public Optional<Individual>  getIndividualByMobileOrEmail(String mobileOremail)
 {
	 return individualRepository.findByPhoneNumberOrEmail(mobileOremail);
 }
 public Optional<Individual> getUserByToken(String token) {
	 String mobileNoOrEmail="";
 	//String mobileNoOrEmail=jwtHelper.getUsernameFromToken(token);
	//	 System.out.println("hi "+userName);
		
     return individualRepository.findByPhoneNumberOrEmail(mobileNoOrEmail);
 }

 
 
}
