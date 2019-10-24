package rs.hybridit.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class LibraryDto {

	private Long id;

	@Min(value = 1, message = "Minimum value is 1.")
	@Max(value = 20, message = "Maximum value is 20.")
	private Integer rentPeriod;

	public LibraryDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRentPeriod() {
		return rentPeriod;
	}

	public void setRentPeriod(Integer rentPeriod) {
		this.rentPeriod = rentPeriod;
	}

}
