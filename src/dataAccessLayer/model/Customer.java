package dataAccessLayer.model;
 
 
import java.util.Date;
import java.util.Set;
 
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
 
/**
 * 
 * @author Jeremy
 *
 */
@Entity
public class Customer {
 
        @Id    
        @GeneratedValue
        private int id;
       
        @Embedded
        private Name name;
       
        @Embedded
        private CustomerAdress adress;
       
        private Date date_registration;
       
        @OneToMany(mappedBy="customer")
    private Set<Borrowing> borrowing;
   
 
        public Customer(){
       
        }
       
        public Customer(Name name,CustomerAdress adress,Date date_registration){
                this.name=name;
                this.adress=adress;
                this.date_registration=date_registration;
        }
       
       
 
        public int getId() {
                return id;
        }
 
        public void setId(int id) {
                this.id = id;
        }
 
        public Name getName() {
                return name;
        }
 
        public void setName(Name name) {
                this.name = name;
        }
 
        public CustomerAdress getAdress() {
                return adress;
        }
 
        public void setAdress(CustomerAdress adress) {
                this.adress = adress;
        }
 
        public Date getDate_registration() {
                return date_registration;
        }
 
        public void setDate_registration(Date date_registration) {
                this.date_registration = date_registration;
        }
       
        public Set<Borrowing> getBorrowing() {
                return borrowing;
        }
 
        public void setBorrowing(Set<Borrowing> borrowing) {
                this.borrowing = borrowing;
        }
       
       
}