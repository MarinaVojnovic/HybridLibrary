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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.hybridit.dto.BookDto;

import org.hibernate.validator.constraints.ISBN;

@Entity
@Table(catalog = "dbhybridlibrary", name = "books")
@Getter
@Setter
@NoArgsConstructor
public class Book {

	@Id
	@GeneratedValue
	private Long id;

	private String isbn;

	private String name;

	private String author;

	private String image;

	private Integer rentingCounter;

	@JsonIgnore
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<BookCopy> bookCopies = new HashSet<BookCopy>();

	public Book(BookDto bookDto) {
		this.isbn = bookDto.getIsbn();
		this.name = bookDto.getName();
		this.image = bookDto.getImage();
		this.author = bookDto.getAuthor();
		this.rentingCounter = bookDto.getRentingCounter();
	}

	public void increaseCounter(){
		this.rentingCounter++;
	}

}

