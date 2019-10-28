package rs.hybridit.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LibraryDto {

	private Long id;
	@Min(value = 1, message = "Minimum value is 1.")
	@Max(value = 20, message = "Maximum value is 20.")
	private Integer rentPeriod;

}
