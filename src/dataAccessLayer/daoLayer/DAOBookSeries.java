package dataAccessLayer.daoLayer;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dataAccessLayer.hibernateConfig.DBConnection;
import dataAccessLayer.model.BookSeries;

/**
 * 
 * @author Jeremy
 *
 */
public class DAOBookSeries implements Dao<BookSeries> {

	Session session;

	public DAOBookSeries() {
		this.session = DBConnection.getSession();
	}

	
	@Override
	public boolean create(BookSeries bookSeries) {
		
		Transaction readTransaction = session.beginTransaction();
		session.persist(bookSeries);
		readTransaction.commit();

		return true;
	}

	@Override
	public BookSeries find(int index) {
	
		return null;
	}

	@Override
	public List<BookSeries> list() {
		
		return null;
	}

	@Override
	public int count() {
	
		return 0;
	}

	@Override
	public boolean update(BookSeries object) {
		
		Transaction updateTransaction = session.beginTransaction();
		session.merge(object);
		updateTransaction.commit();

		return true;
	}

	@Override
	public boolean delete(BookSeries bookSeries) {
		
		Transaction deleteTransaction = session.beginTransaction();
		session.delete(bookSeries);
		deleteTransaction.commit();	

		return false;
	}

	@SuppressWarnings("unchecked")
	public List<BookSeries> getBookSeriesByName(String name) {
		
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("FROM BookSeries WHERE name LIKE CONCAT('%', :name, '%')")
				.setString("name", name);
		List<BookSeries> listBookSeries = readQuery.list();
		readTransaction.commit();

		return listBookSeries;
	}
	
	
}
