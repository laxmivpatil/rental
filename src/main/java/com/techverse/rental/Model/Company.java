package com.techverse.rental.Model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Company {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 private String firmName;
	 private String PhoneNumber;
	 private String aadharNumber;
	 private String email; 
	 private String address;
	 private String referralCode; // optional
	 private String aadharCardImg;
	 private Date createdDate;
	 private String gstNo;
	 
	 
	public String getFirmName() {
		return firmName;
	}
	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}
	public String getGstNo() {
		return gstNo;
	}
	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Company(String firmName, String phoneNumber, String aadharNumber, String email,
			String address, String referralCode, String aadharCardImg,String gstNo) {
		super();
		this.firmName = firmName;
		this.PhoneNumber = phoneNumber;
		this.aadharNumber = aadharNumber;
		this.email = email;
		 
		this.address = address;
		this.referralCode = referralCode;
		this.aadharCardImg = aadharCardImg;
		Instant instant = Instant.parse(Instant.now().toString());

	    
		 ZoneId zoneId = ZoneId.of("Asia/Kolkata"); // Choose the appropriate time zone
	     LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
	 
		this.createdDate = createdDate;
		this.gstNo=gstNo;
	}

	 
	 
}
