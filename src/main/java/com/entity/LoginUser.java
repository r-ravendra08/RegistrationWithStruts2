package com.entity;

import java.util.Map;

import javax.servlet.http.Cookie;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.dao.RegisterDao;
import com.opensymphony.xwork2.ActionSupport;

public class LoginUser extends ActionSupport implements SessionAware {
	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(LoginUser.class.getName());

	private static final long serialVersionUID = 1L;
	private String email, password;
	SessionMap<String, String> sessionmap;

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

	// public void validate() {
	// String regex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
	// if (email.matches(regex)) {
	// } else {
	// addFieldError("email", "Enter Valid Email Address");
	// }
	// if (email.length() < 1)
	// addFieldError("email", "Email can't be blank");
	// if (password.length() < 4)
	// addFieldError("password", "Password length should be 4 digit");
	// }
	

	public String execute() {
		  BasicConfigurator.configure();  

		if (RegisterDao.validate(email, password)) {
			
			sessionmap.put("login", "true");
			sessionmap.put("email", email);
			logger.info("Login Success:= "+email);
			return "success";
		} else {
			logger.error("Login Error");
			return "error";
		}
	}

	@Override
	public String toString() {
		return "LoginUser [email=" + email + ", password=" + password + "]";
	}

	public void setSession(Map<String, Object> map) {
		sessionmap = (SessionMap) map;
	}

	public String logoutmethod() {
		sessionmap.invalidate();
		logger.info("Logout Success.");
		return "success";
	}

}
