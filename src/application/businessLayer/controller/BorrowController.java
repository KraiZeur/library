package application.businessLayer.controller;

import application.businessLayer.serviceLayer.BorrowService;
import application.businessLayer.utils.IntegerUtil;

/**
 * The borrow controller control the data according to the user entry and send them to the service layer
 * @author Thomas
 *
 */
public class BorrowController {

	/**
	 * The book service used by the controller
	 */
	private BorrowService borrowService = new BorrowService();
	
	/**
	 * The default constructor
	 */
	public BorrowController() {
		
	}
	
	/**
	 * Create a borrow according to the book id and the user id
	 * @param idUSer the user id
	 * @param idBook the book id
	 * @return return an informative message
	 */
	public String createBorrow(String idUSer, String idBook) {
		
		if(idUSer.equals("") || idBook.equals("")) {
			return "One ore more fields are empty";
		}
		
		if(!IntegerUtil.isInteger(idUSer) || !IntegerUtil.isInteger(idBook)) {
			return "The field's value must be numeric";
		}
		
		int idUserInt = Integer.parseInt(idUSer);
		int idBookInt = Integer.parseInt(idBook);
		
		if(borrowService.createBorrow(idUserInt, idBookInt)) {
			return "Creation succeed";
		} else {
			return "Error the book is not available or wrong ids"; 
		}
	}
	
	/**
	 * Return a book according to his id
	 * @param idBook
	 * @return return an informative message
	 */
	public String returnBook(String idBook) {
		
		if (idBook.equals("")) {
			return "One ore more fields are empty";
		}
		
		if(!IntegerUtil.isInteger(idBook)) {
			return "The field's value must be numeric";
		}

		int idBookInt = Integer.parseInt(idBook);
		
		if (!borrowService.returnBook(idBookInt)) {
			return "The id is not correct";
		}
		
		return "Return succeed";
	}
}
