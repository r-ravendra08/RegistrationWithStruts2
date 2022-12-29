package com.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dao.RegisterDao;
import com.opensymphony.xwork2.ActionSupport;

@Entity
public class Users extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4399548643038005847L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name, email, password;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// for validation
	public void validate() {
		String regex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

		if (email.matches(regex)) {

		} else {
			addFieldError("email", "Enter Valid Email Address");

		}

		if (name.length() < 1)
			addFieldError("name", "Name can't be blank");
		if (email.length() < 1)
			addFieldError("email", "Email can't be blank");
		if (password.length() < 5)
			addFieldError("password", "Password must be greater than 4");
	}

	public String execute() {
		if (!RegisterDao.check(email)) {
			System.out.println("user already exist");
			return "error";

		} else {
			int i = RegisterDao.register(this);
			if (i > 0) {
				return "success";
			}
			return "error";
		}

	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", email=" + email + ", dateTime=" + dateTime + "]";
	}
	

}
