package application.businessLayer.controller;

import java.util.Date;
import java.util.List;

import application.businessLayer.serviceLayer.CustomerService;
import application.businessLayer.utils.IntegerUtil;
import dataAccessLayer.model.Customer;

/**
 * The Customer controller control the data according to the user entry and send them to the service layer
 * @author Thomas
 *
 */
public class CustomerController {

	/**
	 * The customer service used by the controller
	 */
	CustomerService customerService = new CustomerService();

	/**
	 * create a customer according to his parameters
	 * @param firstname
	 * @param lastname
	 * @param number
	 * @param street
	 * @param city
	 * @param zipcode
	 * @param date
	 * @return an informative message
	 */
	public String createCustomerWithParameters(String firstname, String lastname, String number, String street, String city, String zipcode, Date date){

		// Check the name
		if(firstname.equals("") & lastname.equals("")  ) {
			return "the customer name is empty";
		}

		if(firstname.equals("")){
			return "the customer firstname is empty";
		}

		if(lastname.equals("")){
			return "the customer lastname is empty";
		}
		// Check the adress
		if(number.equals("")) {
			return " the customer adress is empty";
		}
		
		if( !IntegerUtil.isInteger(zipcode)) {
			return "zipcode is not integer";
		}
		
		int zipInt = Integer.parseInt(zipcode);

		// Test if the creation return true
		if(customerService.createCustomerWithParameters(firstname.toLowerCase(), lastname.toLowerCase(), number, street.toLowerCase(), city.toLowerCase(), zipInt, date)) {
			return "Creation succeed";
		} else {
			return "Creation failed";
		}
	}
	
	/**
	 * find the customer according to the parameters
	 * @param firstname
	 * @param lastname
	 * @param zipCode
	 * @return the list of customers matching the parameters
	 */
	public List<Customer> findCustomerWithParameters(String firstname, String lastname, String zipCode){
		
		if( !IntegerUtil.isInteger(zipCode)) {
			return customerService.findCustomerWithParameters(firstname.toLowerCase(), lastname.toLowerCase(), 0);
		} else {
			return customerService.findCustomerWithParameters(firstname.toLowerCase(), lastname.toLowerCase(), Integer.parseInt(zipCode));
		}
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

}