package rs.hybridit.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import rs.hybridit.dto.LibraryDto;

@Entity
@Table(catalog = "dbhybridlibrary", name = "library")
public class Library {

	@Id
	@GeneratedValue
	private Long id;

	@Min(value = 1, message = "Minimum value is 1.")
	@Max(value = 20, message = "Maximum value is 20.")
	private Integer rentPeriod;

	public Library() {
	}

	public Library(LibraryDto libraryDto) {
		this.id = libraryDto.getId();
		this.rentPeriod = libraryDto.getRentPeriod();
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

