package rs.hybridit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import rs.hybridit.dto.UserDto;

import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(catalog = "dbhybridlibrary", name = "users")
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User implements UserDetails {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String lastName;

	private String email;

	private String username;

	private String password;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Set<BookCopy> bookCopies = new HashSet<BookCopy>();

	@ManyToOne(fetch = FetchType.LAZY)
	private Authority authority;

	public User(UserDto userDto) {

		this.id = userDto.getId();
		this.name = userDto.getName();
		this.lastName = userDto.getLastName();
		this.username = userDto.getUsername();
		this.password = userDto.getPassword();
		this.email = userDto.getEmail();
		this.bookCopies = userDto.getBookCopies();

	}

	public Authority getAuhtority() {
		return this.authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

}
