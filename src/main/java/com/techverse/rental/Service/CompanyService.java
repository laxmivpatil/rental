package com.techverse.rental.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.techverse.rental.Model.Company;
import com.techverse.rental.Model.Individual;
import com.techverse.rental.Repository.CompanyRepository;
import com.techverse.rental.Repository.IndividualRepository;

@Service
public class CompanyService {
	@Autowired
	 private CompanyRepository companyRepository;
	 @Autowired
	 private StorageService storageService;

	 public Company registerCompany( String fullName, String phoneNumber, String aadharNumber, String email,
				String address, String referralCode, MultipartFile aadharCardImg,String gstNo) {
		 String path="";
	     // Add validation logic, password hashing, etc.
		
		 if (aadharCardImg != null && !aadharCardImg.isEmpty()) {
	         // Implement logic to handle profile photo upload, save to storage, etc.
	         // For example, you can save the file to a specific directory or cloud storage.
	         // Update admin's profile photo URL in the database accordingly.
	         // admin.setProfilePhotoUrl(savedProfilePhotoUrl);
	       path=storageService.uploadFileOnAzure(aadharCardImg);
	     	 
	     	            }
		 Company company=new Company(fullName,phoneNumber,aadharNumber,email,address,referralCode,path,gstNo);
	     return companyRepository.save(company);
	 }
	 

}
