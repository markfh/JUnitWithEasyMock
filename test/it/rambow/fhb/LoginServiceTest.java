package it.rambow.fhb;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.easymock.EasyMock.createStrictMock; //method call order proove
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.easymock.EasyMock.*;

public class LoginServiceTest {
	private LoginServiceImpl service;
    private UserDAO userDAOMock;


	@Before
	public void setUp() throws Exception {
        service = new LoginServiceImpl();
        //create a mock
        userDAOMock = createStrictMock(UserDAO.class);
        //inject dependency
        service.setUserDAO(userDAOMock);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testValidUserAuthenticate() {
		User user = new User();
        String userName = "testUserName";
        String password = "testPassword";
        String passwordHash = "fed3b61b2681849378080b34e693d2e"; //just created from 'testPassword'
        
        /*define expectations*/
        //We expect userMock gets the method call findByCredentials with userName/passwordHash 
        //and return our defined "result"
        //default comperator is ==  
        //the eq method from EasyMock will give us the comperator we need for MD5 Hash (.equals)
        expect(userDAOMock.findByCredentials(eq(userName), eq(passwordHash))).andReturn(user).times(1);
        
        //test expectations
        replay(userDAOMock);
        //test as usual
        assertTrue(service.authenticate(userName, password));
        //verify that the mock was used the right way
        verify(userDAOMock);
	}

	@Test
	public final void testInvalidUserAuthenticate() {
        String userName = "testUserName";
        String password = "testPassword";

        //expect that userDAOMock is called with String Arguments and return null, no user found
        expect(userDAOMock.findByCredentials((String) anyObject(),(String) anyObject())).andReturn(null).times(1);
        
        //test expectations
        replay(userDAOMock);
        //test as usual
        assertFalse(service.authenticate(userName, password));
        //verify that the mock was used the right way
        verify(userDAOMock);
	}

}
