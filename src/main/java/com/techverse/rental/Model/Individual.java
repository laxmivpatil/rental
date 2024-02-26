package com.techverse.rental.Model;

//Individual.java
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
public class Individual {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String fullName;
 private String phoneNumber;
 private String aadharNumber;
 private String email; 
 private String address;
 private String referralCode; // optional
 private String aadharCardImg;
 private Date createdDate;
 
 private String deviceToken="";
 
 
 
public String getDeviceToken() {
	return deviceToken;
}
public void setDeviceToken(String deviceToken) {
	this.deviceToken = deviceToken;
}
 
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
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
public Individual(String fullName, String phoneNumber, String aadharNumber, String email,
		String address, String referralCode, String aadharCardImg) {
	super();
	this.fullName = fullName;
	this.phoneNumber = phoneNumber;
	this.aadharNumber = aadharNumber;
	this.email = email;
	 
	this.address = address;
	this.referralCode = referralCode;
	this.aadharCardImg = aadharCardImg;
	Instant instant = Instant.parse(Instant.now().toString());

    
	 ZoneId zoneId = ZoneId.of("Asia/Kolkata"); // Choose the appropriate time zone
     LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
 
	this.createdDate = createdDate;
}

 
 
 
 
 // getters and setters

 // You can also create additional methods or constructors as needed
}
