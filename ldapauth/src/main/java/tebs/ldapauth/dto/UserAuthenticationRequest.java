package tebs.ldapauth.dto;

public class UserAuthenticationRequest {
	private UserAuthenticationDto userDto;

	public UserAuthenticationDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserAuthenticationDto userDto) {
		this.userDto = userDto;
	}
}
