package dataAccessLayer.model;


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
public class Author {

	@Id
	@GeneratedValue
	private int id;
	
	@Embedded
	private Name name;
	
	@OneToMany(mappedBy="author")
    private Set<Book> book;
	
	public Author() {
		
	}

	public Author(Name name) {
		this.name = name;
	}

	
	public Author(String biography, String birthdate, Name name) {
		this.name=name;
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

	public Set<Book> getBook() {
		return book;
	}

	public void setBook(Set<Book> book) {
		this.book = book;
	}

}
