package tebs.ldapauth.connection;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;

import org.apache.directory.ldap.client.api.LdapConnection;

public interface ConnectionManager {
	
	public DirContext getLDAPContext() throws NamingException;
	
	public LdapConnection getLDAPConnection();
}
