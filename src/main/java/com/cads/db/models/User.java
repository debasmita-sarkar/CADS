package com.cads.db.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.cads.db.repository.FlatRepository;

@Entity
@Table(name="user")
public class User {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id=0;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;	
	
	@Column
	private String email;
	
	@Column
	private String phone;
	
	@Column(name="dateofbirth")
	private String dateOfBirth;
	
	@Column(name="usergroup")
	private String userGroup;
	
	@Column(name="flatid")
	private int flatId;
	
	@Column
	private int buildingid;	

	public int getBuildingid() {
		return buildingid;
	}
	public void setBuildingid(int buildingid) {
		this.buildingid = buildingid;
	}

	@Transient
	private String flatNumber;
	
	@Column
	private String password;	
	
	@Transient
	private String salt;
	
	@Transient
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public int getFlatId() {
		return flatId;
	}

	public void setFlatId(int flatId) {
		this.flatId = flatId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		if(salt == null) {
			return email;
		}
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}
	
	public String getFlatNumber() {
		return this.flatNumber;
	}

	public void setFlatNumber(String flatNumber) {		
		this.flatNumber = flatNumber;
	}
	
	

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phone=" + phone + ", dateOfBirth=" + dateOfBirth + ", userGroup=" + userGroup + ", flatId="
				+ flatId + ", password=" + password + ", salt=" + salt + ", token=" + token + "]";
	}
}
