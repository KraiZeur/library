package dataProcess.dataAccess;

public interface Dao<T> {
	boolean create(T object);
	boolean read(int index);
	boolean update(T object);
	boolean delete(T object);
}
