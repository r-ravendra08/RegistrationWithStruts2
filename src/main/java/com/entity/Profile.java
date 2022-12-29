package com.entity;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.dao.RegisterDao;

public class Profile {
	public String execute() {
		

		
		HttpSession session = ServletActionContext.getRequest().getSession(false);
		String email = (String) session.getAttribute("email");
	
		System.out.println(RegisterDao.getLoggedInUserDetails(email).toString());

		String s = (String) session.getAttribute("login");
		if (s != null && !s.equals("")) {
			return "success";
		} else {
			return "error";
		}
	}
}
