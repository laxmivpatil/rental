package com.techverse.rental.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techverse.rental.DTO.CompanyDTO;
import com.techverse.rental.DTO.IndividualDTO;
import com.techverse.rental.DTO.ResponseDTO;
import com.techverse.rental.Model.Company;
import com.techverse.rental.Model.Individual;
import com.techverse.rental.Service.CompanyService;
import com.techverse.rental.Service.OtpService; 
@RestController
public class CompanyRegistrationController {
	
	 @Autowired
	    private OtpService otpService;
	@Autowired
	 private CompanyService companyService;

	
 
	@PostMapping("/company/register") 
	public ResponseEntity<ResponseDTO<Object>> registerCompany(
	    @RequestPart("firmName") String firmName,
	    @RequestPart("phoneNumber") String phoneNumber,
	    @RequestPart("aadharNumber") String aadharNumber,
	    @RequestPart("email") String email,
	    @RequestPart("address") String address,
	    @RequestPart("referralCode") String referralCode,
	    @RequestPart("gstNo") String gstNo,
	    @RequestPart("aadharCardImg") MultipartFile aadharCardImg) {

		 ResponseDTO<Object> response = new ResponseDTO<>();
		 String otp=otpService.generateOtpAll(email);
		 if(otp.equals("error")) {
			    response.setStatus(false);
	            response.setMessage("error to send otp");
	            response.setData("");
	              return new ResponseEntity<>(response, HttpStatus.OK);
		             
		 }
	    Company registeredCompany = companyService.registerCompany(firmName, phoneNumber, aadharNumber, email, address, referralCode, aadharCardImg, gstNo);

	    CompanyDTO companyDTO = companyService.convertToDto(registeredCompany);

	    response.setStatus(true);
        response.setMessage("company registration successfull"+otp);
        response.setData(companyDTO);
          return new ResponseEntity<>(response, HttpStatus.OK);
	}
	 
	@GetMapping("/company/checkcompanybymobileoremail")
	public ResponseEntity<ResponseDTO<Object>> checkUserByIdentifier(@RequestParam String mobileoremail) {
	 ResponseDTO<Object> response = new ResponseDTO<>();
	     
	    try {
	    	Optional<Company> registeredCompany=companyService.getCompanyByMobileOrEmail(mobileoremail);
	    	
	        if (registeredCompany.isPresent()) {
	        	CompanyDTO  companyDTO =  companyService.convertToDto(registeredCompany.get());     
	        response.setStatus(false);
             response.setMessage("Company already registered");
             response.setData(companyDTO);
               return new ResponseEntity<>(response, HttpStatus.OK);
	             
	        } 
	             else {
	                response.setStatus(true);
	                response.setMessage("Company not registered");

	                return ResponseEntity.status(HttpStatus.OK).body(response);
	            }
	         
	    } catch (Exception e) {
	     System.out.println("hi "+e);
	        response.setStatus(false);
	        response.setMessage("Failed to retrieve user." + e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

}
