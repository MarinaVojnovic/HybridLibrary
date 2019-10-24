package rs.hybridit.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import rs.hybridit.dto.BookCopyDto;

@Entity
@Table(catalog = "dbhybridlibrary", name = "book_copies")
public class BookCopy {

	@Id
	@GeneratedValue
	private Long id;

	private Date rentStart;

	private Date rentEnd;

	@ManyToOne(fetch = FetchType.EAGER)
	private Book book;

	public BookCopy() {
	}

	public BookCopy(Long id, Date rentStart, Date rent_end, Book book) {
		this.id = id;
		this.rentStart = rentStart;
		this.rentEnd = rentEnd;
		this.book = book;
	}

	public BookCopy(BookCopyDto bookCopyDto) {
		this.id = bookCopyDto.getId();
		this.rentStart = bookCopyDto.getRentStart();
		this.rentEnd = bookCopyDto.getRentEnd();
		this.book = bookCopyDto.getBook();
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

	public void setRentEnd(Date rent_end) {
		this.rentEnd = rentEnd;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}


}

