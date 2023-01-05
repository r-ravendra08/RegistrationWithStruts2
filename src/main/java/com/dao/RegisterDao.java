package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.entity.Users;
import com.mysql.jdbc.PreparedStatement;

public class RegisterDao {

	public static SessionFactory factory = new Configuration().configure().buildSessionFactory();

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registrationwithstruts", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public static boolean check(String email) {
		boolean status = false;
		try {
			Connection con = RegisterDao.getConnection();
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("select*from users where email=?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				// System.out.println("Email Already exist");
			} else {
				// System.out.println("You are New User.");
				status = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public static int register(Users u) {
		int status = 0;
		try {
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			status = (Integer) session.save(u);
			tx.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public static boolean validate(String email, String password) {
		Transaction transaction = null;
		Users users = null;
		Session session = factory.openSession();
		try {
			transaction = session.beginTransaction();
			users = (Users) session.createQuery("from Users e WHERE e.email=:email").setParameter("email", email)
					.uniqueResult();

			if (users != null && users.getPassword().equals(password)) {
				// System.out.println(users.toString());
				return true;
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public static Users getLoggedInUserDetails(String email) {

		Session session = factory.openSession();
		Users user = (Users) session.createQuery("FROM Users WHERE email = :email").setParameter("email", email)
				.uniqueResult();
		//System.out.println(user+"hhh");
		return user;

	}

}
