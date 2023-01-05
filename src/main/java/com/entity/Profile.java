package com.entity;

import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.dao.RegisterDao;

public class Profile {
	public static final Logger logger = Logger.getLogger(Profile.class.getName());

	public String execute() {
		BasicConfigurator.configure();

		HttpSession session = ServletActionContext.getRequest().getSession(false);
		String email = (String) session.getAttribute("email");

		//System.out.println(RegisterDao.getLoggedInUserDetails(email).toString());

		String s = (String) session.getAttribute("login");
		if (s != null && !s.equals("")) {
			logger.info("Login User details:= "+RegisterDao.getLoggedInUserDetails(email).toString());
			return "success";
		} else {
			logger.error("Logged in user getting error.");

			return "error";
		}
	}
}
