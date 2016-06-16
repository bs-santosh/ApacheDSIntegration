package tebs.ldapauth.dto;

import java.util.List;

import tebs.ldapauth.model.TEBSUser;

public class FindUserResponse {
	private List<TEBSUser> matchedUsers;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<TEBSUser> getMatchedUsers() {
		return matchedUsers;
	}

	public void setMatchedUsers(List<TEBSUser> matchedUsers) {
		this.matchedUsers = matchedUsers;
	}
}
