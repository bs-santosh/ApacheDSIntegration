package tebs.ldapauth.dao;

import java.util.List;

import tebs.ldapauth.dto.ADUserSearchDto;
import tebs.ldapauth.model.ActiveDirectoryUser;

public interface ActiveDirectoryDao {
	public List<ActiveDirectoryUser> retrieveAllADUsers();
	public ActiveDirectoryUser retrieveActiveADUser(String searchBy, ADUserSearchDto searchDto);
	public String updateADUser(ActiveDirectoryUser user);
}
