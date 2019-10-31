package rs.hybridit.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import lombok.NoArgsConstructor;
import rs.hybridit.dto.UserDto;

@Entity
@NoArgsConstructor
public class Admin extends User {

	public Admin(UserDto userDto) {
		super(userDto);
		setAuthority(Role.ADMIN);
	}

}
