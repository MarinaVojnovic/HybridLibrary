package rs.hybridit.dto;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.ISBN;
import rs.hybridit.model.BookCopy;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}
