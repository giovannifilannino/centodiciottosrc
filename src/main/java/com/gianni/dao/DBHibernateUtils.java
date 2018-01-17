package com.gianni.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBHibernateUtils {

	private static final SessionFactory sessionFactory;
	private static final ThreadLocal<Session> session = new ThreadLocal<Session>();

	static {

		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError();
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session currentSession() {
		Session s = (Session) session.get();
		if (s == null) {
			s = sessionFactory.openSession();
			session.set(s);
		}

		return s;
	}

	public static void closeSession() {
		Session s = (Session) session.get();

		if (s != null) {
			s.close();
		}

		session.set(null);
	}

}
