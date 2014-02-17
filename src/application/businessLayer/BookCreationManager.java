package application.businessLayer;

import dataAccessLayer.daoLayer.DAOBook;
import dataAccessLayer.domainObjects.Book;

public class BookCreationManager {

	public static void createBook(String name) {
		
		DAOBook daoBook = new DAOBook();
		Book book = new Book();
		daoBook.create(book);
	}
	
}
