package root.consultantassistant.model;

import java.io.Serializable;

import org.apache.commons.validator.routines.EmailValidator;

public class Customer implements Serializable {
	/**
	 * 
	 */
	public static int id = 1;
	private static final long serialVersionUID = -5084787419862194987L;
	private String firstName = "";
	private String lastName = "";
	private String address = "";
	private int zipCode = 0;
	private String city = "";
	private String state = "";
	private String phoneNumber = "";
	private String email = "";
	private int customerId;
	private boolean mailingList = false;

	public Customer(){
		customerId = id;
		id++;
	}
	
	public Customer(String firstName, String lastName) {
		customerId = id;
		id++;
		setFirstName(firstName);
		setLastName(lastName);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}
	
	public int getId(){
		return customerId;
	}
	
	public void setId(int customerId){
		this.customerId = customerId;
	}
	public boolean setEmail(String email) {
		EmailValidator eV = EmailValidator.getInstance();
		if (eV.isValid(email)) {
			this.email = email;
			return true;
		}
		return false;
	}

	public boolean isMailingList() {
		return mailingList;
	}

	public void setMailingList(boolean mailingList) {
		this.mailingList = mailingList;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	

	/**
	 * @Override
	 * Checks to see if the <code>Customer</code> is equal to the other object being provided. The check consists of a name equality comparison and it
	 * also compares the equality of the customer id. The result of the equality is returned.
	 * 
	 * @param object - The object to be compared against. This should be a <code>Customer</code> for it to ever return true.
	 * 
	 * @return if the object is equal to the Customer  it is being compared to in respect to the first name, last name, and id, then return true.
	 * Otherwise return false. 
	 */
	public boolean equals(Object object){
		if(object instanceof Customer){
			Customer customer = (Customer)object;
			return((this.getFirstName().equals(customer.getFirstName())) && (this.getLastName().equals(customer.getLastName()))
					&&(this.getAddress().equals(customer.getAddress())) && (this.getCity().equals(customer.getCity()))
					&&(this.getEmail().equals(customer.getEmail())) && (this.getState().equals(customer.getState()))
					&&(Integer.valueOf(this.getZipCode()).equals(Integer.valueOf(customer.getZipCode()))) && (this.getPhoneNumber().equals(customer.getPhoneNumber()))
					||((Integer.valueOf(this.getId()).equals((Integer)customer.getId()))));
		}
		return false;
	}
	/**
	 * @Override
	 */
	public String toString(){
		return(getFirstName() + " " + getLastName() +"\n" + getEmail() + "\n" + getPhoneNumber() + "\n" + getAddress() + 
				"\n" + getCity() +", " + getState() + " " + getZipCode() + "\n");
		
	}
	

}
