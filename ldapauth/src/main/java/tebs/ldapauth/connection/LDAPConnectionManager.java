package tebs.ldapauth.connection;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;

public class LDAPConnectionManager implements ConnectionManager{
	private final String CONTEXT_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
	private final String PROVIDE_URL = "ldap://localhost:10389";
	private final String SERVER_NAME = "127.0.0.1";
	private final int PORT = 10389;
	private final String ADMIN_NAME = "";
	private final String ADMIN_PASSWORD = "";
	private final String AUTH_METHOD = "simple";
	private final String SECURITY_PROTOCOL = "ssl";
	
	
	public DirContext getLDAPContext() throws NamingException{
		Properties properties = new Properties();
		properties.put(Context.INITIAL_CONTEXT_FACTORY, CONTEXT_FACTORY);
		properties.put(Context.PROVIDER_URL, PROVIDE_URL);
		properties.put(Context.SECURITY_AUTHENTICATION, AUTH_METHOD);
		properties.put(Context.SECURITY_PRINCIPAL, ADMIN_NAME);
		properties.put(Context.SECURITY_CREDENTIALS, ADMIN_PASSWORD);
		properties.put(Context.SECURITY_PROTOCOL,SECURITY_PROTOCOL);
		DirContext context = new InitialDirContext(properties);
		return context;
	}
	
	public LdapConnection getLDAPConnection(){
	    LdapConnection connection = new LdapNetworkConnection(SERVER_NAME, PORT );
		return connection;
	}
}
