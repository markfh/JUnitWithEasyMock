package it.rambow.fhb;

public interface UserDAO {
	/**
     * Loads a User by credentials
     * 
     * @parameter userName
     * @parameter passwordHash
     * @return    User
     */
     User findByCredentials(String userName, String passwordHash);

}
