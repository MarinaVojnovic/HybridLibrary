package rs.hybridit.model;

public class UserTokenState {

	private String accessToken;
	private Long expiresIn;
	private Role role;

	public UserTokenState() {
		this.accessToken = null;
		this.expiresIn = null;
		this.role = null;
	}

	public UserTokenState(String accessToken, long expiresIn, Role userType) {
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
		this.setUserRoleName(userType);
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public Role getUserRoleName() {
		return role;
	}

	public void setUserRoleName(Role role) {
		this.role = role;
	}

}

