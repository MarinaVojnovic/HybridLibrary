package rs.hybridit.dto;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.hybridit.model.Book;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookCopyDto {

	private Long id;
	private LocalDate rentStart;
	private LocalDate rentEnd;
	private Book book;

}
