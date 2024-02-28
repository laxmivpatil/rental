package com.techverse.rental.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.techverse.rental.Model.Company;
import com.techverse.rental.Model.Individual;  

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

	
	 @Query("SELECT u FROM Company u WHERE u.phoneNumber = :value OR u.email = :value")
	    Optional<Company> findByPhoneNumberOrEmail(@Param("value") String phoneNumberOrEmail);
}
