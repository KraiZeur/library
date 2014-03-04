package application.businessLayer.serviceLayer;

import java.util.List;

import dataAccessLayer.daoLayer.DAOAuthor;
import dataAccessLayer.daoLayer.DAOBook;
import dataAccessLayer.daoLayer.DAOFactory;
import dataAccessLayer.model.Author;
import dataAccessLayer.model.Book;
import dataAccessLayer.model.BookType;
import dataAccessLayer.model.Name;

/**
 * The book service is a link between the book dao and the book controller
 * @author Thomas
 *
 */
public class BookService {

	/**
	 * The book dao of this controller
	 */
	private DAOBook daoBook = DAOFactory.getBookDAO();
	/**
	 * The author dao of this controller
	 */
	private DAOAuthor daoAuthor = new DAOAuthor();

	/**
	 * The default constructor
	 */
	public BookService() {

	}
	
	/**
	 * Method to create a book according to parameters
	 * @param name the book's name
	 * @param authorFirst the author's firstname
	 * @param authorLast the author's lastname
	 * @param year the book's year
	 * @param bookType the book's type
	 * @param cover the book's cover
	 * @param description the book's description
	 * @return a boolean if the book is saved or not
	 */
	public boolean createBookWithParameters(String name, String authorFirst, String authorLast, int year, BookType bookType, String cover, String description) {
		
		Author authorVar = new Author(new Name(authorFirst, authorLast));
		daoAuthor.create(authorVar);
		Book book = new Book(bookType, authorVar, cover, name, description, year, true);

		return daoBook.create(book);
	}
	
	/**
	 * Find a book by id
	 * @param id the book's id
	 * @return the book found
	 */
	public Book findById(int id) {
		return daoBook.find(id);
	}
	
	/**
	 * List all the book
	 * @return the list of book
	 */
	public List<Book> list() {
		return daoBook.list();
	}
	
	public boolean update(Book book) {
		return daoBook.update(book);
	}
	
	/**
	 * Find books according to the parameters
	 * @param name the book's name
	 * @param authorName the author's name of the book
	 * @param year the book's year
	 * @param bookType the book type
	 * @param series the book's series
	 * @param availibility the book's availability
	 * @return the book list
	 */
	public List<Book> getBooksByParameters(String name, String authorName, int year, String bookType, String series, Boolean availibility) {
		return daoBook.getBooksByParameters(name, authorName, year, bookType, series, availibility);
	}
	
	/**
	 * 
	 * @return the dao used by the service
	 */
	public DAOBook getDaoBook() {
		return daoBook;
	}
}
