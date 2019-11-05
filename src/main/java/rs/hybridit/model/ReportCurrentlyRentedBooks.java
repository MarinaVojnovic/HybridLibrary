package rs.hybridit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportCurrentlyRentedBooks {

	private String bookName;
	private Integer rentedCopies;
	private Integer availableCopies;

}

