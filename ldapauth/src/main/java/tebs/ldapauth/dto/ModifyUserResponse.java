package tebs.ldapauth.dto;

public class ModifyUserResponse {
	String message;
	TEBSModifyUserDto userDto;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public TEBSModifyUserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(TEBSModifyUserDto userDto) {
		this.userDto = userDto;
	}
}
