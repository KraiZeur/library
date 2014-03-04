package dataAccessLayer.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Jeremy
 *
 */
@Entity
public class Borrowing {
	
	@Id
	@GeneratedValue
	private int id;
	
	private double price;
	
	@Temporal(TemporalType.DATE)
	private Date date_borrowing;
	
	@Temporal(TemporalType.DATE)
	private Date date_delivery;
	
	@ManyToOne
    @JoinColumn(name="Id_Book")
    private Book book;
	
	@ManyToOne
    @JoinColumn(name="Id_Customer")
    private Customer customer;
	
	public Borrowing (){
		
	}
	
	public Borrowing(double price, Date date_borrowing,Date date_delivery){
		this.price=price;
		this.date_borrowing=date_borrowing;
		this.date_delivery=date_delivery;
		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getDate_borrowing() {
		return date_borrowing;
	}

	public void setDate_borrowing(Date date_borrowing) {
		this.date_borrowing = date_borrowing;
	}

	public Date getDate_delivery() {
		return date_delivery;
	}

	public void setDate_delivery(Date date_delivery) {
		this.date_delivery = date_delivery;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	

	

}
