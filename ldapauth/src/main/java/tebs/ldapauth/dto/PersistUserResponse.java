package tebs.ldapauth.dto;

public class PersistUserResponse {
	private TEBSUserDto userDto;
	private String message;
	public TEBSUserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(TEBSUserDto userDto) {
		this.userDto = userDto;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
