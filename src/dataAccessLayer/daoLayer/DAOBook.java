package dataAccessLayer.daoLayer;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dataAccessLayer.hibernateConfig.DBConnection;
import dataAccessLayer.model.Book;

/**
 * 
 * @author Jeremy
 *
 */
public class DAOBook implements Dao<Book>{

	Session session;

	public DAOBook() {
		this.session = DBConnection.getSession();
	}

	@Override
	public boolean create(Book book) {
		try {
			Transaction readTransaction = session.beginTransaction();
			session.persist(book);
			readTransaction.commit();
		} catch (Exception e) {
			return false;
		}


		return true;
	}

	@Override
	public Book find(int index) {
		try {
			Transaction readTransaction = session.beginTransaction();

			Query readQuery = session.createQuery("FROM Book WHERE id = :id")
					.setInteger("id", index);
			Book result = (Book)readQuery.list().get(0);
			readTransaction.commit();	

			return result;
			
		} catch (Exception e) {
			return null;
		}
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
	public List<Book> getBooksByAuthor(int id) {

		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("SELECT DISTINCT b FROM Book b INNER JOIN b.author a WHERE a.id = :id")
				.setInteger("id",id);
		List<Book> listBooks = readQuery.list();
		readTransaction.commit();

		return listBooks;
	}

	@SuppressWarnings("unchecked")
	public List<Book> getBooksByParameters(String name, String author, int year, String bookType, String series, Boolean availibility){
		
		Transaction readTransaction = session.beginTransaction();
		if (year == 0) {
			Query readQuery = session.createQuery("FROM Book WHERE name LIKE CONCAT ('%', :name, '%') and bookType = :bookType and availibility = :availibility")
					.setString("name",name).setString("bookType",bookType).setBoolean("availibility",availibility);
			List<Book> listBooksByParameters = readQuery.list();
			readTransaction.commit();
			return listBooksByParameters;
		} else {
			Query readQuery = session.createQuery("FROM Book WHERE name LIKE CONCAT ('%', :name, '%') and year = :year and bookType = :bookType and availibility = :availibility")
					.setString("name",name).setInteger("year",year).setString("bookType",bookType).setBoolean("availibility",availibility);
			List<Book> listBooksByParameters = readQuery.list();
			readTransaction.commit();
			return listBooksByParameters;
		}


	}

	//public List<Book> getBooksByBookSeries
}