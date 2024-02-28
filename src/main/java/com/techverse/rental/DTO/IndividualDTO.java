package com.techverse.rental.DTO;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.techverse.rental.Model.Individual;

public class IndividualDTO {
	 private Long id;

	 private String fullName;
	 private String PhoneNumber;
	 private String aadharNumber;
	 private String email; 
	 private String address;
	 private String referralCode; // optional
	 private String aadharCardImg;
	 private Date createdDate;
	 
	 private String role;
	 
	 
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
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
	 
	 
	 
	 
}
