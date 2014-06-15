/**
 * 
 */
package root.consultant.assistant.Test.Model;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import root.consultantassistant.model.Consultant;

/**
 * @author Brad
 *
 */
public class ConsultantTest {
	private String userId;
	private String password;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private int zip;
	private String email;
	private String state;
	private String phoneNumber;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		userId = "janeDoe";
		password = "password";
		firstName = "Jane";
		lastName = "Doe";
		address = "111 West King St.";
		city = "DoeTown";
		zip = 11254;
		email = "janedoe@gmail.com";
		state = "Michigan";
		phoneNumber = "(111)111-0011";
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	/**
	 * Test method for {@link root.consultantassistant.model.Consultant#getFirstName()}.
	 */
	@Test
	public void testGetFirstName() {
		Consultant consultant = new Consultant();
		consultant.setFirstName(firstName);
		assertEquals("Consultant's first name should be Jane","Jane",consultant.getFirstName());
	}

	/**
	 * Test method for {@link root.consultantassistant.model.Consultant#setFirstName(java.lang.String)}.
	 */
	@Test
	public void testSetFirstName() {
		Consultant consultant = new Consultant();
		consultant.setFirstName(firstName);
		assertEquals("Consultant's first name should be Jane","Jane",consultant.getFirstName());
		consultant.setFirstName("John");
		assertEquals("Consultant's first name should be John", "John", consultant.getFirstName());
	}

	/**
	 * Test method for {@link root.consultantassistant.model.Consultant#getLastName()}.
	 */
	@Test
	public void testGetLastName() {
		Consultant consultant = new Consultant();
		consultant.setLastName(lastName);
		assertEquals("Consultant's first name should be Doe","Doe",consultant.getLastName());
	}

	/**
	 * Test method for {@link root.consultantassistant.model.Consultant#setLastName(java.lang.String)}.
	 */
	@Test
	public void testSetLastName() {
		Consultant consultant = new Consultant();
		consultant.setLastName(lastName);
		assertEquals("Consultant's first name should be Doe","Doe",consultant.getLastName());
		consultant.setLastName("Smith");
		assertEquals("Consultant's first name should be Smith","Smith",consultant.getLastName());
		
	}

	/**
	 * Test method for {@link root.consultantassistant.model.Consultant#getId()}.
	 */
	@Test
	public void testGetUserId() {
		Consultant consultant = new Consultant();
		consultant.setUserId(userId);
		assertEquals("Consultant's id should be janeDoe","janeDoe",consultant.getUserId());
	}

	/**
	 * Test method for {@link root.consultantassistant.model.Consultant#setId(java.lang.String)}.
	 */
	@Test
	public void testSetId() {
		Consultant consultant = new Consultant();
		consultant.setUserId(userId);
		assertEquals("Consultant's id should be janeDoe","janeDoe",consultant.getUserId());
		consultant.setUserId("johnDoe");
		assertEquals("Consultant's id should be johnDoe", "johnDoe",consultant.getUserId());
	}

	/**
	 * Test method for {@link root.consultantassistant.model.Consultant#getAddress()}.
	 */
	@Test
	public void testGetAddress() {
		Consultant consultant = new Consultant();
		consultant.setAddress(address);
		assertEquals("Consultant's address shoud be 111 West King St.", "111 West King St.", consultant.getAddress());
	}

	/**
	 * Test method for {@link root.consultantassistant.model.Consultant#setAddress(java.lang.String)}.
	 */
	@Test
	public void testSetAddress() {
		Consultant consultant = new Consultant();
		consultant.setAddress(address);
		assertEquals("Consultant's address shoud be 111 West King St.", "111 West King St.", consultant.getAddress());
		consultant.setAddress("110 West King St.");
		assertEquals("Consultant's address shoud be 110 West King St.", "110 West King St.", consultant.getAddress());
	}

	/**
	 * Test method for {@link root.consultantassistant.model.Consultant#getZip()}.
	 */
	@Test
	public void testGetZip() {
		Consultant consultant = new Consultant();
		consultant.setZip(zip);
		assertEquals("Consultant's zip should be 11254",11254,consultant.getZip());
	}

	/**
	 * Test method for {@link root.consultantassistant.model.Consultant#setZip(int)}.
	 */
	@Test
	public void testSetZip() {
		Consultant consultant = new Consultant();
		consultant.setZip(zip);
		assertEquals("Consultant's zip should be 11254",11254,consultant.getZip());
		consultant.setZip(11253);
		assertEquals("Consultant's zip should be 11253",11253,consultant.getZip());
	}

	/**
	 * Test method for {@link root.consultantassistant.model.Consultant#getCity()}.
	 */
	@Test
	public void testGetCity() {
		Consultant consultant = new Consultant();
		consultant.setCity(city);
		assertEquals("Consultant's city should be DoeTown","DoeTown",consultant.getCity());
	}

