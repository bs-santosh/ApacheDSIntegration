package tebs.ldapauth.dao;

import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.DirContext;

import org.springframework.beans.factory.annotation.Autowired;

import tebs.ldapauth.connection.ConnectionManager;
import tebs.ldapauth.dto.ADUserSearchDto;
import tebs.ldapauth.model.ActiveDirectoryUser;

public class ActiveDirectoryDaoImpl implements ActiveDirectoryDao{

	@Autowired
	ConnectionManager connectionManager;
	
	@Override
	public List<ActiveDirectoryUser> retrieveAllADUsers() {
		List<ActiveDirectoryUser> users = new ArrayList<ActiveDirectoryUser>();
		try{
			DirContext contex = connectionManager.getLDAPContext();
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		return users;
	}

	@Override
	public ActiveDirectoryUser retrieveActiveADUser(String searchBy, ADUserSearchDto searchDto) {
		ActiveDirectoryUser user = new ActiveDirectoryUser();
		
		return user;
	}

	@Override
	public String updateADUser(ActiveDirectoryUser user) {
		String updateStatus = "";
		
		return updateStatus;
	}

}
