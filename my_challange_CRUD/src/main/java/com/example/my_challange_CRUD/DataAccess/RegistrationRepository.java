package com.example.my_challange_CRUD.DataAccess;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.my_challange_CRUD.model.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

	@Query("select r from Registration r where r.Email = :email")
	Optional<Registration> findByEmail(@Param("email") String email);
	
}
