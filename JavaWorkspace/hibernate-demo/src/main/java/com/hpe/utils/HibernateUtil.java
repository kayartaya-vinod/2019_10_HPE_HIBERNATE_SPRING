package com.hpe.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateUtil {

	// singleton
	private static SessionFactory factory = null;

	private HibernateUtil() {
	}

	public static SessionFactory getSessionFactory() {
		if (factory == null) {
			Configuration cfg = new Configuration();
			cfg.configure();
			factory = cfg.buildSessionFactory();
		}
		return factory;
	}
}
