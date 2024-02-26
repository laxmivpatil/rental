package com.techverse.rental.Repository;

import java.util.Optional;

//IndividualRepository.java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.techverse.rental.Model.Individual;
 

@Repository
public interface IndividualRepository extends JpaRepository<Individual, Long> {
 // You can add custom queries if needed
	Optional<Individual> findByPhoneNumber(String phoneNumber);
	
	  @Query("SELECT u FROM Individual u WHERE u.phoneNumber = :value OR u.email = :value")
	    Optional<Individual> findByPhoneNumberOrEmail(@Param("value") String phoneNumberOrEmail);
}
