package tebs.ldapauth.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.shared.ldap.model.cursor.SearchCursor;
import org.apache.directory.shared.ldap.model.entry.DefaultEntry;
import org.apache.directory.shared.ldap.model.entry.DefaultModification;
import org.apache.directory.shared.ldap.model.entry.Entry;
import org.apache.directory.shared.ldap.model.entry.Modification;
import org.apache.directory.shared.ldap.model.entry.ModificationOperation;
import org.apache.directory.shared.ldap.model.message.AddRequest;
import org.apache.directory.shared.ldap.model.message.AddRequestImpl;
import org.apache.directory.shared.ldap.model.message.AddResponse;
import org.apache.directory.shared.ldap.model.message.ResultCodeEnum;
import org.apache.directory.shared.ldap.model.message.SearchRequest;
import org.apache.directory.shared.ldap.model.message.SearchRequestImpl;
import org.apache.directory.shared.ldap.model.message.SearchScope;
import org.apache.directory.shared.ldap.model.name.Dn;
import org.springframework.beans.factory.annotation.Autowired;

import tebs.ldapauth.connection.ConnectionManager;
import tebs.ldapauth.constants.LDAPMessageConstants;
import tebs.ldapauth.constants.SearchBy;
import tebs.ldapauth.dto.TEBSModifyUserDto;
import tebs.ldapauth.dto.TEBSUserDto;
import tebs.ldapauth.dto.UserAuthenticationDto;
import tebs.ldapauth.model.TEBSUser;
import tebs.ldapauth.translator.TEBSUserTranslator;
import tebs.ldapauth.util.PasswordUtil;

public class LDAPDaoImpl implements LDAPDao {

	@Autowired
	ConnectionManager ldapConnectionManager;
	@Autowired
	TEBSUserTranslator userTranslator;
	
	private final String LDAP_DN_STRING = "uid=admin,ou=system";
	private final String LDAP_CREDENTIAL = "secret";
	private final String BASE_DN = "ou=users,o=tebs";
	
	@Override
	public String createUser(TEBSUser user) throws Exception {
		String message = "";
		LdapConnection con = ldapConnectionManager.getLDAPConnection();
		con.bind(LDAP_DN_STRING, LDAP_CREDENTIAL);
		Entry de = new DefaultEntry("uid=" + user.getUserId() + ",ou=users,o=tebs", "objectclass: inetOrgPerson",
				"objectclass: organizationalPerson", "objectclass: person", "objectclass: top", "objectclass: TEBSUser", 
				"cn", user.getName(),
				"sn", user.getlName(),
				"uid", user.getUserId(),
				"mobile", user.getMobile(),
				"email", user.getEmail(),
				"userPassword", user.getPassword(),
				"employeeNumber", user.getEmployeeNo(),
				"department", user.getDepartment(),
				"employeeType", user.getEmployeeType(),
				"designation", user.getDesignation(),
				"firstName", user.getfName(),
				"lastName", user.getlName(),
				"middleName", user.getmName(),
				"gender", user.getGender(),
				"personalEmail", user.getPersonalEmail());
		
		AddRequest addReq = new AddRequestImpl();
		addReq.setEntry(de);

		AddResponse response = con.add(addReq);
		con.unBind();
		con.close();
		if (response.getLdapResult().getResultCode().equals(ResultCodeEnum.SUCCESS))
			message = LDAPMessageConstants.USER_CREATED_SUCCESSFULLY;
		else if (response.getLdapResult().getResultCode().equals(ResultCodeEnum.ENTRY_ALREADY_EXISTS))
			message = LDAPMessageConstants.USER_ALREADY_EXISTS;

		return message;
	}

	@Override
	public void modifyUser(TEBSModifyUserDto user) throws Exception{
		LdapConnection con = ldapConnectionManager.getLDAPConnection();
		con.bind(LDAP_DN_STRING, LDAP_CREDENTIAL);
		Modification changePassword = null;
		Modification changeMobile = null;
		if(user.getNewPassword() != null || "".equals(user.getNewPassword())){
			changePassword = new DefaultModification( ModificationOperation.REPLACE_ATTRIBUTE, "userPassword", user.getNewPassword());
			con.modify("uid=" + user.getUserId() + ",ou=users,o=tebs", changePassword);
		}
		if(user.getNewMobile() != null || "".equals(user.getNewMobile())){
			changeMobile = new DefaultModification( ModificationOperation.REPLACE_ATTRIBUTE, "mobile", user.getNewMobile());
			con.modify("uid=" + user.getUserId() + ",ou=users,o=tebs", changeMobile);
		}
		con.unBind();
		con.close();
	}

