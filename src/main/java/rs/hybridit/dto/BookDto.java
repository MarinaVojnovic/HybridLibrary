package rs.hybridit.dto;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.ISBN;
import rs.hybridit.model.BookCopy;

public class BookDto {

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

	private Set<BookCopy> bookCopies = new HashSet<BookCopy>();

	public BookDto() {
	}

	public BookDto(Long id, String isbn, String name, String author, String image, Integer rentingCounter,
		Set<BookCopy> bookCopies) {
		this.id = id;
		this.isbn = isbn;
		this.name = name;
		this.author = author;
		this.image = image;
		this.rentingCounter = rentingCounter;
		this.bookCopies = bookCopies;
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
