package rs.hybridit.dto;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.hybridit.model.BookCopy;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private Long id;

	@Size(min = 3, max = 15, message = "Length of name must be between 3 and 15.")
	@NotBlank(message = "Name is not allowed to be empty.")
	private String name;

	@Size(min = 3, max = 15, message = "Length of last name must be between 3 and 15.")
	@NotBlank(message = "Last name is not allowed to be empty.")
	private String lastName;

	@Email
	private String email;

	@Size(min = 3, max = 15, message = "Length of username must be between 3 and 15.")
	@NotBlank(message = "Username is not allowed to be empty.")
	private String username;

	@Size(min = 5, message = "Password must be at least 5 characters long.")
	@NotBlank(message = "Password is not allowed to be empty.")
	private String password;

	private Set<BookCopy> bookCopies = new HashSet<BookCopy>();

}
