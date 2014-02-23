package dataAccessLayer.model;


import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Author {

	@Id
	@GeneratedValue
	private int id;
	
	@Embedded
	private Name name;
	
	private String birthdate;
	
	private String biography;
	
	@OneToMany(mappedBy="author")
    private Set<Book> book;
	
	public Author(){
		
	}

	public Author(String biography,String birthdate,Name name){
		this.biography=biography;
		this.birthdate=birthdate;
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

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public Set<Book> getBook() {
		return book;
	}

	public void setBook(Set<Book> book) {
		this.book = book;
	}
	
	
	
	
	
	
}
