package dataProcess.hibernateConfig;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import dataProcess.persistenceLayer.Administrator;
import dataProcess.persistenceLayer.Author;
import dataProcess.persistenceLayer.Book;
import dataProcess.persistenceLayer.BookSeries;
import dataProcess.persistenceLayer.Borrowing;
import dataProcess.persistenceLayer.Customer;
import dataProcess.persistenceLayer.Editor;
import dataProcess.persistenceLayer.Theme;

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

			String packageName = DBConnection.class.getPackage().getName();

			config.configure(packageName + "/connection.cfg.xml");

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
