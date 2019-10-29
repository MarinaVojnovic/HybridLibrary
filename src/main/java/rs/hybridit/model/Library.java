package rs.hybridit.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.hybridit.dto.LibraryDto;

@Entity
@Table(catalog = "dbhybridlibrary", name = "library")
@Getter
@Setter
@NoArgsConstructor
public class Library {

	@Id
	@GeneratedValue
	private Long id;

	private Integer rentPeriod;

	public Library(LibraryDto libraryDto) {
		this.id = libraryDto.getId();
		this.rentPeriod = libraryDto.getRentPeriod();
	}

}

