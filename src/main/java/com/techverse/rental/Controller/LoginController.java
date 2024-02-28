package com.techverse.rental.Controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techverse.rental.DTO.ApiResponse;
import com.techverse.rental.DTO.OtpVerificationResult;
import com.techverse.rental.DTO.ResponseDTO;
import com.techverse.rental.Model.Company;
import com.techverse.rental.Model.Individual;
import com.techverse.rental.Repository.IndividualRepository;
import com.techverse.rental.Service.CompanyService;
import com.techverse.rental.Service.IndividualService;
import com.techverse.rental.Service.OtpService;

@RestController
@RequestMapping("")
public class LoginController {
	
	 @Autowired
	    private OtpService otpService;
	@Autowired
	 private IndividualService individualService;
	
	@Autowired
	 private CompanyService companyService;
	 @Autowired
	 private IndividualRepository individualRepository;
	
	
	@GetMapping("/checkforlogin")
	public ResponseEntity<?> checkUserByIdentifier(@RequestParam String mobileoremail) {
		 
	    ApiResponse responseBody = new ApiResponse();
	    try {
	         if (!individualService.getIndividualByMobileOrEmail(mobileoremail).isPresent()&&!companyService.getCompanyByMobileOrEmail(mobileoremail).isPresent()) {
	        	responseBody.setStatus(false);
             responseBody.setMessage("This email or phone not registered as a individual or company");
               return new ResponseEntity<>(responseBody, HttpStatus.OK);
	             
	        } 
	        else {
	                responseBody.setStatus(true);
	                responseBody.setMessage("This email or phone allready registered");

	                return ResponseEntity.status(HttpStatus.OK).body(responseBody);
	            }
	         
	    } catch (Exception e) {
	     System.out.println("hi "+e);
	        responseBody.setStatus(false);
	        responseBody.setMessage("Failed to retrieve user." + e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
	    }
	}

	
	 @PostMapping("/login")
		public ResponseEntity<ResponseDTO<Object>> login(@org.springframework.web.bind.annotation.RequestBody Map<String, String> request) {
		    ResponseDTO<Object> response = new ResponseDTO<>();
		   
		    String emailorphone = request.get("emailorphone");
		    String otp = request.get("otp");

		    if (StringUtils.isEmpty( emailorphone) || StringUtils.isEmpty(otp)) {
		    	 response.setStatus(false);
		        response.setMessage("Missing required credentials.");
		        return ResponseEntity.badRequest().body(response);
		    }

		    int otpVerificationResult = otpService.verifyOtp(emailorphone, otp);

		    if (otpVerificationResult == OtpVerificationResult.SUCCESS) {
		        // Generate authentication token (you can use JWT)
		    	//String token = generateToken(phoneNumber);
		    	Optional<Individual> individual=individualService.getIndividualByMobileOrEmail(emailorphone );
		    	Optional<Company> company=companyService.getCompanyByMobileOrEmail(emailorphone );
		    	if(individual.isPresent())
		    	{
		    	    response.setData(individualService.convertToDto(individual.get()));
		    	}
		    	if(company.isPresent())
		    	{
		    	    response.setData(companyService.convertToDto(company.get()));
		    	}
		        response.setStatus(true);
		        response.setMessage("verification successful");
		    
		      //  response.setData(token);
		        return ResponseEntity.ok(response);
		    } else if (otpVerificationResult == OtpVerificationResult.EXPIRED) {
		    	 response.setStatus(false);
		        response.setMessage("OTP has expired. Please request a new OTP.");
		         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		    } else {
		    	 response.setStatus(false);
		        response.setMessage("Invalid OTP. Please enter a valid OTP.");
		         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		    }
		}

}
