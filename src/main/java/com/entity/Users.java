package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.dao.RegisterDao;
import com.opensymphony.xwork2.ActionSupport;

@Entity
public class Users extends ActionSupport {

	/**
	 * 
	 * 
	 */
	public static final Logger logger = Logger.getLogger(Users.class.getName());

	private static final long serialVersionUID = 4399548643038005847L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name, email, password;
	@Column(name = "Date_Of_Joining")
	private String dateofjoining;

	public String getDateofjoining() {
		return dateofjoining;
	}

	public void setDateofjoining(String dateofjoining) {
		this.dateofjoining = dateofjoining;
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

		} else if (email.length() < 1) {
			addFieldError("email", "Email can't be blank");
		} else {
			addFieldError("email", "Enter vaid email address");

		}

		if (name.length() < 1)
			addFieldError("name", "Name can't be blank");

		if (password.length() < 1) {
			addFieldError("password", "Password can't be blank");

		} else if (password.length() < 5) {
			addFieldError("password", "Password must be greater than 4");
		}

	}

	public String execute() {
		  BasicConfigurator.configure();  
		if (!RegisterDao.check(email)) {
			System.out.println("user already exist");
			logger.error("User already exist:= " +email);
			return "error";

		} else {
			int i = RegisterDao.register(this);
			if (i > 0) {
				  logger.info("You are a new User:= "+email);
				return "success";
			}
			return "error";
		}
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", email=" + email + ", dateofjoining=" + dateofjoining + "]";
	}
}
