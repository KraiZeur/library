package dataAccessLayer.daoLayer;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dataAccessLayer.hibernateConfig.DBConnection;
import dataAccessLayer.model.Administrator;

/**
 * 
 * @author Jeremy
 *
 */
public class DAOAdministrator implements Dao<Administrator>{

	Session session;

	public DAOAdministrator() {
		this.session = DBConnection.getSession();
	}
	
	@Override
	public boolean create(Administrator object) {
		return false;
	}

	@Override
	public Administrator find(int index) {
		return null;
	}

	@Override
	public List<Administrator> list() {
		return null;
	}

	@Override
	public int count() {
		return 0;
	}

	@Override
	public boolean update(Administrator object) {
		return false;
	}
	
	public Administrator findByLogin(String username, String password) {
		try {
			Transaction readTransaction = session.beginTransaction();

			Query readQuery = session.createQuery("FROM Administrator WHERE username = :username AND password = :password")
					.setString("username", username).setString("password", password);
			Administrator result = (Administrator)readQuery.list().get(0);
			readTransaction.commit();	

			return result;
			
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean delete(Administrator object) {
		return false;
	}

}
