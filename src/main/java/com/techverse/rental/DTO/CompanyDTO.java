package com.techverse.rental.DTO;
 

import java.util.Date;

public class CompanyDTO {
    private Long id;
    private String firmName;
    private String phoneNumber;
    private String aadharNumber;
    private String email;
    private String address;
    private String referralCode;
    private String aadharCardImg;
    private Date createdDate;
    private String gstNo;

    // Constructors, getters, and setters

    private String role;
	 
	 
   	public String getRole() {
   		return role;
   	}
   	public void setRole(String role) {
   		this.role = role;
   	}
    public CompanyDTO() {
    }

     

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}

	public String getAadharCardImg() {
		return aadharCardImg;
	}

	public void setAadharCardImg(String aadharCardImg) {
		this.aadharCardImg = aadharCardImg;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}
    
    
    

    // Other methods as needed
}
