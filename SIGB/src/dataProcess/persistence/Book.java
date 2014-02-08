package dataProcess.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {

	@Id
	@GeneratedValue
	private int id;
	
	private String description;
	
	public Book() {
		
	}
	
	public Book(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString(){
		return "id : " +id +", description : " +description; 
	}
	
	
	
}
