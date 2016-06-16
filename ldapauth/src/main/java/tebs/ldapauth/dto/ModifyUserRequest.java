package tebs.ldapauth.dto;

public class ModifyUserRequest {
	private TEBSModifyUserDto userDto;

	public TEBSModifyUserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(TEBSModifyUserDto userDto) {
		this.userDto = userDto;
	}
}
