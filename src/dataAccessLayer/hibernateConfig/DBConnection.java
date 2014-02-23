package dataAccessLayer.hibernateConfig;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import dataAccessLayer.model.Administrator;
import dataAccessLayer.model.Author;
import dataAccessLayer.model.Book;
import dataAccessLayer.model.BookSeries;
import dataAccessLayer.model.Borrowing;
import dataAccessLayer.model.Customer;
import dataAccessLayer.model.Editor;
import dataAccessLayer.model.Theme;

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
