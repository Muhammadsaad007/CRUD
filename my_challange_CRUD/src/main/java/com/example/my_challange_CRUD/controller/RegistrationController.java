package com.example.my_challange_CRUD.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.my_challange_CRUD.model.Registration;
import com.example.my_challange_CRUD.model.RegistrationDTO;
import com.example.my_challange_CRUD.model.RegistrationResponse;
import com.example.my_challange_CRUD.service.RegistrationService;

@RestController
@RequestMapping("api/registration")
public class RegistrationController {
	private RegistrationService registrationService;
	
	@GetMapping("/get")
	public List<Registration> getRegistration() {
		return registrationService.getRegistrations();
	}
	
	@PostMapping("/create/")
	@ResponseStatus(code = HttpStatus.OK)
	public RegistrationResponse createRegistration(@RequestBody RegistrationDTO rDTO) {
		return registrationService.createRegistration(rDTO);
	}
	
	@PostMapping("/update/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public RegistrationResponse updateRegistration(@PathVariable long id, @RequestBody RegistrationDTO rDTO) {
		return registrationService.updateRegistration(id, rDTO);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(code = HttpStatus.OK) 
	public ResponseEntity<Boolean> deleteRegistration(@PathVariable long id) {
		boolean deleteStatus = registrationService.deleteRegistration(id);
		return new ResponseEntity<Boolean>(deleteStatus, deleteStatus != false ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/upload")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<Boolean> registrationUpload(MultipartFile file) {
		if (!"text/csv".equals(file.getContentType())) {
			throw new RuntimeException("only csv format accepted");
		}
		long records = registrationService.uploadRegistrations(file);		
		return new ResponseEntity<Boolean>(records > 0 ? true : false, HttpStatus.OK);
	}
}