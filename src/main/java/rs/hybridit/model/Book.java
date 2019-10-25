package rs.hybridit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import rs.hybridit.dto.BookDto;

import org.hibernate.validator.constraints.ISBN;

@Entity
@Table(catalog = "dbhybridlibrary", name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ISBN
	private String isbn;

	@Size(min = 3, max = 15, message = "Length of name must be between 3 and 15.")
	@NotBlank(message = "Name is not allowed to be empty.")
	private String name;

	@Size(min = 3, max = 25, message = "Length of author's name must be between 3 and 15.")
	@NotBlank(message = "Author is not allowed to be empty.")
	private String author;

	private String image;

	@Min(value = 0, message = "Value of renting counter must be greater or equal 0.")
	private Integer rentingCounter;

	@JsonIgnore
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<BookCopy> bookCopies = new HashSet<BookCopy>();

	public Book() {
	}

	public Book(BookDto bookDto) {
		this.id = bookDto.getId();
		this.isbn = bookDto.getIsbn();
		this.name = bookDto.getName();
		this.image = bookDto.getImage();
		this.author = bookDto.getAuthor();
		this.rentingCounter = bookDto.getRentingCounter();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getRentingCounter() {
		return rentingCounter;
	}

	public void setRentingCounter(Integer rentingCounter) {
		this.rentingCounter = rentingCounter;
	}

	public Set<BookCopy> getBookCopies() {
		return bookCopies;
	}

	public void setBookCopies(Set<BookCopy> bookCopies) {
		this.bookCopies = bookCopies;
	}

}