	@Override
	public List<TEBSUser> findUsers(TEBSUserDto findUserDto, String[] searchFilters) throws Exception {
		List<TEBSUser> usersList = new ArrayList<TEBSUser>();
		String filter = "(&(objectClass=person)";
		
		for(String searchBy:searchFilters){
			if(SearchBy.USERID.toString().equals(searchBy.toUpperCase())){
				filter = filter + "(&(uid=" + findUserDto.getUserId() + "))";
			}
			if(SearchBy.EMAIL.toString().equals(searchBy.toUpperCase())){
				filter = filter + "(&(email=" + findUserDto.getEmail() + "))";
			}
			if(SearchBy.MOBILE.toString().equals(searchBy.toUpperCase())){
				filter = filter + "(&(mobile=" + findUserDto.getMobile() + "))";
			}
			if(SearchBy.NAME.toString().equals(searchBy.toUpperCase())){
				filter = filter + "(&(cn=" + findUserDto.getName() + "))";
			}
			if(SearchBy.DEPARTMENT.toString().equals(searchBy.toUpperCase())){
				filter = filter + "(&(department=" + findUserDto.getName() + "))";
			}
			if(SearchBy.EMPLOYEENUMBER.toString().equals(searchBy.toUpperCase())){
				filter = filter + "(&(employeenumber=" + findUserDto.getName() + "))";
			}
		}
		
		filter = filter + ")";
		
		SearchScope scope = SearchScope.SUBTREE;
		LdapConnection con = ldapConnectionManager.getLDAPConnection();
		con.bind(LDAP_DN_STRING, LDAP_CREDENTIAL);
		SearchRequest req = new SearchRequestImpl();
		Dn systemDn = new Dn(BASE_DN);
		req.setBase(systemDn);
		req.setFilter(filter);
		req.setScope(scope);
		SearchCursor cursor = con.search(req);
		while (cursor.next()) {
			Entry entry = cursor.getEntry();
			TEBSUser user = userTranslator.getTEBSUserFromLDAPEntry(entry);
			usersList.add(user);
		}
		con.unBind();
		con.close();
		return usersList;
	}
	
	@Override
	public List<TEBSUser> findAllUsers() throws Exception {
		List<TEBSUser> usersList = new ArrayList<TEBSUser>();
		String filter = "(&(objectClass=person)))";
		SearchScope scope = SearchScope.SUBTREE;
		LdapConnection con = ldapConnectionManager.getLDAPConnection();
		con.bind(LDAP_DN_STRING, LDAP_CREDENTIAL);
		SearchRequest req = new SearchRequestImpl();
		Dn systemDn = new Dn(BASE_DN);
		req.setBase(systemDn);
		req.setFilter(filter);
		req.setScope(scope);
		SearchCursor cursor = con.search(req);
		while (cursor.next()) {
			Entry entry = cursor.getEntry();
			TEBSUser user = userTranslator.getTEBSUserFromLDAPEntry(entry);
			usersList.add(user);
		}
		con.unBind();
		con.close();
		return usersList;
	}

	@Override
	public TEBSUser authenticateUser(UserAuthenticationDto userDto) throws Exception{
		TEBSUser user = null;
		String filter = "(&(objectClass=person)(&(uid=" + userDto.getUserId() + "))(&(userPassword=" + PasswordUtil.encodePasswordText(userDto.getPassword()) + ")))";
		SearchScope scope = SearchScope.SUBTREE;
		LdapConnection con = ldapConnectionManager.getLDAPConnection();
		con.bind(LDAP_DN_STRING, LDAP_CREDENTIAL);
		SearchRequest req = new SearchRequestImpl();
		Dn systemDn = new Dn(BASE_DN);
		req.setBase(systemDn);
		req.setFilter(filter);
		req.setScope(scope);
		SearchCursor cursor = con.search(req);
		while (cursor.next()) {
			Entry entry = cursor.getEntry();
			user = userTranslator.getTEBSUserFromLDAPEntry(entry);
			break;
		}
		con.unBind();
		con.close();
		return user;
	}
	
}
