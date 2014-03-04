package dataAccessLayer.daoLayer;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dataAccessLayer.hibernateConfig.DBConnection;
import dataAccessLayer.model.Editor;

/**
 * 
 * @author Jeremy
 *
 */
public class DAOEditor implements Dao<Editor> {

	Session session;

	public DAOEditor() {
		this.session = DBConnection.getSession();
	}
	
	@Override
	public boolean create(Editor editor) {
		
		Transaction readTransaction = session.beginTransaction();
		session.persist(editor);
		readTransaction.commit();

		return true;
	}

	@Override
	public Editor find(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Editor> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean update(Editor object) {
		Transaction updateTransaction = session.beginTransaction();
		session.merge(object);
		updateTransaction.commit();

		return true;
	}

	@Override
	public boolean delete(Editor editor) {
		Transaction deleteTransaction = session.beginTransaction();
		session.delete(editor);
		deleteTransaction.commit();	

		return false;
	}
	
	@SuppressWarnings("unchecked")
	public List<Editor> getEditorByName(String name) {
		
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("FROM Editor WHERE name LIKE CONCAT('%', :name, '%')")
				.setString("name", name);
		List<Editor> listEditor = readQuery.list();
		readTransaction.commit();

		return listEditor;
	}

}