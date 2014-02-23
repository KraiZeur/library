package dataAccessLayer.daoLayer;

public class DAOFactory {

	public static DAOBook getUserDAO() {
		return new DAOBook();
	}

}
