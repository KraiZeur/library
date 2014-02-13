package dataProcess.domainModel;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Editor {

	@Id
	private String name;
	
	@OneToMany(mappedBy="editor")
    private Set<Book> book;
	
	public Editor(){
		
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
