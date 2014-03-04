package dataAccessLayer.daoLayer;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dataAccessLayer.hibernateConfig.DBConnection;
import dataAccessLayer.model.Author;

/**
 * 
 * @author Jeremy
 *
 */
public class DAOAuthor implements Dao<Author> {

	Session session;
	
	public DAOAuthor() {
		this.session = DBConnection.getSession();
	}

	@Override
	public boolean create(Author author) {
		
		
		Transaction readTransaction = session.beginTransaction();
		session.persist(author);
		readTransaction.commit();

		return true;
	}

	@Override
	public Author find(int index) {
		
       Transaction readTransaction = session.beginTransaction();
		
		Query readQuery = session.createQuery("FROM Author WHERE id = :id")
				.setInteger("id", index);
		Author result = (Author)readQuery.list().get(0);
		readTransaction.commit();

		return result;
	}

	@Override
	public List<Author> list() {
		
		return null;
	}

	@Override
	public int count() {

		return 0;
	}

	@Override
	public boolean update(Author object) {

		Transaction updateTransaction = session.beginTransaction();
		session.merge(object);
		updateTransaction.commit();

		return true;
	}

	@Override
	public boolean delete(Author author) {

		Transaction deleteTransaction = session.beginTransaction();
		session.delete(author);
		deleteTransaction.commit();	

		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Author> getAuthorByName(String firstname,String lastname) {
		
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("FROM Author WHERE firstname LIKE CONCAT('%', :firstname, '%') and lastname LIKE CONCAT('%', :lastname, '%')")
				.setString("firstname",firstname).setString("lastname",lastname);
		List<Author> listAuthors = readQuery.list();
		readTransaction.commit();

		return listAuthors;
	}
	
	@SuppressWarnings("unchecked")
	public List<Author> getAuthorByParameters(String firstname, String lastname,  String birthdate, String biography){
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("FROM Author where firstname LIKE CONCAT('%', :firstname, '%') and lastname LIKE CONCAT('%', :lastname, '%') and birthdate LIKE CONCAT ('%', :birthdate, '%') and biography LIKE CONCAT ('%', :biography, '%') ")
				.setString("firstname",firstname).setString("lastname",lastname).setString("birthdate",birthdate).setString("biography",biography);
		List<Author> listAuthorsByParameters = readQuery.list();
		readTransaction.commit();
		return listAuthorsByParameters;
	}
}
