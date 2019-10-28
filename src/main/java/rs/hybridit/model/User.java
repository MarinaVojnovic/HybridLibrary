package rs.hybridit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.hybridit.dto.UserDto;

@Entity
@Table(catalog = "dbhybridlibrary", name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String lastName;

	private String email;

	private String username;

	private String password;

	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Set<BookCopy> bookCopies = new HashSet<BookCopy>();

	public User(UserDto userDto) {

		this.id = userDto.getId();
		this.name = userDto.getName();
		this.lastName = userDto.getLastName();
		this.username = userDto.getUsername();
		this.password = userDto.getPassword();
		this.email = userDto.getEmail();
		this.bookCopies = userDto.getBookCopies();

	}

}
