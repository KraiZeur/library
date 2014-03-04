package application.businessLayer.serviceLayer;

import application.businessLayer.utils.DateUtil;
import dataAccessLayer.daoLayer.DAOBook;
import dataAccessLayer.daoLayer.DAOBorrow;
import dataAccessLayer.daoLayer.DAOCustomer;
import dataAccessLayer.daoLayer.DAOFactory;
import dataAccessLayer.model.Book;
import dataAccessLayer.model.Borrowing;
import dataAccessLayer.model.Customer;

/**
 * The borrow service is a link between the borrow dao and the borrow controller
 * @author Thomas
 *
 */
public class BorrowService {

	/**
	 * 
	 */
	private DAOBorrow daoBorrow = new DAOBorrow();
	/**
	 * 
	 */
	private DAOBook daoBook = DAOFactory.getBookDAO();
	/**
	 * 
	 */
	private DAOCustomer daoCustomer = new DAOCustomer();

	public BorrowService() {

	}
	
	/**
	 * 
	 * @param idUSer
	 * @param idBook
	 * @return
	 */
	public boolean createBorrow(int idUSer, int idBook) {
		
		Book book = daoBook.find(idBook);
		Customer customer = daoCustomer.find(idUSer);
		
		if(book == null || customer == null) {
			return false;
		} 
		
		if(!book.getAvailibility()) {
			return false;
		}
		
		Borrowing borrow = new Borrowing(50.0, DateUtil.getCurrentDate(), null);
		book.setAvailibility(false);
		borrow.setBook(book);
		daoBook.update(book);
		borrow.setCustomer(customer);
		
		return daoBorrow.create(borrow);
	}
	
	/**
	 * 
	 * @param idBook
	 * @return
	 */
	public boolean returnBook(int idBook) {
		
		Borrowing borrow = daoBorrow.findByBookIdWhenNull(idBook);
		
		if(borrow == null) {
			return false;
		}
		
		borrow.setDate_delivery(DateUtil.getCurrentDate());
		Book book = daoBook.find(borrow.getBook().getId());
		book.setAvailibility(true);
		
		daoBook.update(book);
		
		daoBorrow.update(borrow);
		
		return true;
	}
}
