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
		super(userDto);
		List<Authority> authorities = new ArrayList<>();
		Authority a = new Authority();
		a.setName(Role.LIBRARIAN);
		authorities.add(a);
		this.setAuthorities(authorities);
	}

}
