package com.example.my_challange_CRUD.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Registration")
public class Registration {
	
	public Registration(String firstName, String lastName, String email, String phoneNumber, String gender,
			String password, LocalDate dOB) {
		super();
		FirstName = firstName;
		LastName = lastName;
		Email = email;
		PhoneNumber = phoneNumber;
		Gender = gender;
		Password = password;
		DOB = dOB;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public LocalDate getDOB() {
		return DOB;
	}

	public void setDOB(LocalDate dOB) {
		DOB = dOB;
	}

	public Registration(RegistrationDTO registDTO) {
		this.FirstName = registDTO.getFirstName();
		this.LastName = registDTO.getLastName();
		this.Email = registDTO.getEmail();
		this.DOB = registDTO.getDOB();
		this.Gender = registDTO.getGender();
		this.Password = registDTO.getPassword();
		this.PhoneNumber = registDTO.getPhoneNumber();
	}

	public Registration(RegistrationResponse registDTO) {
		this.FirstName = registDTO.getFirstName();
		this.LastName = registDTO.getLastName();
		this.Email = registDTO.getEmail();
		this.DOB = registDTO.getDOB();
		this.Gender = registDTO.getGender();
		this.Password = registDTO.getPassword();
		this.PhoneNumber = registDTO.getPhoneNumber();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "First_Name", nullable = false)
	private String FirstName;
	
	@Column(name = "Last_Name")
	private String LastName;
	
	@Column(name = "Email", nullable = false)
	private String Email;
	
	@Column(name = "Phone_Number", nullable = false)
	private String PhoneNumber;
	
	@Column(name = "Gender")
	private String Gender;
	
	@Column(name = "Password", nullable = false)
	private String Password;
	
	@Column(name = "DOB")
	private LocalDate DOB;
}
