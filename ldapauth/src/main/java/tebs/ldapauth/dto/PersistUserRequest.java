package tebs.ldapauth.dto;

public class PersistUserRequest {
	TEBSUserDto userDto;

	public TEBSUserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(TEBSUserDto userDto) {
		this.userDto = userDto;
	}
}
