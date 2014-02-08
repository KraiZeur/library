package dataProcess.dataAccess;

import java.util.List;

public interface Dao<T> {
	boolean create(T object);
	T find(int index);
	List<T> findAll();
	List<T> findLimited(int first, int offset);
	int numberOfElement();
	boolean update(T object);
	boolean delete(T object);
}
