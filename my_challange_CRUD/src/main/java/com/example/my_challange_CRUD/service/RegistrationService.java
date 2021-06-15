package com.example.my_challange_CRUD.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.example.my_challange_CRUD.DataAccess.RegistrationRepository;
import com.example.my_challange_CRUD.model.Registration;
import com.example.my_challange_CRUD.model.RegistrationDTO;
import com.example.my_challange_CRUD.model.RegistrationResponse;

public class RegistrationService {
	private RegistrationRepository registrationRepo;
	
	public List<Registration> getRegistrations() {
		return registrationRepo.findAll();
	}
	
	public RegistrationResponse createRegistration(RegistrationDTO registDTO) {
		Registration registered = registrationRepo.save(new Registration(registDTO));
		RegistrationResponse registrationResponse = new RegistrationResponse(registered);
		return registrationResponse;
	}

	public RegistrationResponse updateRegistration(long id, RegistrationDTO rDTO) {
		Registration registration = registrationRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("No such registration found"));
		if (registration != null) {
			registration.setFirstName(rDTO.getFirstName());
			registration.setLastName(rDTO.getLastName());
			registration.setGender(rDTO.getGender());
			registration.setEmail(rDTO.getEmail());
			registration.setDOB(rDTO.getDOB());
			registration.setPhoneNumber(rDTO.getPhoneNumber());
			registration.setPassword(rDTO.getPassword());
			
			registrationRepo.save(registration);
		}
		return new RegistrationResponse(registration);
	}

	public boolean deleteRegistration(long id) {
		Registration registration = registrationRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("No such registration found"));
		if (registration != null) {
			registrationRepo.delete(registration);
		}
		return true;
	}

	public long uploadRegistrations(MultipartFile file) {
		List<Registration> successfulRegistrations = new ArrayList<Registration>();
		
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
		        CSVParser csvParser = new CSVParser(fileReader,
		            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

		      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

		      for (CSVRecord csvRecord : csvRecords) {
		    	  Registration regs = new Registration(
		    			  csvRecord.get("First_Name"),
		    			  csvRecord.get("Last_Name"),
		    			  csvRecord.get("Email"),
		    			  csvRecord.get("Phone_Number"),
		    			  csvRecord.get("Gender"),
		    			  csvRecord.get("Password"),
		    			  LocalDate.parse(csvRecord.get("DOB"), DateTimeFormatter.ISO_LOCAL_DATE)
		            );
		    	  
		    	  Optional<Registration> existingRegistration = registrationRepo.findByEmail("Email");
		    	  if (existingRegistration.isPresent()) {
		    		  RegistrationResponse updatedRegistration = updateRegistration(existingRegistration.get().getId(), new RegistrationDTO(existingRegistration.get()));
		    		  successfulRegistrations.add(new Registration(updatedRegistration));
		    	  }
		    	  else {
		    		  successfulRegistrations.add(regs);
		    	  }
		      }
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		    }
		
		return successfulRegistrations.size();
	}
}
