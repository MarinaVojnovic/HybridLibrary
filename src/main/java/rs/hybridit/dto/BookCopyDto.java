package rs.hybridit.dto;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import rs.hybridit.model.Book;

public class BookCopyDto {

	private Long id;
	private LocalDate rentStart;
	private LocalDate rentEnd;
	private Book book;

	public BookCopyDto() {

	}

	public BookCopyDto(Long id, LocalDate rentStart, LocalDate rentEnd, Book book) {
		this.id = id;
		this.rentStart = rentStart;
		this.rentEnd = rentEnd;
		this.book = book;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getRentStart() {
		return rentStart;
	}

	public void setRentStart(LocalDate rentStart) {
		this.rentStart = rentStart;
	}

	public LocalDate getRentEnd() {
		return rentEnd;
	}

	public void setRentEnd(LocalDate rentEnd) {
		this.rentEnd = rentEnd;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
