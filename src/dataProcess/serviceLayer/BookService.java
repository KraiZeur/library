package dataProcess.serviceLayer;

import dataProcess.daoLayer.DAOBook;

public class BookService {

	private DAOBook daoBook = new DAOBook();

	public BookService() {

	}

	public DAOBook getDaoBook() {
		return daoBook;
	}

	public void setDaoBook(DAOBook daoBook) {
		this.daoBook = daoBook;
	}
	
}
