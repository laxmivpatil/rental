package com.techverse.rental.DTO;

import org.springframework.web.multipart.MultipartFile;

public class IndividualRegistrationDTO {

    private String fullName;
    private String aadharNumber;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    private String referralCode;
    private MultipartFile aadharCardImg;
    
    
    
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getReferralCode() {
		return referralCode;
	}
	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}
	public MultipartFile getAadharCardImg() {
		return aadharCardImg;
	}
	public void setAadharCardImg(MultipartFile aadharCardImg) {
		this.aadharCardImg = aadharCardImg;
	}

    
    
    
    // Getters and setters...

    // You can also add validation or transformation methods if needed
}

