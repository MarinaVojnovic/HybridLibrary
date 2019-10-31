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
		setAuthority(Role.LIBRARIAN);
	}

}
