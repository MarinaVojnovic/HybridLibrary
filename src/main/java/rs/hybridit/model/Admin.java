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
		List<Authority> authorities = new ArrayList<>();
		Authority a = new Authority();
		a.setName(Role.ADMIN);
		authorities.add(a);
		this.setAuthorities(authorities);
	}

}
