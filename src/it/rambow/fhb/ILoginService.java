package it.rambow.fhb;
public interface ILoginService {
	/**
	 * Returns boolean for Login credentials 
	 * @parameter userName
	 * @parameter password
	 * @return boolean
	 */
	boolean authenticate(String userName, String password);
}
