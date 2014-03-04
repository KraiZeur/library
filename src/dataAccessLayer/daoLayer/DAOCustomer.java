package dataAccessLayer.daoLayer;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dataAccessLayer.hibernateConfig.DBConnection;
import dataAccessLayer.model.Customer;

/**
 * 
 * @author Jeremy
 *
 */
public class DAOCustomer implements Dao<Customer> {

	Session session;

	public DAOCustomer() {
		this.session = DBConnection.getSession();
	}

	@Override
	public boolean create(Customer customer) {
		Transaction readTransaction = session.beginTransaction();
		session.persist(customer);
		readTransaction.commit();

		return true;
	}

	@Override
	public Customer find(int index) {

		try {
			Transaction readTransaction = session.beginTransaction();

			Query readQuery = session.createQuery("FROM Customer WHERE id = :id")
					.setInteger("id", index);
			Customer result = (Customer)readQuery.list().get(0);
			readTransaction.commit();

			return result;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<Customer> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean update(Customer object) {
		Transaction updateTransaction = session.beginTransaction();
		session.merge(object);
		updateTransaction.commit();

		return true;
	}

	@Override
	public boolean delete(Customer customer) {
		Transaction deleteTransaction = session.beginTransaction();
		session.delete(customer);
		deleteTransaction.commit();    

		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Customer> getCustomerByName(String lastname) {

		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("FROM Customer WHERE lastname LIKE CONCAT ('%', :lastname, '%')")
				.setString("lastname",lastname);
		List<Customer> listCustomer = readQuery.list();
		readTransaction.commit();

		return listCustomer;
	}


	@SuppressWarnings("unchecked")
	public List<Customer> getCustomerByParameters(String firstname, String lastname, int zipcode){

		if (zipcode == 0) {
			Transaction readTransaction = session.beginTransaction();
			Query readQuery = session.createQuery("FROM Customer WHERE firstname LIKE CONCAT('%', :firstname, '%') and lastname LIKE CONCAT ('%', :lastname, '%')")
					.setString("firstname",firstname).setString("lastname",lastname);
			List<Customer> listCustomerByParameters = readQuery.list();
			readTransaction.commit();
			return listCustomerByParameters;
		}
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("FROM Customer WHERE firstname LIKE CONCAT('%', :firstname, '%') and lastname LIKE CONCAT ('%', :lastname, '%') and zipcode=:zipcode")
				.setString("firstname",firstname).setString("lastname",lastname).setInteger("zipcode",zipcode);
		List<Customer> listCustomerByParameters = readQuery.list();
		readTransaction.commit();
		return listCustomerByParameters;
	}

}