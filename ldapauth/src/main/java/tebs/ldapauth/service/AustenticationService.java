package tebs.ldapauth.service;

import tebs.ldapauth.dto.FindUserRequest;
import tebs.ldapauth.dto.FindUserResponse;
import tebs.ldapauth.dto.ModifyUserRequest;
import tebs.ldapauth.dto.ModifyUserResponse;
import tebs.ldapauth.dto.PersistUserRequest;
import tebs.ldapauth.dto.PersistUserResponse;
import tebs.ldapauth.dto.UserAuthenticationRequest;
import tebs.ldapauth.dto.UserAuthenticationResponse;

public interface AustenticationService {
	public PersistUserResponse createTEBSUser(PersistUserRequest pesristRequest);
	public ModifyUserResponse modifyTEBSUser(ModifyUserRequest modifyUserReq);
	public FindUserResponse findTEBSUsers(FindUserRequest findUserRequest);
	public UserAuthenticationResponse authenticateUser(UserAuthenticationRequest authRequest);
	public FindUserResponse findAllTEBSUsers();
}
