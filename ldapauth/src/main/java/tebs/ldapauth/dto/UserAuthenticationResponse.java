package tebs.ldapauth.dto;

public class UserAuthenticationResponse {
	private TEBSUserDto userDto;
	private boolean isAuthenticated;
	private String message;
	public TEBSUserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(TEBSUserDto userDto) {
		this.userDto = userDto;
	}
	public boolean isAuthenticated() {
		return isAuthenticated;
	}
	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
