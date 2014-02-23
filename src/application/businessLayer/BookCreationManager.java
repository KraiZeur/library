package application.businessLayer;

import dataAccessLayer.daoLayer.DAOBook;
import dataAccessLayer.model.Book;
import dataAccessLayer.model.BookType;

public class BookCreationManager {

	public static int createBook(String name, BookType bookType, String cover, String description, String year) {
		
		DAOBook daoBook = new DAOBook();
		int yearInt;
		try {
			yearInt = Integer.parseInt(year);
		} catch(Exception e) {
			return 2;
		}
		
		Book book = new Book(bookType, cover, name, description, yearInt, true);
		daoBook.create(book);
		
		return 1;
	}
	
}
