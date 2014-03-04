package application.businessLayer.controller;

import java.util.List;

import application.businessLayer.serviceLayer.BookService;
import application.businessLayer.utils.IntegerUtil;
import dataAccessLayer.model.Book;
import dataAccessLayer.model.BookType;

/**
 * The book controller control the data according to the user entry and send them to the service layer
 * @author Thomas
 *
 */
public class BookController {

	/**
	 * The book service used in the controller
	 */
	BookService bookService = new BookService();
	
	/**
	 * The default constructor
	 */
	public BookController() {
		
	}
	
	/**
	 * Create a book with all the parameters
	 * @param name the book's name
	 * @param author the author's name
	 * @param year the book's year
	 * @param bookType the book type
	 * @param cover the image of the book
	 * @param description the book's description
	 * @return A message resulting of the data entered by the user
	 */
	public String createBookWithParameters(String name, String author, String year, BookType bookType, String cover, String description) {
		
		int yearToInt = 0;
		
		// Check the name
		if (name.equals("")) {
			return "the book name is empty";
		}
		
		if (author.equals("")) {
			return "the author name is empty";
		} 
		String str[] = author.split(",");
		
		if (str.length < 2) {
			return "the firstname and lastname must be separated by a coma";
		}
		
		
		// Check the year
		if (year.equals("") || !IntegerUtil.isInteger(year)) {
			return "the year must be valid";
		} else {
			yearToInt = Integer.parseInt(year);
		}
		
		// We test if the cover is null if -> we give the default picture
		if (cover == null) {
			cover = Book.DEFAULT_IMG_PATH;
		}
		
		// Test if the creation return true
		if (bookService.createBookWithParameters(name.toLowerCase(), str[0], str[1], yearToInt, bookType, cover, description)) {
			return "Creation succeed";
		} else {
			return "Creation failed";
		}
	}
	
	/**
	 * Find all the books according to the parameters
	 * @param name the book's name
	 * @param authorName the author's name
	 * @param year the book's year
	 * @param bookType the book type
	 * @param series the book series
	 * @param availibility the book availability
	 * @return the list of all the books that match the parameters
	 */
	public List<Book> getBooksByParameters(String name, String authorName,String year, String bookType, String series, String availibility) {
		
		boolean available = false;
		int yearInt = 0;
		
		if (!IntegerUtil.isInteger(year) || year.equals("")) {
			yearInt = 0;
		} else {
			yearInt = Integer.parseInt(year);
		}
		
		if (availibility.equals("available")) {
			available = true;
		} else if (availibility.equals("not available")) {
			available = false;
		}

		List<Book> list = bookService.getBooksByParameters(name.toLowerCase(), authorName,yearInt, bookType, series.toLowerCase(), available);
		return list;
	}
	
	/**
	 * Get the book service of this controller
	 * @return the book service
	 */
	public BookService getBookService() {
		return this.bookService;
	}
}
