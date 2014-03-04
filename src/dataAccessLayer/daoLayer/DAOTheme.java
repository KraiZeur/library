package dataAccessLayer.daoLayer;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dataAccessLayer.hibernateConfig.DBConnection;
import dataAccessLayer.model.Theme;

/**
 * 
 * @author Jeremy
 *
 */
public class DAOTheme implements Dao<Theme>{

	Session session;

	public DAOTheme() {
		this.session = DBConnection.getSession();
	}
	
	@Override
	public boolean create(Theme theme) {
		Transaction readTransaction = session.beginTransaction();
		session.persist(theme);
		readTransaction.commit();

		return true;
	}

	@Override
	public Theme find(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Theme> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean update(Theme object) {
		Transaction updateTransaction = session.beginTransaction();
		session.merge(object);
		updateTransaction.commit();

		return true;
	}

	@Override
	public boolean delete(Theme theme) {
		Transaction deleteTransaction = session.beginTransaction();
		session.delete(theme);
		deleteTransaction.commit();	

		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Theme> getThemeByName(String name) {
		
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("FROM Theme WHERE name LIKE CONCAT('%', :name, '%')")
				.setString("name", name);
		List<Theme> listTheme = readQuery.list();
		readTransaction.commit();

		return listTheme;
	}
}
