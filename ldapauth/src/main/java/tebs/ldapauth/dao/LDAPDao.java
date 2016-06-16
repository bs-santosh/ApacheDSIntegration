package tebs.ldapauth.dao;

import java.util.List;

import tebs.ldapauth.dto.TEBSModifyUserDto;
import tebs.ldapauth.dto.TEBSUserDto;
import tebs.ldapauth.dto.UserAuthenticationDto;
import tebs.ldapauth.model.TEBSUser;

public interface LDAPDao {
	public String createUser(TEBSUser user) throws Exception;
	public void modifyUser(TEBSModifyUserDto user) throws Exception;
	public List<TEBSUser> findUsers(TEBSUserDto findUserDto, String[] searchBy) throws Exception;
	public TEBSUser authenticateUser(UserAuthenticationDto userDto) throws Exception;
	public List<TEBSUser> findAllUsers() throws Exception ;
}
