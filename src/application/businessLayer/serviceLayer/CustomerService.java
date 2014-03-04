package application.businessLayer.serviceLayer;

import java.util.Date;
import java.util.List;

import dataAccessLayer.daoLayer.DAOCustomer;
import dataAccessLayer.model.Customer;
import dataAccessLayer.model.CustomerAdress;
import dataAccessLayer.model.Name;

/**
 * 
 * @author Thomas
 *
 */
public class CustomerService {

	/**
	 * 
	 */
	private DAOCustomer daoCustomer = new DAOCustomer();

	/**
	 * The default constructor
	 */
	public CustomerService() {

	}

	/**
	 * 
	 * @param firstname
	 * @param lastname
	 * @param number
	 * @param street
	 * @param city
	 * @param zipcode
	 * @param date
	 * @return
	 */
	public boolean createCustomerWithParameters(String firstname, String lastname, String number, String street, String city, int zipcode, Date date) {

		Customer customer = new Customer(new Name(firstname,lastname), new CustomerAdress(number,street,city,zipcode), date);
		return daoCustomer.create(customer);
	}
	
	/**
	 * 
	 * @param firstname
	 * @param lastname
	 * @param zipcode
	 * @return
	 */
	public List<Customer> findCustomerWithParameters(String firstname, String lastname, int zipcode) {

		return daoCustomer.getCustomerByParameters(firstname, lastname, zipcode);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Customer findById(int id) {
		return daoCustomer.find(id);
	}

	/**
	 * 
	 * @return
	 */
	public List<Customer> list() {
		return daoCustomer.list();
	}

	/**
	 * 
	 * @return
	 */
	public DAOCustomer getDaoCustomer() {
		return daoCustomer;
	}
	
	public boolean update(Customer customer) {
		return daoCustomer.update(customer);
	}

	/**
	 * 
	 * @param lastname
	 * @return
	 */
	public boolean RemoveCustomerWithParameters(String lastname) {
		Customer customer = new Customer(new Name(null, lastname), null, null);
		return daoCustomer.delete(customer);
	}
}