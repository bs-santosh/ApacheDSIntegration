package tebs.ldapauth.translator;

import org.apache.directory.shared.ldap.model.entry.Entry;
import org.apache.directory.shared.ldap.model.exception.LdapInvalidAttributeValueException;

import tebs.ldapauth.dto.TEBSUserDto;
import tebs.ldapauth.model.TEBSUser;

public class TEBSUserTranslator {
	public TEBSUser getTEBSUserDomainFromDto(TEBSUserDto userDto){
		TEBSUser user = new TEBSUser();
		user.setName(userDto.getName());
		user.setUserId(userDto.getUserId());
		user.setPassword(userDto.getPassword());
		user.setMobile(userDto.getMobile());
		user.setEmail(userDto.getEmail());
		user.setEmployeeNo(userDto.getEmployeeNo());
		user.setDepartment(userDto.getDepartment());
		user.setEmployeeType(userDto.getEmployeeType());
		user.setDesignation(userDto.getDesignation());
		user.setfName(userDto.getfName());
		user.setlName(userDto.getlName());
		user.setmName(userDto.getmName());
		user.setGender(userDto.getGender());
		user.setPersonalEmail(userDto.getPersonalEmail());
			
		return user;
	}
	
	public TEBSUser getTEBSUserFromLDAPEntry(Entry entry) throws LdapInvalidAttributeValueException{
		TEBSUser user = new TEBSUser();
		user.setName(entry.get("cn") != null ? entry.get("cn").getString() : "");
		user.setUserId(entry.get("uid") != null ? entry.get("uid").getString() : "");
		user.setPassword(entry.get("userPassword") != null ? entry.get("userPassword").getString() : "");
		user.setMobile(entry.get("mobile") != null ? entry.get("mobile").getString() : "");
		user.setEmail(entry.get("email") != null ? entry.get("email").getString() : "");
		user.setDepartment(entry.get("department") != null ? entry.get("department").getString() : "");
		user.setDesignation(entry.get("designation") != null ? entry.get("designation").getString() : "");
		user.setEmployeeNo(entry.get("employeeNumber") != null ? entry.get("employeeNumber").getString() : "");
		user.setEmployeeType(entry.get("employeeType") != null ? entry.get("employeeType").getString() : "");
		user.setfName(entry.get("firstName") != null ? entry.get("firstName").getString() : "");
		user.setlName(entry.get("lastName") != null ? entry.get("lastName").getString() : "");
		user.setmName(entry.get("middleName") != null ? entry.get("middleName").getString() : "");
		user.setGender(entry.get("gender") != null ? entry.get("gender").getString() : "");
		return user;
	}
	
	public TEBSUserDto getTEBSUserDtoFromDomain(TEBSUser user){
		TEBSUserDto userDto = new TEBSUserDto();
		userDto.setName(user.getName());
		userDto.setUserId(user.getUserId());
		userDto.setPassword(user.getPassword());
		userDto.setMobile(user.getMobile());
		return userDto;
	}
}
