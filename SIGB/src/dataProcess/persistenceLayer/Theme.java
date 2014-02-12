package dataProcess.persistenceLayer;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Theme {

	@Id
	private String name;
	
	@OneToMany(mappedBy="theme")
    private Set<Book> book;
	
	public Theme(){
		
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
