package dataProcess.dataAccess;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dataProcess.DBConnection;
import dataProcess.persistence.Book;

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
	@SuppressWarnings("unchecked")
	public List<Book> findAll() {

		Transaction readTransaction = session.beginTransaction();

		Query readQuery = session.createQuery("FROM Book");
		List<Book> result = readQuery.list();

		readTransaction.commit();

		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Book> findLimited(int first, int offset) {

		Transaction readTransaction = session.beginTransaction();

		List<Book> list = session.createQuery("FROM Book").setFirstResult(first).setMaxResults(offset).list();

		readTransaction.commit();

		return list;
	}

	@Override
	public int numberOfElement() {

		Transaction readTransaction = session.beginTransaction();

		int count = ((Integer)session.createQuery("SELECT count(*) FROM Book").iterate().next()).intValue();

		readTransaction.commit();

		return count;

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

}