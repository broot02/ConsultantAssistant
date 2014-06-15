package root.consultant.assistant.Test.Model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import root.consultantassistant.model.Consultant;
import root.consultantassistant.model.ConsultantList;

/**
 * <code>ConsultantListTest</code> is used to test the logic of the class
 * <code>ConsultantList</code>. This is used in conjunction with jUnit to
 * run the tests. 
 * 
 * @since 1.0
 * @author Brad Root
 * @version 1.0
 *
 */
public class ConsultantListTest {
	
	/**
	 *Used for testing purposes, arbitrary object.
	 */
	private Consultant consultantOne;
	/**
	 *Used for testing purposes, arbitrary object.
	 */
	private Consultant consultantTwo;
	/**
	 *Used for testing purposes, arbitrary object. 
	 */
	private Consultant consultantThree;
	/**
	 * 
	 */
	private ArrayList<Consultant> otherConsultantList;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public final void setUp() throws Exception {
		consultantOne = new Consultant();
		consultantOne.setAddress("111 West King St.");
		consultantOne.setCity("DoeTown");
		consultantOne.setEmail("janedoe@gmail.com");
		consultantOne.setFirstName("Jane");
		consultantOne.setUserId("janeDoe");
		consultantOne.setLastName("Doe");
		consultantOne.setPassword("password");
		consultantOne.setPhoneNumber("(111)101-1192");
		consultantOne.setState("Michigan");
		/*11202 was chosen as an arbitrary value and
		is provided only to check if field was 
		changed. No validation occurs on this number to
		check if it is a real zip-code.*/ 
		consultantOne.setZip(11202);

		consultantTwo = new Consultant();
		consultantTwo.setAddress("1110 West King St.");
		consultantTwo.setCity("Doe");
		consultantTwo.setEmail("jedo@gmail.com");
		consultantTwo.setFirstName("Jim");
		consultantTwo.setUserId("jimDo");
		consultantTwo.setLastName("Do");
		consultantTwo.setPassword("password98");
		consultantTwo.setPhoneNumber("(111)101-9102");
		consultantTwo.setState("Michigan");
		/*43576 was chosen as an arbitrary value and is provided
		only to check if field was 
		changed. No validation occurs on this number to check
		if it is a real zip-code.*/ 
		consultantTwo.setZip(43576);

		consultantThree = new Consultant();
		consultantThree.setAddress("10 West King St.");
		consultantThree.setCity("Doe");
		consultantThree.setEmail("johndoe@gmail.com");
		consultantThree.setFirstName("John");
		consultantThree.setUserId("johnDoe");
		consultantThree.setLastName("Doe");
		consultantThree.setPassword("dogsword98");
		consultantThree.setPhoneNumber("(990)201-9102");
		consultantThree.setState("New York");
		/*43098 was chosen as an arbitrary value and is provided
		only to check if field was 
		changed. No validation occurs on this number to check
	 	if it is a real zip-code.*/
		consultantThree.setZip(43098);

		otherConsultantList = new ArrayList<>();
		otherConsultantList.add(consultantOne);
		otherConsultantList.add(consultantTwo);
		otherConsultantList.add(consultantThree);
	}

	/**
	 * Test method for
	 * {@link root.consultantassistant.model.ConsultantList#
	 * ConsultantList(java.util.ArrayList)}
	 * .
	 */
	@Test
	public final void testConsultantListArrayListOfConsultant() {
		ConsultantList consultantList = new ConsultantList(otherConsultantList);
		assertEquals("The consultant list should contain the first consultant",
				true, consultantList.isConsultant(consultantOne));
		assertEquals(
				"The consultant list should contain the second consultant",
				true, consultantList.isConsultant(consultantTwo));
		assertEquals("The consultant list should contain the third consultant",
				true, consultantList.isConsultant(consultantThree));
	}

	/**
	 * Test method for
	 * {@link root.consultantassistant.model.ConsultantList#
	 * addConsultant(root.consultantassistant.model.Consultant)}
	 * .
	 */
	@Test
	public final void testAddConsultant() {
		ConsultantList consultantList = new ConsultantList();
		assertEquals(
				"The first consultant should be added to the consultant list",
				true, consultantList.addConsultant(consultantOne));
		assertEquals("The first consultant should be in the consultant list",
				true, consultantList.isConsultant(consultantOne));
		assertEquals(
				"The first consultant shouldn't add to the list as"
				+ " it is already in the list",
				false, consultantList.addConsultant(consultantOne));
	}

	/**
	 * Test method for
	 * {@link root.consultantassistant.model.ConsultantList#
	 * removeConsultant(root.consultantassistant.model.Consultant)}
	 * .
	 */
	@Test
	public final void testRemoveConsultant() {
		ConsultantList consultantList = new ConsultantList();
		consultantList.addConsultant(consultantOne);
		consultantList.addConsultant(consultantTwo);
		assertEquals(
				"The first consultant should be removed from the consultant"
						+ " list", true,
				consultantList.removeConsultant(consultantOne));
		assertEquals("The first consultant should no longer be in the list",
				false, consultantList.isConsultant(consultantOne));
	}

	/**
	 * Test method for
	 * {@link root.consultantassistant.model.ConsultantList#
	 *  isConsultant(root.consultantassistant.model.Consultant)}
	 * .
	 */
	@Test
	public final void testIsConsultant() {
		ConsultantList consultantList = new ConsultantList();
		assertEquals("There should be no consultants in the consultant"
				+ " list", false, consultantList.isConsultant(consultantOne));
		consultantList.addConsultant(consultantOne);
		assertEquals("The first consultant should be in the consultant"
				+ " list", true, consultantList.isConsultant(consultantOne));
		consultantList.removeConsultant(consultantOne);
		assertEquals("The first consultant shoudln't be in the consultant"
				+ " list", false, consultantList.isConsultant(consultantOne));
	}

	/**
	 * Test method for
	 * {@link root.consultantassistant.model.ConsultantList
	 * #validateConsultant(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public final void testValidateConsultant() {
		ConsultantList consultantList = new ConsultantList();
		consultantList.addConsultant(consultantOne);
		consultantList.addConsultant(consultantTwo);
		consultantList.addConsultant(consultantThree);
		assertEquals(
				"The consultant should be validated to consultant one "
				+ "with the credentials janeDoe/password",
				true, consultantOne.equals(consultantList.validateConsultant(
						"janeDoe", "password")));
		assertEquals(
				"The consultant shouldn't be validated with the"
				+ " credentials john/useruser",
				null, consultantList.validateConsultant("john", "useruser"));
	}

}
