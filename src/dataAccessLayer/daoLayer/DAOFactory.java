package dataAccessLayer.daoLayer;
 
/**
 * 
 * @author Jeremy
 *
 */
public class DAOFactory {
 
        private static DAOBook daoBook = new DAOBook();
       
        public static DAOBook getBookDAO() {
                return daoBook;
        }
 
        private static DAOCustomer daoCustomer = new  DAOCustomer();
       
        public static DAOCustomer getCustomerDAO(){
                return daoCustomer;
        }
}