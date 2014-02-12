package dataProcess.dataAccessLayer;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dataProcess.hibernateConfig.DBConnection;
import dataProcess.persistenceLayer.Book;

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

	@Override
	public List<Book> list() {
		return null;
	}


}