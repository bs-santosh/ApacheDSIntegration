package tebs.ldapauth.dto;

public class FindUserRequest {
	
	String[] searchBy;
	TEBSUserDto findUserDto;
	public String[] getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String[] searchBy) {
		this.searchBy = searchBy;
	}

	public TEBSUserDto getFindUserDto() {
		return findUserDto;
	}

	public void setFindUserDto(TEBSUserDto findUserDto) {
		this.findUserDto = findUserDto;
	}
}
