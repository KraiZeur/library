package dataProcess.daoLayer;

import java.util.List;


public interface Dao<T> {
	boolean create(T object);
	T find(int index);
	List<T> list();
	int count();
	boolean update(T object);
	boolean delete(T object);
}
