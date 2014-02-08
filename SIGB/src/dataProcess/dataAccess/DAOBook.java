package dataProcess.dataAccess;

import dataProcess.persistence.Book;

public class DAOBook implements Dao<Book>{

	@Override
	public boolean create(Book object) {
		return false;
	}

	@Override
	public boolean read(int index) {
		return false;
	}

	@Override
	public boolean update(Book object) {
		return false;
	}

	@Override
	public boolean delete(Book object) {
		return false;
	}

}
