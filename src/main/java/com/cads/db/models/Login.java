package com.cads.db.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Login {
	
	@Column
	private String userName;	
	@Column
	private String password;	
	@Column
	private String lastLoginUser;
	@Id
	private int Id;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLastLoginUser() {
		return lastLoginUser;
	}
	public void setLastLoginUser(String lastLoginUser) {
		this.lastLoginUser = lastLoginUser;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
}
