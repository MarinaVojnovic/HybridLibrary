package rs.hybridit.model;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.hybridit.dto.BookCopyDto;

@Entity
@Table(catalog = "dbhybridlibrary", name = "book_copies")
@Getter
@Setter
@NoArgsConstructor
public class BookCopy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate rentStart;

	private LocalDate rentEnd;

	@ManyToOne
	private Book book;

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	public BookCopy(BookCopyDto bookCopyDto) {
		this.id = bookCopyDto.getId();
		this.rentStart = bookCopyDto.getRentStart();
		this.rentEnd = bookCopyDto.getRentEnd();
		this.book = bookCopyDto.getBook();
	}

}

