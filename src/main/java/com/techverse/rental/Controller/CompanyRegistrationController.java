package com.techverse.rental.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techverse.rental.Model.Company;
import com.techverse.rental.Model.Individual;
import com.techverse.rental.Service.CompanyService; 
@RestController
public class CompanyRegistrationController {
	
	@Autowired
	 private CompanyService companyService;

	
 
		@PostMapping("/company/register") 
		 public ResponseEntity<String> registerIndividual(
				 @RequestPart("firmName") String firmName,
					@RequestPart("phoneNumber") String phoneNumber,
					@RequestPart("aadharNumber") String aadharNumber,
					@RequestPart("email") String email,
					@RequestPart("address") String address,
					@RequestPart("referralCode") String referralCode,
					@RequestPart("gstNo") String gstNo,
					@RequestPart("aadharCardImg") MultipartFile aadharCardImg) {
			 
		    Company registeredCompany = companyService.registerCompany(firmName,phoneNumber,aadharNumber,email,address,referralCode,aadharCardImg,gstNo);
		     return new ResponseEntity<>("Company registered successfully with ID: " + registeredCompany.getId(), HttpStatus.CREATED);
		 }
	 
}
