package rs.hybridit.dto;

import java.util.Date;
import rs.hybridit.model.Book;

public class BookCopyDto {

	private Long id;
	private Date rentStart;
	private Date rentEnd;
	private Book book;

	public BookCopyDto(){

	}

	public BookCopyDto(Long id, Date rentStart, Date rentEnd, Book book) {
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

	public Date getRentStart() {
		return rentStart;
	}

	public void setRentStart(Date rentStart) {
		this.rentStart = rentStart;
	}

	public Date getRentEnd() {
		return rentEnd;
	}

	public void setRentEnd(Date rentEnd) {
		this.rentEnd = rentEnd;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
