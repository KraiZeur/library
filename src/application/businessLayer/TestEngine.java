package application.businessLayer;

import java.util.List;

import org.hibernate.Session;

import dataAccessLayer.hibernateConfig.DBConnection;
import dataAccessLayer.hibernateConfig.DataInit;
import dataAccessLayer.model.Administrator;
import dataAccessLayer.model.Author;
import dataAccessLayer.model.Book;
import dataAccessLayer.model.BookSeries;
import dataAccessLayer.model.BookType;
import dataAccessLayer.model.Borrowing;
import dataAccessLayer.model.Customer;
import dataAccessLayer.model.CustomerAdress;
import dataAccessLayer.model.Editor;
import dataAccessLayer.model.Login;
import dataAccessLayer.model.Name;
import dataAccessLayer.model.Theme;
import dataAccessLayer.serviceLayer.BookService;

public class TestEngine {

		public static void main(String[] args) {
			//DataInit.createTables();
			Session session = DBConnection.getSession();
			session.beginTransaction();
			
			//create author
			Author author1 = new Author("biography","12-12-1992",new Name("Agatha","Christie"));
			session.save(author1);
			Author author2 = new Author("biography","25-11-1984",new Name("Emile","Zola"));
			session.save(author2);
			
			//create editor
			Editor editor1=new Editor();
			editor1.setName("1er editeur");
			session.save(editor1);
			
			//create collection
			BookSeries bookSeries1 =new BookSeries();
			bookSeries1.setName("1ère collection");
			session.save(bookSeries1);
			
			//create theme
			Theme theme1=new Theme();
			theme1.setName("Dessins animés");
			session.save(theme1);
			
			//create book
			Book book1= new Book (BookType.comics,"cover1","Roi lion","simba et pumba",1987,true);
			Book book2= new Book (BookType.novel,"cover2","Code Lyoko","on ira ,on saura, sauver notre existence",1992,false);
			Book book3= new Book (BookType.comics,"cover3","Batman","Batman_description",1974,true);
			Book book4= new Book (BookType.comics,"cover4","Pokemon","PIKACHUUU",1992,false);
			session.save(book1);
			session.save(book2);
			session.save(book3);
			session.save(book4);
			
			//create customer
			Customer customer1 = new Customer(new Name("Jeremy","Carayon"), new CustomerAdress("33","rue Lebon","Sartrouville",78500),"10-02-2014");
			session.save(customer1);
			
			//create Borrowing
			Borrowing borrowing1=new Borrowing(9.99,"10-02-2014","11-02-2014");
			session.save(borrowing1);
			
			//create Administrator
			Administrator administrator1 = new Administrator(new Login("Prénom_Nom","password"));
			session.save(administrator1);
			
			
			//operations allow to link table "Book" and others table
			book1.setAuthor(author1);
			book1.setEditor(editor1);
			book1.setBookSeries(bookSeries1);
			book1.setTheme(theme1);
			book2.setAuthor(author1);
			book2.setTheme(theme1);
			book3.setEditor(editor1);
			book3.setAuthor(author2);
			book4.setAuthor(author2);
			book4.setBookSeries(bookSeries1);
			book4.setTheme(theme1);
			
			//operations allow to link table "Borrowing" with others
			borrowing1.setBook(book1);
			borrowing1.setCustomer(customer1);
			
			session.getTransaction().commit();
			
			BookService bs = new BookService();
			//System.out.println("nombre de livre : " +bs.getDaoBook().count());
			
			// TEST 2
			
			List<Book> liste = bs.getDaoBook().getBooksByYear(1987);
			
			for(Book bk : liste) {
				System.out.println(bk.getName());
			}
			
			liste = bs.getDaoBook().getBooksByName("o");
			
			for(Book bk : liste) {
				System.out.println(bk.getName());
			}
			
			session.close();
		}
	
}