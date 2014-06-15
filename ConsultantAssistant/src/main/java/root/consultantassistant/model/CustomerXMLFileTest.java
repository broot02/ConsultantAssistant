package root.consultantassistant.model;

public class CustomerXMLFileTest {
	public static void main(String args[]){
		CustomerListXML customerList = new CustomerListXML("fileTest.xml");
		//Testing the add customer functionality
		
		System.out.println(Customer.id);
		Customer customer = new Customer("Jane","Doe");
		customer.setAddress("111 West Rd");
		customer.setCity("Test");
		customer.setEmail("test@test.com");
		customer.setPhoneNumber("(999)999-9999");
		customer.setMailingList(false);
		customer.setState("Michigan");
		customer.setZipCode(48867);
		customerList.addCustomer(customer);

		//Testing the add customer functionality
		Customer customer1 = new Customer("Jane","Doe");
		customer1.setAddress("111 West Rd");
		customer1.setCity("Test");
		customer1.setEmail("test@test.com");
		customer1.setPhoneNumber("(999)999-9999");
		customer1.setMailingList(false);
		customer1.setState("Michigan");
		customer1.setZipCode(48867);
		customerList.addCustomer(customer1);
		
		customerList.writeToFile();
		
		System.out.println(customer.equals(customer1));
		
		System.out.println(customerList.toString());


	}
}
