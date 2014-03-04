package dataAccessLayer.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * 
 * @author Jeremy
 *
 */
@Entity
public class Editor {

	@Id
	private String name;
	
	@OneToMany(mappedBy="editor")
    private Set<Book> book;
	
	public Editor(){
		
	}
	
	public Editor(String name){
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Book> getBook() {
		return book;
	}

	public void setBook(Set<Book> book) {
		this.book = book;
	}
	
	
	
}