	/**
	 * Test method for {@link root.consultantassistant.model.Consultant#setCity(java.lang.String)}.
	 */
	@Test
	public void testSetCity() {
		Consultant consultant = new Consultant();
		consultant.setCity(city);
		assertEquals("Consultant's city should be DoeTown","DoeTown",consultant.getCity());
		consultant.setCity("City");
		assertEquals("Consultant's city should be City", "City", consultant.getCity());
	}

	/**
	 * Test method for {@link root.consultantassistant.model.Consultant#getState()}.
	 */
	@Test
	public void testGetState() {
		Consultant consultant = new Consultant();
		consultant.setState(state);
		assertEquals("Consultant's state should be Michigan","Michigan",consultant.getState());
	}

	/**
	 * Test method for {@link root.consultantassistant.model.Consultant#setState(java.lang.String)}.
	 */
	@Test
	public void testSetState() {
		Consultant consultant = new Consultant();
		consultant.setState(state);
		assertEquals("Consultant's state should be Michigan","Michigan",consultant.getState());
		consultant.setState("New York");
		assertEquals("Consultant's state should be New York", "New York",consultant.getState());
	}

	/**
	 * Test method for {@link root.consultantassistant.model.Consultant#getEmail()}.
	 */
	@Test
	public void testGetEmail() {
		Consultant consultant = new Consultant();
		consultant.setEmail(email);
		assertEquals("Consultant's email should be janedoe@gmail.com", "janedoe@gmail.com", consultant.getEmail());
	}

	/**
	 * Test method for {@link root.consultantassistant.model.Consultant#setEmail(java.lang.String)}.
	 */
	@Test
	public void testSetEmail() {
		Consultant consultant = new Consultant();
		consultant.setEmail(email);
		assertEquals("Consultant's email should be janedoe@gmail.com", "janedoe@gmail.com", consultant.getEmail());
		consultant.setEmail("test@test");
		assertEquals("Consultant's email shouldn't be set to test@test", "janedoe@gmail.com", consultant.getEmail());
	}

	/**
	 * Test method for {@link root.consultantassistant.model.Consultant#getPhoneNumber()}.
	 */
	@Test
	public void testGetPhoneNumber() {
		Consultant consultant = new Consultant();
		consultant.setPhoneNumber(phoneNumber);
		assertEquals("Consultant's phone number should be (111)111-0011", "(111)111-0011", consultant.getPhoneNumber());
	}

	/**
	 * Test method for {@link root.consultantassistant.model.Consultant#setPhoneNumber(java.lang.String)}.
	 */
	@Test
	public void testSetPhoneNumber() {
		Consultant consultant = new Consultant();
		consultant.setPhoneNumber(phoneNumber);
		assertEquals("Consultant's phone number should be (111)111-0011", "(111)111-0011", consultant.getPhoneNumber());
		consultant.setPhoneNumber("1101101100");
		assertEquals("Consultant's phone number should be (111)111-0011", "(111)111-0011", consultant.getPhoneNumber());
	}

	/**
	 * Test method for {@link root.consultantassistant.model.Consultant#getPassword()}.
	 */
	@Test
	public void testGetPassword() {
		Consultant consultant = new Consultant();
		consultant.setPassword(password);
		assertEquals("Consultant's password should be password", "password", consultant.getPassword());
	}

	/**
	 * Test method for {@link root.consultantassistant.model.Consultant#setPassword(java.lang.String)}.
	 */
	@Test
	public void testSetPassword() {
		Consultant consultant = new Consultant();
		consultant.setPassword(password);
		assertEquals("Consultant's password should be password", "password", consultant.getPassword());
		consultant.setPassword("pass");
		assertEquals("Consultant's password should be password", "password", consultant.getPassword());
	}
	
	/**
	 * Test method for {@link root.consultantassistant.model.Consultant#equals(java.lang.Object)}.
	 */
	@Test
	public void testEquals(){
		Consultant consultant = new Consultant();
		consultant.setAddress(address);
		consultant.setCity(city);
		consultant.setEmail(email);
		consultant.setFirstName(firstName);
		consultant.setUserId(userId);
		consultant.setLastName(lastName);
		consultant.setPassword(password);
		consultant.setPhoneNumber(phoneNumber);
		consultant.setState(state);
		consultant.setZip(zip);
		
		Consultant consultant1 = new Consultant();
		consultant1.setAddress(address);
		consultant1.setCity(city);
		consultant1.setEmail(email);
		consultant1.setFirstName(firstName);
		consultant1.setUserId(userId);
		consultant1.setLastName(lastName);
		consultant1.setPassword(password);
		consultant1.setPhoneNumber(phoneNumber);
		consultant1.setState(state);
		consultant1.setZip(zip);
		//Test the two Consultant with identical information for equality
		assertEquals("The consultants should be considered the same",true,consultant.equals(consultant1));
		consultant1.setUserId("first");
		//Test the two Consultant with identical information but different id for equality
		assertEquals("The consultants shouldn't be considered the same",false,consultant.equals(consultant1));
		
		
	}

}
