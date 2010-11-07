package it.rambow.fhb;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginServiceImpl implements LoginService {

	private UserDAO userDao;

	public void setUserDAO(UserDAO userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean authenticate(String userName, String password) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.reset();
			md5.update(password.getBytes());
			byte[] result = md5.digest();

			StringBuffer passwordHash = new StringBuffer();
			for (byte b : result) {
				passwordHash.append(Integer.toHexString(0xFF & b));
			}

			User user = userDao.findByCredentials(userName, passwordHash
					.toString());

			return user == null ? false : true;

		} catch (NoSuchAlgorithmException e) {
			return false;
		}
	}
}