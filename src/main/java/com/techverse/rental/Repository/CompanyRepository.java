package com.techverse.rental.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techverse.rental.Model.Company;  

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

}
