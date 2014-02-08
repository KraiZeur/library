package application;

import java.util.List;

import dataProcess.dataAccess.DAOBook;
import dataProcess.persistence.Book;

public class TestEngine {

	public static void main(String[] args) {
//		DataInit.createTables();
//		Book book = new Book("ceci est un text");
//		Book book2 = new Book("ceci est un text");
//
		DAOBook daoBook = new DAOBook();
//
//		daoBook.create(book);
//		daoBook.create(book2);
//		daoBook.create(new Book("ok"));
//		daoBook.create(new Book("ok2"));
//		daoBook.create(new Book("ok3"));
//
//		book.setDescription("lolilo");
//		
//		daoBook.update(book);

		List<Book> list = daoBook.findLimited(0,10);

		if(list != null) {
			for (Book bookTmp : list) {
				System.out.println(bookTmp);
			}
		} else {
			System.out.println("No result found");
		}
		
		list = daoBook.findLimited(0,2);

		System.out.println("");
		
		if(list != null) {
			for (Book bookTmp : list) {
				System.out.println(bookTmp);
			}
		} else {
			System.out.println("No result found");
		}
		
		System.out.println(daoBook.find(1));
		
	}
	
}