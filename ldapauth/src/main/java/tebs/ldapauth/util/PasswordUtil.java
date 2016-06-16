package tebs.ldapauth.util;

import java.security.MessageDigest;
import Decoder.BASE64Encoder;

public class PasswordUtil {
	private static final String ALGORITHM = "SHA-256";
	private static final String ALGORITHM_PASS_STRING = "SHA256";
	public static String encodePasswordText(String base) {
		String password = "";
		try {
			MessageDigest md = MessageDigest.getInstance(ALGORITHM);
			md.update(base.getBytes());
			byte[] bytes = md.digest();
			BASE64Encoder base64encoder = new BASE64Encoder();
			String hash = base64encoder.encode(bytes);
			password = "{" + ALGORITHM_PASS_STRING + "}" + hash;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return password;
	}

}
