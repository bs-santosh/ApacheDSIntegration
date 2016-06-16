package tebs.ldapauth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tebs.ldapauth.constants.LDAPMessageConstants;
import tebs.ldapauth.dao.LDAPDao;
import tebs.ldapauth.dto.FindUserRequest;
import tebs.ldapauth.dto.FindUserResponse;
import tebs.ldapauth.dto.ModifyUserRequest;
import tebs.ldapauth.dto.ModifyUserResponse;
import tebs.ldapauth.dto.PersistUserRequest;
import tebs.ldapauth.dto.PersistUserResponse;
import tebs.ldapauth.dto.UserAuthenticationRequest;
import tebs.ldapauth.dto.UserAuthenticationResponse;
import tebs.ldapauth.model.TEBSUser;
import tebs.ldapauth.translator.TEBSUserTranslator;

public class AuthenticationServiceImpl implements AustenticationService{
	@Autowired
	LDAPDao ldapDao;
	@Autowired
	TEBSUserTranslator userTranslator;

	@Override
	public PersistUserResponse createTEBSUser(PersistUserRequest pesristRequest) {
		TEBSUser user = null;
		PersistUserResponse response = new PersistUserResponse();
		try{
			user = userTranslator.getTEBSUserDomainFromDto(pesristRequest.getUserDto());
			String message = ldapDao.createUser(user);
			response.setMessage(message);
			response.setUserDto(pesristRequest.getUserDto());
		}
		catch(Exception ex){
			ex.printStackTrace();
			response.setMessage(LDAPMessageConstants.USER_CREATION_FAILED);
		}
		return response;
	}

	@Override
	public ModifyUserResponse modifyTEBSUser(ModifyUserRequest modifyUserReq) {
		ModifyUserResponse response = new ModifyUserResponse();
		try{
			ldapDao.modifyUser(modifyUserReq.getUserDto());
			response.setMessage(LDAPMessageConstants.USER_MODIFIED_SUCCESSFULLY);
			response.setUserDto(modifyUserReq.getUserDto());
		}
		catch(Exception ex){
			ex.printStackTrace();
			response.setMessage(LDAPMessageConstants.USER_MODIFICATION_FAILED);
		}
		return response;
	}

	@Override
	public FindUserResponse findTEBSUsers(FindUserRequest findUserRequest) {
		List<TEBSUser> users = null;
		FindUserResponse response = new FindUserResponse();
		try{
			users = ldapDao.findUsers(findUserRequest.getFindUserDto(), findUserRequest.getSearchBy());
			if(users == null || users.size() == 0){
				response.setMessage(LDAPMessageConstants.USER_NOT_FOUND);
			}
			response.setMatchedUsers(users);
		}
		catch(Exception ex){
			ex.printStackTrace();
			response.setMessage(LDAPMessageConstants.USER_SEARCH_INTERRUPTED);
		}
		return response;
	}
	
	@Override
	public FindUserResponse findAllTEBSUsers() {
		List<TEBSUser> users = null;
		FindUserResponse response = new FindUserResponse();
		try{
			users = ldapDao.findAllUsers();
			if(users == null || users.size() == 0){
				response.setMessage(LDAPMessageConstants.USER_NOT_FOUND);
			}
			response.setMatchedUsers(users);
		}
		catch(Exception ex){
			ex.printStackTrace();
			response.setMessage(LDAPMessageConstants.USER_SEARCH_INTERRUPTED);
		}
		return response;
	}
	
	@Override
	public UserAuthenticationResponse authenticateUser(UserAuthenticationRequest authRequest){
		UserAuthenticationResponse response = new UserAuthenticationResponse();
		try{
			TEBSUser user = ldapDao.authenticateUser(authRequest.getUserDto());
			if(user == null){
				response.setMessage(LDAPMessageConstants.USER_INVALID_CREDENTIALS);
				response.setAuthenticated(false);
			}
			else{
				response.setMessage(LDAPMessageConstants.USER_AUTHENTICATED_SUCCESSFULLY);
				response.setAuthenticated(true);
				response.setUserDto(userTranslator.getTEBSUserDtoFromDomain(user));
			}
			
		}
		catch(Exception ex){
			ex.printStackTrace();
			response.setMessage(LDAPMessageConstants.USER_AUTHENTICATION_FAILED);
			response.setAuthenticated(false);
		}
		return response;
	}
}
