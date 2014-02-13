package dataProcess.daoLayer;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dataProcess.domainModel.Book;
import dataProcess.hibernateConfig.DBConnection;

public class DAOBook implements Dao<Book>{

	Session session;

	public DAOBook() {
		this.session = DBConnection.getSession();
	}

	@Override
	public boolean create(Book book) {

		Transaction readTransaction = session.beginTransaction();
		session.persist(book);
		readTransaction.commit();

		return true;
	}

	@Override
	public Book find(int index) {
		
		Transaction readTransaction = session.beginTransaction();
		
		Query readQuery = session.createQuery("FROM Book WHERE id = :id")
				.setInteger("id", index);
		Book result = (Book)readQuery.list().get(0);
		readTransaction.commit();

		return result;
	}

	@Override
	public List<Book> list() {
		return null;
	}
	
	@Override
	public int count() {
		Transaction readTransaction = session.beginTransaction();
		//int bookNumber = ((int)session.createQuery("select count(*) from Book").uniqueResult()).intValue();
		readTransaction.commit();
		return 0;
	}
	
	@Override
	public boolean update(Book object) {
		
		Transaction updateTransaction = session.beginTransaction();
		session.merge(object);
		updateTransaction.commit();

		return true;
	}

	@Override
	public boolean delete(Book book) {

		Transaction deleteTransaction = session.beginTransaction();
		session.delete(book);
		deleteTransaction.commit();	

		return false;
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> getBooksByYear(int year) {
		
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("FROM Book WHERE year = :year")
				.setInteger("year", year);
		List<Book> listBooks = readQuery.list();
		readTransaction.commit();

		return listBooks;
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> getBooksByName(String name) {
		
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("FROM Book WHERE name LIKE CONCAT('%', :name, '%')")
				.setString("name", name);
		List<Book> listBooks = readQuery.list();
		readTransaction.commit();

		return listBooks;
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> getBooksByAuthor(String name) {
		
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("FROM Book WHERE name LIKE CONCAT('%', :name, '%')")
				.setString("name", name);
		List<Book> listBooks = readQuery.list();
		readTransaction.commit();

		return listBooks;
	}

}