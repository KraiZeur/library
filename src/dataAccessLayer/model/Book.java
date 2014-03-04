package dataAccessLayer.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 * 
 * @author Jeremy
 *
 */
@Entity
public class Book {

	@Id
	@GeneratedValue 
	private int id;
	
	private String name;
	
	private int year;
	
	@Enumerated(EnumType.STRING)
	private BookType bookType;

	private String cover;
	
	private String description;
	
	private Boolean availibility;
	
	@ManyToOne
    @JoinColumn(name="Id_Author")
    private Author author;
	
	@ManyToOne
    @JoinColumn(name="Name_Collection")
	private BookSeries bookSeries;
	
	@ManyToOne
    @JoinColumn(name="Name_Editor")
	private Editor editor;
	
	@ManyToOne
    @JoinColumn(name="Name_Theme")
	private Theme theme;
	
	@OneToMany(mappedBy="book")
    private Set<Borrowing> borrowing;
	
	@Transient
	public static final String  DEFAULT_IMG_PATH = "img/books/default_book.png";
	
	public Book(){
		
	}
	
	public Book(BookType bookType, Author author, String cover, String name, String description,int year,Boolean availibility) {
		this.bookType = bookType;
		this.author = author;
		this.cover = cover;
        this.name = name;
        this.description = description;
        this.year = year;
        this.availibility = availibility;
	}


	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BookType getBookType() {
		return bookType;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getAvailibility() {
		return availibility;
	}

	public void setAvailibility(Boolean availibility) {
		this.availibility = availibility;
	}
	

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public BookSeries getBookSeries() {
		return bookSeries;
	}

	public void setBookSeries(BookSeries bookSeries) {
		this.bookSeries = bookSeries;
	}

	public Editor getEditor() {
		return editor;
	}

	public void setEditor(Editor editor) {
		this.editor = editor;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

//	public Set<Borrowing> getBorrowing() {
//		return borrowing;
//	}
//
//	public void setBorrowing(Set<Borrowing> borrowing) {
//		this.borrowing = borrowing;
//	}
	
	public String toString() {
		return "name : " +name +" year : " +year;
	}

}
