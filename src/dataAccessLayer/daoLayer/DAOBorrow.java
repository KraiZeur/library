package dataAccessLayer.daoLayer;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dataAccessLayer.hibernateConfig.DBConnection;
import dataAccessLayer.model.Borrowing;

/**
 * 
 * @author Jeremy
 *
 */
public class DAOBorrow implements Dao<Borrowing> {

	Session session;

	public DAOBorrow() {
		this.session = DBConnection.getSession();
	}

	@Override
	public boolean create(Borrowing object) {
		try {
			Transaction readTransaction = session.beginTransaction();
			session.persist(object);
			readTransaction.commit();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public Borrowing find(int index) {
		try {
			Transaction readTransaction = session.beginTransaction();

			Query readQuery = session.createQuery("FROM Borrowing WHERE id = :id")
					.setInteger("id", index);
			Borrowing result = (Borrowing)readQuery.list().get(0);
			readTransaction.commit();	

			return result;
			
		} catch (Exception e) {
			return null;
		}
	}
	
	public int countByYear(int year) {
		try {
			Transaction readTransaction = session.beginTransaction();

			int result = ( (Long) session.createQuery("select count(*) from Borrowing WHERE date_borrowing BETWEEN '"+year+"-01-01' AND '"+year+"-12-30'").iterate().next() ).intValue();
			readTransaction.commit();	

			return result;
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return 0;
		}
	}
	
	public Borrowing findByBookId(int bookId) {
		try {
			Transaction readTransaction = session.beginTransaction();

			Query readQuery = session.createQuery("FROM Borrowing WHERE id_book = :bookId")
					.setInteger("bookId", bookId);
			Borrowing result = (Borrowing)readQuery.list().get(0);
			readTransaction.commit();	

			return result;
			
		} catch (Exception e) {
			return null;
		}
	}
	
	public Borrowing findByBookIdWhenNull(int bookId) {
		try {
			Transaction readTransaction = session.beginTransaction();

			Query readQuery = session.createQuery("FROM Borrowing WHERE id_book = :bookId AND date_delivery IS NULL")
					.setInteger("bookId", bookId);
			Borrowing result = (Borrowing)readQuery.list().get(0);
			readTransaction.commit();	

			return result;
			
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Borrowing> list() {
		try {
			Transaction readTransaction = session.beginTransaction();

			Query readQuery = session.createQuery("FROM Borrowing");
			List<Borrowing> result = readQuery.list();
			readTransaction.commit();	

			return result;
			
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int count() {
		return 0;
	}
	
	@Override
	public boolean update(Borrowing object) {
		Transaction updateTransaction = session.beginTransaction();
		session.merge(object);
		updateTransaction.commit();

		return true;
	}

	@Override
	public boolean delete(Borrowing object) {
		return false;
	}

}
