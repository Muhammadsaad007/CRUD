package com.example.my_challange_CRUD.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
	private long id;
	private String FirstName;
	public RegistrationDTO(Registration regist) {
		this.FirstName = regist.getFirstName();
		this.LastName = regist.getLastName();
		this.Email = regist.getEmail();
		this.DOB = regist.getDOB();
		this.Gender = regist.getGender();
		this.Password = regist.getPassword();
		this.PhoneNumber = regist.getPhoneNumber();
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
	private String LastName;
	private String Email;
	private String PhoneNumber;
	private String Gender;
	private String Password;
	private LocalDate DOB;
}
