package dataAccessLayer.hibernateConfig;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import dataAccessLayer.domainObjects.Administrator;
import dataAccessLayer.domainObjects.Author;
import dataAccessLayer.domainObjects.Book;
import dataAccessLayer.domainObjects.BookSeries;
import dataAccessLayer.domainObjects.Borrowing;
import dataAccessLayer.domainObjects.Customer;
import dataAccessLayer.domainObjects.Editor;
import dataAccessLayer.domainObjects.Theme;

public class DBConnection {
	private static SessionFactory sessionFactory;
	private static AnnotationConfiguration config;

	public static AnnotationConfiguration getConfig() {
		if (config == null) {
			config = new AnnotationConfiguration();
			config.addAnnotatedClass(Administrator.class);
			config.addAnnotatedClass(Author.class);
			config.addAnnotatedClass(Book.class);
			config.addAnnotatedClass(Borrowing.class);
			config.addAnnotatedClass(Customer.class);
			config.addAnnotatedClass(BookSeries.class);
			config.addAnnotatedClass(Editor.class);
			config.addAnnotatedClass(Theme.class);

			config.configure("dataAccessLayer/hibernateConfig/connection.cfg.xml");

		}
		return config;
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				AnnotationConfiguration config = getConfig();
				sessionFactory = config.buildSessionFactory();
			} catch (Throwable ex) {
				System.err.println("Initial SessionFactory creation failed." + ex);
				throw new ExceptionInInitializerError(ex);
			}
		}
		return sessionFactory;
	}

	public static Session getSession() {
		return getSessionFactory().openSession();
	}
}
