package rs.hybridit.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import lombok.NoArgsConstructor;
import rs.hybridit.dto.UserDto;

@Entity
@NoArgsConstructor
public class Librarian extends User {

	public Librarian(UserDto userDto) {
		this.setId(userDto.getId());
		this.setName(userDto.getName());
		this.setLastName(userDto.getLastName());
		this.setUsername(userDto.getUsername());
		this.setEmail(userDto.getEmail());
		this.setBookCopies(userDto.getBookCopies());
		List<Authority> authorities = new ArrayList<>();
		Authority a = new Authority();
		a.setName(UserRoleName.ROLE_ADMIN);
		authorities.add(a);
		this.setAuthorities(authorities);
	}

}
